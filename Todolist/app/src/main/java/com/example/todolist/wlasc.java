package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.content.Context;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;


public class wlasc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlasc);
        TextView dzien = (TextView)findViewById(R.id.dzien);
        TimePicker time=findViewById(R.id.hour);
        time.setIs24HourView(true);



        String prze=getIntent().getExtras().getString("sd");


        dzien.setText("Wybrany dzień " +prze);

    }

    public void addtask(View view) throws IOException {
        String prze=getIntent().getExtras().getString("sd");
        String mies=getIntent().getExtras().getString("mn");
        EditText nazwa=(EditText) findViewById(R.id.nazwa);
        TimePicker time=findViewById(R.id.hour);
        int hourOfDay = time.getCurrentHour();
        int minute = time.getCurrentMinute();
        String czas = hourOfDay + ":" + minute;
        String akt=czas+' '+nazwa.getText().toString()+' '+prze+'\n';
        String fileName = "zadania.txt";
        FileHelper.saveToFile(this, fileName, akt);
        Intent main=new Intent(this,MainActivity.class);
        main.putExtra("god",czas);
        main.putExtra("mn",mies);
        startActivity(main);
        Toast.makeText(this, "Dodano Zadanie!", Toast.LENGTH_SHORT).show();
    }
    public static class FileHelper {

        public static void saveToFile(Context context, String fileName, String data) {
            File file = new File(context.getExternalFilesDir(null), fileName);
            try {
                FileOutputStream outputStream = new FileOutputStream(file, true);
                outputStream.write(data.getBytes());
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
