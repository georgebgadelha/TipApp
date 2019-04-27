package com.example.tipapp;

import android.widget.TextView;

public class TipFinalPercentageView implements ModelObserver {

    private TextView finalPercentage;

    TipFinalPercentageView(TextView textField) {

        finalPercentage = textField;

    }

    @Override
    public void update(TipModel model) {

        double result = model.getEffectiveTipPercentage();
        String text = result + "%";

        finalPercentage.setText(text);

//		model.setFinalPercentageOutput(Double.parseDouble(finalPercentage.getText()));

    }


}
