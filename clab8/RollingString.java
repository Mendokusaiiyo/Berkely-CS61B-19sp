/**
 * A String-like class that allows users to add and remove characters in the String
 * in constant time and have a constant-time hash function. Used for the Rabin-Karp
 * string-matching algorithm.
 */
class RollingString{

    /**
     * Number of total possible int values a character can take on.
     * DO NOT CHANGE THIS.
     */
    static final int UNIQUECHARS = 128;

    /**
     * The prime base that we are using as our mod space. Happens to be 61B. :)
     * DO NOT CHANGE THIS.
     */
    static final int PRIMEBASE = 6113;

    String aString;
    int fixedLength;

    /**
     * Initializes a RollingString with a current value of String s.
     * s must be the same length as the maximum length.
     */
    public RollingString(String s, int length) {
        assert(s.length() == length);
        /* FIX ME */
        aString = s;
        fixedLength = length;
    }

    /**
     * Adds a character to the back of the stored "string" and 
     * removes the first character of the "string". 
     * Should be a constant-time operation.
     */
    public void addChar(char c) {
        /* FIX ME */
        StringBuilder strd = new StringBuilder(aString);
        strd.append(c);
        strd.deleteCharAt(0);
        aString = strd.toString();
    }


    /**
     * Returns the "string" stored in this RollingString, i.e. materializes
     * the String. Should take linear time in the number of characters in
     * the string.
     */
    public String toString() {
        StringBuilder strb = new StringBuilder();
        /* FIX ME */
        for (int i = 0; i < aString.length(); i += 1) {
            strb.append(aString.charAt(i));
        }
        return strb.toString();
    }

    /**
     * Returns the fixed length of the stored "string".
     * Should be a constant-time operation.
     */
    public int length() {
        /* FIX ME */
        return fixedLength;
    }


    /**
     * Checks if two RollingStrings are equal.
     * Two RollingStrings are equal if they have the same characters in the same
     * order, i.e. their materialized strings are the same.
     */
    @Override
    public boolean equals(Object o) {
        /* FIX ME */
        if (this.toString().equals(o.toString())) {
            return true;
        }
        return false;
    }

    /**
     * Returns the hashcode of the stored "string".
     * Should take constant time.
     */
    @Override
    public int hashCode() {
        /* FIX ME */
        int h = 0;
        StringBuilder reversedStrb = new StringBuilder(aString).reverse();
        for (int i = 0; i < aString.length(); i += 1) {
            h += ((int)reversedStrb.charAt(i)) * UNIQUECHARS ^i % PRIMEBASE;
        }
        return h;
    }
}
