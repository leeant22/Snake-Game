import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class GameObjects extends JPanel implements ActionListener {
    private Timer timer = new Timer(100, this); // Every 100 milliseconds, timer redraws game window
    public String state;
    private Snake player;
    private Apple food;
    private Coin coin;
    private Game game;

    // Constructs GameObject

    public GameObjects(Game game) {
        timer.start(); // Starts game timer
        this.state = "START";
        this.game = game;
        this.player = game.getPlayer();
        this.food = game.getApple();
        this.coin = game.getCoin();

        this.addKeyListener(game); // adds a keyListener to the game
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    // This method redraws the game window every 100 ms. While the game is running the game will
    // spawn a coin for every 5 food consumed. If the game has not started, the player will be
    // prompted to hit any key to start. Once the game ends, the user will be shown their score.
    // Parameters:
    //      - graphics: java.awt.Graphics library

    public void paintComponent(java.awt.Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.green); // sets background color to green
        graphics2D.fillRect(0, 0, Game.width * Game.dimension, Game.height * Game.dimension); // paints background color green
        if(state.equals("START")) {
            graphics2D.setColor(Color.white);
            graphics2D.drawString("Press Any Key to Begin", Game.width / 2 * Game.dimension - 40, Game.height / 2 * Game.dimension - 20);
        }
        else if(state.equals("ACTIVE")) {
            graphics2D.setColor(Color.red); // sets color to red
            graphics2D.fillRect(food.getX() * Game.dimension, food.getY() * Game.dimension, Game.dimension, Game.dimension); // paints red apple
            graphics2D.setColor(Color.blue); // sets color to blue
            for (Rectangle r : player.getSnakeLength()) { // paints blue snake
                graphics2D.fill(r);
            }
            if(game.count <= 5 && game.count % 5 == 0 && game.count != 0) {
                graphics2D.setColor(Color.yellow);
                graphics2D.fillRect(coin.getX() * Game.dimension, coin.getY() * Game.dimension, Game.dimension, Game.dimension);
            }
            else if(game.count > 5 && game.count % 6 - 5 == 0 && game.count != 0 && game.count != 6) {
                graphics2D.setColor(Color.yellow);
                graphics2D.fillRect(coin.getX() * Game.dimension, coin.getY() * Game.dimension, Game.dimension, Game.dimension);
            }
        }
        else {
            graphics2D.setColor(Color.white);
            graphics2D.drawString("Score: " + (player.getScore()), Game.width / 2 * Game.dimension - 40, Game.height / 2 * Game.dimension - 20);
        }
    }

    // This method redraws the game window background everytime the snake moves.
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        game.update();
    }
}
