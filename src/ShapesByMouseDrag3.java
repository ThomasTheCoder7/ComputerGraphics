
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.util.*;

public class ShapesByMouseDrag3 extends JFrame {

    private static final int FW = 800;
    private static final int FH = 600;

    private Point p1;
    private Point p2;

    JRadioButton lineRB = new JRadioButton("Line", true);
    JRadioButton rectRB = new JRadioButton("Rectangle");
    JRadioButton polyRB = new JRadioButton("Polygon");
    JButton Colorchangebtn = new JButton("Change Color");
    public static void main(String[] args) {
        new ShapesByMouseDrag3();
    }

    public ShapesByMouseDrag3() {
        setSize(FW, FH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Draw Graphics Objects by Mouse Drag");
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        PaintPanel paintPanelObj = new PaintPanel(FW, FH);

        JPanel chooseShapePanel = new JPanel();
        chooseShapePanel.setBackground(Color.WHITE);
        chooseShapePanel.setLayout(new GridLayout(4, 1));

        chooseShapePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Shapes"));

         //Create the radio buttons.
         //Create a button group and add buttons

        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(lineRB);
        bgroup.add(rectRB);
        bgroup.add(polyRB);
        bgroup.add(Colorchangebtn);

        // add buttons to chooseShapePanel panel
        chooseShapePanel.add(lineRB);
        chooseShapePanel.add(rectRB);
        chooseShapePanel.add(polyRB);
        chooseShapePanel.add(Colorchangebtn);
        add(paintPanelObj, BorderLayout.CENTER);
        add(chooseShapePanel, BorderLayout.WEST);
        setVisible(true);
        pack();
    }

    private class PaintPanel extends JPanel {

        private int PW; // panel-width
        private int PH; // panel-height

        Collection<Shape> shapeList = new ArrayList<Shape>();
        ShapeArray shapeArray = new ShapeArray();

        public PaintPanel(int W, int H) {
            PW = W;
            PH = H;
            setPreferredSize(new Dimension(PW, PH)); // set width & height of panel 
            setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), "Canvas"));

            addMouseListener(new MouseClickListener());
            ColorChangeButton();

        }

        Color c = Color.BLUE;
        public void ColorChangeButton()
        {
            Colorchangebtn.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                   JColorChooser jColorChooser = new JColorChooser();
                   c = JColorChooser.showDialog(null,"COLORS",Color.GREEN);
                }
            });
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
            } else if(polyRB.isSelected()){
                makePolygon();
            }

            // draw all the shapes stored in the collection
            for(int i = 0;i<shapeArray.Size;i++)
            {
                g2.setColor(shapeArray.getColor(i));
                g2.draw(shapeArray.getShape(i));
            }

            // reset
            p1 = null;
            p2 = null;

        }

        public void makeLine() {
            if (p1 != null && p2 != null) {
                shapeArray.add(new Line2D.Double(p1.x, p1.y, p2.x, p2.y),c);
            }
        }

        public void makeRectangle() {
            int pntx = p1.x,pnty = p1.y;
            if(p1.x>=p2.x){pntx=p2.x;}
            if(p1.y>=p2.y){pnty=p2.y;}
            shapeArray.add(new Rectangle(pntx,pnty,Math.abs(p2.x-p1.x),Math.abs(p2.y-p1.y)),c);

        }

        public void makeOval()
        {
            int pntx = p1.x,pnty = p1.y;
            if(p1.x>=p2.x){pntx=p2.x;}
            if(p1.y>=p2.y){pnty=p2.y;}
            shapeArray.add(new Ellipse2D.Double(pntx,pnty,Math.abs(p2.x-p1.x),Math.abs(p2.y-p1.y)),c);
        }



        boolean samepoly = false; //determine if it is the same polygon or another one (selected//not selected)
        Collection<Integer> pntsx = new ArrayList<>();    // x points
        Collection<Integer> pntsy = new ArrayList<>();    // y points
        public void makePolygon(){
            if(samepoly){



            }
            else
            {
                pntsx.clear();
                pntsy.clear();
                samepoly=true;
                makePolygon();
            }
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
