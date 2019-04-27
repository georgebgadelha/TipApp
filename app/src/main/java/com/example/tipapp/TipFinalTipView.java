package com.example.tipapp;

import android.widget.TextView;

public class TipFinalTipView implements ModelObserver {

    private TextView finalTip;

    TipFinalTipView(TextView textField) {

        finalTip = textField;
    }

    @Override
    public void update(TipModel model) {


        double result = model.getRoundedTip();
        String text = "$" + result;

        finalTip.setText(text);

//		model.setFinalTipOutput(Double.parseDouble(finalTip.getText()));


    }

}
