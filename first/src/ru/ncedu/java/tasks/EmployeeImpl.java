package ru.ncedu.java.tasks;

/**
 * Created by Sony on 15.07.2015.
 */
public class EmployeeImpl implements Employee {
    private int Salary = 1000;
    private String FirstName = "";
    private String SecondName = "";
    private Employee manager = null;

    public EmployeeImpl() {};

    @Override
    public int getSalary() {
        return this.Salary;
    }

    @Override
    public void increaseSalary(int value) {
        this.Salary += value;
    }

    @Override
    public String getFirstName() {
        return this.FirstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }

    @Override
    public String getLastName() {
        return this.SecondName;
    }

    @Override
    public void setLastName(String lastName) {
        this.SecondName = lastName;
    }

    @Override
    public String getFullName() {
        String answer = this.getFirstName() + " " + this.getLastName();
        return answer;
    }

    @Override
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Override
    public String getManagerName() {
        if (this.manager == null) return "No manager";
        else return (this.manager.getFullName());
    }

    @Override
    public Employee getTopManager() {
        Employee answer = this.manager;
        if (this.manager != null)
            return this.manager.getTopManager();
        else return this;
    }
}
