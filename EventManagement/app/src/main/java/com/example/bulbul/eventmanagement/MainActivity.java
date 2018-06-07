package com.example.bulbul.eventmanagement;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final CalendarView calendarView=(CalendarView) findViewById(R.id.calendarView1);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month,
                                            final int dayOfMonth) {


              
                //calendarView.setDateTextAppearance(getResources().getColor());
                calendarView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        calendarView.setBackgroundColor(Color.RED);

                    }
                });

                
                //Toast.makeText(getApplicationContext(),""+month+" "+dayOfMonth,Toast.LENGTH_LONG).show();
               // Toast.makeText(getApplicationContext(), ""+dayOfMonth, 0).show();// TODO Auto-generated method stub
                Intent userDateMonth = new Intent(MainActivity.this,CustomerInfo.class);
                userDateMonth.putExtra("Date",dayOfMonth);
                userDateMonth.putExtra("Month",month);
                userDateMonth.putExtra("year",year);
                startActivity(userDateMonth);

            }
        });
    }
}
