
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.geom.*;
import java.util.*;

public class RadioButtonChoiceForShapes extends JFrame {

    private static final int FW = 800;
    private static final int FH = 600;

    JRadioButton lineRB = new JRadioButton("Line", true);
    JRadioButton rectRB = new JRadioButton("Rectangle");
    JRadioButton CircleRB = new JRadioButton("Circle"); //Circle Radio BTN Creation
    public static void main(String[] args) {
        new RadioButtonChoiceForShapes();
    }

    public RadioButtonChoiceForShapes() {
        setSize(FW, FH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Radio Buttons Choice for Shapes");
        setLocationRelativeTo(null);

        setLayout(new BorderLayout()); //layout of frame

        // create panel called PaintPanel
        PaintPanel paintPanelObj = new PaintPanel(FW, FH);        

        // create panel called chooseShapePanel
        JPanel chooseShapePanel = new JPanel();
        chooseShapePanel.setBackground(Color.WHITE);
        chooseShapePanel.setLayout(new GridLayout(3, 1));
        
        chooseShapePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Shapes"));

        //Create a button group and add buttons
        ButtonGroup bgroup = new ButtonGroup();
        bgroup.add(lineRB);
        bgroup.add(rectRB);
        bgroup.add(CircleRB); //adding CircleRB to the Button group. note it's still not visible to us

        // add buttons to chooseShapePanel panel
        chooseShapePanel.add(lineRB);
        chooseShapePanel.add(rectRB);
        chooseShapePanel.add(CircleRB); //adding it to the panel so we can see it :D

        // add the paintPanelObj panel to CENTER of frame
        add(paintPanelObj, BorderLayout.CENTER);

        // add the chooseShapePanel panel to WEST of frame
        add(chooseShapePanel, BorderLayout.WEST);


        setVisible(true);
        pack();
    }

    private class PaintPanel extends JPanel {

        private int PW; // panel-width
        private int PH; // panel-height

        public PaintPanel(int W, int H) {
            PW = W;
            PH = H;
            setPreferredSize(new Dimension(PW, PH)); // set width & height of panel 
            setBorder(BorderFactory.createTitledBorder(
                    BorderFactory.createEtchedBorder(), "Canvas"));

            addMouseListener(new MouseClickListener());
        }
        boolean clicked = true;
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            if (lineRB.isSelected()) {
                g2.setColor(Color.red);
                g2.drawLine(0, 0, PW, PH);


            } else if (rectRB.isSelected()) {
                g2.setColor(Color.blue);
                g2.drawRect(200, 200, 300, 200);

            } else if(CircleRB.isSelected()){
                g2.setColor(Color.GREEN);
                g2.drawOval((PW-300)/2,(PH-300)/2,300,300);

            }

            lineRB.addActionListener(e->repaint());
            rectRB.addActionListener(e -> repaint());
            CircleRB.addActionListener(e->repaint());

        }

        private class MouseClickListener extends MouseAdapter {
            public void mouseClicked(MouseEvent event) {
                repaint();
            }
        }



    }

}
