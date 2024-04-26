package com.example.rdbdemo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private TextView tvRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvRecords = findViewById(R.id.tvRecords);

        // Get the saved records passed from MainActivity
        List<String> savedRecords = getIntent().getStringArrayListExtra("savedRecords");

        // Display the saved records in TextView
        StringBuilder sb = new StringBuilder();
        for (String record : savedRecords) {
            sb.append(record).append("\n\n");
        }
        tvRecords.setText(sb.toString());
    }
}
