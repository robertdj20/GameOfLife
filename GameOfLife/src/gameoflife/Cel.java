/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author Hendrik
 */
public class Cel {

    int generation = 0;
    boolean LastStatus = false;

    public Cel() {
        IsAlive = false;
    }
    private boolean IsAlive;
    final Color dead = Color.RED;
    final Color live = Color.GREEN;

    //status cel veranderen
    public void SetCelStatus(boolean status) {
        LastStatus = IsAlive;
        if (IsAlive != status) {
            IsAlive = !IsAlive;
            generation = 1;
            if (!IsAlive) {
                generation = 0;
            }
        } else if (IsAlive == true) {
            generation++;
        }
    }

    //cel tekeken
    void DrawCel(Graphics2D image, int x, int y, int cellType) {
        Font font = new Font("Serif", Font.PLAIN, 12);
        image.setFont(font);


        if (IsAlive == false) {
            image.setColor(dead);

        } else {
            image.setColor(live);
        }
        if (cellType == 0) {

            image.fill(new Rectangle2D.Double(x * Controller.celWidth, y * Controller.celHeight, Controller.celWidth, Controller.celHeight));

            image.setColor(Color.BLACK);
            image.drawLine(x * Controller.celWidth, (y) * Controller.celHeight, x * Controller.celWidth,
                    (y + 1) * Controller.celHeight);
            image.drawLine((x + 1) * Controller.celWidth, (y) * Controller.celHeight, (x + 1) * Controller.celWidth,
                    (y + 1) * Controller.celHeight);

            image.drawLine(x * Controller.celWidth, (y) * Controller.celHeight, (x + 1) * Controller.celWidth,
                    (y) * Controller.celHeight);
            image.drawLine(x * Controller.celWidth, (y + 1) * Controller.celHeight, (x + 1) * Controller.celWidth,
                    (y + 1) * Controller.celHeight);
            if (generation != 0) {
                image.drawString(Integer.toString(generation), (x * Controller.celWidth) + Controller.celWidth / 2, (y * Controller.celHeight) + Controller.celHeight / 2);
            }

        } else if (cellType == 1) {
            Point p1 = new Point();
            Point p2 = new Point();
            Point p3 = new Point();

            if (x % 2 == 0) {
                p1 = new Point((x * Controller.celWidth), (y * Controller.celHeight));
                p2 = new Point(((x + 1) * Controller.celWidth), (y * Controller.celHeight));
                p3 = new Point((((x + 1) * Controller.celWidth) - (Controller.celWidth / 2)), ((y + 1) * Controller.celHeight));
            } else {
                p1 = new Point((x * Controller.celWidth), ((y + 1) * Controller.celHeight));
                p2 = new Point(((x + 1) * Controller.celWidth), ((y + 1) * Controller.celHeight));
                p3 = new Point((((x + 1) * Controller.celWidth) - (Controller.celWidth / 2)), ((y) * Controller.celHeight));
            }
            int halfeCelwidth = x * (Controller.celWidth / 2);
            int[] xcor = {p1.x - halfeCelwidth, p2.x - halfeCelwidth, p3.x - halfeCelwidth};

            int[] ycor = {p1.y, p2.y, p3.y};

            Polygon triangle = new Polygon(xcor, ycor, xcor.length);

            image.fill(triangle);
            image.setColor(Color.BLACK);
            image.draw(triangle);
  int letterX = (x * Controller.celWidth) - halfeCelwidth + (Controller.celWidth /3);
            int letterY = (y * Controller.celHeight) + (Controller.celHeight / 2);
              if (generation != 0) {
          
            if (x % 2 != 0) {
                letterY+= (Controller.celHeight / 4);
            }
                    image.drawString(Integer.toString(generation), letterX, letterY);
          
             }
        //      image.drawString(x+","+y, letterX, letterY);
        


        } else if (cellType == 2) {

            Point p1 = new Point((x * Controller.celWidth) + (Controller.celWidth / 4), ((y - 1) * Controller.celHeight));

            Point p2 = new Point(((x + 1) * Controller.celWidth) - (Controller.celWidth / 4), ((y - 1) * Controller.celHeight));

            Point p3 = new Point(((x + 1) * Controller.celWidth) + (Controller.celWidth / 4), ((y - 1) * Controller.celHeight) + (Controller.celHeight / 2));

            Point p4 = new Point(((x + 1) * Controller.celWidth) - (Controller.celWidth / 4), ((y) * Controller.celHeight));

            Point p5 = new Point(((x) * Controller.celWidth) + (Controller.celWidth / 4), ((y) * Controller.celHeight));

            Point p6 = new Point(((x) * Controller.celWidth) - (Controller.celWidth / 4), ((y) * Controller.celHeight) - (Controller.celHeight / 2));
            int letterY = (y * Controller.celHeight);
            if (x % 2 != 0) {
                /*  _
                 * / \
                 * \_/
                 */

                p1.y -= Controller.celHeight / 2;
                p2.y -= Controller.celHeight / 2;
                p3.y -= Controller.celHeight / 2;
                p4.y -= Controller.celHeight / 2;
                p5.y -= Controller.celHeight / 2;
                p6.y -= Controller.celHeight / 2;
                letterY -= Controller.celHeight;



            } else {
                letterY -= Controller.celHeight / 2;
            }
            Polygon hexagon = new Polygon();
            hexagon.addPoint(p1.x, p1.y);
            hexagon.addPoint(p2.x, p2.y);
            hexagon.addPoint(p3.x, p3.y);
            hexagon.addPoint(p4.x, p4.y);
            hexagon.addPoint(p5.x, p5.y);
            hexagon.addPoint(p6.x, p6.y);
            //hexagon.addPoint(p7.x, p7.y);
            image.fill(hexagon);
            image.setColor(Color.BLACK);
            image.draw(hexagon);
            if (generation != 0) {
                image.drawString(Integer.toString(generation), (x * Controller.celWidth) + (Controller.celWidth / 2), letterY);
            }
      
        }

    }

    boolean getIsAlive() {
        return IsAlive;
    }

    boolean getLastStatus() {
        return LastStatus;
    }
}
