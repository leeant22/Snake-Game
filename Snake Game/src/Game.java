import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {
    private Snake player;
    private Apple food;
    private Coin coin;
    private GameObjects gameObject;
    private JFrame gameWindow;
    public int count = 0;
    public static final int height = 30;
    public static final int width = 30;
    public static final int dimension = 20;

    // Constructs Game object

    public Game() {
        this.gameWindow = new JFrame(); // creates window for game
        this.player = new Snake();
        this.food = new Apple(player);
        this.coin = new Coin(player, food);
        this.gameObject = new GameObjects(this);
        gameWindow.add(gameObject); // adds all game elements to window
        gameWindow.setTitle("Snake Game"); // sets title for game
        gameWindow.setSize(width * dimension + 2, height * dimension + 4); //sets size of game window
        gameWindow.setVisible(true); // makes game window visible
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // provides game window with exit button
    }

    // This method sets the state of the game to active to start the game

    public void startGame() {
        gameObject.state = "ACTIVE";
    }

    // This method updates the state of the game and keeps the game active as long as the state does not
    // change to end. The state will change to end if the snake runs into a wall or itself without any
    // extra lives left. If the snake runs into food, it will increase its size by 1 and will randomly
    // spawn another food item. If the snake dies with extra lives remaining, it will spawn in the middle
    // of the screen.

    public void update() {
        if (gameObject.state.equals("ACTIVE")) {
            if (checkFood()) {
                player.increaseSize();
                food.random(player);
            }
            else if (checkCoin()) {
                player.addLives();
                coin.random(player, food);
            }
            else if (checkWall() && player.getLives() == 0) {
                gameObject.state = "END";
            }
            else if (checkSelf() && player.getLives() == 0) {
                gameObject.state = "END";
            }
            else if((checkWall() && player.getLives() > 0) || (checkSelf() && player.getLives() > 0)) {
                player.removeLife();
                int temp = 0;
                for(Rectangle r : player.getSnakeLength()) {
                    r.setLocation(Game.width / 2 * Game.dimension - (40 + temp), Game.height / 2 * Game.dimension - 20);
                    temp ++;
                }
            }
            else {
                player.moveSnake();
            }
        }
    }

    // This method gets the coin (extra life).
    // Returns:
    //      - Coin: the coin object

    public Coin getCoin() {
        return this.coin;
    }

    // This method gets the player (snake).
    // Returns:
    //      - Snake: the snake object

    public Snake getPlayer() {
        return this.player;
    }

    // This method gets the apple (food).
    // Returns:
    //      - Apple: the apple object

    public Apple getApple() {
        return this.food;
    }

    // Disregard method - Only exists to allow keyPressed method to work
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    // This method detects when a key is pressed and changes the direction of the snake if the
    // game is actively running. If it is not running, it will start the game if a key is pressed.
    // Parameters:
    //      - key: key that is pressed by user
    public void keyPressed(KeyEvent key) {
        int keyCode = key.getKeyCode();
        if(gameObject.state.equals("ACTIVE")) {
            if (keyCode == KeyEvent.VK_W && !player.getDirection().equals("DOWN")) {
                player.goUp();
            }
            else if (keyCode == KeyEvent.VK_A && !player.getDirection().equals("RIGHT")) {
                player.goLeft();
            }
            else if (keyCode == KeyEvent.VK_D && !player.getDirection().equals("LEFT")) {
                player.goRight();
            }
            else if(keyCode == KeyEvent.VK_S && !player.getDirection().equals("UP")) {
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

    // This method checks if the snake's head has hit a wall.
    // Returns:
    //      - boolean: true if snake's head hits a wall false otherwise
    private boolean checkWall() {
        if(player.getHeadX() < 0 || player.getHeadX() >= width * dimension || player.getHeadY() < 0 || player.getHeadY() >= height * dimension) {
            return true;
        }
        return false;
    }

    // This method checks if the snake's head has hit a food item.
    // Returns:
    //      - boolean: true if snake's head hits a food item false otherwise

    private boolean checkFood() {
        if(player.getHeadX() == food.getX() * dimension && player.getHeadY() == food.getY() * dimension) {
            count ++;
            return true;
        }
        return false;
    }

    // This method checks if the snake's head has hit its own body.
    // Returns:
    //      - boolean: true if the snake's head hits its own body false otherwise

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

    // This method checks if the snake's head has hit a coin (extra life).
    // Returns:
    //      - boolean: true if the snake's head hits a coin false otherwise

    private boolean checkCoin() {
        if(player.getHeadX() == coin.getX() * dimension && player.getHeadY() == coin.getY() * dimension) {
            count ++;
            player.addLives();
            return true;
        }
        return false;
    }
}
