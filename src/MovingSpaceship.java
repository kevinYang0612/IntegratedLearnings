import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;
/**
 * Draw a space where your spaceship will move around
 * implement for direction buttons(up, down, left, right)
 * that will move your spaceship within this space when they are clicked
 *
 * 1. create a frame
 * 2. draw a shape, personally, a rectangle
 * 3. draw a spaceship inside the rectangle
 * 4. implement up, down, left, right
 * 5. addActionListener for 4 buttons
 *
 * */
class Main
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            DrawFrame frame = null;
            try {
                frame = new DrawFrame();
            } catch (IOException e) {
                e.printStackTrace();
            }
            frame.setTitle("DrawTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

/**
 * A frame that contains a panel with drawings.
 */
class DrawFrame extends JFrame
{
    public DrawFrame() throws IOException {
        add(new DrawComponent());
        pack();
    }
}

/**
 * A component that displays rectangles and ellipses.
 */
class DrawComponent extends JPanel implements ActionListener, KeyListener
{
    private static final int DEFAULT_WIDTH = 1000;
    private static final int DEFAULT_HEIGHT = 1000;
    Timer t = new Timer(5, this);
    double x = 100, y = 100, velX = 0, velY = 0;

    BufferedImage spaceship;
    double leftX = 100, topY = 100, width = 600, height = 600;

    public DrawComponent() throws IOException {
        t.start();

        spaceship = ImageIO.read(Objects.requireNonNull(Main.class.getResourceAsStream("spaceship_icon.gif")));

        this.addKeyListener(this);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // draw a rectangle

        Rectangle2D rect = new Rectangle2D.Double(leftX, topY, width, height);
        g2.draw(rect);

        g2.setColor(Color.CYAN);
        // g2.fill(new Ellipse2D.Double(x, y, 40, 40));
        g2.drawImage(spaceship, (int)x, (int)y, null);
        g2.drawString("DrawTest", 350, 80);
    }

    public Dimension getPreferredSize()
    {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (x < 100 || x > 660)
        {
            velX = -velX;
        }
        if (y < 100 || y > 660)
        {
            velY = -velY;
        }
        repaint();
        x += velX;
        y += velY;
    }
    public void up()
    {
        velY = -1.5;
        velX = 0;
    }
    public void down()
    {
        velY = 1.5;
        velX = 0;
    }
    public void left()
    {
        velY = 0;
        velX = -1.5;
    }
    public void right()
    {
        velY = 0;
        velX = 1.5;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP)
        {
            up();
        }
        if (code == KeyEvent.VK_DOWN)
        {
            down();
        }
        if (code == KeyEvent.VK_LEFT)
        {
            left();
        }
        if (code == KeyEvent.VK_RIGHT)
        {
            right();
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN ||
                code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT)
        {
            this.velY = 0;
            this.velX = 0;
        }



    }
}