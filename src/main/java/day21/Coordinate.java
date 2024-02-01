package day21;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Coordinate implements Iterable<Coordinate>{
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

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }

    public class Neighbor implements Iterator<Coordinate>{
        private int currentNeighbor = 0;

        @Override
        public boolean hasNext() {
            return currentNeighbor < 4;
        }

        @Override
        public Coordinate next() {
            if (!hasNext()) {
                throw  new NoSuchElementException();
            }
            switch (currentNeighbor++) {
                case 0: return up();
                case 1: return down();
                case 2: return left();
                default: return right();
            }
        }
    }

    @Override
    public Iterator<Coordinate> iterator() {
        return new Neighbor();
    }
}
