package com.company;

public class Node {
    String word;
    int occurence;
    Node next;

    public Node(String str) {
        word = str;
        occurence = 1;
    }

    public int getOccurence() {
        return occurence;
    }

    public String getWord() {
        return word;
    }

    public void inc() {
        occurence++;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
