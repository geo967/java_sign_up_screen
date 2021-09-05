package com.example.handson3;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {
    TextView name,dateOfBirth,Age,gender,email,phone,wayOfCommunication;
    String nameMsg,dobMsg,ageMsg,sex,emailId,mobileNo,communication;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_to_display_details);
        name=findViewById(R.id.display_name);
        dateOfBirth=findViewById(R.id.display_dob);
        Age=findViewById(R.id.display_age);
        gender=findViewById(R.id.display_sex);
        email=findViewById(R.id.display_email);
        phone=findViewById(R.id.display_mobile);
        wayOfCommunication=findViewById(R.id.display_communication);


        viewData();

        name.setText(String.format("Name : %s", nameMsg));
        dateOfBirth.setText("DOb:"+dobMsg);
        Age.setText("Age:"+ageMsg);
        gender.setText("Sex:"+sex);
        email.setText("Email:"+emailId);
        phone.setText("Mobile no:"+mobileNo);
        wayOfCommunication.setText("Preferred way of communication is:"+communication);

    }

    public void viewData(){
    SharedPreferences sp=getSharedPreferences("datafile",MODE_PRIVATE);
    nameMsg=sp.getString("name","");
    dobMsg=sp.getString("dob","");
    ageMsg=sp.getString("age","");
    sex=sp.getString("sex","");
    emailId=sp.getString("email","");
    mobileNo=sp.getString("phone","");
    communication=sp.getString("wayOfCommunication","");

    }


}
