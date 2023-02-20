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
    public void reDraw(java.awt.Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setColor(Color.GREEN); // sets background color to green
        graphics2D.fillRect(0, 0, Game.width * Game.dimension, Game.height * Game.dimension); // paints background color green
        graphics2D.setColor(Color.RED); // sets color to red
        graphics2D.fillRect(food.getX() * Game.dimension, food.getY() * Game.dimension, Game.dimension, Game.dimension); // paints red apple
        graphics2D.setColor(Color.BLUE); // sets color to blue
        for(Rectangle r: player.getSnakeLength()) { // paints blue snake
            graphics2D.fill(r);
        }
    }

    // This method redraws the game window background everytime the snake moves
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
