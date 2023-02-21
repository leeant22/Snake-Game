import java.awt.*;

public class Coin {
    private int x;
    private int y;
    private boolean check = true;

    // This method constructs a coin object.

    public Coin(Snake player, Apple food) {
        this.random(player, food);
    }

    // This method returns the x coordinate of the coin.
    // Returns:
    //      - int: the x coordinate of the coin

    public int getX() {
        return this.x;
    }

    // This method returns the y coordinate of the coin.
    // Returns:
    //      - int: the y coordinate of the coin

    public int getY() {
        return this.y;
    }

    // This method checks that the coordinates of where to spawn the coin is not
    // occupied by the food or the snake's body. If it is, it will generate a new
    // set of coordinates.
    // Parameters:
    //      - player: the snake object
    //      - food: the food object

    public void random(Snake player, Apple food) {
        while(check) {
            check = false;
            x = (int)(Math.random() * Game.width - 1);
            y = (int)(Math.random() * Game.height - 1);
            for (Rectangle r : player.getSnakeLength()) {
                if(r.x == x && r.y == y) {
                    check = true;
                }
            }
            if(x == food.getX() && y == food.getY()) {
                check = true;
            }
        }
        check = true;
    }
}
