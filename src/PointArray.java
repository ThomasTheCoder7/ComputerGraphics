import java.awt.*;

public class PointArray {
     private Point[] p;
     private int Capacity=20;
     public  int size=0;

    public PointArray() {
       p = new Point[Capacity];
    }

    public void add(int x,int y){

         if(size >= Capacity-5)
         {
             increaseSize();
         }

         p[size]=new Point(x,y);
         size++;
     }


     public int getx(int i){ return p[i].x; }
     public int gety(int i){ return p[i].y; }
     public void clear()   { size=0;        }

     private void increaseSize(){
         Capacity*=2;
         Point[] temp = new Point[Capacity];
         for(int i = 0;i<p.length;i++){
             temp[i]=p[i];
         }
         p=temp;
     }
}
