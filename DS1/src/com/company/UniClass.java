package com.company;

public class UniClass {
    public enum Rep { //Representation method
        EMAIL, ID
    }
    private Enum r;          //Current representation
    private String[] emails; //NOTE: This would be better achieved using arraylists
    private int[] ids;       //however i decided to use regular arrays because they already have the functions
    private int length;      //that I need to implement

    UniClass(Enum e) {
        this.r = e;
        this.length = 0;
        if (e == Rep.EMAIL) emails = new String[999];
        else ids = new int[999];
    }

    //Since this function uses binary search, this function has a complexity of O(log n)
    public boolean isRegistered(int i) throws Exception //i = Identifier
    {
        if (this.r != Rep.ID) throw new Exception("Incorrect Identifier Type: Use IDs not Emails"); //Something went horribly wrong and this should not happen

        int high = this.length - 1; //This implements binary search method
        int low = 0;
        while (high != low) {
            if (high < low) return false;
            int mid = low + (high - low) / 2;
            if (i == this.ids[mid]) return true;
            if (i > this.ids[mid]) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }

    //Worst case, this function will have a complexity of n + log n which is equivalent to O(n). Its necessary to keep the array ordered because of isRegistered
    public boolean addStudent(int s) throws Exception //s = Student
    {
        if (this.r != Rep.ID) throw new Exception("Incorrect Identifier Type: Use IDs not Emails"); //Something went horribly wrong and this should not happen
        if (this.length == this.ids.length) return false;

        if(isRegistered(s)) return false; //In a real case, we don't want to register a student 2 times, so this check is necessary

        int i; //Inserting into ordered array
        for (i = this.length - 1; (i >= 0 && this.ids[i] > s); i--)
            this.ids[i + 1] = this.ids[i];

        this.ids[i + 1] = s;
        this.length++;
        return true;
    }

    public boolean isRegistered(String i) throws Exception //i = Identifier
    {
        if (this.r != Rep.EMAIL) throw new Exception("Incorrect Identifier Type: Use IDs not Emails"); //Something went horribly wrong and this should not happen

        int high = this.length - 1; //This implements binary search method
        int low = 0;
        while (high != low) {
            if (high < low) return false;
            int mid = low + (high - low) / 2;
            if (i.equals(this.emails[mid])) return true;
            if (i.compareToIgnoreCase(this.emails[mid]) > 0) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }

    public boolean addStudent(String s) throws Exception //s = Student
    {
        if (this.r != Rep.EMAIL) throw new Exception("Incorrect Identifier Type: Use IDs not Emails"); //Something went horribly wrong and this should not happen
        if (this.length == this.emails.length) return false;

        if(isRegistered(s)) return false; //In a real case, we don't want to register a student 2 times, so this check is necessary

        int i; //Inserting into ordered array
        for (i = this.length - 1; (i >= 0 && this.emails[i].compareToIgnoreCase(s) > 0); i--)
            this.emails[i + 1] = this.emails[i];

        this.emails[i + 1] = s;
        this.length++;
        return true;
    }

    public void dump()
    {
        for(int i = 0 ; i < this.length; i++) {
            System.out.print(this.emails[i] + " ");
        }
    }
}