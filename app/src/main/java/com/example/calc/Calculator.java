package com.example.calc;

public class Calculator {

    private StringBuilder numberTemp = new StringBuilder();
    private double numberOne = 0;
    private double numberTwo = 0;
    private Operations operation;

    public enum Operations {
        SUM,
        MULT,
        DIV,
        DIFF
    }

    public String getResult() {
        double result = 0;
        if (numberTemp.length() == 0) {
            return "";
        }
        numberTwo = Double.valueOf(numberTemp.toString());
        numberTemp.delete(0, numberTemp.length());
        if (operation == Operations.SUM) {
            result = numberOne + numberTwo;
        } else if (operation == Operations.MULT) {
            result = numberOne * numberTwo;
        } else if (operation == Operations.DIFF) {
            result = numberOne - numberTwo;
        } else if (operation == Operations.DIV) {

            if (numberTwo != 0) {
                result = numberOne / numberTwo;
            } else {
                result = 0;
            }
        }
        numberTemp.append(result);
        numberOne = 0;
        numberTwo = 0;
        return String.valueOf(result);
    }

    public void setOperation (Operations operation) {
        this.operation = operation;
        if (numberTemp.length() != 0) {
            numberOne = Double.valueOf(numberTemp.toString());
            numberTemp.delete(0, numberTemp.length());
        }

    }

    public void reset (){
        numberTemp.delete(0, numberTemp.length());
        numberOne = 0;
        numberTwo = 0;
    }

    public void setValueToNumberTemp(String buttonNum) {

        if (buttonNum.equals(".")) {
            if (!numberTemp.toString().contains(".")) {
                numberTemp.append(buttonNum);
            }
        } else {
            numberTemp.append(buttonNum);
        }
    }

    public String getValueForTextView() {
        return numberTemp.toString();
    }



    public Calculator() {

    }



}
