package com.example.handson3;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    EditText name,dob,email,mobile;
    TextView age;
    Spinner spin;
    RadioGroup rg;
    RadioButton emailRadioButton,phoneRadioButton;
    CheckBox saveBox;
    Button submit;

    int mYear,mMonth,mDay,mage;
    int dobYear,dobMonth,dobDay;

    String genderSelected,radioButtonSelected;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=findViewById(R.id.name);
        dob=findViewById(R.id.date_picker);
        age=findViewById(R.id.age);
        spin=findViewById(R.id.sex);
        email=findViewById(R.id.mail);
        mobile=findViewById(R.id.mobile_no);
        rg=findViewById(R.id.radio_gp);
        emailRadioButton=findViewById(R.id.radio_email);
        phoneRadioButton=findViewById(R.id.radio_phone);
        saveBox=findViewById(R.id.save_button);
        submit=findViewById(R.id.submit_button);

        List<String> gender=new ArrayList<>();
        gender.add("male");
        gender.add("female");
        gender.add("others");

        ArrayAdapter<String> adapter =new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,gender);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genderSelected=gender.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                genderSelected="male";
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==R.id.radio_email){
                    radioButtonSelected="Email";
                }
                if(checkedId==R.id.radio_phone){
                    radioButtonSelected="Phone";
                }
            }
        });
    }


    public void openCalender(View view){
        final Calendar c = Calendar.getInstance();
         mYear = c.get(Calendar.YEAR);
         mMonth = c.get(Calendar.MONTH);
         mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view1, year, monthOfYear, dayOfMonth) -> {
                    String dateToSetText=dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                    dobYear=year;
                    dobMonth=monthOfYear+1;
                    dobDay=dayOfMonth;
                    mage = mYear - dobYear;
                    if (dobMonth > mMonth) {
                        mage--;
                    } else if (mMonth == dobMonth) {
                        if (dobDay > mDay) {
                            mage--;
                        }
                    }
                    String ageForText="Age:"+mage;
                    age.setText(ageForText);
                    dob.setText(dateToSetText);



                }, mYear, mMonth, mDay);
        datePickerDialog.show();
        //calculateAge();

        LocalDate myObj = LocalDate.now();
        int year= myObj.getYear();
        int month=myObj.getMonthValue();
        int day=myObj.getDayOfMonth();


        int mage = mYear - dobYear;

        if (dobMonth > month) {
            mage--;
        } else if (month == dobMonth) {
            if (dobDay > day) {
                mage--;
            }
        }
        String ageForText="Age:"+mage;
        age.setText(ageForText);
    }


    public void nextActivity(View view){
        SharedPreferences sp=getSharedPreferences("datafile",MODE_PRIVATE);
        SharedPreferences.Editor ed=sp.edit();
        ed.putString("name",name.getText().toString().trim());
        ed.putString("dob",dob.getText().toString().trim());
        ed.putString("age",String.valueOf(mage));
        ed.putString("sex",genderSelected);
        ed.putString("email",email.getText().toString().trim());
        ed.putString("phone",mobile.getText().toString().trim());
        ed.putString("wayOfCommunication",radioButtonSelected);
        ed.apply();

        Intent intent=new Intent(MainActivity.this,Activity2.class);
        startActivity(intent);

    }

}