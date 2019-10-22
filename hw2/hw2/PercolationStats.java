package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    int experimentTime;
    PercolationFactory aPF;
    double mean;
    double stddev;
    double [] x; // The fraction of open sites.
    /** Performs T independent experiments on an N-by-N grid. */
    public PercolationStats(int N , int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("the number of grid or the time of experiment is illegal");
        }
        experimentTime = T;
        x = new double[T];
        for (int i = 0; i < T; i += 1) {
            Percolation grid = aPF.make(N);
            while (!grid.percolates()) {
                grid.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }
            x[i] = grid.numOfOpenSites / grid.N * grid.N;
        }
        mean = StdStats.mean(x);
        stddev = StdStats.stddev(x);
    }

    /** Sample mean of percolation threshold. */
    public double mean() {
        return mean;
    }

    /** Sample standard deviation of percolation threshold. */
    public double stddev() {
        return stddev;
    }

    /** Low endpoint of 95% confidence interval. */
    public double confidenceLow() {
        return mean - (1.96 * stddev) / (Math.sqrt(experimentTime));
    }

    /** High endpoint of 95% confidence interval. */
    public double confidenceHigh() {
        return mean + (1.96 *   stddev) / (Math.sqrt(experimentTime));
    }
}
