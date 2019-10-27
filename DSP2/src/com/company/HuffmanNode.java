package com.company;

public class HuffmanNode {
    private Character schar;
    private int frequency;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(Character c)
    {
        this.schar = c;
        this.frequency = 0;
    }

    public HuffmanNode(int frequency)
    {
        this.frequency = frequency;
        this.schar = null;
    }

    public HuffmanNode getLeft()
    {
        return left;
    }

    public HuffmanNode getRight()
    {
        return right;
    }

    public int getFrequency()
    {
        return frequency;
    }

    public void setLeft(HuffmanNode left)
    {
        this.left = left;
    }

    public void setRight(HuffmanNode right)
    {
        this.right = right;
    }

    public void incrementFreq()
    {
        frequency++;
    }

    public Character inChar()
    {
        return schar;
    }

    public int getHeight()
    {
        if (left == null && right == null)
            return 0;
        else if (left == null)
            return 1 + right.getHeight();
        else if (right == null)
            return 1 + left.getHeight();
        int lheight = left.getHeight();
        int rheight = right.getHeight();
        return 1 + Math.max(lheight, rheight);
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this) return true;
        if (!(o instanceof HuffmanNode)) return false;
        return ((HuffmanNode) o).schar.equals(schar);
    }

    @Override
    public String toString()
    {
        return schar + ": " + frequency;
    }
}
