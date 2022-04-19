import java.awt.*;

public class ShapeArray {
    private Shape[] as;
    private Color[] ac;
    MyShape[] a;
    private int Capacity = 10;
    public int size = 0;

    public ShapeArray()
    {
        a=new MyShape[Capacity];
    }
    public void set(Shape s, Color c, boolean fill, int i){
        a[i]=new MyShape(s,c,fill);
    }
    public void add(Shape s,Color c,boolean fill)
    {
        System.out.println("Capacity:"+Capacity+"  "+"Size:"+size);

        if(size >= Capacity)//RUNNING OUT OF SPACE?... NO PROBLEM
        {
            increaseSize();
        }

        a[size]=new MyShape(s,c,fill);
        size++;

    }

    public void add(Shape s,Color c)
    {
        System.out.println("Capacity:"+Capacity+"  "+"Size:"+size);
        if(size >= Capacity)//RUNNING OUT OF SPACE?... NO PROBLEM
        {
            increaseSize();
        }

        a[size]=new MyShape(s,c);
        size++;

    }
    public Shape getShape(int i){ return a[i].getS(); } //returning shape

    public Color getColor(int i){ return a[i].getC(); } //returning color

    public boolean getFilled(int i){ return a[i].isfilled(); }

    private void increaseSize()
    {
        Capacity*=2; //FULL CAPACITY !!!!! DOUBLE IT UP AND MAKE RAM CRY
        MyShape[] temp = new MyShape[Capacity];

        for(int i = 0;i<size;i++){  //pass every elem into temp array
            temp[i]=a[i];
        }
        //pass temp array to main array BIGGER SIZE!!!!!!
        a=temp;
    }

    public void clear(){
        size =0;
        decreaseSize();
    }


    public void decreaseSize(){
        Capacity = 10;
        a = new MyShape[Capacity];
    }
}
