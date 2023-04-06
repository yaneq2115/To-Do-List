package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import java.util.Date;


public class Dodawanie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodawanie);
    }
    public void dodaj(View view) {
        CalendarView calendar = (CalendarView) findViewById(R.id.cal);

                SimpleDateFormat mies = new SimpleDateFormat("EE,dd");
                SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM EEEE");
                String selectedDate = sdf.format(new Date(calendar.getDate()));
                String selectedMonth = mies.format(new Date(calendar.getDate()));
                Intent wlas=new Intent(this,wlasc.class);
                wlas.putExtra("sd",selectedDate);
                wlas.putExtra("mn",selectedMonth);
                startActivity(wlas);
            }

    }


