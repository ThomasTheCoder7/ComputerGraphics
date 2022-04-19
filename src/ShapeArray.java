import java.awt.*;

public class ShapeArray {
    private Shape[] as;
    private Color[] ac;
    private boolean[] filled;
    private int Capacity = 20;
    public int Size = 0;

    public ShapeArray()
    {
        as = new Shape[Capacity];
        ac = new Color[Capacity];
        filled = new boolean[Capacity];
    }

    public void add(Shape s,Color c,boolean fill)
    {

        if(Size >= Capacity - 5)//RUNNING OUT OF SPACE?... NO PROBLEM
        {
            increaseSize();
        }

        as[Size]=s;
        ac[Size]=c;
        filled[Size]=fill;
        Size++;

    }

    public void add(Shape s,Color c)
    {

        if(Size >= Capacity - 5)//RUNNING OUT OF SPACE?... NO PROBLEM
        {
            increaseSize();
        }

        as[Size]=s;
        ac[Size]=c;
        filled[Size]=false;
        Size++;

    }
    public void add(Shape s)
    {

        if(Size >= Capacity - 5)//RUNNING OUT OF SPACE ? NO PROBLEM
        {
            increaseSize();
        }

        as[Size]=s;
        ac[Size]=Color.BLUE;
        Size++;

    }

    public Shape getShape(int i){ return as[i]; } //returning shape

    public Color getColor(int i){ return ac[i]; } //returning color

    private void increaseSize()
    {
        Capacity*=2; //FULL CAPACITY !!!!! DOUBLE IT UP AND MAKE RAM CRY
        Shape[] temps = new Shape[Capacity];
        Color[] tempc = new Color[Capacity];

        for(int i = 0;i<as.length;i++){  //pass every elem into temp array
            temps[i]=as[i];
            tempc[i]=ac[i];
        }
        //pass temp array to main array BIGGER SIZE!!!!!!
        as=temps;
        ac=tempc;
    }

    public void clear(){
        Size=0;
    }

}
