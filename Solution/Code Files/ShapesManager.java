import java.awt.*;
import java.util.*;



public class ShapesManager {
    public boolean inRange(int x, int a, int b){
        if (x>=a && x<=b)
        return true;
        else
        return false;
    }

    ArrayList<Shape> shapes;
    private boolean boundBoxDisplay = false;
    private boolean nameDisplay = false;

    public ShapesManager(){
        shapes = new ArrayList<>();
    }
    
    public void addShape(Shape shape){
        shapes.add(shape);
    }

    public void drawShapes(Graphics graphicsContext){
        for(Shape shape : shapes){
            shape.drawShape(graphicsContext);
            
            if(boundBoxDisplay)
            shape.drawBoundBox(graphicsContext);

            if(nameDisplay){
                shape.drawName(graphicsContext);
            }
        
        }
    }

    public boolean leftClick(int targetX,int targetY){
        // Title : Toggles Color
        boolean indicator = false;
        //System.out.printf("\n[ = ] shapes array size : %d",shapes.size()); // DELETE
        for(Shape shape : shapes){
            Point bottomLeft = shape.boundBox.bottomLeft;
            Point topRight = shape.boundBox.topRight;
            //System.out.printf("\n\t bottomLeft(%d,%d) and topRight(%d,%d)",bottomLeft.x,bottomLeft.y,topRight.x,topRight.y); // DELETE
            if(inRange(targetX, bottomLeft.x, topRight.x)&&inRange(targetY,topRight.y, bottomLeft.y)){
                shape.toggleFilled();
                indicator = true;
            }
        }
        return indicator;
    }

    public boolean rightClick(int targetX,int targetY){
        // Title : Performs Unique Right Click action 
        boolean indicator = false;
        

        //System.out.printf("\n[ = ] shapes array size : %d",shapes.size()); // DELETE
        for(Shape shape : shapes){
            Point bottomLeft = shape.boundBox.bottomLeft;
            Point topRight = shape.boundBox.topRight;
            //System.out.printf("\n\t bottomLeft(%d,%d) and topRight(%d,%d)",bottomLeft.x,bottomLeft.y,topRight.x,topRight.y); // DELETE
            if(inRange(targetX, bottomLeft.x, topRight.x)&&inRange(targetY,topRight.y, bottomLeft.y)){
                indicator = shape.rightClick();
                
            }
        }

        return indicator;
    }

    public void setDisplayBoundingBox(boolean state){
        this.boundBoxDisplay = state;
    }

    public void setDisplayName(boolean state){
        this.nameDisplay = state;
    }
}
