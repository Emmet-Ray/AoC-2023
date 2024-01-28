package day16;

import day16.Direction.*;

import javax.lang.model.element.UnknownAnnotationValueException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

public class Contraption {

    private char[][] grid;
    private Energized[][] energized;


    private final Queue<Beam> beams = new ArrayDeque<>();

    public Contraption(String file) throws IOException{
        BufferedReader reader = new BufferedReader(new FileReader(file));
        int rowLen = (int) reader.lines().count();
        reader = new BufferedReader(new FileReader(file));
        String currentLine = reader.readLine();
        int colLen = currentLine.length();

        grid = new char[rowLen][colLen];
        energized = new Energized[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                energized[i][j] = new Energized();
            }

        }

        int rowIndex = 0;
        while (currentLine != null) {
            grid[rowIndex] = currentLine.toCharArray();
            rowIndex++;
            currentLine = reader.readLine();
        }
        Beam initialBeam = new Beam(Direction.RIGHT, new Coordinate(0, 0));
        beams.add(initialBeam);
    }

    public int simulate() {
        while (!beams.isEmpty()) {
            Beam currentBeam = beams.remove();
            while (beamIsInGrid(currentBeam)) {
                Coordinate currentPosition = currentBeam.getPosition();
                Direction currentDirection = currentBeam.getDirection();
                if (alreadyTraverse(currentPosition, currentDirection)) {
                    break;
                }
                setEnergized(currentPosition, currentDirection);
                char currentSymbol = symbolAt(currentPosition);

                switch (currentSymbol) {
                    case '.': {
                        currentBeam.move();
                        break;
                    }
                    case '\\', '/': {
                        reflect(currentBeam, currentSymbol);
                        break;
                    }
                    case '-', '|': {
                        splitter(currentBeam, currentSymbol);
                        break;
                    }
                    default: {
                        throw new RuntimeException();
                    }
                }
            }
            //showEnergized();
        }
        return countEnergizedTiles();
    }

    private void showEnergized() {
        for (int i = 0; i < energized.length; i++) {
            for (int j = 0; j < energized[0].length; j++) {
                Energized current = energized[i][j];
                if (current.isEnergnized()) {
                    System.out.print('#');
                } else {
                    System.out.print('.');
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------------------------------");
    }

    private boolean alreadyTraverse(Coordinate coordinate, Direction direction) {
        Energized current = energized[coordinate.getRow()][coordinate.getCol()];
        return current.alreadyTraverse(direction);
    }

    private void splitter(Beam beam, char splitter) {
        // -    |
        Direction direction = beam.getDirection();
        if ((direction == Direction.DOWN || direction == Direction.UP) && splitter == '|'
                || (direction == Direction.RIGHT || direction == Direction.LEFT) && splitter == '-') {
            beam.move();
            return;
        }

        if ((direction == Direction.RIGHT || direction == Direction.LEFT) && splitter == '|') {
            beams.add(new Beam(Direction.UP, beam.getPosition().up()));
            beam.down();
            beam.changeDirection(Direction.DOWN);
        } else if ((direction == Direction.UP || direction == Direction.DOWN) && splitter == '-') {
            beams.add(new Beam(Direction.LEFT, beam.getPosition().left()));
            beam.right();
            beam.changeDirection(Direction.RIGHT);
        }
    }
    private void reflect(Beam beam, char mirror) {
        //  /   \
        Direction direction = beam.getDirection();
        if (direction == Direction.RIGHT && mirror == '\\' || direction == Direction.LEFT && mirror == '/') {
            beam.down();
            beam.changeDirection(Direction.DOWN);
        } else if (direction == Direction.RIGHT && mirror == '/' || direction == Direction.LEFT && mirror == '\\') {
            beam.up();
            beam.changeDirection(Direction.UP);
        } else if (direction == Direction.DOWN && mirror == '/' || direction == Direction.UP && mirror == '\\') {
            beam.left();
            beam.changeDirection(Direction.LEFT);
        } else if (direction == Direction.DOWN && mirror == '\\' || direction == Direction.UP && mirror == '/') {
            beam.right();
            beam.changeDirection(Direction.RIGHT);
        }
    }



    private char symbolAt(Coordinate coordinate) {
        return grid[coordinate.getRow()][coordinate.getCol()];
    }

    private void setEnergized(Coordinate coordinate, Direction direction) {
        Energized current = energized[coordinate.getRow()][coordinate.getCol()];
        current.setEnergnized(true);
        switch (direction) {
            case UP: {
                current.setUp(true);
                break;
            }
            case DOWN: {
                current.setDown(true);
                break;
            }
            case LEFT: {
                current.setLeft(true);
                break;
            }
            case RIGHT: {
                current.setRight(true);
                break;
            }
        }
    }

    private boolean beamIsInGrid(Beam beam) {
        return validCoordinate(beam.getPosition());
    }
    private boolean validCoordinate(Coordinate coordinate) {
        return validRow(coordinate.getRow()) && validCol(coordinate.getCol());
    }
    private boolean validRow(int row) {
        return row >= 0 && row < grid.length;
    }
    private boolean validCol(int col) {
        return col >= 0 && col < grid[0].length;
    }

    private int countEnergizedTiles() {
        int result = 0;
        for (int i = 0; i < energized.length; i++) {
            for (int j = 0; j < energized[0].length; j++) {
                if (energized[i][j].isEnergnized()) {
                    result++;
                }
            }
        }
        return result;
    }

    public static int result(String file) throws IOException {
        Contraption contraption = new Contraption(file);
        int result = contraption.simulate();
        return result;
    }
}
