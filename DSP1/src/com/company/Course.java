package com.company;

public class Course {
    private String courseID;
    private int capacity;
    private Course next;

    public Course(String courseID, int capacity) {
        this.courseID = courseID;
        this.capacity = capacity;
        this.next = null;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getCourseID() {
        return this.courseID;
    }
    public int getCapacity() {
        return capacity;
    }

    public Course getNext() {
        return next;
    }

    public void setNext(Course next) {
        this.next = next;
    }
}
