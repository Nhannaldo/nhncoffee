package com.example.appandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class Signup extends AppCompatActivity {
    EditText edtName,edtPass,edtEmail,edtPassAgain;
    Button btnSignup;
    private CheckBox chkHienThiPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        edtName=(EditText) findViewById(R.id.edtName);
        edtPass=(EditText) findViewById(R.id.edtPass);
        edtEmail=(EditText) findViewById(R.id.edtEmail) ;
        edtPassAgain=(EditText) findViewById(R.id.edtPassAgain);
        chkHienThiPass=(CheckBox) findViewById(R.id.chkHienThiPass);
        btnSignup=(Button) findViewById(R.id.btnSignup);
        chkHienThiPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    edtPass.setTransformationMethod(null);
                    edtPassAgain.setTransformationMethod(null);
                }
                else{
                    edtPass.setTransformationMethod( new PasswordTransformationMethod());
                    edtPassAgain.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplication(),Signin.class);
                startActivity(intent);
            }
        });
    }
}