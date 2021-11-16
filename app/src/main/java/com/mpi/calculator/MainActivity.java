package com.mpi.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.text.MessageFormat;

import static android.text.TextUtils.isEmpty;

public class MainActivity extends AppCompatActivity {

    private EditText operationDisplay;
    private EditText resultDisplay;
    private EditText savedValueDisplay;

    private double operationValue;
    private double tempResult;
    private double savedValue;

    private boolean isAddition;
    private boolean isSubtraction;
    private boolean isMultiplication;
    private boolean isDivision;
    private boolean isMultipleCalculation;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonZero = findViewById(R.id.button0);
        Button buttonOne = findViewById(R.id.button1);
        Button buttonTwo = findViewById(R.id.button2);
        Button buttonThree = findViewById(R.id.button3);
        Button buttonFour = findViewById(R.id.button4);
        Button buttonFive = findViewById(R.id.button5);
        Button buttonSix = findViewById(R.id.button6);
        Button buttonSeven = findViewById(R.id.button7);
        Button buttonEight = findViewById(R.id.button8);
        Button buttonNine = findViewById(R.id.button9);
        Button buttonComma = findViewById(R.id.buttonComma);
        Button buttonResult = findViewById(R.id.buttonResult);
        Button buttonAddition = findViewById(R.id.buttonAddition);
        Button buttonSubtraction = findViewById(R.id.buttonSubtraction);
        Button buttonMultiplication = findViewById(R.id.buttonMultiplication);
        Button buttonDivision = findViewById(R.id.buttonDivision);
        Button buttonMemorySave = findViewById(R.id.buttonMs);
        Button buttonMemoryRead = findViewById(R.id.buttonMr);
        Button buttonMemoryClear = findViewById(R.id.buttonMc);
        Button buttonClear = findViewById(R.id.buttonClear);

        operationDisplay = findViewById(R.id.operations);
        resultDisplay = findViewById(R.id.result);
        savedValueDisplay = findViewById(R.id.memorySavedText);

        buttonZero.setOnClickListener(view -> operationDisplay.setText(MessageFormat.format("{0}0",
                operationDisplay.getText())));

        buttonOne.setOnClickListener(view -> operationDisplay.setText(MessageFormat.format("{0}1",
                operationDisplay.getText())));

        buttonTwo.setOnClickListener(view -> operationDisplay.setText(MessageFormat.format("{0}2",
                operationDisplay.getText())));

        buttonThree.setOnClickListener(view -> operationDisplay.setText(MessageFormat.format("{0}3",
                operationDisplay.getText())));

        buttonFour.setOnClickListener(view -> operationDisplay.setText(MessageFormat.format("{0}4",
                operationDisplay.getText())));

        buttonFive.setOnClickListener(view -> operationDisplay.setText(MessageFormat.format("{0}5",
                operationDisplay.getText())));

        buttonSix.setOnClickListener(view -> operationDisplay.setText(MessageFormat.format("{0}6",
                operationDisplay.getText())));

        buttonSeven.setOnClickListener(view -> operationDisplay.setText(MessageFormat.format("{0}7",
                operationDisplay.getText())));

        buttonEight.setOnClickListener(view -> operationDisplay.setText(MessageFormat.format("{0}8",
                operationDisplay.getText())));

        buttonNine.setOnClickListener(view -> operationDisplay.setText(MessageFormat.format("{0}9",
                operationDisplay.getText())));

        buttonComma.setOnClickListener(view -> operationDisplay.setText(MessageFormat.format("{0}.",
                operationDisplay.getText())));

        buttonMemorySave.setOnClickListener(view -> {
            savedValue = tempResult;
            savedValueDisplay.setText(Double.toString(savedValue));
        });

        buttonMemoryRead.setOnClickListener(view -> {
            String enteredValue = String.valueOf(operationDisplay.getText());

            if ((enteredValue.endsWith("+") || enteredValue.endsWith("-")
                    || enteredValue.endsWith("/") || enteredValue.endsWith("*"))) {
                operationDisplay.setText(MessageFormat.format("{0}{1}",
                        operationDisplay.getText(), savedValue));
            }
        });

