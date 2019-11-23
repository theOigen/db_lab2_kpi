package com.lab2.model;

import com.lab2.annotations.DiscriminatorValue;
import com.lab2.annotations.TableName;

@TableName(name = "human")
@DiscriminatorValue("teacher")
public class Teacher extends  Human {
    String subject;
    String academicDegree;
    Integer teachingExperience;

    public Teacher() {}

    public Teacher(Long id, String name, Integer age, Gender gender, String subject, String academicDegree, Integer teachingExperience) {
        super(id, name, age, gender);
        this.subject = subject;
        this.academicDegree = academicDegree;
        this.teachingExperience = teachingExperience;
    }

    public String getSubject() {
        return subject;
    }

    public String getAcademicDegree() {
        return academicDegree;
    }

    public Integer getTeachingExperience() {
        return teachingExperience;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setAcademicDegree(String academicDegree) {
        this.academicDegree = academicDegree;
    }

    public void setTeachingExperience(Integer teachingExperience) {
        this.teachingExperience = teachingExperience;
    }
}
