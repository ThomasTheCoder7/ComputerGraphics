import java.awt.*;

public class MyShape {
    Shape s;
    boolean isfilled;
    Color c;

    public MyShape(Shape s,Color c,boolean isfilled){
        this.s=s;
        this.isfilled=isfilled;
        this.c=c;
    }

    public MyShape(Shape s,Color c){
        this.s = s;
        this.c = c;
        this.isfilled = false;
    }

    public Shape getS() {
        return s;
    }


    public boolean isfilled() {
        return isfilled;
    }


    public Color getC() {
        return c;
    }

}
