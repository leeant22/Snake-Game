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
    private Game game;

    // Constructs GameObject
    public GameObjects(Game game) {
        timer.start(); // Starts game timer
        this.state = "START";
        this.game = game;
        this.player = game.getPlayer();
        this.food = game.getApple();

        this.addKeyListener(game); // adds a keyListener to the game
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    // This method redraws the game window every 100 ms
    public void paintComponent(java.awt.Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.black); // sets background color to green
        graphics2D.fillRect(0, 0, Game.width * Game.dimension, Game.height * Game.dimension); // paints background color green
        if(state == "START") {
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawString("Press Any Key to Begin", Game.width / 2 * Game.dimension - 40, Game.height / 2 * Game.dimension - 20);
        }
        else if(state == "ACTIVE") {
            graphics2D.setColor(Color.red); // sets color to red
            graphics2D.fillRect(food.getX() * Game.dimension, food.getY() * Game.dimension, Game.dimension, Game.dimension); // paints red apple
            graphics2D.setColor(Color.blue); // sets color to blue
            for (Rectangle r : player.getSnakeLength()) { // paints blue snake
                graphics2D.fill(r);
            }
        }
        else {
            graphics2D.setColor(Color.white);
            graphics2D.drawString("Score: " + (player.getSnakeLength().size() - 3), Game.width / 2 * Game.dimension - 40, Game.height / 2 * Game.dimension - 20);
        }
    }

    // This method redraws the game window background everytime the snake moves
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        game.update();
    }
}
