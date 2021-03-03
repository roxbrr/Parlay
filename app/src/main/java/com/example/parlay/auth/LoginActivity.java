package com.example.parlay.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.parlay.R;
import com.example.parlay.UserHomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
    private static final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;

    Button btnLogin;
    TextView tvRegister, tvNeedHelp;
    EditText etEmail, etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d(TAG,"onCreate: started");

        mAuth = FirebaseAuth.getInstance();

        btnLogin = findViewById(R.id.login_btn_login);
        tvRegister = findViewById(R.id.login_tv_register);
        tvNeedHelp = findViewById(R.id.login_tv_need_help);
        etEmail = findViewById(R.id.login_et_email);
        etPassword = findViewById(R.id.login_et_password);

        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvNeedHelp.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.login_btn_login:

                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if(email.isEmpty() || password.isEmpty())
                {
                    Toast.makeText(LoginActivity.this, "Missing Email/Password", Toast.LENGTH_LONG).show();
                }
                else {
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                                    } else
                                    {
                                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                                        Intent myIntent = new Intent(LoginActivity.this, UserHomeActivity.class);
                                        LoginActivity.this.startActivity(myIntent);
                                    }
                                }
                            });
                }
                break;

            case R.id.login_tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                Log.d(TAG, "onClick: login_tv_register_link Clicked.");
                break;

            case R.id.login_tv_need_help:
//                startActivity(new Intent(this, RegisterActivity.class));
                Log.d(TAG, "onClick: login_tv_need_help Clicked.");
                break;

            default:
                Log.d(TAG, "onClick: Default Case Selected");
                break;
        }
    }
}