package com.company;

import java.io.*;
import java.util.*;

public class Main {
    static HashMap<Character, Integer> memo = new HashMap<>();

    public static void main(String[] args) {
	    huffmanCoder("test.txt", "out.txt");
    }

    public static String huffmanCoder(String inputFileName, String outputFileName)
    {
        List<HuffmanNode> freqList = createFrequencyList(inputFileName);
        HuffmanNode tree = createHuffmanTree(freqList);
        displayEncoding(tree);
        try {
            writeHuffmanOutput(inputFileName, outputFileName, tree);
        } catch (Exception e) {

        }
        return "";
    }

    public static void writeHuffmanOutput(String inputFileName, String outputFileName, HuffmanNode root) throws Exception {
        OutputStream os = new FileOutputStream(new File(outputFileName));
        BufferedReader br = new BufferedReader(new FileReader(inputFileName));
        int ch;
        int seq = 0;
        while ((ch = br.read()) != -1) {
            if (bitLength(seq) + bitLength(ch) > 8) {
                    os.write(seq);
                    seq = 0;
                    continue;
            }
            int n = reverseBits(ch);
            while (n > 0) {
                seq <<= 1;
                if ((n & 1) == 1) seq ^= 1;
                n >>= 1;
            }
        }
        os.close();
    }

    public static void displayEncoding(HuffmanNode root)
    {
        System.out.println("char : Freq : Encoded");
        Queue<HuffmanNode> queue = new LinkedList<HuffmanNode>();
        queue.add(root);
        while (!queue.isEmpty()) { //I had a working recursive version for DFS, but I changed it to BFS, IDK why
            HuffmanNode temp = queue.remove();
            if (temp.inChar() != null) {
                System.out.println(temp.inChar() + " : " + temp.getFrequency() + " : 0b" + Integer.toBinaryString(getEncoding(root, temp.inChar())));
            } else {
                queue.add(temp.getLeft());
                queue.add(temp.getRight());
            }
        }
    }

    public static int bitLength(int n)
    {
        int count = 0;
        while (n > 0) {
            n >>= 1;
            count++;
        }
        return count;
    }

    public static int getEncoding(HuffmanNode root, char c)
    {
        if (memo.containsKey(c)) return memo.get(c);
        int res = reverseBits(getEncodingHelper(c, root, 0b1)) >> 1;
        memo.put(c, res);
        return res;
    }

    public static int reverseBits(int n)
    {
        int ret = 0;

        while (n > 0) {
            ret <<= 1;
            if ((n & 0b1) == 0b1) ret ^= 1;
            n >>= 1;
        }
        return ret;
    }

    private static int getEncodingHelper(char c, HuffmanNode currNode, int progress)
    {
        int l = -0b1, r = -0b1;
        HuffmanNode left = currNode.getLeft(), right = currNode.getRight();

        if (left == null && right == null) return  -0b1;
        if (left.inChar() == null)
            l = getEncodingHelper(c, left, progress);
        if (right.inChar() == null)
            r = getEncodingHelper(c, right, progress);

        if (left.inChar() != null && left.inChar().equals(c))
            return progress << 1;
        if (right.inChar() != null && right.inChar().equals(c))
            return (progress << 1) + 0b1;

        if (l == -1 && r == -1) return -1;
        if (l >= 0)
            progress = l << 1;
        else
            progress = (r << 1) + 0b1;
        return progress;
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
