package com.thadsanapoom.nitipat.googlelogin.io.model;


/**
 * Created by Nitipat on 20/3/2559.
 */
public class Person {
    // private variables
    public int id;
    public String name;
    public String lastname;
    public String birthday;
    public String gender;
    public String weight;
    public String lenght;
    public String bloodtype;
    public String allergic;


    public Person() {
    }

    public Person(String name, String lastname, String birthday, String gender, String weight, String lenght, String bloodtype, String allergic) {
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.gender = gender;
        this.weight = weight;
        this.lenght = lenght;
        this. bloodtype = bloodtype;
        this.allergic = allergic;

    }

    public Person(int id,String name, String lastname, String birthday, String gender, String weight, String lenght, String bloodtype, String allergic) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.gender = gender;
        this.weight = weight;
        this.lenght = lenght;
        this. bloodtype = bloodtype;
        this.allergic = allergic;

    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthDay() {
        return birthday;
    }

    public void setBirthDay(String birthday) {
        this.birthday = birthday;
    }

    public String getGender(){
        return gender;
    };

    public void setGender(String gender) {
        this.gender = gender ;
    }
    public String getWeight(){
        return weight;
    };
    public void setWeigth(String weigth) {
        this.weight= weigth;
    }
    public String getLenght(){
        return lenght;
    };
    public void setLenght(String lenght) {
        this.lenght = lenght;
    }
    public String getBloodType(){
        return bloodtype;
    };
    public void setBloodType(String bloodtype) {
        this.bloodtype = bloodtype;
    }
    public String getAllergic(){
        return allergic;
    };
    public void setAllergic(String allergic) { this.allergic = allergic; }

}