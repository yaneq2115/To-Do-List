package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txt=(TextView) findViewById(R.id.date);
        String[] godziny=new String[4];

        txt.setText("Dzisiaj jest "+giveDate());
        TextView[] textViews=new TextView[4];
        textViews[0] =(TextView)findViewById(R.id.zad1);
        textViews[1] =(TextView)findViewById(R.id.zad2);
        textViews[2] =(TextView)findViewById(R.id.zad3);
        textViews[3] =(TextView)findViewById(R.id.zad4);
        String fileName = "zadania.txt";
        List<String> lines = readFromFile(this, fileName);
        boolean isEmpty = isFileEmpty(this, fileName);
        int i=0;

        if (isEmpty) {
            textViews[0].setText("Brak zaplanowanych zadań");
        } else {
            for (String line : lines) {
                godziny[i]=line.substring(0,5) ;
                textViews[i].setText(line);
                i++;
            }
        }


    }

    public static List<String> readFromFile(Context context, String fileName) {
        List<String> lines = new ArrayList<>();
        File file = new File(context.getExternalFilesDir(null), fileName);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
    public static boolean isFileEmpty(Context context, String fileName) {
        File file = new File(context.getExternalFilesDir(null), fileName);
        return (file.length() == 0);
    }
        public String giveDate() {
        String st="GMT+1";
        Locale loc = new Locale("pl", "PL");
        String patterndata="d MMMM yyyy";
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat data = new SimpleDateFormat(patterndata,loc);
        data.setTimeZone(TimeZone.getTimeZone(st));
        return data.format(cal.getTime());

    }
    public void add(View view) {
        Intent add=new Intent(this,Dodawanie.class);
        //add.putExtra()
        startActivity(add);
    }
}
