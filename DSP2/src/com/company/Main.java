package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	    huffmanCoder("/home/ford/IdeaProjects/DSP2/test.txt", "");
    }

    public static String huffmanCoder(String inputFileName, String outputFileName)
    {
        List<HuffmanNode> freqList = createFrequencyList(inputFileName);
        HuffmanNode tree = createHuffmanTree(freqList);
        displayEncoding(tree);
        return "";
    }

    public static void displayEncoding(HuffmanNode root)
    {
        Queue<HuffmanNode> queue = new LinkedList<HuffmanNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            HuffmanNode temp = queue.remove();
            if (temp.inChar() != null) {
                System.out.println(temp.inChar() + ": " + temp.getFrequency() + " : ");
            } else {
                queue.add(temp.getLeft());
                queue.add(temp.getRight());
            }
        }
    }

    public static short getEncoding(HuffmanNode root, char c)
    {
        return getEncodingHelper(c, root, (short) 0b1);
    }

    private static short getEncodingHelper(char c, HuffmanNode currNode, short progress)
    {

        return 0;
    }

    public static HuffmanNode merge(HuffmanNode n1, HuffmanNode n2)
    {
        HuffmanNode n = new HuffmanNode(n1.getFrequency() + n2.getFrequency());
        if (n1.getFrequency() > n2.getFrequency()) {
            n.setRight(n1);
            n.setLeft(n2);
        } else {
            n.setRight(n2);
            n.setLeft(n1);
        }

        return n;
    }

    public static HuffmanNode createHuffmanTree(List<HuffmanNode> freqlist)
    {
        while (freqlist.size() > 1) {
            HuffmanNode n1 = freqlist.remove(0);
            HuffmanNode n2 = freqlist.remove(0);
            HuffmanNode newNode = merge(n1, n2);
            freqlist.add(newNode);
            Collections.sort(freqlist, Comparator.comparingInt(HuffmanNode::getFrequency)); //If I was able to use a priority queue instead of List, this would be much more efficient
        }
        return freqlist.get(0);
    }

    public static List<HuffmanNode> createFrequencyList(String inputFileName)
    {
        //Create the frequency list, would have preferred to use a priority queue
        List<HuffmanNode> freqList = new ArrayList<HuffmanNode>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFileName));
            int ch;
            while ((ch = br.read()) != -1) {
                HuffmanNode temp = new HuffmanNode((Character) ((char) ch));
                if (freqList.contains(temp)) {
                    freqList.get(freqList.indexOf(temp)).incrementFreq();
                } else {
                    freqList.add(temp);
                    temp.incrementFreq();
                }
            }
        } catch (Exception e)
        {
            //Sad boi hours
        }
        Collections.sort(freqList, Comparator.comparingInt(HuffmanNode::getFrequency));
        return freqList;
    }
}
