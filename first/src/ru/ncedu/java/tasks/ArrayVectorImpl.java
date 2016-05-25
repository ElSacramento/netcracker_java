package ru.ncedu.java.tasks;

import java.util.Arrays;
import java.lang.ArrayIndexOutOfBoundsException;

import static java.lang.Math.sqrt;
import static java.util.Arrays.sort;

/**
 * Created by Sony on 14.07.2015.
 */
public class ArrayVectorImpl implements ArrayVector {
    public  double[] vector;
    public ArrayVectorImpl(){}
    @Override
    public void set(double... elements) {
        vector = new double[elements.length];
        for (int i = 0; i < elements.length; i++)
            vector[i] = elements[i];
    }

    @Override
    public double[] get() {
        return this.vector;
    }

    @Override
    public ArrayVector clone() {
        ArrayVectorImpl train = new ArrayVectorImpl();
        train.vector = this.get().clone();
        return train;
    }

    @Override
    public int getSize() {
        return this.get().length;
    }

    @Override
    public void set(int index, double value) {
        if (index < 0) return;
        else {
            if (index >= this.getSize()) {
                double[] new_vector = new double[index + 1];
                for (int i = 0; i < this.getSize(); i++)
                    new_vector[i] = this.get()[i];
                this.set(new_vector);
                this.get()[index] = value;
            }
            else this.get()[index] = value;
        }
    }

    @Override
    public double get(int index) throws ArrayIndexOutOfBoundsException {
        return this.get()[index];
    }

    @Override
    public double getMax() {
        double max = Double.MIN_VALUE;
        for (int i = 0; i < this.getSize(); i++)
            if (this.get()[i] > max)
                max = this.get()[i];
        return max;
    }

    @Override
    public double getMin() {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < this.getSize(); i++)
            if (this.get()[i] < min)
                min = this.get()[i];
        return min;
    }

    @Override
    public void sortAscending() {
        sort(this.get());
    }

    @Override
    public void mult(double factor) {
        for (int i = 0; i < this.getSize(); i++)
            this.get()[i] *= factor;
    }

    @Override
    public ArrayVector sum(ArrayVector anotherVector) {
        int n = (this.getSize() > anotherVector.getSize())? anotherVector.getSize():this.getSize();
        double[] new_vector = this.get();
        for (int i = 0; i < n; i++)
            this.get()[i] += anotherVector.get()[i];
        return this;
    }

    @Override
    public double scalarMult(ArrayVector anotherVector) {
        double scalar = 0;
        int n = (this.getSize() > anotherVector.getSize())? anotherVector.getSize():this.getSize();
        for (int i = 0; i < n; i++)
            scalar += this.get()[i] * anotherVector.get()[i];
        return scalar;
    }

    @Override
    public double getNorm() {
        return sqrt(this.scalarMult(this));
    }
}
