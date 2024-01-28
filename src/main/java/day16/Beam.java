package day16;

import java.awt.*;

public class Beam {
    private Direction direction;

    private Coordinate position;

    public Beam(Direction direction, Coordinate position) {
        this.direction = direction;
        this.position = position;
    }

    public void move() {
        switch (direction) {
            case UP:
                up();
                break;
            case DOWN:
                down();
                break;
            case LEFT:
                left();
                break;
            case RIGHT:
                right();
                break;
        }
    }

    public void up() {
        position = position.up();
    }

    public void down() {
        position = position.down();
    }
    public void left() {
        position = position.left();
    }
    public void right() {
        position = position.right();
    }

    public void changeDirection(Direction newDirection) {
        direction = newDirection;
    }

    public Direction getDirection() {
        return direction;
    }

    public Coordinate getPosition() {
        return position;
    }
}
