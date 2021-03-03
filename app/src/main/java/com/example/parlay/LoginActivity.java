package com.example.parlay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final String TAG = "LoginActivity";

    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG,"onCreate: started");

        btnLogin = findViewById(R.id.login_btn_login);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {
        Intent myIntent = new Intent(LoginActivity.this, UserHomeActivity.class);
        LoginActivity.this.startActivity(myIntent);
    }
}