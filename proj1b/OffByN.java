public class OffByN implements CharacterComparator {
    private int off;
    public OffByN(int N){
        off = N;
    }

    public boolean equalChars(char x, char y) {
        if (Math.abs(x - y) == off) {
            return true;
        }
        return false;
    }
}
