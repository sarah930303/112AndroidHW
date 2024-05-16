package com.example.OrderHW;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView showOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get references to the CheckBoxes
        CheckBox chk1 = findViewById(R.id.chk1);
        CheckBox chk2 = findViewById(R.id.chk2);
        CheckBox chk3 = findViewById(R.id.chk3);
        CheckBox chk4 = findViewById(R.id.chk4);
        CheckBox chk5 = findViewById(R.id.chk5);

        // Get references to the ImageViews
        ImageView imgOutput1 = findViewById(R.id.output1);
        ImageView imgOutput2 = findViewById(R.id.output2);
        ImageView imgOutput3 = findViewById(R.id.output3);
        ImageView imgOutput4 = findViewById(R.id.output4);
        ImageView imgOutput5 = findViewById(R.id.output5);

        // Get reference to the TextView
        showOrder = findViewById(R.id.showOrder);

        // Set listeners for the CheckBoxes
        setupCheckBoxListener(chk1, imgOutput1, "漢堡");
        setupCheckBoxListener(chk2, imgOutput2, "薯條");
        setupCheckBoxListener(chk3, imgOutput3, "可樂");
        setupCheckBoxListener(chk4, imgOutput4, "玉米濃湯");
        setupCheckBoxListener(chk5, imgOutput5, "咖啡");
    }

    private void setupCheckBoxListener(CheckBox checkBox, ImageView imageView, String itemName) {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            imageView.setVisibility(isChecked ? ImageView.VISIBLE : ImageView.GONE);
            updateOrderText();
        });
    }

    private void updateOrderText() {
        StringBuilder orderBuilder = new StringBuilder("您的餐點如下: ");
        if (((CheckBox) findViewById(R.id.chk1)).isChecked()) orderBuilder.append("漢堡, ");
        if (((CheckBox) findViewById(R.id.chk2)).isChecked()) orderBuilder.append("薯條, ");
        if (((CheckBox) findViewById(R.id.chk3)).isChecked()) orderBuilder.append("可樂, ");
        if (((CheckBox) findViewById(R.id.chk4)).isChecked()) orderBuilder.append("玉米濃湯, ");
        if (((CheckBox) findViewById(R.id.chk5)).isChecked()) orderBuilder.append("咖啡, ");

        String orderText = orderBuilder.toString();
        if (orderText.endsWith(", ")) {
            orderText = orderText.substring(0, orderText.length() - 2);
        }

        showOrder.setText(orderText.isEmpty() ? "請點餐" : orderText);
    }
}
