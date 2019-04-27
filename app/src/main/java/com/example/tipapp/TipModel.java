package com.example.tipapp;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class TipModel {

    //Variables
    private double totalBill, tipPercentage, tipQuantum, finalTipOutput, finalPercentageOutput;

    private List<ModelObserver> arrayObserver = new ArrayList<>();

    /**
     * This method is used to calculate the Tip based on the amount spent and the percentage of the tip from the establishment, with those things it is possible to return the exact tip.
     *
     * @param baseAmount is the amount spent.
     * @param percentage is the percentage of the tip from the establishment.
     * @return the calculated tip.
     */
    private static double calcTip(double baseAmount, double percentage) {
        return baseAmount * (percentage / 100);
    }

    //Getters and Setters
    private double getTotalBill() {
        return totalBill;
    }

    void setTotalBill(double totalBill) {
        this.totalBill = totalBill;
    }

    private double getTipPercentage() {
        return tipPercentage;
    }

    void setTipPercentage(double tipPercentage) {
        this.tipPercentage = tipPercentage;
    }

    private double getTipQuantum() {
        return tipQuantum;
    }

    /*Not using this getter for now*/
//    public double getFinalTipOutput() {
//        return finalTipOutput;
//    }

    void setTipQuantum(double tipQuantum) {
        this.tipQuantum = tipQuantum;
    }


    /*Not using this getter for now*/
//    public double getFinalPercentageOutput() {
//        return finalPercentageOutput;
//    }

    void setFinalTipOutput(double finalTipOutput) {
        this.finalTipOutput = finalTipOutput;
    }

    void setFinalPercentageOutput(double finalPercentageOutput) {
        this.finalPercentageOutput = finalPercentageOutput;
    }

    int arraySize() {
        return arrayObserver.size();
    }

    /**
     * This method is used to round the Tip based on the amount spent and the coin that is going to be used, with those things it is possible to return the value rounded.
     *
     * @param amountToRound is the amount calculated before.
     * @param quantum       is the coin selected to use as base to round.
     * @return the rounded tip.
     */
    private double round(double amountToRound, double quantum) {

        double overage = amountToRound;
        double result = amountToRound;
        while ( overage >= quantum ) overage -= quantum;
        if ( overage != 0 ) {
            if ( overage > quantum / 2 ) {
                result = amountToRound - overage + quantum;
            } else if ( overage < quantum / 2 ) {
                result = amountToRound - overage;
            }
        }
        return result;
    }

    /**
     * This method is used to format the Tip into a String value, it is based on the final number calculated before and the number of decimals that will appear on the tip (which will depend on the interface).
     * With those things it is possible to return the value of the tip with a String type.
     *
     * @param num           is the calculated tip already rounded.
     * @param decimalDigits is the number of digits that you wish to appear on the interface.
     * @return the formatted Tip with a String type.
     */
    private String formatNumber(double num, int decimalDigits) {
        return BigDecimal.valueOf(num).setScale(decimalDigits, RoundingMode.DOWN).toString();
    }

    double getRoundedTip() {

        double calculatedTip = calcTip(getTotalBill(), getTipPercentage());

        if ( calculatedTip < 0 ) {
            throw new IllegalArgumentException("You must use a number greater or equal than 0");
        } else {
            calculatedTip = round(calculatedTip, getTipQuantum());
            calculatedTip = Double.parseDouble(formatNumber(calculatedTip, 2));
        }

        return calculatedTip;
    }

    double getEffectiveTipPercentage() {
        double effectivePercentage = ((getRoundedTip() + getTotalBill()) * getTipPercentage()) / getTotalBill();
        effectivePercentage = Double.parseDouble(formatNumber(effectivePercentage, 2));
        return effectivePercentage;

    }

    void addObserver(ModelObserver modelObserver) {

        this.arrayObserver.add(modelObserver);
    }

    void notifyObservers(TipModel model) {

        for (ModelObserver observer : this.arrayObserver) {
            observer.update(model);
        }
    }

    @NonNull
    public String toString() {
        return "Your total amount is " + getTotalBill() + ". Your selected percentage is " + getTipPercentage() + ". Your quantum to round is " + getTipQuantum();
    }


}