        buttonMemoryClear.setOnClickListener(view -> {
            savedValue = 0.0;
            savedValueDisplay.setText("");
        });

        buttonClear.setOnClickListener(view -> {
            operationDisplay.setText("");
            resultDisplay.setText("");
            tempResult = 0;
        });

        buttonSubtraction.setOnClickListener(view -> {
            if (isEmpty(operationDisplay.getText())) {
                operationDisplay.setText("");
            } else {
                validateAndEnterSymbol("-");
                if (isMultipleCalculation) {
                    tempResult = getDisplayedResult();
                } else {
                    tempResult = getNumber('-');
                }
                isSubtraction = true;
            }
        });

        buttonAddition.setOnClickListener(view -> {
            if (isEmpty(operationDisplay.getText())) {
                operationDisplay.setText("");
            } else {
                validateAndEnterSymbol("+");
                if (isMultipleCalculation) {
                    tempResult = getDisplayedResult();
                } else {
                    tempResult = getNumber('+');
                }
                isAddition = true;
            }
        });

        buttonMultiplication.setOnClickListener(view -> {
            if (isEmpty(operationDisplay.getText())) {
                operationDisplay.setText("");
            } else {
                validateAndEnterSymbol("*");
                if (isMultipleCalculation) {
                    tempResult = getDisplayedResult();
                } else {
                    tempResult = getNumber('*');
                }
                isMultiplication = true;
                isMultipleCalculation = true;
            }
        });

        buttonDivision.setOnClickListener(view -> {
            if (isEmpty(operationDisplay.getText())) {
                operationDisplay.setText("");
            } else {
                validateAndEnterSymbol("/");
                if (isMultipleCalculation) {
                    tempResult = getDisplayedResult();
                } else {
                    tempResult = getNumber('/');
                }
                isDivision = true;
                isMultipleCalculation = true;
            }
        });

        buttonResult.setOnClickListener(v -> {
            String enteredValue = String.valueOf(operationDisplay.getText());

            if (isAddition){
                String numericValue = enteredValue.substring(enteredValue.lastIndexOf('+') + 1);
                operationValue = Double.parseDouble(numericValue);
                tempResult = tempResult + operationValue;
                isAddition = false;
            } else if (isSubtraction){
                String numericValue = enteredValue.substring(enteredValue.lastIndexOf('-') + 1);
                operationValue = Double.parseDouble(numericValue);
                tempResult = tempResult - operationValue;
                isSubtraction = false;
            } else if (isMultiplication){
                String numericValue = enteredValue.substring(enteredValue.lastIndexOf('*') + 1);
                operationValue = Double.parseDouble(numericValue);
                tempResult = tempResult * operationValue;
                isMultiplication = false;
            } else if (isDivision){
                String numericValue = enteredValue.substring(enteredValue.lastIndexOf('/') + 1);
                operationValue = Double.parseDouble(numericValue);
                tempResult = tempResult / operationValue;
                isDivision = false;
            }
            resultDisplay.setText(tempResult + "");
            isMultipleCalculation = true;
        });

    }

    /**
     * Get result that is shown as current result to the user
     * @return numeric value of displayed result
     */
    private Double getDisplayedResult() {
        return Double.parseDouble(String.valueOf(resultDisplay.getText()));
    }

    /**
     * Allows to extract last entered numeric value.
     * @param operand mathemtatical operation sign
     * @return entered number as string
     */
    private Double getNumber(char operand) {
        String enteredValue = String.valueOf(operationDisplay.getText());
        String numericValue = enteredValue.substring(0, enteredValue.indexOf(operand));
        return Double.parseDouble(numericValue);
    }

    /**
     * Prevent user from entering operands multiple times in a row.
     * @param sign operand that user wants to enter
     */
    private void validateAndEnterSymbol(String sign) {
        String enteredValue = String.valueOf(operationDisplay.getText());

        if (!(enteredValue.endsWith("+") || enteredValue.endsWith("-")
                || enteredValue.endsWith("/") || enteredValue.endsWith("*"))) {
            operationDisplay.setText(MessageFormat.format("{0}{1}",
                    operationDisplay.getText(), sign));
        }
    }

}