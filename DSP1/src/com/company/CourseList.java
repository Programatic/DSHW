package com.company;

public interface CourseList {
    int size();
    void addCourse(int i, Course course);
    boolean changeCapacity(String id, int cap);
    boolean removeCourse(int i);
    Course search(int i);
}
