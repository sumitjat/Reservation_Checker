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
        Log.d("sumit","Test for best" +s1+" ths " +s3+' '+s2);
    // Checker Object to store and get the all necessary data of an particular object
        Checker checker=new Checker();

        FirstMethod(checker);


    }

    private void FirstMethod(Checker checker) throws ParseException
    {


            if(s1.equalsIgnoreCase(sport))
        {
            if(tennislist.isEmpty())
            {

                checker.setDate(s2);
                checker.setStime(s3);
                checker.setEndtime(s4);
                Log.d("sumit first list",checker.getDate());
                Log.d("sumit first list", String.valueOf(checker.getStime()));
                Log.d("sumit first list", String.valueOf(checker.getEndtime()));
                CalulatePrice(checker,1);
                tennislist.add(checker);
            }
            else
            {
                if(CheckforAvilableSlot(tennislist))
                {
                    //if slot if available for our user we will store object and find the price of that particular slot  and display it
                    checker.setDate(s2);
                    checker.setStime(s3);
                    checker.setEndtime(s4);
                    Log.d("sumit avaible for first",checker.getDate());
                    Log.d("sumit avaible for first", String.valueOf(checker.getStime()));
                    Log.d("sumit avaible for first", String.valueOf(checker.getEndtime()));
                    CalulatePrice(checker,1);
                    tennislist.add(checker);
                    Log.d("sumit","FItst chekc slot");

                }
                else
                {
                    // Show the simple message for user that there is error
                    Log.d("sumit error","sorry Overlapping something maybe First List  ");
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
                    Log.d("sumit check for secondlist","NOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOo");
                    checker.setDate(s2);
                    checker.setStime(s3);
                    checker.setEndtime(s4);
                    secondlist.add(checker);
                }
                else
                {
                    // Show the simple message for user that there is error
                    Log.d("sumit","sorry Overlapping something maybe ");
                }
            }
        }
    }



    boolean CheckforAvilableSlot(ArrayList<Checker> list) throws ParseException {
        int i;
        for(i=0;i<list.size();i++)

        {
            Log.d("sumit tetsing", String.valueOf(list.size()));
            Log.d("sumit object0", String.valueOf(list.get(i)));
//            Object object = secondlist.get(i);
//            Log.d("sumit", String.valueOf(object));
//

            Checker person=list.get(i);
            Date date1,date2;

            // only need to check if that user entering same date as the date entered and the will check the if time interval is overlapping or not
            Log.d("sumit date check", String.valueOf(person.getDate()==s2));
            SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
            Date first,second;
            first=fmt.parse(person.getDate());
            second=fmt.parse(s2);
            Log.d("sumit after date check", String.valueOf(first.equals(second)));

            if(first.equals(second))
            {

                date1=dateparsemethod(person.getStime());
                date2=dateparsemethod(person.getEndtime());
                Log.d("sumitit for ", String.valueOf(person.getStime()));
                Log.d("sumitit for", String.valueOf(person.getEndtime()));

                int hour= (int) ((date1.getTime()/(1000*60*60))%24);
                int hour2= (int) ((date2.getTime()/(1000*60*60))%24);
                Date date3=dateparsemethod(s3);
                Date date4= dateparsemethod(s4);
                Log.d("sumitit test for ", String.valueOf(date2.getHours()));
                Log.d("sumitit  tets for" , String.valueOf(date3.getHours()));
                int hour3= (int) ((date3.getTime()/(1000*60*60))%24);
                int hour4= (int) ((date4.getTime()/(1000*60*60))%24);
                Log.d("sumitit", String.valueOf(hour));
                Log.d("sumitit", String.valueOf(hour2));
                Log.d("sumitit", String.valueOf(hour3));
                Log.d("sumitit", String.valueOf(hour4 ));

                if( !(date4.before(date1) || date3.after(date2) )  )
                {
                    return false;
                }

            }
            Log.d("sumit",person.getDate());
            Log.d("sumitj", String.valueOf(person.getStime()));
            Log.d("sumitj", String.valueOf(person.getEndtime()));


        }
        return true;
    }

    // Check pdf to proper review what was the price for per hour of slot booking and all :-)
    private int CalulatePrice(Checker checker,int i) throws ParseException {


        if(s1.equalsIgnoreCase(sport))
        {
            Date date,date1;
            date = dateparsemethod(s3);
            date1= dateparsemethod(s4);
            int diff = (int) (date1.getTime() - date.getTime());
            long hour=(diff/(1000*60*60))%24;
            Log.d("sumit", String.valueOf(hour*50));
            return 1;
        }

        else
        {

            // it's gonna be big I think LOL Fuck off bro
            Date date2=dateparsemethod(s4);
            Date date=dateparsemethod(s3);


            if (date.after(dateparsemethod(slot1)) && date2.before(dateparsemethod(mid)))
            {
                long diff =  (date2.getTime() - date.getTime());
                long hour=(diff/(1000*60*60))%24;
                Log.d("sumitjat", String.valueOf(hour*100));
                return 1;
            }
            if (date.after(dateparsemethod(mid)) && date2.before(dateparsemethod(slot2)))
            {
                long diff =  (date.getTime() - date2.getTime());
                long hour=(diff/(1000*60*60))%24;
                Log.d("sumitjat", String.valueOf(hour*500));
                return 1;
            }
            if(date.after(dateparsemethod(slot1)) && date2.before(dateparsemethod(slot2)))
            {
                long diff =  (dateparsemethod(mid).getTime() - date.getTime() );
                long hour=(diff/(1000*60*60))%24;
                Log.d("sumit price", String.valueOf(hour*100));

                long diff2 =  (date2.getTime() - dateparsemethod(mid).getTime() );
                long hour2=(diff/(1000*60*60))%24;
                Log.d("sumit price", String.valueOf(hour2*500));
                return 1;
            }

        }

return  1;
   }

   // for parsing the time in hour format as of now
    private Date dateparsemethod(String d1) throws ParseException {

        Date d;
        DateFormat dateFormat= new SimpleDateFormat("hh");
        d=dateFormat.parse(d1);


        return  d;
    }
}