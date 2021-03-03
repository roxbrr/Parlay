package com.example.parlay.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.parlay.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener
{

    private static final String TAG = "RegisterActivity";
    public static final String EMAIL = "email";
    public static final String L_NAME = "lName";
    public static final String F_NAME = "fName";
    public static final String PHONE_NUM = "phoneNum";

    private FirebaseAuth fAuth;

    EditText etFirstName, etLastName, etPhoneNum, etEmail, etPassword, etConfirmPassword;
    Button btnRegister;
    ImageView ivBackButton;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.d(TAG, "onCreate: started.");

        fAuth = FirebaseAuth.getInstance();

        etFirstName = findViewById(R.id.register_et_first_name);
        etLastName = findViewById(R.id.register_et_last_name);
        etPhoneNum = findViewById(R.id.register_et_phone_num);
        etEmail = findViewById(R.id.register_et_email);
        etPassword = findViewById(R.id.register_et_password);
        etConfirmPassword = findViewById(R.id.register_et_confirm_password);
        btnRegister = findViewById(R.id.register_btn_register_account);
        ivBackButton = findViewById(R.id.register_iv_back_button);

        btnRegister.setOnClickListener(this);
        ivBackButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.register_iv_back_button:
                startActivity(new Intent(this, LoginActivity.class));
                break;

            case R.id.register_btn_register_account:
                createAccount();
                break;
        }
    }

    public void createAccount()
    {
//        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//        String phonePattern = "[0-9]{10}";

        final String fName = etFirstName.getText().toString().trim();
        final String lName = etLastName.getText().toString().trim();
        final String phoneNum = etPhoneNum.getText().toString().trim();
        final String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if(fName.isEmpty())
        {
            etFirstName.setError("First name required");
            etFirstName.requestFocus();
            return;
        }
        else if(fName.length()<3)
            {
            etFirstName.setError("Must be at least 3 characters");
            etFirstName.requestFocus();
            }
        else if(fName.length()>30)
        {
            etFirstName.setError("Must be 30 characters or less");
            etFirstName.requestFocus();
        }
        else if(lName.isEmpty())
        {
            etLastName.setError("Last name required");
            etLastName.requestFocus();
            return;
        }
        else if(lName.length()<3)
        {
            etLastName.setError("Must be at least 3 characters");
            etLastName.requestFocus();
        }
        else if(lName.length()>30)
        {
            etLastName.setError("Must be 30 characters or less");
            etLastName.requestFocus();
        }
        else if(phoneNum.isEmpty())
        {
            etPhoneNum.setError("Phone Number required");
            etPhoneNum.requestFocus();
            return;
        }
        //TO DO: Implement Phone Number Format Validation//////////////////////////////////////////
//        else if();
//        {
//            etPhoneNum.setError("Invalid Number");
//            etPhoneNum.requestFocus();
//            return;
//        }
        else if(email.isEmpty())
        {
            etEmail.setError("E-Mail required");
            etEmail.requestFocus();
            return;
        }
        //TO DO: Implement E-Mail Format Validation//////////////////////////////////////////
//        else if();
//        {
//            etEmail.setError("Invalid Format");
//            etEmail.requestFocus();
//            return;
//        }
        else if(email.length()>30)
        {
            etEmail.setError("Must be 30 characters or less");
            etEmail.requestFocus();
        }
        else if(password.isEmpty())
        {
            etPassword.setError("Password required");
            etPassword.requestFocus();
            return;
        }
        else if(password.length()<8)
        {
            etPassword.setError("Must be at least 8 characters");
            etPassword.requestFocus();
            return;
        }
        else if(confirmPassword.isEmpty())
        {
            etConfirmPassword.setError("Confirm Your Password");
            etConfirmPassword.requestFocus();
            return;
        }
        else if(!password.equals(confirmPassword))
        {
            etConfirmPassword.setError("Password does not match");
            etConfirmPassword.requestFocus();
            return;
        }


        fAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if(!task.isSuccessful())
                {
                    Toast.makeText(RegisterActivity.this, "Registration failed: "
                            + task.getException(), Toast.LENGTH_LONG).show();
                }else
                    {
                        if(fAuth.getCurrentUser().getUid().equals(null))
                        {
                            Log.d(TAG, "onComplete: error setting userID");
                        }else
                            {
                            userID = fAuth.getCurrentUser().getUid();
                            }
                        Map<String, Object> userData = new HashMap<>();
                        userData.put(F_NAME, fName);
                        userData.put(L_NAME, lName);
                        userData.put(PHONE_NUM, phoneNum);
                        userData.put(EMAIL, email);
                        DocumentReference documentReference = FirebaseFirestore.getInstance().collection("Users").document(userID);
                        documentReference.set(userData).addOnSuccessListener(new OnSuccessListener<Void>()
                        {
                            @Override
                            public void onSuccess(Void aVoid)
                            {
                                Log.d(TAG, "Document Saved");
                            }
                        }).addOnFailureListener(new OnFailureListener()
                        {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                Log.w(TAG, "Document not saved", e);
                            }
                        });

                        Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    }
            }
        });


    }


}