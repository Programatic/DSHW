package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {
        wordcount("test.txt", "test.out");
    }

    public static void wordcount(String input, String output) throws Exception {
        File f = new File(input);
        BufferedReader br = new BufferedReader(new FileReader(f));
        HashTable table = new HashTable();

        String st;
        while ((st = br.readLine()) != null) {
            for (String s : st.split(" |\\n|\\t|;|'|\\?|>|<")) {
                Node temp;
                if ((temp = table.get(s)) == null) {
                    table.insert(s);
                } else {
                    temp.inc();
                }
            }
        }
        table.output(output);
    }

}
