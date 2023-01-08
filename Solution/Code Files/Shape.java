//import javax.swing.*; // DELETE
import java.awt.*;
//import java.awt.geom.GeneralPath; // DELETE


public abstract class Shape {
    protected Color color;
    protected boolean filled;
    protected int xCenter;
    protected int yCenter;

    protected BoundingBox boundBox;

    public Shape(Color color,boolean filled, int xCenter, int yCenter){
        this.color = color;
        this.filled = filled;
        this.xCenter = xCenter;
        this.yCenter = yCenter;
    }

    public abstract void drawShape(Graphics g);

    public void toggleFilled(){
        if(filled)
        filled = false;
        else
        filled = true;
    }

    public boolean rightClick(){
        return false;
    }

    public void drawBoundBox(Graphics g){
        g.setColor(Color.red);
        int startPointx = boundBox.bottomLeft.x;
        int startPointy = boundBox.topRight.y;
        int width = boundBox.topRight.x-boundBox.bottomLeft.x;
        int height = boundBox.bottomLeft.y-boundBox.topRight.y;
        g.drawRect(startPointx,startPointy,width,height);
    }

    public void drawName(Graphics g){
        g.setColor(Color.black);
        Font textFont = new Font("Arial", Font.BOLD, 12);

        g.setFont(textFont);
        g.drawString(this.getClass().getSimpleName(),xCenter,yCenter);
    }

    public String toString(){
        return String.format("%s | Center Point = (%d,%d) , Color = %s , Filled = %b ~()> ",this.getClass().getSimpleName(),xCenter,yCenter,color,filled);
    }
}

// ------------------------------------

class BoundingBox{
    Point bottomLeft;
    Point topRight;

    public BoundingBox(Point bottomLeft,Point topRight){
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }
}

class Point{
    int x=0;
    int y=0;

    public Point(){

    }

    public Point(int x,int y){
        this.x = x;
        this.y = y;
    }
}

// ------------------------------------



class Circle extends Shape{
    private int radius;

    public Circle(Color color,boolean filled, int xCenter, int yCenter, int radius){
        super(color, filled, xCenter, yCenter);
        this.radius = radius;

        this.boundBox = new BoundingBox(new Point(xCenter-(radius/2),yCenter+(radius/2)),new Point(xCenter+(radius/2),yCenter-(radius/2)));
    }
    public void drawShape(Graphics g){
        g.setColor(color);
        int startPointx = xCenter - radius/2;
        int startPointy = yCenter - radius/2;

        if(!filled)
        g.drawOval(startPointx,startPointy,radius,radius);
        else
        g.fillOval(startPointx,startPointy,radius,radius);
    }

    public String toString(){
        String s1 = super.toString();
        String s2 = String.format("Radius = %d",radius);
        String s3 = s1+s2;
        return s3;
    }
}





class Rectangle extends Shape{
    protected int width;
    protected int height;
    
    public Rectangle(Color color,boolean filled, int xCenter, int yCenter, int width, int height){
        super(color, filled, xCenter, yCenter);
        this.width = width;
        this.height = height;

        this.boundBox = new BoundingBox(new Point(xCenter-(width/2),yCenter+(height/2)),new Point(xCenter+(width/2),yCenter-(height/2)));
    }
    public void drawShape(Graphics g){
        g.setColor(color);
        int startPointx = xCenter - width/2;
        int startPointy = yCenter - height/2;

        if(!filled)
        g.drawRect(startPointx,startPointy,width,height);
        else
        g.fillRect(startPointx,startPointy,width,height);
    }

    public boolean rightClick(){
        moveTenUnits();
        return true;
    }

    public void moveTenUnits(){
        xCenter = xCenter + 10;
        boundBox.bottomLeft.x += 10;
        boundBox.topRight.x += 10;
    }

    public String toString(){    
        String s1 = super.toString();
        String s2 = String.format("Width = %d , Height = %d",width,height);
        String s3 = s1+s2;
        return s3;
    }
}



class Square extends Rectangle{

    public Square(Color color,boolean filled, int xCenter, int yCenter, int side){
        super(color,filled,xCenter,yCenter,side,side);
    }

    public String toString(){
        return String.format("%s | Center Point = (%d,%d) , Color = %s , Filled = %b ~()> Side = %d",this.getClass().getSimpleName(),xCenter,yCenter,color,filled, width); // HERE! DELETE 
    }
}







class Quadrilateral extends Shape{
    
    private Point points[] = new Point[4];
    
