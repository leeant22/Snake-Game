import java.awt.*;

public class Coin {
    private int x;
    private int y;
    private boolean check = true;
    public Coin(Snake player, Apple food) {
        this.random(player, food);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
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
