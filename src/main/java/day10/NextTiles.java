package day10;

public class NextTiles {
   private Coordinate first;
    private Coordinate second;

   public NextTiles(Coordinate first, Coordinate second) {
        this.first = first;
        this.second = second;
   }

    public static NextTiles nextTiles(char[][] maze, Coordinate current) {
        char currentSymbol = maze[current.getRow()][current.getCol()];
        Coordinate first = null, second = null;
        switch (currentSymbol) {
            case 'S': {
                break;
            }
            case '|': {
                first = current.up();
                second = current.down();
                break;
            }
            case '-': {
                first = current.left();
                second = current.right();
                break;
            }
            case '7': {
                first = current.left();
                second = current.down();
                break;
            }
            case 'F': {
                first = current.right();
                second = current.down();
                break;
            }
            case 'J': {
                first = current.left();
                second = current.up();
                break;
            }
            case 'L': {
                first = current.right();
                second = current.up();
                break;
            }
            default: {
                throw new UnknownError("couldn't interpret symbol " + currentSymbol);
            }
        }

        return new NextTiles(first, second);
    }

    public Coordinate getFirst() {
        return first;
    }

    public Coordinate getSecond() {
        return second;
    }
}
