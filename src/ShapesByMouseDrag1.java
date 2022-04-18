
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.geom.*;
import java.util.*;

public class ShapesByMouseDrag1 extends JFrame {

    private static final int FW = 800;
    private static final int FH = 600;

    private Point p1;
    private Point p2;

    JRadioButton lineRB = new JRadioButton("Line", true);
    JRadioButton rectRB = new JRadioButton("Rectangle");

    public static void main(String[] args) {
        new ShapesByMouseDrag1();
    }

    public ShapesByMouseDrag1() {
        setSize(FW, FH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Draw Graphics Objects by Mouse Drag");
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        PaintPanel paintPanelObj = new PaintPanel(FW, FH);
        add(paintPanelObj, BorderLayout.CENTER);

        JPanel chooseShapePanel = new JPanel();
        chooseShapePanel.setBackground(Color.WHITE);
        chooseShapePanel.setLayout(new GridLayout(2, 1));

        chooseShapePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Shapes"));

        //Create the radio buttons.
         //Create a button group and add buttons
        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(lineRB);
        bgroup.add(rectRB);

        // add buttons to chooseShapePanel panel
        chooseShapePanel.add(lineRB);
        chooseShapePanel.add(rectRB); 

        add(chooseShapePanel, BorderLayout.WEST);
        setVisible(true);
        pack();
    }

    private class PaintPanel extends JPanel {

        private int PW; // panel-width
        private int PH; // panel-height

        Collection<Shape> shapeList = new ArrayList<Shape>();

        public PaintPanel(int W, int H) {
            PW = W;
            PH = H;
            setPreferredSize(new Dimension(PW, PH)); // set width & height of panel 
            setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), "Canvas"));

            addMouseListener(new MouseClickListener());
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            g2.setStroke(new BasicStroke(2f));
            g2.setColor(Color.blue);

            if (lineRB.isSelected()) {
                makeLine();
            } else if (rectRB.isSelected()) {
                makeRectangle();
            }

            // draw all the shapes stored in the collection
            Iterator itr = shapeList.iterator();
            while (itr.hasNext()) {
                g2.draw((Shape) itr.next());
            }

            // reset
            p1 = null;
            p2 = null;

        }

        public void makeLine() {
            if (p1 != null && p2 != null) {
                shapeList.add(new Line2D.Double(p1.x, p1.y, p2.x, p2.y));
            }
        }

        public void makeRectangle() {
        int pntx = p1.x;
        int pnty = p1.y;
        if(pntx>p2.x){pntx = p2.x;}
        if(pnty>p2.y){pnty = p2.y;}

        shapeList.add(new Rectangle(pntx,pnty,Math.abs(p1.x-p2.x),Math.abs(p1.y-p2.y)));

        }

    }

    private class MouseClickListener extends MouseAdapter {

        public void mousePressed(MouseEvent event) {
            p1 = new Point(event.getX(), event.getY());
        }

        public void mouseReleased(MouseEvent event) {
            p2 = new Point(event.getX(), event.getY());
            repaint();
        }
    }

}
