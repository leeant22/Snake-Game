import java.awt.*;

public class Apple {
    private int x;
    private int y;
    private boolean check = true;

    // This method creates an Apple object.

    public Apple(Snake player) {
        this.random(player);
    }

    // This method returns the x coordinate of the food.
    // Returns:
    //      - int: the x coordinate of the food

    public int getX() {
        return this.x;
    }

    // This method returns the y coordinate of the food.
    // Returns:
    //      - int: the y coordinate of the food

    public int getY() {
        return this.y;
    }

    // This method checks that the coordinates of where to spawn the food is not
    // occupied by the snake's body. If it is, it will generate a new set of coordinates.
    // Parameters:
    //      - player: the snake object

    public void random(Snake player) {
        while(check) {
            check = false;
            x = (int)(Math.random() * Game.width - 1);
            y = (int)(Math.random() * Game.height - 1);
            for (Rectangle r : player.getSnakeLength()) {
                if(r.x == x && r.y == y) {
                    check = true;
                }
            }
        }
        check = true;
    }
}
