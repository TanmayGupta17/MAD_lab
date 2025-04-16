package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.Toast;


import java.util.Stack;

public class MainActivity extends AppCompatActivity{

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0;
    TextView result;
    Button add,sub,divide,multiply,EqualsTo,dot;
    Button buttonC,button_open,button_Close,buttonAC;

    StringBuilder equation = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toast.makeText(MainActivity.this, "No button clicked", Toast.LENGTH_SHORT).show();

//        equation = "";
        b0 = findViewById(R.id.button0);
        b1 = findViewById(R.id.button1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        b4 = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        b8 = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);

        add = findViewById(R.id.add);
        sub = findViewById(R.id.subtract);
        divide = findViewById(R.id.divide);
        multiply = findViewById(R.id.multiply);
        EqualsTo = findViewById(R.id.EqualsTo);
        dot = findViewById(R.id.dot);

        buttonC = findViewById(R.id.button_Clear);
        buttonAC = findViewById(R.id.button_ClearAll);
        button_open = findViewById(R.id.button_opem);
        button_Close = findViewById(R.id.button_Close);

        result = findViewById(R.id.result);

        b0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.append("0");
                result.setText(equation);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.append("1");
                result.setText(equation);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.append("2");
                result.setText(equation);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.append("3");
                result.setText(equation);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.append("4");
                result.setText(equation);
            }
        });

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.append("5");
                result.setText(equation);
            }
        });

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.append("6");
                result.setText(equation);
            }
        });

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.append("7");
                result.setText(equation);
            }
        });

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.append("8");
                result.setText(equation);
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.append("9");
                result.setText(equation);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.append("+");
                result.setText(equation);
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.append("-");
                result.setText(equation);
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.append("*");
                result.setText(equation);
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.append("/");
                result.setText(equation);
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.append(".");
                result.setText(equation);
            }
        });

        button_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.append(")");
                result.setText(equation);
            }
        });

        button_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equation.append("(");
                result.setText(equation);
            }
        });

        buttonAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                equation = "";
                equation.setLength(0);
                result.setText(equation);
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                equation += "1";
                equation.deleteCharAt(equation.length()-1);
                result.setText(equation);
            }
        });

        EqualsTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double ans = evaluateExpression(equation.toString());
                Intent intent = new Intent(MainActivity.this, ResultScreen.class);
                intent.putExtra("result",ans);
                startActivity(intent);
            }
        });

    }

    public double evaluateExpression(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // Skip spaces
            if (c == ' ')
                continue;

            // If digit, extract full number (including decimals)
            if (Character.isDigit(c) || c == '.') {
                StringBuilder num = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    num.append(expression.charAt(i++));
                }
                i--; // Adjust index
                values.push(Double.parseDouble(num.toString()));
            }
            // If opening bracket, push to operators
            else if (c == '(') {
                operators.push(c);
            }
            // If closing bracket, solve enclosed expression
            else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop(); // Remove '('
            }
            // If operator, process precedence
            else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(c);
            }
        }

        // Process remaining operators
        while (!operators.isEmpty()) {
            values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
        }

        return values.pop();
    }

    private int precedence(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    private double applyOperation(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            default: throw new IllegalArgumentException("Invalid operator: " + op);
        }
    }


}