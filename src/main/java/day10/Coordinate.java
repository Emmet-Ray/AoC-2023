package day10;

public class Coordinate {

    private int row;
    private int col;
    public Coordinate(int r, int c) {
        row = r;
        col = c;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Coordinate left() {
        return new Coordinate(row, col - 1);
    }

    public Coordinate right() {
        return new Coordinate(row, col + 1);
    }

    public Coordinate up() {
        return new Coordinate(row - 1, col);
    }

    public Coordinate down() {
        return new Coordinate(row + 1, col);
    }

    @Override
    public boolean equals(Object o) {
        Coordinate that = (Coordinate) o;
        return row == that.row && col == that.col;
    }

}
