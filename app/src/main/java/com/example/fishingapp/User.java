package com.example.fishingapp;

/**
 * This is the user class. It represents the user
 * @author Devon Alonzo
 * @date 9/25/2024
 */
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String emailAddress;
    private String phoneNumber;
    private String dateOfBirth;

    public User(int id, String firstName, String lastName, String userName, String password, String emailAddress, String phoneNumber, String dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * This method sets the users emailAddress
     * @param emailAddress the users emailAddress
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * This method returns the users phoneNumber
     * @return a string, the users phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * This method sets the users phoneNumber
     * @param phoneNumber the users phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * This method returns the users dateOfBirth
     * @return a string, the users dateOfBirth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * This method sets the users dateOfBirth
     * @param dateOfBirth the users dateOfBirth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
