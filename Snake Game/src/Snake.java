import java.util.*;
import java.awt.Rectangle;
public class Snake {
    private ArrayList<Rectangle> snakeLength;
    private int lives;
    private String direction;
    private final int width = Game.width;
    private final int height = Game.height;
    private final int dimension = Game.dimension;

    // Constructs a snake object out of Rectangles
    public Snake() {
        this.snakeLength = new ArrayList<Rectangle>(); // creates ArrayList object to represent snake body
        Rectangle r = new Rectangle(dimension, dimension); // creates a rectangle with a size of a 1x1 pixel in game window
        r.setLocation((width / 2) * dimension, (height / 2) * dimension); // sets location of rectangle to center
        snakeLength.add(r); // adds the rectangle to the snake's body

        r = new Rectangle(dimension, dimension);
        r.setLocation((width / 2 + 1) * dimension, (height / 2 + 1) * dimension);
        snakeLength.add(r);

        r = new Rectangle(dimension, dimension);
        r.setLocation((width / 2 + 2) * dimension, (height / 2 + 2) * dimension);
        snakeLength.add(r);

        this.direction = "NONE"; // gives the snake no direction to move in
    }

    // This method returns the x coordinate of the snake's head.
    // Returns:
    //      - int: the x coordinate of the snake's head

    public int getHeadX() {
        return snakeLength.get(0).x;
    }

    // This method returns the y coordinate of the snake's head.
    // Returns:
    //      - int: the y coordinate of the snake's head

    public int getHeadY() {
        return snakeLength.get(0).y;
    }

    // This method returns the snake's body.
    // Returns:
    //      - ArrayList<Rectangle>: the snake's body

    public ArrayList<Rectangle> getSnakeLength() {
        return this.snakeLength;
    }

    // This method sets the direction of the snake to up.

    public void goUp() {
        this.direction = "UP";
    }

    // This method sets the direction of the snake to down.

    public void goDown() {
        this.direction = "DOWN";
    }

    // This method sets the direction of the snake to left.

    public void goLeft() {
        this.direction = "LEFT";
    }

    // This method sets the direction of the snake to right.

    public void goRight() {
        this.direction = "RIGHT";
    }

    // This method returns the direction that the snake is currently traveling in.
    // Returns:
    //      - String: direction that snake is traveling

    public String getDirection() {
        return this.direction;
    }

    // This method allows the snake to change directions by adding a pixel (rectangle) / (head)
    // in the direction that the player wants and removing the last pixel.

    public void moveSnake() {
        if(!this.direction.equals("NONE")) {
            Rectangle head = snakeLength.get(0); // gets the first rectangle of the snake or the head of the snake
            Rectangle r = new Rectangle(Game.dimension, Game.dimension);
            if(direction.equals("UP")) {
                r.setLocation(head.x, head.y - Game.dimension); // creates a new rectangle for snake body 1 pixel up
            }
            else if(direction.equals("DOWN")) {
                r.setLocation(head.x, head.y + Game.dimension); // creates a new rectangle for snake body 1 pixel down
            }
            else if(direction.equals("LEFT")) {
                r.setLocation(head.x - Game.dimension, head.y); // creates a new rectangle for snake body 1 pixel left
            }
            else {
                r.setLocation(head.x + Game.dimension, head.y); // creates a new rectangle for snake body 1 pixel right
            }
            snakeLength.add(0, r); // adds the newly created rectangle to the head of the snake
            snakeLength.remove(snakeLength.size() - 1); // deletes a rectangle from the tail of the snake
        }
    }

    // This method increases the size of the snake by adding another pixel to the head.

    public void increaseSize() {
        Rectangle head = snakeLength.get(0); // gets the first rectangle of the snake or the head of the snake
        Rectangle r = new Rectangle(Game.dimension, Game.dimension);
        if(direction.equals("UP")) {
            r.setLocation(head.x, head.y - Game.dimension); // creates a new rectangle for snake body 1 pixel up
        }
        else if(direction.equals("DOWN")) {
            r.setLocation(head.x, head.y + Game.dimension); // creates a new rectangle for snake body 1 pixel down
        }
        else if(direction.equals("LEFT")) {
            r.setLocation(head.x - Game.dimension, head.y); // creates a new rectangle for snake body 1 pixel left
        }
        else {
            r.setLocation(head.x + Game.dimension, head.y); // creates a new rectangle for snake body 1 pixel right
        }
        snakeLength.add(0, r); // adds the newly created rectangle to the head of the snake
    }

    // This method returns the current score.
    // Returns:
    //      - int: the current score

    public int getScore() {
        return snakeLength.size() - 3;
    }

    // This method adds a life to the snake.

    public void addLives() {
        lives ++;
    }

    // This method returns the number of lives of the snake.
    // Returns:
    //      - int: the number of lives of the snake

    public int getLives() {
        return this.lives;
    }

    // This method removes a life from the snake.

    public void removeLife() {
        lives --;
    }
}
