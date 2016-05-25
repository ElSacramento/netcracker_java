package com.netcracker.edu.java.tasks;

/**
 * Created by Sony on 14.07.2015.
 */
public class ZeroTaskImpl implements ZeroTask {
    private int integerValue = 0;
    @Override
    public void setIntegerValue(int value) {
        integerValue = value;
    }

    @Override
    public double getDoubleValue() {
        return integerValue;
    }
}
