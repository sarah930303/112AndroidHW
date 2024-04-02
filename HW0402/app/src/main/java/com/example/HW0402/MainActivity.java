package com.example.HW0402;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

enum State { FirstNumberInput, OperatorInputed, NumberInput }
enum OP { None, Add, Sub, Mul, Div }

public class MainActivity extends AppCompatActivity {

    private double theValue = 0;
    private double operand1 = 0, operand2 = 0;
    private OP op = OP.None;
    private State state = State.FirstNumberInput;
    private boolean decimalClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        GridLayout keysGL = findViewById(R.id.keys);

        int kbHeight = keysGL.getHeight() / keysGL.getRowCount();
        int kbWidth = keysGL.getWidth() / keysGL.getColumnCount();

        Button btn;

        for (int i = 0; i < keysGL.getChildCount(); i++) {
            btn = (Button) keysGL.getChildAt(i);
            btn.setHeight(kbHeight);
            btn.setWidth(kbWidth);
            btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, 36);
        }
    }

    public void processKeyInput(View view) {
        Button b = (Button) view;
        String bstr = b.getText().toString();
        EditText edt = findViewById(R.id.display);

        switch (bstr) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
                if (decimalClicked) {
                    String currentText = edt.getText().toString();
                    // 检查当前文本中小数点后数字的位数
                    if (currentText.contains(".")) {
                        String[] parts = currentText.split("\\.");
                        // 检查小数部分的位数是否超过两位
                        if (parts.length > 1 && parts[1].length() >= 2) {
                            return;
                        }
                    }
                    edt.setText(currentText + bstr);
                } else {
                    if (theValue == 0) {
                        edt.setText(bstr);
                    } else {
                        edt.setText(edt.getText().toString() + bstr);
                    }
                }

                theValue = Double.parseDouble(edt.getText().toString());

                switch (state) {
                    case FirstNumberInput:
                    case OperatorInputed:
                    case NumberInput:
                        state = State.NumberInput;
                        break;
                }
                break;
            case ".":
                if (!decimalClicked) {
                    edt.setText(edt.getText().toString() + ".");
                    decimalClicked = true;
                    state = State.NumberInput;
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                op = getOperatorFromButton(bstr);
                operand1 = theValue;
                state = State.OperatorInputed;
                edt.setText("");
                decimalClicked = false;
                break;
            case "=":
                operand2 = theValue;
                double result = performOperation(op, operand1, operand2);
                edt.setText(String.valueOf(result));
                theValue = result;
                state = State.FirstNumberInput;
                decimalClicked = false;
                break;
            case "Clear":
                theValue = 0;
                edt.setText("0");
                state = State.FirstNumberInput;
                decimalClicked = false;
                break;
            case "Back":
                String currentValue = edt.getText().toString();
                if (!currentValue.isEmpty()) {
                    if (currentValue.charAt(currentValue.length() - 1) == '.') {
                        decimalClicked = false;
                    }
                    currentValue = currentValue.substring(0, currentValue.length() - 1);
                    edt.setText(currentValue);
                    theValue = Double.parseDouble(currentValue);
                }
                break;
        }
    }



    private OP getOperatorFromButton(String buttonText) {
        switch (buttonText) {
            case "+":
                return OP.Add;
            case "-":
                return OP.Sub;
            case "*":
                return OP.Mul;
            case "/":
                return OP.Div;
            default:
                return OP.None;
        }
    }

    private double performOperation(OP operation, double operand1, double operand2) {
        switch (operation) {
            case Add:
                return operand1 + operand2;
            case Sub:
                return operand1 - operand2;
            case Mul:
                return operand1 * operand2;
            case Div:
                return operand1 / operand2;
            default:
                return 0;
        }
    }
}