package com.example.hallym_smartapp.Login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hallym_smartapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    Button signBt;
    Intent intent;
    EditText newName, newPwd, newId;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("회원가입");

        newId = (EditText)findViewById(R.id.newId);
        newName = (EditText)findViewById(R.id.newName);
        newPwd = (EditText)findViewById(R.id.newPwd);
        signBt = findViewById(R.id.signBt);

        // 회원가입 버튼 클릭 이벤트
        signBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = newId.getText().toString();
                String pwd = newPwd.getText().toString();
                String name = newName.getText().toString();
                UserDTO userDTO = new UserDTO(id, pwd, name);
                signUp(id,pwd,name);
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
    }
        // 회원가입
    private void signUp(String id, String pwd, String name){
        String Key = databaseReference.child("User").push().getKey();
        UserDTO userDTO = new UserDTO(name, id, pwd);
        Map<String, Object> postValues = userDTO.map();
        Map<String,Object> childUpdates = new HashMap<>();
        childUpdates.put("/User/"+id, postValues);
        databaseReference.updateChildren(childUpdates);
    }

}
