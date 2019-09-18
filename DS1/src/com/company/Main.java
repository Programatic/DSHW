package com.company;

public class Main {

    public static void main(String[] args) throws Exception {
        UniClass uni = new UniClass(UniClass.Rep.EMAIL);
        uni.addStudent("ford");
        uni.addStudent("apple");
        uni.addStudent("bana");
        uni.addStudent("forrd");

        uni.dump();
        System.out.println("\n" + uni.isRegistered("basna"));
    }
}