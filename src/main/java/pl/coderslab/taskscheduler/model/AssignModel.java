package pl.coderslab.taskscheduler.model;

import org.hibernate.validator.constraints.NotEmpty;

public class AssignModel {

    @NotEmpty(message = "Field may not be empty")
    private String name;

    @NotEmpty(message = "Field may not be empty")
    private String surname;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "AssignModel{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
