package ru.ncedu.java.tasks;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;


/**
 * Created by Sony on 06.10.2015.
 */
public class BusinessCardImpl implements BusinessCard {
    String name = "";
    String lastName = "";
    String department = "";
    String birthDate = "";
    String gender = "";
    int salary = 0;
    long phoneNumber = 0;

    public BusinessCardImpl() {
    }

    ;

    public BusinessCardImpl(String[] card) throws InputMismatchException {
        try {
            this.name = card[0];
            this.lastName = card[1];
            this.department = card[2];
            if (card[3].matches("\\d{2}-\\d{2}-\\d{4}"))
                this.birthDate = card[3];
            else throw new InputMismatchException();
            if (card[4].matches("[M, F]"))
                this.gender = card[4];
            else throw new InputMismatchException();
            if (card[5].matches("\\d*") && Integer.parseInt(card[5]) > 100 && Integer.parseInt(card[5]) < 100000)
                this.salary = Integer.parseInt(card[5]);
            else throw new InputMismatchException();
            if (card[6].matches("\\d{10}"))
                this.phoneNumber = Long.parseLong(card[6]);
            else throw new InputMismatchException();
        } catch (ArrayIndexOutOfBoundsException eo) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public BusinessCard getBusinessCard(Scanner scanner) {
        String input = scanner.nextLine();
        String delimit = "[;]";
        String[] resultList = input.split(delimit);
        BusinessCard result = new BusinessCardImpl(resultList);
        return result;
    }

    @Override
    public String getEmployee() {
        String result = this.name + " " + this.lastName;
        return result;
    }

    @Override
    public String getDepartment() {
        return this.department;
    }

    @Override
    public int getSalary() {
        return this.salary;
    }

    @Override
    public int getAge() {
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
        Date date = new Date();
        try {
            date = format.parse(this.birthDate.replace('-', '/'));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar currentCalendar = Calendar.getInstance();
        Calendar dateBirth = Calendar.getInstance();
        dateBirth.setTime(date);
        int temp = currentCalendar.get(Calendar.YEAR) - dateBirth.get(Calendar.YEAR);
        if (currentCalendar.get(Calendar.MONTH) < dateBirth.get(Calendar.MONTH)) {
            temp--;
        } else if (currentCalendar.get(Calendar.MONTH) == dateBirth.get(Calendar.MONTH) && currentCalendar.get(Calendar.DAY_OF_MONTH) < dateBirth.get(Calendar.DAY_OF_MONTH)) {
            temp--;
        }
        return temp;
    }

    @Override
    public String getGender() {
        if (this.gender.equals("M"))
            return "Male";
        else return "Female";
    }

    @Override
    public String getPhoneNumber() {
        String result = "+7 ";
        String number = Long.toString(this.phoneNumber);
        result = result + String.format("%s-%s-%s-%s", number.substring(0, 3), number.substring(3, 6), number.substring(6, 8), number.substring(8, 10));
        return result;
    }
}
