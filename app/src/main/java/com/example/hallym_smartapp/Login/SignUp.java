package com.example.hallym_smartapp.Login;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    // 회원가입
    private void signUp(String id, String pwd, String name){
        String Key = databaseReference.child("User").push().getKey();
    }

}
