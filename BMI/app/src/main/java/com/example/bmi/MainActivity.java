package com.example.bmi;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txvshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvshow = findViewById(R.id.txvshow);
        txvshow.setTextSize(36);
        Button btnCalc = findViewById(R.id.btncalc);
        Button btnClear = findViewById(R.id.btnclear);
        btnCalc.setOnClickListener(this);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText ediHeight = findViewById(R.id.edthight);
        EditText editWeight = findViewById(R.id.edtweight);

        if (v.getId() == R.id.btncalc) {
            String heightStr = ediHeight.getText().toString();
            String weightStr = editWeight.getText().toString();

            if (heightStr.isEmpty() || weightStr.isEmpty()) {
                txvshow.setText("請輸入身高和體重。");
                txvshow.setTextColor(Color.BLACK); // 您可以設置任何您喜歡的顏色作為錯誤消息的顏色
                return;
            }

            double height = Double.parseDouble(heightStr);
            double weight = Double.parseDouble(weightStr);

            if (height <= 0 || weight <= 0) {
                txvshow.setText("無效的輸入。請輸入身高和體重的正數。");
                txvshow.setTextColor(Color.BLACK); // 您可以設置任何您喜歡的顏色作為錯誤消息的顏色
                return;
            }

            double bmi = weight / Math.pow(height / 100.0, 2);

            if (bmi >= 24.9)
                txvshow.setTextColor(Color.RED);
            else if (bmi < 18.5)
                txvshow.setTextColor(Color.BLUE);
            else
                txvshow.setTextColor(Color.GREEN);

            txvshow.setText(String.format("%.2f", bmi));
        } else {
            editWeight.setText("");
            ediHeight.setText("");
            txvshow.setText("");
        }
    }
}
