package org.example.entity;

public class Student {
    private int id;
    private String name;
    private String email;
    private int semester;
    private String city;
    private String gender;
    private String program;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Student(int id, String name, String email,
                   int semester, String city, String gender, String program) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.semester = semester;
        this.city = city;
        this.gender = gender;
        this.program = program;
    }
}

