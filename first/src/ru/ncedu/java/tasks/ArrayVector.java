package ru.ncedu.java.tasks;

/**
 * Created by Sony on 14.07.2015.
 */
/**
 * ���� ������ - ����������� � ��������� � ������� � Java, ���������� ��������� ������,
 *  ������� ����������� ������� (����������� �������).<br/>
 * <p/>
 * �������<br/>
 * ����������� ����� ��� ������ � �������� (����� ������������ �����, ���������)
 *  � ������� �������� ��������� ����������.<br/>
 * <p/>
 * ����������<br/>
 * ��������� ������, ������������ ������ ���������, ������ ��������������� ������ �������
 *  � ������� �������� ������� � ���� ���� "������".<br/>
 * ������ ������������ �������� ���������� Java, ����� ������� Math � Arrays.<br/>
 * ����� ������������� ������ ���� ����������� ��� ���������� (default constructor), ������ public.<br/>
 * ����� ������ �������� ��������� ����� ������ ������ ������������ � ��� ������ set-������.<br/>
 * <p/>
 * ����������<br/>
 * ������ ����� ������ ��� ����� ��������� � ��������� �������������� �������� (Exceptions).<br/>
 *
 * @author Andrey Gavrilov
 * @author Alexander Kharichkin
 * @author Alexey Evdokimov
 */
public interface ArrayVector {
    /**
     * ������ ��� �������� ������� (���������� ����� �������).
     * ������������ ������ �� �����������.
     * @param elements �� ����� null
     */
    void set(double... elements);
    /**
     * ���������� ��� �������� �������. ������ �� �����������.
     */
    double[] get();
    /**
     * ���������� ����� ������� (�����, ��������� ���������
     *  � ������� �� �������� � ��������� ��������� ������� �������).<br/>
     * ������������� ������� ����� clone() � ������ ������� ��� ������������
     *   {@link System#arraycopy(Object, int, Object, int, int)}.
     */
    ArrayVector clone();
    /**
     * ���������� ����� ��������� �������.
     */
    int getSize();

    /**
     * �������� ������� �� �������.
     * @param index � ������ ������ ������� �� ������� �������:<br/>
     *  �) ���� index<0, ������ �� ����������;<br/>
     *  �) ���� index>=0, ������ ������� ������������� ���, ����� index ���� ���������.
     */
    void set(int index, double value);
    /**
     * ���������� ������� �� �������.
     * @param index � ������ ������ ������� �� ������� �������
     *   ������ �������������� ArrayIndexOutOfBoundsException
     */
    double get(int index) throws ArrayIndexOutOfBoundsException;

    /**
     * ���������� ������������ ������� �������.
     */
    double getMax();
    /**
     * ���������� ����������� ������� �������.
     */
    double getMin();
    /**
     * ��������� �������� ������� � ������� �����������.
     */
    void sortAscending();

    /**
     * �������� ������ �� �����.<br/>
     * ���������: �� ��������� ������������ ������������ ���� foreach:
     *  ��� ��������� �������� ������� ����� ����� ��� ������.
     * @param factor
     */
    void mult(double factor);
    /**
     * ���������� ������ � ������ ��������, ��������� ���������� � ��������� ������� �������.<br/>
     * ���� ������� ����� ������ ������, ��������� �������� �������� ������� �� �����������<br/>
     *  (���� ������ ������ - �������, ��� ������ ������ �� ����, ������ �� ������� ��������� ��������).
     * @param anotherVector �� ����� null
     * @return ������ �� ���� (��������� ��������)
     */
    ArrayVector sum(ArrayVector anotherVector);
    /**
     * ���������� ��������� ������������ ���� ��������.<br/>
     * ���� ������� ����� ������ ������, ��������� �������� �������� ������� �� �����������.
     * @param anotherVector �� ����� null
     */
    double scalarMult(ArrayVector anotherVector);
    /**
     * ���������� ��������� ����� ������� (����� �������
     *  � n-������ ���������� ������������, n={@link #getSize()}).
     * ������� ����� ������� ����� ���������� ������������ ������� �� ����.
     */
    double getNorm();
}
