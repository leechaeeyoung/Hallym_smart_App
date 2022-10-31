package com.example.hallym_smartapp.Login;

import static com.example.hallym_smartapp.R.id.signBt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hallym_smartapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {
    Button newSignBt;
    EditText newName, newPwd, newId;
    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = firebaseDatabase.getReference();

    public SignUp(String id, String pw, String name) {
        String key = databaseReference.child("User").push().getKey();
        UserDTO userDTO = new UserDTO(id,pw,name);
        Map<String,Object> postValues = userDTO.map();
        Map<String,Object> childUpdates = new HashMap<>();
        childUpdates.put("/User/"+id, postValues);
        databaseReference.updateChildren(childUpdates);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        setTitle("회원가입");

        newId = findViewById(R.id.newId);
        newName = findViewById(R.id.newName);
        newPwd = findViewById(R.id.newPwd);
        newSignBt = findViewById(R.id.newSignBt);

        // 회원가입 버튼 클릭 이벤트
        newSignBt.setOnClickListener(v -> {
            String id = newId.getText().toString();
            String pw = newPwd.getText().toString();
            String name = newName.getText().toString();
            UserDTO userDTO = new UserDTO(id, pw, name);
            new SignUp(id, pw, name);
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });
    }
}
