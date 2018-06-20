package com.example.siddhesh.sports_attendance;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FirstActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText eSportsName;
    EditText eDate;
    EditText edittext;
    EditText edittext1;
    EditText other;
    String sportsname;
    String date1;
    String Stime;
    String Etime;
    String sname;

    String out;
    String out1;


    String[] arr_time = {"8:00", "8:50","9:40","10_30","11_20","12_10","1_00","1_50","2_40","3_30","4_20","5_10","6_00"};
    String[] arr_sname = {"football", "basketball", "volleyball", "hockey", "shot put", "table tennis", "badminton", "water polo", "swimming", "chess", "carrom", "archery", "hand ball", "running 100", "running 200", "running 400", "running 800", "other"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        //eSportsName = (EditText)findViewById(R.id.sports_name);
        eDate = (EditText)findViewById(R.id.date);

        other = (EditText)findViewById(R.id.editText);


        edittext = (EditText) findViewById(R.id.date);
        edittext1 = (EditText)findViewById(R.id.time);

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(FirstActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        Spinner j_spin = (Spinner) findViewById(R.id.start_time);
        j_spin.setOnItemSelectedListener(this);
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arr_time);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        j_spin.setAdapter(aa);

        Spinner j_spin1 = (Spinner) findViewById(R.id.end_time);
        j_spin1.setOnItemSelectedListener(this);
        ArrayAdapter aa1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arr_time);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        j_spin1.setAdapter(aa1);
        j_spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long arg3) {
                Etime = arg0.getItemAtPosition(pos).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        Spinner j_spin2 = (Spinner) findViewById(R.id.spinner2);
        j_spin2.setOnItemSelectedListener(this);
        ArrayAdapter aa2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arr_sname);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        j_spin2.setAdapter(aa2);

        j_spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int pos, long arg3) {
                sname = arg0.getItemAtPosition(pos).toString();


            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Stime = adapterView.getItemAtPosition(i).toString();
    }

    public void onNothingSelected(AdapterView<?>arg0){}


    Calendar myCalendar = Calendar.getInstance();


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };


    public void register(View view){


        //eSportsName.getText().toString();
        updateOther();



        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        String d = sdf.format(myCalendar.getTime());

        date1 = d;


        /*try {
        SimpleDateFormat dateFormat = new SimpleDateFormat("hmm");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("hh:mm", Locale.US);

            Date date = dateFormat.parse(Stime);
            Date date2 = dateFormat.parse(Etime);

             out = dateFormat2.format(date);
             out1 = dateFormat2.format(date2);
            Log.e("---------Time", out);
            Log.e("---------Time2", out1);



        } catch (Exception e) {
        }*/



        Intent in = new Intent(FirstActivity.this, SecondActivity.class);
        in.putExtra("sportsname", sportsname);
        in.putExtra("date", date1);
        in.putExtra("stime", Stime);
        in.putExtra("etime", Etime);
        startActivity(in);

        //sportsname = "";
        date1 = "";


    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);


        edittext.setText(sdf.format(myCalendar.getTime()));
    }

    public void updateOther(){

        if(sname.equals("other")){
            //other.isEnabled();
            sportsname = other.getText().toString();
            Log.d("++++++other+++++",sportsname);
        }
        else{
            sportsname = sname;
        }

    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
