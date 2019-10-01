package com.company;

public class CourseLinkedList implements CourseList {
    private int size;
    private Course root;

    CourseLinkedList() {
        size = 0;
        root = null;
    }

    public int size() {
        return size;
    }

    public void addCourse(int i, Course course) {
        if (root == null) {
            root = course;
            size++;
            return;
        }
        if (i == 0) { //I need to address the edge case of i == 0
            course.setNext(root);
            root = course;
            size++;
            return;
        }
        Course parent = root;
        Course trav = root;
        int count = 0;
        while (trav != null && count < i) {
            parent = trav;
            trav = trav.getNext();
            count++;
        }
        Course temp = parent.getNext();
        parent.setNext(course);
        course.setNext(temp);
        size++;
    }

    public boolean changeCapacity(String id, int cap) {
        Course trav = root;
        while (trav != null) {
            if (trav.getCourseID().equals(id)) {
                trav.setCapacity(cap);
                return true;
            }
            trav = trav.getNext();
        }
        return false;
    }

    public boolean removeCourse(int i) {
        if (i == 0) {
            root = root.getNext();
            return true;
        }
        if (i >= size) return false;
        Course parent = root;
        Course trav = root;
        int count = 0;
        while (trav != null && count < i) {
            parent = trav;
            trav = trav.getNext();
            count++;
        }
        parent.setNext(trav.getNext());
        size--;
        return true;
    }

    public Course search(int i) {
        if (i >= size) return null;
        Course parent = root;
        Course trav = root;
        int count = 0;
        while (trav != null && count < i) {
            parent = trav;
            trav = trav.getNext();
            count++;
        }
        return trav;
    }
    public void dump() {
        Course trav = root;
        while (trav != null) {
            System.out.print(trav.getCourseID() + " ");
            trav = trav.getNext();
        }
    }
}
