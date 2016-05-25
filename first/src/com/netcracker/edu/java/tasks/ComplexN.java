package com.netcracker.edu.java.tasks;

/**
 * Created by Sony on 29.07.2015.
 */
public class ComplexN implements ComplexNumber {
    double re =0;
    double im = 0;
    @Override
    public double getRe() {
        return re;
    }

    @Override
    public double getIm() {
        return im;
    }

    @Override
    public boolean isReal() {
        return false;
    }

    @Override
    public void set(double re, double im) {
        this.re = re;
        this.im = im;
    }

    @Override
    public void set(String value) throws NumberFormatException {

    }

    @Override
    public ComplexNumber copy() {
        return null;
    }

    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {
        return null;
    }

    @Override
    public int compareTo(ComplexNumber other) {
        return 0;
    }

    @Override
    public void sort(ComplexNumber[] array) {

    }

    @Override
    public ComplexNumber negate() {
        return null;
    }

    @Override
    public ComplexNumber add(ComplexNumber arg2) {
        return null;
    }

    @Override
    public ComplexNumber multiply(ComplexNumber arg2) {
        return null;
    }
}
