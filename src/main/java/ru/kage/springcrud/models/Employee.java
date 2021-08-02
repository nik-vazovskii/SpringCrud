package ru.kage.springcrud.models;

import javax.validation.constraints.*;

public class Employee {

    private int id;

    @NotEmpty(message = "First Name should not be empty")
    @Size( min = 2, max = 30, message = "First Name should not be between 2 and 30 characters")
    private String firstName;

    @NotEmpty(message = "Last Name should not be empty")
    @Size( min = 2, max = 50, message = "Last Name should not be between 2 and 30 characters")
    private String lastName;

    @Min(value = 18, message = "Age should be greater then 18" )
    @Max( value = 100, message = "Age should be less then 100" )
    private int age;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    public Employee() {
    }

    public Employee(int id, String firstName, String lastName, int age, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
