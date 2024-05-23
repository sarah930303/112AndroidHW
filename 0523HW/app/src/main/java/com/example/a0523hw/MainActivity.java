package com.example.a0523hw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerMealType;
    private LinearLayout buttonContainer;
    private TextView selected_item;
    private String mainDish = "請選擇";
    private String sideDish = "請選擇";
    private String drink = "請選擇";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize views
        spinnerMealType = findViewById(R.id.spinner_meal_type);
        buttonContainer = findViewById(R.id.button_container);
        selected_item = findViewById(R.id.selected_item);

        // Setup Spinner Adapter
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.meal_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMealType.setAdapter(adapter);

        // Spinner item selection handling
        spinnerMealType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateButtons(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // Update the displayed text
        updateSelectedItemText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        int id = item.getItemId();
        if (id == R.id.send) {
            // Handle send action
            if (mainDish.equals("請選擇") || sideDish.equals("請選擇") || drink.equals("請選擇")) {
                Toast.makeText(this, "請確保所有選項都已選擇", Toast.LENGTH_SHORT).show();
            } else {
                String orderDetails = "主餐: " + mainDish + "\n附餐: " + sideDish + "\n飲料: " + drink;
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("ORDER_DETAILS", orderDetails);
                startActivity(intent);
            }
            return true;
        } else if (id == R.id.cancel) {
            // Handle cancel action
            mainDish = "請選擇";
            sideDish = "請選擇";
            drink = "請選擇";
            updateSelectedItemText();
            Toast.makeText(this, "已取消選擇", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void updateButtons(int mealType) {
        buttonContainer.removeAllViews();  // Clear previous buttons

        int arrayId;
        switch (mealType) {
            case 0:
                arrayId = R.array.main_dish_options;  // Index matches meal_types array
                break;
            case 1:
                arrayId = R.array.side_dish_options;
                break;
            case 2:
                arrayId = R.array.drink_options;
                break;
            default:
                return; // In case of an unexpected value
        }

        String[] items = getResources().getStringArray(arrayId);
        for (String item : items) {
            Button button = new Button(this);
            button.setText(item);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            button.setOnClickListener(v -> {
                switch (mealType) {
                    case 0:
                        mainDish = item;
                        break;
                    case 1:
                        sideDish = item;
                        break;
                    case 2:
                        drink = item;
                        break;
                }
                updateSelectedItemText();
            });
            buttonContainer.addView(button);
        }
    }

    private void updateSelectedItemText() {
        selected_item.setText("主餐: " + mainDish + "\n附餐: " + sideDish + "\n飲料: " + drink);
    }
}
