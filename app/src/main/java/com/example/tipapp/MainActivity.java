package com.example.tipapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText amountEditText, percentageEditText, roundEditText;
    TextView finalTipEditText, newPercentageEditText;
    Button calculateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText = findViewById(R.id.amountEdit);
        percentageEditText = findViewById(R.id.percentageEdit);
        calculateButton = findViewById(R.id.calculatorButton);
        roundEditText = findViewById(R.id.roundEdit);
        finalTipEditText = findViewById(R.id.finalTipAns);
        newPercentageEditText = findViewById(R.id.newPercentageAns);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast buttonClicked = Toast.makeText(MainActivity.this, "Button Clicked!", Toast.LENGTH_SHORT);
                buttonClicked.show();
                setButton();

            }
        });
    }

    private void setButton() {

        float amount = Float.parseFloat(String.valueOf((amountEditText.getText())));
        float percentage = Float.parseFloat(String.valueOf((percentageEditText.getText())));
        float round = Float.parseFloat(String.valueOf((roundEditText.getText())));

        //Avoiding hardcode text warnings...
        String text1, text2;
        text1 = amount * percentage + "";
        text2 = round + percentage + "";

        finalTipEditText.setText(text1);
        newPercentageEditText.setText(text2);

    }


}
