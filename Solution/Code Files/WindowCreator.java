
import javax.swing.*;
import java.awt.*;

public class WindowCreator {
    
    // Shapes Manager - Global Shape Manager Object
    static ShapesManager shapesManager = new ShapesManager();

    // For Creating Shapes & Adding to Manager 
    static void createShapes(){
                
        // Create Shapes and Add to Shapes Manager
        Rectangle r1 = new Rectangle(Color.MAGENTA,true,50+20,20+30,40,60);
        Circle c1 = new Circle(Color.green,true,230,50,50);        
        Square s1 = new Square(Color.YELLOW, false, 240,25, 30);
        shapesManager.addShape(r1);
        shapesManager.addShape(c1);
        shapesManager.addShape(s1);

        
        // Quadrilateral # 1
        Point centerPoint = new Point(230,150);
        Point points[] = new Point[4];
        points[2] = new Point(200,100);
        points[0] = new Point(260,90);
        points[1] = new Point(260,200);
        points[3] = new Point(190,210);


        Quadrilateral q1 = new Quadrilateral(Color.green,false, centerPoint, points);
        shapesManager.addShape(q1);

        // Quadrilateral # 2
        Point p1,p2,p3,p4, centerPoint2;
        p1 = new Point(200,250);
        p2 = new Point(360,240);
        p3 = new Point(330,300);
        p4 = new Point(190,350);
        centerPoint2 = new Point(230,250);
        
        Quadrilateral q2 = new Quadrilateral(Color.ORANGE,false, centerPoint2, p3,p2,p1,p4);
        shapesManager.addShape(q2);

        // Quadrilateral # 3
        Rectangle r = new Rectangle(Color.green,true,50+20,300,40,60);
        Quadrilateral q3 = new Quadrilateral(Color.darkGray,false, r);
        shapesManager.addShape(q3);

    }


    public static void main(String[] args) {
        // Used to set some features, False by default
        shapesManager.setDisplayBoundingBox(false); 
        shapesManager.setDisplayName(true); 

        // This Function is used to create and add shapes to SHapeManager
        createShapes();

        //Create and configure our JFrame (window)
        CustomWindow customWindow = new CustomWindow(shapesManager);
        customWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customWindow.setTitle("Drawing Template");
        customWindow.setVisible(true);
    }
}
