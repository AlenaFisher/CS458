package com.example.fishingapp;

/**
 * This is the user class. It represents the user
 * @author Devon Alonzo
 * @date 9/25/2024
 */
public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    public User(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }


    /**
     * This method gets the user first name
     * @return a string, the users first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * This method sets the users first name
     * @param firstName the users first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * This method gets the users last name
     * @return a string, the users last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * This method sets the user last name
     * @param lastName the users last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * This method gets the users username
     * @return a string, the users username
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method sets the users username
     * @param userName the users username
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * This method gets the users password
     * @return a string, the users password
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method sets the users password
     * @param password the users password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
