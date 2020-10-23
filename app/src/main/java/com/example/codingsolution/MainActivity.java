package com.example.codingsolution;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    EditText editText , date,firsttime,lasttime;
    String s1,s2,s3,s4;
    ArrayList<Checker> tennislist = new ArrayList<Checker>();
    ArrayList<Checker> secondlist = new ArrayList<Checker>();

//    Date

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editText=findViewById(R.id.editTextTextPersonName2);
        date=findViewById(R.id.editTextDate);
        firsttime=findViewById(R.id.editTextTime2);
        lasttime=findViewById(R.id.editTextTime3);
    }

    // Method  for onclick on button which will be used here for the all purpose

    public void chcekfor(View view) throws ParseException
    {
        s1=editText.getText().toString();
        s2=date.getText().toString();
        s3=firsttime.getText().toString();
        s4=lasttime.getText().toString();

        Log.d("sumit","Test for best" +s1+" ths " +s3+' '+s2);
    // Checker Object to store and get the all necessary data of an particular object
        Checker checker=new Checker();

        FirstMethod(checker);


    }

    private void FirstMethod(Checker checker) throws ParseException
    {

            if(s1=="tennis")
        {
            if(tennislist.isEmpty())
            {

                checker.setDate(s2);
                checker.setStime(s3);
                checker.setEndtime(s4);
                Log.d("sumit",checker.getDate());
                Log.d("sumit", String.valueOf(checker.getStime()));
                Log.d("sumit", String.valueOf(checker.getEndtime()));
                CalulatePrice(checker,1);
                tennislist.add(checker);
            }
            {
                if(CheckforAvilableSlot(tennislist))
                {
                    checker.setDate(s2);
                    checker.setStime(s3);
                    checker.setEndtime(s4);
                    Log.d("sumit",checker.getDate());
                    Log.d("sumit", String.valueOf(checker.getStime()));
                    Log.d("sumit", String.valueOf(checker.getEndtime()));
                    CalulatePrice(checker,1);
                    tennislist.add(checker);
                }
                else
                {
                    // Show the simple message for user that there is error
                    Log.d("error","sorry Overlapping something maybe First List  ");
                }
            }
        }
        else
        {
            if(secondlist.isEmpty())
            {
                checker.setDate(s2);
                checker.setStime(s3);
                checker.setEndtime(s4);
                Log.d("sumit",checker.getDate());
                Log.d("sumit", String.valueOf(checker.getStime()));
                Log.d("sumit", String.valueOf(checker.getEndtime()));
                CalulatePrice(checker,0);
                secondlist.add(checker);
            }

            else
            {
                if(CheckforAvilableSlot(secondlist))
                {
                    // Make Something what is price and if avilable then only
                    checker.setDate(s2);
                    checker.setStime(s3);
                    checker.setEndtime(s4);
                    secondlist.add(checker);
                }
                else
                {
                    // Show the simple message for user that there is error
                    Log.d("error","sorry Overlapping something maybe ");
                }
            }
        }
    }

    boolean CheckforAvilableSlot(ArrayList<Checker> list) throws ParseException {
        int i;
        for(i=0;i<list.size();i++)

        {
            Log.d("tetsing", String.valueOf(list.size()));
//            Object object = secondlist.get(i);
//            Log.d("sumit", String.valueOf(object));
//

            Checker person=list.get(i);
            Date date1,date2;
            if(person.getDate()==s2)
            {
                String d1,d2;
                d1=person.getStime();
                d2=person.getEndtime();
                DateFormat dateFormat= new SimpleDateFormat("hh");
                date1=dateFormat.parse(d1);
                date2=dateFormat.parse(d2);

                Date date3=dateparsemethod(s3);
                Date date4= dateparsemethod(s4);

                if(( date1.before( date4 ) ) && ( date2.after( date3 ) ) )
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            Log.d("sumit",person.getDate());
            Log.d("sumitj", String.valueOf(person.getStime()));
            Log.d("sumitj", String.valueOf(person.getEndtime()));


        }
        return true;
    }
    private void CalulatePrice(Checker checker,int i) throws ParseException {


        if(s1=="tennis")
        {
            Date date,date1;
            date = dateparsemethod(s3);
            date1= dateparsemethod(s4);
            int diff = (int) (date.getTime() - date1.getTime());
            long hour=(diff/(1000*60*60))%24;
            Log.d("sumit", String.valueOf(hour*50));
        }

        else
        {


            Date date2=dateparsemethod(s4);
            Date date=dateparsemethod(s3);
            long diff =  (date.getTime() - date2.getTime());
            long hour=(diff/(1000*60*60))%24;
            Log.d("sumitjat", String.valueOf(hour*50));
        }


   }

    private Date dateparsemethod(String d1) throws ParseException {

        Date d;
        DateFormat dateFormat= new SimpleDateFormat("hh");
        d=dateFormat.parse(d1);

        return  d;
    }
}