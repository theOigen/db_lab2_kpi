package com.lab2.model;

public class Mark {
    Long id;
    Long teacherId;
    Long studentId;
    Integer value;

    public Mark() {}

    public Mark(Long id, Long teacherId, Long studentId, Integer value) {
        this.id = id;
        this.teacherId = teacherId;
        this.studentId = studentId;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Integer getValue() {
        return value;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
