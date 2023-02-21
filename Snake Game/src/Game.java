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
        Rectangle head = player.getSnakeLength().get(0); // gets the first rectangle of the snake or the head of the snake
        Rectangle r = new Rectangle(Game.dimension, Game.dimension);
        if (gameObject.state.equals("ACTIVE")) {
            if (checkFood()) {
                player.increaseSize();
                food.random(player);
            } else if (checkCoin()) {
                player.addLives();
                coin.random(player, food);
            } else if (checkWall() && player.getLives() == 0) {
                gameObject.state = "END";
            } else if (checkSelf() && player.getLives() == 0) {
                gameObject.state = "END";
            }
            else if((checkWall() && player.getLives() > 0) || (checkSelf() && player.getLives() > 0)) {
                player.removeLife();
                if (player.getDirection().equals("UP")) {
                    r.setLocation(head.x - Game.dimension, head.y);
                }
                else if (player.getDirection().equals("DOWN")) {
                    r.setLocation(head.x + Game.dimension, head.y);
                }
                else if (player.getDirection().equals("LEFT")) {
                    r.setLocation(head.x, head.y - Game.dimension);
                }
                else if(player.getDirection().equals("RIGHT")) {
                    r.setLocation(head.x, head.y + Game.dimension);
                }
                player.getSnakeLength().add(0, r);
                player.getSnakeLength().remove(player.getSnakeLength().size() - 1);
            }
            else {
                player.moveSnake();
            }
        }
    }

    public Coin getCoin() {
        return this.coin;
    }

    public Snake getPlayer() {
        return this.player;
    }

    public Apple getApple() {
        return this.food;
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

    private boolean checkWall() {
        if(player.getHeadX() < 0 || player.getHeadX() >= width * dimension || player.getHeadY() < 0 || player.getHeadY() >= height * dimension) {
            return true;
        }
        return false;
    }

    private boolean checkFood() {
        if(player.getHeadX() == food.getX() * dimension && player.getHeadY() == food.getY() * dimension) {
            count ++;
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

    private boolean checkCoin() {
        if(player.getHeadX() == coin.getX() * dimension && player.getHeadY() == coin.getY() * dimension) {
            count ++;
            player.addLives();
            return true;
        }
        return false;
    }
}
