package com.example.tipapp;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    //Layout components
    EditText amountEditText, percentageEditText, roundEditText;
    TextView finalTipText, newPercentageText;
    Button calculateButton;
    Switch colorSwitch;
    ConstraintLayout layout;

    //Tip components
    TipModel tipModel = new TipModel();
    TipFinalTipView tipFinalTipView;
    TipFinalPercentageView tipFinalPercentageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountEditText = findViewById(R.id.amountEdit);
        percentageEditText = findViewById(R.id.percentageEdit);
        calculateButton = findViewById(R.id.calculatorButton);
        roundEditText = findViewById(R.id.roundEdit);
        finalTipText = findViewById(R.id.finalTipAns);
        newPercentageText = findViewById(R.id.newPercentageAns);
        colorSwitch = findViewById(R.id.colorSwitch);
        layout = findViewById(R.id.mainActivityLayout);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast buttonClicked = Toast.makeText(MainActivity.this, "Button Clicked!", Toast.LENGTH_SHORT);
                buttonClicked.show();
                buttonClicked();

            }
        });

        colorSwitch.setOnCheckedChangeListener(this);
    }

    public void buttonClicked() {
        updateModel();

        tipFinalTipView = new TipFinalTipView(finalTipText);
        tipFinalPercentageView = new TipFinalPercentageView(newPercentageText);

        tipFinalTipView.update(tipModel);
        tipFinalPercentageView.update(tipModel);

        if ( tipModel.arraySize() != 2 ) {
            tipModel.addObserver(tipFinalTipView);
            tipModel.addObserver(tipFinalPercentageView);
        }
        tipModel.notifyObservers(tipModel);
    }

    private void updateModel() {
        tipModel.setTotalBill(Double.parseDouble(String.valueOf(amountEditText.getText())));
        tipModel.setTipPercentage(Double.parseDouble(String.valueOf(percentageEditText.getText())));
        tipModel.setTipQuantum(Double.parseDouble(String.valueOf(roundEditText.getText())));
        tipModel.setFinalTipOutput(tipModel.getRoundedTip());
        tipModel.setFinalPercentageOutput(tipModel.getEffectiveTipPercentage());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if ( colorSwitch.isChecked() ) {
            layout.setBackgroundResource(R.drawable.app_background_switched);
            calculateButton.setBackgroundResource(R.drawable.button_switched);
            getWindow().setStatusBarColor(getResources().getColor(R.color.topOrange));
        } else {
            layout.setBackgroundResource(R.drawable.app_background_default);
            calculateButton.setBackgroundResource(R.drawable.button);
            getWindow().setStatusBarColor(getResources().getColor(R.color.topBlue));
        }
    }
}
