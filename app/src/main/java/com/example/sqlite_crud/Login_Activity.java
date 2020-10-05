package com.example.sqlite_crud;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login_Activity extends AppCompatActivity {
    DataBaseHelper db;
   Button btnlogin;
   EditText etemail,etpassword;
   String email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_);
        etemail=findViewById(R.id.Et_L_email);
        etpassword=findViewById(R.id.Et_L_password);
        btnlogin=findViewById(R.id.Btn_Login);
        db=new DataBaseHelper(getApplicationContext());

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                email=etemail.getText().toString();
                password=etpassword.getText().toString();
                db.LoginData(email,password);
            }
        });
    }
}