    public Quadrilateral(Color color,boolean filled, Point centerPoint, Point points[]){
        super(color, filled, centerPoint.x, centerPoint.y);
        Point bottomLeft = new Point();
        Point topRight = new Point();
        Point topLeft = new Point();
        Point bottomRight = new Point();

        for(Point point : points){
            if( point.x <= centerPoint.x && point.y >= centerPoint.y){
                bottomLeft = point;
            }
            if(point.x >= centerPoint.x && point.y <= centerPoint.y){
                topRight = point;
            }
            //Now others 
            if(point.x <= centerPoint.x && point.y <= centerPoint.y){
                topLeft = point;
            }
            if(point.x >= centerPoint.x && point.y >= centerPoint.y){
                bottomRight = point;
            }
        }

        this.points[0] = topLeft;
        this.points[1] = topRight;
        this.points[2] = bottomRight;
        this.points[3] = bottomLeft;


       boundingBoxSet(points);
    }

    public Quadrilateral(Color color,boolean filled, Point centerPoint, Point p1, Point p2, Point p3, Point p4){
        super(color, filled, centerPoint.x, centerPoint.y);
        Point bottomLeft = new Point();
        Point topRight = new Point();
        Point topLeft = new Point();
        Point bottomRight = new Point();

        Point temp[] = {p1, p2, p3, p4};

        for(Point point : temp){
            //System.out.printf("\npoint = (%d,%d)",point.x,point.y);// DELETE
            if( point.x <= centerPoint.x && point.y >= centerPoint.y){
                bottomLeft = point;
            }
            if(point.x >= centerPoint.x && point.y <= centerPoint.y){
                topRight = point;
            }
            //Now others 
            if(point.x <= centerPoint.x && point.y <= centerPoint.y){
                topLeft = point;
            }
            if(point.x >= centerPoint.x && point.y >= centerPoint.y){
                bottomRight = point;
            }
        }

        this.points[0] = topLeft;
        this.points[1] = topRight;
        this.points[2] = bottomRight;
        this.points[3] = bottomLeft;

        boundingBoxSet(points);
    }

    public Quadrilateral(Color color,boolean filled, Rectangle rectangle){
      
        super(color, filled, rectangle.xCenter, rectangle.yCenter);
        int xc = rectangle.xCenter;
        int yc = rectangle.yCenter;
        int w = rectangle.width;
        int h = rectangle.height;

        this.points[0] = new Point(xc-w,yc-h);
        this.points[1] = new Point(xc+w,yc-h);
        this.points[2] = new Point(xc+w,yc+h);
        this.points[3] = new Point(xc-w,yc+h);
        
        boundBox = new BoundingBox(points[3],points[1]);
    }




    public void drawShape(Graphics g){
        g.setColor(color);

        Polygon poly = new Polygon();
        for ( int i=0; i<points.length;i++)
        poly.addPoint(points[i].x, points[i].y);

        if(!filled)
        g.drawPolygon(poly);
        else
        g.fillPolygon(poly);

    }

    public boolean rightClick(){
        return rotateNinetyDegrees();
    
    }

    private boolean rotateNinetyDegrees(){
        int disX, disY;

        Point temp[] = new Point[4];

        for(int i=0; i<temp.length;i++)
        temp[i] = new Point();

        for(int i=0; i<temp.length; i++){
            int j = i+1;
        
            disX = xCenter - points[i].x;
            disY = yCenter - points[i].y;
            
            if(j>3)
            j=0;
            
            temp[j].x = xCenter + disY;
            temp[j].y = yCenter - disX;
        }

        for(int i=0; i<temp.length;i++){
            points[i] = temp[i];     
        }
        
        boundingBoxSet(points);


        return true;
    }


    public String toString(){
        String s1 = super.toString();
        String s2 = String.format("Points = [ (%d,%d), (%d,%d), (%d,%d), (%d,%d) ]",points[0].x,points[0].y,points[1].x,points[1].y,points[2].x,points[2].y,points[3].x,points[3].y);
        String s3 = s1+s2;
        return s3;
    }


    // --------------------

    private void boundingBoxSet(Point points[]){
        // To determine Bounding Box
        int x_min = points[0].x, x_max=points[1].x;
        int y_min = points[3].y, y_max = points[2].y;
        for (int i=0; i<points.length;i++){
            if(points[i].x<x_min)
                x_min = points[i].x;

            if(points[i].y<y_min)
            y_min = points[i].y;

            if(points[i].x>x_max)
            x_max = points[i].x;

            if(points[i].y>y_max)
            y_max = points[i].y;
        }

        Point bottomLeft = new Point(x_min,y_max);
        Point topRight = new Point(x_max,y_min);

        this.boundBox = new BoundingBox(bottomLeft,topRight);
    }
}