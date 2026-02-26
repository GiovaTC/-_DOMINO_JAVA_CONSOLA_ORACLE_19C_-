package game;

public class DominoTile {
    private int left;
    private int right;

    public DominoTile(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public boolean matches(int value) {
        return left == value || right == value;
    }

    @Override
    public String toString() {
        return "[" + left + "|" + right + "]";
    }
}
