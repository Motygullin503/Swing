
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Shape;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class JPaintingPanel extends JPanel implements ActionListener{

    private Color backgroundColor = Color.white;
    private Graphics2D g2d;
    private Color color = Color.decode("#000000");
    private int x1, x2, y1, y2;
    private int radius = 10;
    BufferedImage image = null;
    private BufferedImage background;

    private Ellipse2D ball = new Ellipse2D.Double(200, 200, 100, 100);
    private Rectangle rectangle2D = new Rectangle(200, 200, 100, 100);
    private double speed = 10.0;
    private int xDirection = 1;
    private int yDirection = 1;
    Timer t;


    public JPaintingPanel() throws IOException {


        setOpaque(true);
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
            }

        });

        addMouseMotionListener(new MouseMotionAdapter() {

            public void mouseDragged(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();

                if (g2d != null) {
                    g2d.drawLine(x1, y1, x2, y2);
                    repaint();
                    x1 = x2;
                    y1 = y2;
                }
            }
        });

        addComponentListener(new ComponentListener() {
            public void componentResized(ComponentEvent e) {
                if (image!=null){
                    try {
                        setBackgroundImage(image);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

            public void componentMoved(ComponentEvent e) {

            }

            public void componentShown(ComponentEvent e) {

            }

            public void componentHidden(ComponentEvent e) {

            }
        });

        t = new Timer(20, this);
        t.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (background == null) {
            background = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_ARGB);
            g2d = (Graphics2D) background.getGraphics();
            g2d.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

            g2d.setColor(Color.RED);
            g2d.fill(rectangle2D);
            g2d.setColor(Color.black);
        }
        g.drawImage(background, 0, 0, null);

    }


    public void setRadius(int value) {
        g2d.setStroke(new BasicStroke(value, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        g2d.setColor(color);
    }


    protected void drawDot(Point p, int width, int height) {
        if (background == null) {
            repaint();
        }

        if (background != null) {
            Graphics2D g2d = background.createGraphics();
            g2d.setColor(color);
            g2d.fillOval(p.x - 5, p.y - 5, width, height);
            g2d.dispose();
        }
        repaint();
    }

    public void setBackgroundImage(BufferedImage image) throws IOException {
        this.image = image;
        g2d.drawImage(this.image.getScaledInstance(getSize().width,getSize().height, Image.SCALE_SMOOTH), 0, 0, this);
        repaint();
    }





    public void clear() {
        g2d.setColor(Color.white);
        g2d.fillRect(0, 0, getSize().width, getSize().height);
        g2d.setColor(Color.black);
        repaint();
    }


    public void actionPerformed(ActionEvent e) {
        rectangle2D.x += xDirection*speed;
        rectangle2D.y += yDirection*speed;

        if (rectangle2D.x<0){
            xDirection = 1;
            rectangle2D.x = 0;
        } else if(rectangle2D.x>getWidth()-rectangle2D.getBounds().width){
            xDirection = -1;
            rectangle2D.x = getWidth()-rectangle2D.getBounds().width;
        }

        if (rectangle2D.y<0){
            yDirection = 1;
            rectangle2D.y = 0;
        } else if(rectangle2D.y>getHeight()-rectangle2D.getBounds().height){
            yDirection = -1;
            rectangle2D.y = getHeight()-rectangle2D.getBounds().height;
        }


        repaint();
    }
}
