package hw3.hash;

import java.util.LinkedList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        LinkedList<Integer> [] hashArray = new LinkedList[M];
            for (int i = 0; i < oomages.size(); i += 1) {
                int bucketNum = (oomages.get(i).hashCode() & 0x7FFFFFFF) % M;
                if (hashArray[bucketNum] == null) {
                    hashArray[bucketNum] = new LinkedList<>();
                }
                hashArray[bucketNum].add(oomages.get(i).hashCode());
            }
            for (LinkedList x : hashArray) {
                if (x == null) {
                    x = new LinkedList<>();
                }
                if ((x.size() > oomages.size() / 50) && (x.size() < oomages.size() / 2.5)) {
                    return true;
                }
            }
            return false;
    }
}
