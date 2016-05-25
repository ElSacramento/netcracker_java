package ru.ncedu.java.tasks;

import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 * Created by Sony on 14.07.2015.
 */
public class ControlFlowStatements2Impl implements ControlFlowStatements2 {

    @Override
    public int getFunctionValue(int x) {
        if ((x > 2) || (x < -2)) return (2*x);
        else return (-3*x);
    }

    @Override
    public String decodeMark(int mark) {
        String answer = "";
        switch (mark) {
            case 1:
                answer = "Fail";
                break;
            case 2:
                answer = "Poor";
                break;
            case 3:
                answer = "Satisfactory";
                break;
            case 4:
                answer = "Good";
                break;
            case 5:
                answer = "Excellent";
                break;
            default:
                answer = "Error";
                break;
        }
        return answer;
    }

    @Override
    public double[][] initArray() {
        double[][] arr = new double[5][8];
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 8; j++)
                arr[i][j] = pow((double)i, 4.0) - pow((double)j, 0.5);
        return arr;
    }

    @Override
    public double getMaxValue(double[][] array) {
        double answer = Double.MIN_VALUE;
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array[i].length; j++)
                if (array[i][j] > answer)
                    answer = array[i][j];
        return answer;
    }

    @Override
    public Sportsman calculateSportsman(float P) {
        Sportsman result = new Sportsman();
        result.addDay(10);
        P = P/100;
        float dist = 10*(P+1);
        while (result.getTotalDistance() < 200 ) {
            result.addDay(dist);
            dist *= (P+1);
        }
        return result;
    }
}