package com.lab2.model;

import com.lab2.annotations.DiscriminatorValue;
import com.lab2.annotations.TableName;

@TableName(name = "human")
@DiscriminatorValue("student")
public class Student extends Human {
    Integer course;
    String specialization;
    Boolean isScholarshipHolder;

    public Student() {}

    public Student(Long id, String name, Integer age, Gender gender, Integer course, String specialization, Boolean isScholarshipHolder) {
        super(id, name, age, gender);
        this.course = course;
        this.specialization = specialization;
        this.isScholarshipHolder = isScholarshipHolder;
    }

    public Integer getCourse() {
        return course;
    }

    public String getSpecialization() {
        return specialization;
    }

    public Boolean getScholarshipHolder() {
        return isScholarshipHolder;
    }

    public void setCourse(Integer course) {
        this.course = course;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setScholarshipHolder(Boolean scholarshipHolder) {
        isScholarshipHolder = scholarshipHolder;
    }
}
