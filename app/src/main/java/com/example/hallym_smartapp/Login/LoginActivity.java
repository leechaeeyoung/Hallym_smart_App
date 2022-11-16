package com.example.hallym_smartapp.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hallym_smartapp.MainActivity;
import com.example.hallym_smartapp.R;
import com.example.hallym_smartapp.Reservation.Activity.ReservationActivity;
import com.example.hallym_smartapp.Splash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity {
    EditText idText, passwordText;
    Button loginBt,signBt;
    Intent intent;

    private final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private final DatabaseReference databaseReference = firebaseDatabase.getReference();

    public static boolean loginStatus = false; // 기본으로 로그인 상태가 아님으로 표시
    public static String loginId="";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        //Splash
        startActivity(new Intent(this, Splash.class));

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        idText = findViewById(R.id.idText);
        passwordText = findViewById(R.id.passwordText);
        loginBt = findViewById(R.id.loginBt);
        signBt = findViewById(R.id.signBt);

        // 회원가입 버튼 클릭 이밴트
        signBt.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), SignUp.class);
            startActivity(intent);
        });
        // 로그인 버튼 클릭 이벤트
        loginBt.setOnClickListener(v -> {
            String id = idText.getText().toString();
            String pw = passwordText.getText().toString();

            if (id.equals("") || pw.equals(""))
                Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
            else
                loginCheck(id, pw);
        });

        // 예약 메인 화면 테스트
        Button btnMain = findViewById(R.id.main);
        btnMain.setOnClickListener(v -> {
            intent = new Intent(getApplicationContext(), ReservationActivity.class);
            startActivity(intent);
        });

        Button btn3 = (Button) findViewById(R.id.thirdFloor);
        btn3.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ThreeFloorActivity.class);
            startActivity(intent);
        });
    }
        // 등록된 유저인지 확인
        public void loginCheck(final String id,final String pwd){
            final Query query = databaseReference.child("User");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(id))
                        if(snapshot.child(id).child("pwd").getValue().equals(pwd)){
                            Log.e("loginCheck: ", "로그인 되었습니다.");
                            Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                            loginStatus = true;
                            loginId = id;

                            intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else{
                            Log.e("loginCheck: ","비밀번호가 틀렸습니다.");
                            Toast.makeText(getApplicationContext(), "아이디 또는 패스워드를 다시 확인하세요.", Toast.LENGTH_LONG).show();
                    }else
                        Log.e("loginCheck: "," 해당 아이디가 존재하지 않습니다.");
                    Toast.makeText(getApplicationContext(), "아이디 또는 패스워드를 다시 확인하세요.", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.e("loadUser:onCancelled", String.valueOf(error.toException()));
                }
            });
        }

    // TEST code
/*        Button btn3 = (Button) findViewById(R.id.thirdFloor);
        btn3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), ThirdFloorActivity.class);
                startActivity(intent);
            }
        });*/
}