package com.example.forev.seriesboiler.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.forev.seriesboiler.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileFragment extends Fragment {

    View view;
    FirebaseAuth auth;
    FirebaseUser user;

    FirebaseDatabase database;
    DatabaseReference reference;

    EditText changeName,changePassword;
    TextView changeEmail;

    Button changeButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_profile, container, false);
        define();

        reference.child("Users/").child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {

                changeName.setText(dataSnapshot.child("name").getValue().toString());
                changeEmail.setText(dataSnapshot.child("email").getValue().toString());
                changeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String pass="";
                        pass = changePassword.getText().toString();

                        if(!pass.equals(""))
                        {
                            reference.child("Users/").child(user.getUid()).child("name").setValue(changeName.getText().toString());
                            user.updatePassword(pass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(getContext(),"Saved.",Toast.LENGTH_LONG).show();
                                    }
                                    else{
                                        Toast.makeText(getContext(),"Please enter a valid password.",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(getContext(),"Please enter your password.",Toast.LENGTH_LONG).show();
                        }

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return view;
    }

    void define()
    {
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        changeName = (EditText)view.findViewById(R.id.changeName);
        changePassword = (EditText)view.findViewById(R.id.changePassword);
        changeEmail = (TextView) view.findViewById(R.id.changeEmail);
        changeButton = (Button)view.findViewById(R.id.changeButton);
    }

}
