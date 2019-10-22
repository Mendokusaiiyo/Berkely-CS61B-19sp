package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    int N;
    int virtualTopSite1D;
    int virtualBottomSite1D;
    int numOfOpenSites = 0;
    boolean[][] array;
    WeightedQuickUnionUF uf;
    // Another Disjoint Set without connecting the virtual bottom site to avoid backwash.
    WeightedQuickUnionUF ufNoBottom;

    /** Creates N-by-N grid, with all sites initially blocked. */
    public Percolation(int N) {
        if (N < 0) {
            throw new IllegalArgumentException("N can not be negative");
        }
        this.N = N;
        virtualTopSite1D = N * N;
        virtualBottomSite1D = N * N + 1;
        array = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufNoBottom = new WeightedQuickUnionUF(N * N + 1);
    }

    /** Changes the 2-D site into 1-D. */
    private int xyTo1D(int row, int col) {
        return row * N + col;
    }

    /** Opens the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException("The index is out of the grid bound");
        }
        if (!isOpen(row, col)) {
            array[row][col] = true;
            numOfOpenSites += 1;
        }
        if (row == 0) {
            uf.union(virtualTopSite1D, xyTo1D(row, col));
            ufNoBottom.union(virtualTopSite1D, xyTo1D(row, col));
        }
        if (row == N - 1) {
            uf.union(virtualBottomSite1D, xyTo1D(row, col));
        }
        if (row - 1 >= 0 && isOpen(row - 1, col)) {
                uf.union(xyTo1D(row, col), xyTo1D(row - 1, col));
                ufNoBottom.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }
        if (row + 1 < N && isOpen(row + 1, col)) {
            uf.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            ufNoBottom.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
        if (col - 1 >= 0 && isOpen(row, col - 1)) {
            uf.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            ufNoBottom.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }
        if (col + 1 < N && isOpen(row, col + 1)) {
            uf.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            ufNoBottom.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }
    }

    /** Returns true if the site (row, col) is open, false otherwise */
    public boolean isOpen(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException("The index is out of the grid bound");
        }
        return array[row][col];
    }

    /** Returns true if the site(row, col) is full, false otherwise. */
    public boolean isFull(int row, int col) {
        return ufNoBottom.connected(xyTo1D(row, col), virtualTopSite1D);
    }

    /** Returns the number of open sites. */
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    /** Return true if the system percolates, false otherwise. */
    public boolean percolates() {
        return uf.connected(virtualTopSite1D, virtualBottomSite1D);
    }

}
