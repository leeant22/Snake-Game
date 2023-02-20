import java.awt.*;

public class Apple {
    private int x;
    private int y;
    private boolean check = false;

    public Apple(Snake player) {
        random(player);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void random(Snake player) {
        while(!check) {
            x = (int)(Math.random() * Game.width);
            y = (int)(Math.random() * Game.height);
            for (Rectangle r : player.getSnakeLength()) {
                if(r.getX() == x && r.getY() == y) {
                    check = true;
                }
            }
        }
    }
}
