package ru.ncedu.java.tasks;

import static java.lang.Math.sin;

/**
 * Created by Sony on 13.07.2015.
 */
public class ControlFlowStatements1Impl implements ControlFlowStatements1 {
    @Override
    public float getFunctionValue(float x) {
        if (x > 0) return (float) (2*sin(x)); //возможна потеря точности
        else return 6-x;
    }

    @Override
    public String decodeWeekday(int weekday) {
        String answer = "";
        switch (weekday) {
            case 1:
                answer = "Monday";
                break;
            case 2:
                answer = "Tuesday";
                break;
            case 3:
                answer = "Wednesday";
                break;
            case 4:
                answer = "Thursday";
                break;
            case 5:
                answer = "Friday";
                break;
            case 6:
                answer = "Saturday";
                break;
            case 7:
                answer = "Sunday";
                break;
        }
        return answer;
    }

    @Override
    public int[][] initArray() {
        int[][] arr = new int[8][5];
        for (int i = 1; i < 8; i++)
            for (int j = 1; j < 5; j++)
                arr[i][j] = i*j;
        return arr;
    }

    @Override
    public int getMinValue(int[][] array) {
        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array[i].length; j++)
                if (array[i][j] < answer)
                    answer = array[i][j];
        return answer;
    }

    @Override
    public BankDeposit calculateBankDeposit(double P) {
        BankDeposit answer = new BankDeposit();
        answer.amount = 1000;
        do {
            answer.amount *= (P+1);
            answer.years++;
        } while (answer.amount <= 5000);
        return answer;
    }
}

