public class RabinKarpAlgorithm {


    /**
     * This algorithm returns the starting index of the matching substring.
     * This method will return -1 if no matching substring is found, or if the input is invalid.
     */
    public static int rabinKarp(String input, String pattern) {
        int inputLength = input.length();
        int patternLength = pattern.length();
        if (inputLength < patternLength) {
            return -1;
        }
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i < patternLength; i += 1) {
            strb.append(input.charAt(i));
        }
        RollingString inputRolling = new RollingString(strb.toString(), patternLength);
        RollingString patternRolling = new RollingString(pattern, patternLength);
        int inputHash = inputRolling.hashCode();
        int patternHash = patternRolling.hashCode();
        for (int i = 0; i <= inputLength - patternLength; i+= 1) {
            if (patternHash == inputHash) {
                if (inputRolling.equals(patternRolling)) {
                    return i;
                }
            }
            if (i < inputLength - patternLength) {
                inputRolling.addChar(input.charAt(i + patternLength));
                inputHash = inputRolling.hashCode();
            }
        }

        return -1;
    }

}
