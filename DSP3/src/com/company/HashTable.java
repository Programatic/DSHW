package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Stack;

public class HashTable {
    private Node[] table;

    public HashTable() {
        table = new Node[4];
    }

    public Node get(String s) {
        int hash = Math.abs(s.hashCode()) % table.length;
        Node p = table[hash];
        if (p == null)
            return null;
        while (p.getNext() != null) {
            if (p.getWord().equals(s.toLowerCase()))
                return p;
            p = p.getNext();
        }
        return null;
    }

    public void insert(String s) {
        int hash = Math.abs(s.hashCode()) % table.length;
        if (table[hash] == null) {
            table[hash] = new Node(s.toLowerCase());
            return;
        }

        Node trav = table[hash];
        while (trav.getNext() != null) {
            trav = trav.getNext();
        }
        trav.setNext(new Node(s.toLowerCase()));
        if (loadFactor() >= 0.75)
            rehash();
    }

    private double numLists() {
        double count = 0;
        for (Node n : table)
            if (n != null)
                count++;

        return count;
    }

    private double loadFactor() {
        return numLists()/table.length;
    }

    private void rehash() {
        Node temp[] = new Node[table.length * 2];
        Stack<String> stack = new Stack<String>();
        for (Node n : table) {
            if (n == null)
                continue;

            Node p = n;
            do {
                stack.push(p.getWord());
                p = p.getNext();
            } while (p != null && p.getNext() != null);
        }
        table = temp;
        while (!stack.empty()) {
            String t = stack.pop();
            insert(t);
        }
    }

    public void output(String out) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(out));
        writer.write(table.length + "\n");

        int empty = 0;
        for (Node n : table)
            if (n == null)
                empty++;

        writer.write(empty + "\n");
        writer.write(loadFactor() + "\n");

        int collisions = 0;
        for (Node n : table) {
            if (n == null) continue;
            Node p = n;
            while (p.getNext() != null) {
                p = p.getNext();
                collisions++;
            }
        }
        writer.write(collisions/empty + "\n");

        for (Node n : table) {
            if (n == null) continue;
            Node p = n;
            do {
                writer.write("(" + p.getWord() + ", " + p.getOccurence() + ") ");
                p = p.getNext();
            } while (p != null && p.getNext() != null);
        }
        writer.close();
    }
}
