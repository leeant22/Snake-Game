import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {
    private Snake player;
    private Apple food;
    private GameObjects gameObject;
    private JFrame gameWindow;
    public static final int height = 30;
    public static final int width = 30;
    public static final int dimension = 20;
    // Constructs Game object
    public Game() {
        this.gameWindow = new JFrame(); // creates window for game
        this.player = new Snake();
        this.food = new Apple(player);
        this.gameObject = new GameObjects(this);
        gameWindow.add(gameObject);
        gameWindow.setTitle("Snake Game"); // sets title for game
        gameWindow.setSize(width * dimension + 2, height * dimension + 4); //sets size of game window
        gameWindow.setVisible(true); // makes game window visible
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // provides game window with exit button
    }

    public void startGame() {
        gameObject.state = "ACTIVE";
    }

    public void update() {
        if(gameObject.state == "ACTIVE") {
            if(checkFood()) {
                player.increaseSize();
                food.random(player);
            }
            else if(checkWall()) {
                gameObject.state = "END";
            }
            else if(checkSelf()) {
                gameObject.state = "END";
            }
            else {
                player.moveSnake();
            }
        }
    }

    public Snake getPlayer() {
        return this.player;
    }

    public void setPlayer(Snake player) {
        this.player = player;
    }

    public Apple getApple() {
        return this.food;
    }

    public void setApple(Apple food) {
        this.food = food;
    }

    public JFrame getWindow() {
        return this.gameWindow;
    }

    public void setWindow(JFrame gameWindow) {
        this.gameWindow = gameWindow;
    }

    // Disregard method - Only exists to allow keyPressed method to work
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    // This method detects when a key is pressed and changes the direction of the snake
    // Parameter:
    //      - key: key that is pressed by user
    public void keyPressed(KeyEvent key) {
        int keyCode = key.getKeyCode();
        if(gameObject.state.equals("ACTIVE")) {
            if (keyCode == KeyEvent.VK_W && player.getDirection() != "DOWN") {
                player.goUp();
            }
            else if (keyCode == KeyEvent.VK_A && player.getDirection() != "RIGHT") {
                player.goLeft();
            }
            else if (keyCode == KeyEvent.VK_D && player.getDirection() != "LEFT") {
                player.goRight();
            }
            else if(keyCode == KeyEvent.VK_S && player.getDirection() != "UP"){
                player.goDown();
            }
        }
        else {
            this.startGame();
        }
    }

    // Disregard method - Only exists to allow keyPressed method to work
    @Override
    public void keyReleased(KeyEvent e) {
    }

    private boolean checkWall() {
        if(player.getHeadX() < 0 || player.getHeadX() >= width * dimension || player.getHeadY() < 0 || player.getHeadY() >= height * dimension) {
            return true;
        }
        return false;
    }

    private boolean checkFood() {
        if(player.getHeadX() == food.getX() * dimension && player.getHeadY() == food.getY() * dimension) {
            return true;
        }
        return false;
    }

    private boolean checkSelf() {
        int x = player.getHeadX();
        int y = player.getHeadY();
        for(int i = 1; i < player.getSnakeLength().size(); i ++) {
            if(x == player.getSnakeLength().get(i).x && y == player.getSnakeLength().get(i).y) {
                return true;
            }
        }
        return false;
    }
}
