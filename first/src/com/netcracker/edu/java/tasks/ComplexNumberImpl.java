package com.netcracker.edu.java.tasks;

import java.util.Arrays;
//import java.util.DoubleSummaryStatistics;

import static java.lang.Math.pow;
import static java.lang.Math.round;

/**
 * Created by Sony on 15.07.2015.
 */
public class ComplexNumberImpl implements ComplexNumber {
    private double re = 0;
    private double im = 0;
    public ComplexNumberImpl() {};
    public ComplexNumberImpl(double re_new, double im_new) {
        re = re_new;
        im = im_new;
    };

    @Override
    public double getRe() {
        return this.re;
    }

    @Override
    public double getIm() {
        return this.im;
    }

    @Override
    public boolean isReal() {
        if (this.getIm() != 0)
        return false;
        else return true;
    }

    @Override
    public void set(double re, double im) {
        this.re = re;
        this.im = im;
    }

    @Override
    public void set(String value) throws NumberFormatException {
        char[] text = value.toCharArray();
        double re = 0;
        double im = 0;
        int i;
        int j;
        boolean flag_i = false;
        int index_i = Integer.MAX_VALUE;
        int index_sign_1 = Integer.MAX_VALUE;
        int index_sign_2 = Integer.MAX_VALUE;
        for(i = 0; i < value.length(); i++) {
            if (text[i] == 'i') {
                flag_i = true;
                index_i = i;
            }
            if (text[i] == '+' || text[i] == '-') {
                if (index_sign_1 < Integer.MAX_VALUE)
                    index_sign_2 = i;
                else index_sign_1 = i;
            }
        }
        if (index_i == Integer.MAX_VALUE) re = Double.parseDouble(value);
        else {
            if (index_sign_2 == Integer.MAX_VALUE && index_sign_1 == Integer.MAX_VALUE) {
                if (index_i == 0)
                    value = "1i";
                im = Double.parseDouble(value.substring(0,value.length()-1));
            }
            if (index_sign_2 == Integer.MAX_VALUE && index_sign_1 < Integer.MAX_VALUE) {
                if (index_sign_1 != 0) re = Double.parseDouble(value.substring(0,index_sign_1));
                if (index_sign_1 + 1 == index_i) {
                    value = value.substring(0, index_sign_1 + 1) + "1i";
                }
                im = Double.parseDouble(value.substring(index_sign_1,value.length()-1));
            }
            if (index_sign_2 < Integer.MAX_VALUE && index_sign_1 < Integer.MAX_VALUE) {
                re = Double.parseDouble(value.substring(0,index_sign_2));
                if(index_sign_2 + 1 == index_i) {
                    value = value.substring(0, index_sign_2 + 1) + "1i";
                }
                im = Double.parseDouble(value.substring(index_sign_2,value.length()-1));
            }
        }
        this.re = re;
        this.im = im;
    }

    @Override
    public ComplexNumber copy() {
        ComplexNumberImpl new_copy = new ComplexNumberImpl(this.getRe(),this.getIm());
        return new_copy;
    }

    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {
        ComplexNumberImpl new_copy = new ComplexNumberImpl(this.getRe(),this.getIm());
        return new_copy;
    }

    @Override
    public String toString() {
        String result = "";
        if (this.getRe() == 0)
            result = Double.toString(this.getIm()) + "i";
        if (this.getIm() == 0)
            result = Double.toString(this.getRe());
        if (this.getIm()!= 0 && this.getRe() != 0) {
            if (this.getIm() > 0)
                result = Double.toString(this.getRe()) + "+" + Double.toString(this.getIm()) + "i";
            else result = Double.toString(this.getRe()) + Double.toString(this.getIm()) + "i";
            }
        return result;
    }

    public boolean equals(Object other) {
        if (other == this) return true;
        if (other instanceof ComplexNumber) {
            ComplexNumber temp = (ComplexNumber) other;
            if(temp.getIm() == this.getIm() && this.getRe() == temp.getRe())
                return true;
            else return false;
        }
        else return false;
    }

    @Override
    public int compareTo(ComplexNumber other) {
        double first = pow((pow(this.getRe(), 2) + pow(this.getIm(), 2)), 0.5);
        double second = pow((pow(other.getRe(), 2) + pow(other.getIm(), 2)), 0.5);
        if (first < second) return -1;
        if (first > second) return 1;
        return 0;
    }

    @Override
    public void sort(ComplexNumber[] array) {
        Arrays.sort(array);
    }

    @Override
    public ComplexNumber negate() {
        this.re = -this.getRe();
        this.im = -this.getIm();
        return this;
    }

    @Override
    public ComplexNumber add(ComplexNumber arg2) {
        this.re = this.getRe()+arg2.getRe();
        this.im = this.getIm()+arg2.getIm();
        return this;
    }

    @Override
    public ComplexNumber multiply(ComplexNumber arg2) {
        ComplexNumberImpl test = new ComplexNumberImpl();
        test.re = this.getRe()*arg2.getRe()-this.getIm()*arg2.getIm();
        test.im = this.getIm()*arg2.getRe()+this.getRe()*arg2.getIm();
        this.set(test.re, test.im);
        return this;
    }
}
