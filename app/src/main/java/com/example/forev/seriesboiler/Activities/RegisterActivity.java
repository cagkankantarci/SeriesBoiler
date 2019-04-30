package com.example.forev.seriesboiler.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forev.seriesboiler.R;
import com.example.forev.seriesboiler.Models.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText registerName, registerEmail, registerPassword;
    private Button registerButton;
    private TextView registerText;
    private FirebaseAuth auth;
    private ProgressBar loadingProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        defineLayout();
        action();
    }

    public void defineLayout() {
        registerName = (EditText) findViewById(R.id.registerName);
        registerEmail = (EditText) findViewById(R.id.registerEmail);
        registerPassword = (EditText) findViewById(R.id.registerPassword);
        registerButton = (Button) findViewById(R.id.registerButton);
        registerText = (TextView) findViewById(R.id.registerText);
        loadingProgress = (ProgressBar)findViewById(R.id.progressBar);

        loadingProgress.setVisibility(View.INVISIBLE);
        auth = FirebaseAuth.getInstance();
    }

    public void action() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerButton.setVisibility(View.INVISIBLE);
                loadingProgress.setVisibility(View.VISIBLE);
                String name = registerName.getText().toString();
                String email = registerEmail.getText().toString();
                String pass = registerPassword.getText().toString();

                if (!pass.equals("") && !pass.equals("")) {
                    register(name,email, pass);
                    registerName.setText("");
                    registerEmail.setText("");
                    registerPassword.setText("");
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Please check your information.", Toast.LENGTH_LONG).show();
                    registerButton.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);
                }
            }
        });

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void register(final String name,final String email, String pass) {
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Users user = new Users(name,email);

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(RegisterActivity.this, "Please check your information.", Toast.LENGTH_LONG).show();
                    registerButton.setVisibility(View.VISIBLE);
                    loadingProgress.setVisibility(View.INVISIBLE);
                }
            }
        });
    }



}
