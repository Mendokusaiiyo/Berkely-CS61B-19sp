import java.sql.Array;
import java.util.*;


/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    List<Bear> sortedBear;
    List<Bed> sortedBed;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        // TODO: Fix me.
        quickSort(bears, beds);
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        // TODO: Fix me.

        return sortedBear;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        // TODO: Fix me.
        return sortedBed;
    }


    private List<Bear> catenateBear(List<Bear> bear1, List<Bear> bear2) {
        List<Bear> catenatedBear = new LinkedList<>();
        for (Bear x : bear1) {
            catenatedBear.add(x);
        }
        for (Bear y: bear2) {
            catenatedBear.add(y);
        }
        return catenatedBear;
    }

    private List<Bed> catenateBed(List<Bed> bed1, List<Bed> bed2) {
        List<Bed> catenatedBed = new LinkedList<>();
        for (Bed x : bed1) {
            catenatedBed.add(x);
        }
        for (Bed y: bed2) {
            catenatedBed.add(y);
        }
        return catenatedBed;
    }

    private void partitionBear(List<Bear> unsorted, Bed pivot,
            List<Bear> less, List<Bear> equal, List<Bear> greater) {
        for (Bear x : unsorted) {
            if (x.compareTo(pivot) < 0) {
                less.add(x);
            }
            else if (x.compareTo(pivot) > 0) {
                greater.add(x);
            } else {
                equal.add(x);
            }
        }
    }

    private void partitionBed(List<Bed> unsorted, Bear pivot,
                                     List<Bed> less, List<Bed> equal, List<Bed> greater) {
        for (Bed x : unsorted) {
            if (x.compareTo(pivot) < 0) {
                less.add(x);
            }
            else if (x.compareTo(pivot) > 0) {
                greater.add(x);
            } else {
                equal.add(x);
            }
        }
    }

    private Pair<List<Bear>, List<Bed>> quickSort(List<Bear> bearList, List<Bed> bedList) {
        if (bearList.size() <= 1 || bedList.size() <= 1) {
            return new Pair<>(bearList, bedList);
        }
        Bed pivotOfbBears = bedList.get(0);
        List<Bear> lessBear = new LinkedList<>();
        List<Bear> equalBear = new LinkedList<>();
        List<Bear> greaterBear = new LinkedList<>();

        Bear pivotOfBeds;
        List<Bed> lessBed = new LinkedList<>();
        List<Bed> equalBed = new LinkedList<>();
        List<Bed> greaterBed = new LinkedList<>();
        partitionBear(bearList, pivotOfbBears, lessBear, equalBear, greaterBear);
        pivotOfBeds = equalBear.get(0);
        partitionBed(bedList, pivotOfBeds, lessBed, equalBed, greaterBed);
        Pair<List<Bear>, List<Bed>> lessPair = quickSort(lessBear, lessBed);
        Pair<List<Bear>, List<Bed>> greaterPair = quickSort(greaterBear, greaterBed);
        sortedBear = catenateBear(lessPair.first(), equalBear);
        sortedBear = catenateBear(sortedBear, greaterPair.first());
        sortedBed = catenateBed(lessPair.second(), equalBed);
        sortedBed = catenateBed(sortedBed, greaterPair.second());
        return new Pair<>(sortedBear, sortedBed);
    }
}
