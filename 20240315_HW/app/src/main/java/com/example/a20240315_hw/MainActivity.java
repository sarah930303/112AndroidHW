package com.example.a20240315_hw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button(View view) {
        EditText account = findViewById(R.id.account);
        EditText password = findViewById(R.id.password);
        String enteredAccount = account.getText().toString();
        String enteredPassword = password.getText().toString();
        TextView textView =(TextView)findViewById(R.id.textView);
        if (enteredAccount.isEmpty()) { textView.setText("帳號未輸入請輸入帳號"); }
        else if (enteredPassword.isEmpty()) { textView.setText("密碼未輸入請輸入密碼"); }
        else if (!enteredAccount.equals("A111222030")) { textView.setText("帳號錯誤"); }
        else if (!enteredPassword.equals("Sc28mk54")) { textView.setText("密碼錯誤"); }
        else { textView.setText("登錄成功");
             }
    }
}