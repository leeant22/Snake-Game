import java.awt.*;

public class Apple {
    private int x;
    private int y;
    private boolean check = true;

    public Apple(Snake player) {
        this.random(player);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

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
    }
}
