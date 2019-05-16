package com.example.myapplication;

public class UserProfile {
    private String Age;
    private String Email;
    private String Name;
    private String DateOfBirth;
    private String MobileNumber;

    public UserProfile(){

    }

    public UserProfile(String userAge, String userEmail, String userName, String dob, String mobileNumber) {
        this.Age = userAge;
        this.Email = userEmail;
        this.Name = userName;
        this.DateOfBirth = dob;
        this.MobileNumber = mobileNumber;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        MobileNumber = mobileNumber;
    }
}
