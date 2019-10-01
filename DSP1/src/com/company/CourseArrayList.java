package com.company;

public class CourseArrayList implements CourseList {
    private Course[] courses;
    private int size;

    CourseArrayList() {
        courses = new Course[9999];
        size = 0;
    }

    public int size() {
        return size;
    }

    public void addCourse(int i, Course course) {
        if (size == courses.length) return;
        if (i >= size) {
            courses[size++] = course;
            return;
        }
        for (int k = size - 1; k >= 0 && k >= i; k--) {
            courses[k+1] = courses[k];
        }
        courses[i] = course;
        size++;
    }

    public boolean changeCapacity(String id, int cap) {
       for (int i = 0; i < size; i++) {
           if (courses[i].getCourseID().equals(id)) {
               courses[i].setCapacity(cap);
               return true;
           }
       }
       return false;
    }

    public boolean removeCourse(int i) {
        if (size <= i) return false;
        for (int k = i; k < size; k++) {
            courses[k] = courses[k + 1];
        }
        size--;
        courses[size] = null;
        return true;
    }

    public Course search(int i) {
        if (i >= size) return null;
        return courses[i];
    }

    public Course[] getList() {
        return courses;
    }


    public void dump() {
        for (Course i : courses) {
            if (i != null) System.out.print(i.getCourseID() + " ");
        }
    }
}
