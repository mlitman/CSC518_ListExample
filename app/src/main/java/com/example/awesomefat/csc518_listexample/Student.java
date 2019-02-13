package com.example.awesomefat.csc518_listexample;

public class Student
{
    private String fname;
    private String lname;
    private int age;

    public Student(String fname, String lname, int age)
    {
        this.fname = fname;
        this.lname = lname;
        this.age = age;
    }

    public void display()
    {
        System.out.println(this.fname + " " + this.lname + " (" + this.age + ")");
    }

    public String toString()
    {
        return this.fname + " " + this.lname + " (" + this.age + ")";
    }
}
