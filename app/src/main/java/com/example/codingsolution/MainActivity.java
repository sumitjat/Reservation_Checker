package com.example.codingsolution;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText  date,firsttime,lasttime;
    Spinner spinner;
    String s1,s2,s3,s4;
    TextView textView;
    final String sport="tennis";
    ArrayList<Checker> tennislist = new ArrayList<Checker>();
    ArrayList<Checker> secondlist = new ArrayList<Checker>();
    final String slot1="10:00";
    final String slot2="22:00";
    final String mid="16:00";

//    Date

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        spinner=findViewById(R.id.spinner);
        date=findViewById(R.id.editTextDate);
        firsttime=findViewById(R.id.editTextTime2);
        lasttime=findViewById(R.id.editTextTime3);
        textView=findViewById(R.id.textView);
        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("tennis");
        categories.add("Club house");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }

    // Method  for onclick on button which will be used here for the all purpose

    public void chcekfor(View view) throws ParseException
    {

        s2=date.getText().toString();
        s3=firsttime.getText().toString();
        s4=lasttime.getText().toString();

        if(dateparsemethod(s3).before(dateparsemethod(slot1)) || dateparsemethod(s3).after(dateparsemethod(slot2)))
        {
            firsttime.setError("Enter Valid Slot");
            firsttime.requestFocus();
            return;
        }

        if(dateparsemethod(s4).before(dateparsemethod(slot1)) || dateparsemethod(s4).after(dateparsemethod(slot2)))
        {
            lasttime.setError("Enter Valid Slot");
            lasttime.requestFocus();
            return;
        }
        // Checker Object to store and get the all necessary data of an particular object
        Checker checker=new Checker();
        FirstMethod(checker);
    }

    private void FirstMethod(Checker checker) throws ParseException
    {
        if(tennislist.isEmpty())
        {
            checker.setName(s1);
            checker.setDate(s2);
            checker.setStime(s3);
            checker.setEndtime(s4);
            long i=CalulatePrice(checker,1);
            textView.setText("Booked Congo price is"+String.valueOf(i));
            tennislist.add(checker);
        }
        else
        {
            if(CheckforAvilableSlot(tennislist))
            {
                //if slot if available for our user we will store object and find the price of that particular slot  and display it
                checker.setName(s1);
                checker.setDate(s2);
                checker.setStime(s3);
                checker.setEndtime(s4);
                long i=CalulatePrice(checker,1);
                textView.setText("Booked Congo price is"+String.valueOf(i));
                tennislist.add(checker);
            }
            else
            {
                // Show the simple message for user that there is error
                textView.setText("Sorry Failed ");
            }
        }

    }

    boolean CheckforAvilableSlot(ArrayList<Checker> list) throws ParseException
    {
        int i;
        for(i=0;i<list.size();i++) {


            Checker person = list.get(i);
            Date date1, date2;

            // only need to check if that user entering same date as the date entered and the will check the if time interval is overlapping or not
            if (person.getName().equalsIgnoreCase(s1))
            {
                SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
                Date first, second;
                first = fmt.parse(person.getDate());
                second = fmt.parse(s2);
                if (first.equals(second))
                {
                    date1 = dateparsemethod(person.getStime());
                    date2 = dateparsemethod(person.getEndtime());
                    Date date3 = dateparsemethod(s3);
                    Date date4 = dateparsemethod(s4);
                    if (!(((date4.before(date1)) || (date4.equals(date1))) || ((date3.after(date2)) ||  (date4.equals(date1))) )) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // Check pdf to proper review what was the price for per hour of slot booking and all :-)
    private long CalulatePrice(Checker checker,int i) throws ParseException {


        if(s1.equalsIgnoreCase(sport))
        {
            Date date,date1;
            date = dateparsemethod(s3);
            date1= dateparsemethod(s4);
            int diff = (int) (date1.getTime() - date.getTime());
            long hour=(diff/(1000*60*60))%24;
            return (hour*50);
        }

        else
        {
            Date date2=dateparsemethod(s4);
            Date date=dateparsemethod(s3);

            if (date.after(dateparsemethod(slot1)) && date2.before(dateparsemethod(mid)))
            {
                long diff =  (date2.getTime() - date.getTime());
                long hour=(diff/(1000*60*60))%24;
                return ((hour*100));
            }

            else if(date.after(dateparsemethod(mid)) && date2.before(dateparsemethod(slot2)))
            {
                long diff =  (date.getTime() - date2.getTime());
                long hour=(diff/(1000*60*60))%24;
                return (hour*500);
            }

            else if(date.after(dateparsemethod(slot1)) && date2.before(dateparsemethod(slot2)))
            {
                long diff =  (dateparsemethod(mid).getTime() - date.getTime() );
                long hour=(diff/(1000*60*60))%24;
                long diff2 =  (date2.getTime() - dateparsemethod(mid).getTime() );
                long hour2=(diff2/(1000*60*60))%24;
                long total=(hour*100)+(hour2*500);

                return (total);
            }

        }

        return (long) 1.000;
    }

    // for parsing the time in hour format as of now
    private Date dateparsemethod(String d1) throws ParseException {

        Date d;
        DateFormat dateFormat= new SimpleDateFormat("hh");
        d=dateFormat.parse(d1);
        return  d;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        s1 = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}