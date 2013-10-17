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

    public Cel() {
        IsAlive = false;
    }
    private boolean IsAlive;
    final Color dead = Color.RED;
    final Color live = Color.GREEN;

    public void SetCelStatus(boolean status) {
        if (IsAlive != status) {
            IsAlive = !IsAlive;
            generation = 1;
        } else {
            generation++;
        }
    }

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
            image.drawString(Integer.toString(generation), (x * Controller.celWidth) + Controller.celWidth / 2, (y * Controller.celHeight) + Controller.celHeight / 2);


        } else if (cellType == 1) {
            Point p1 = new Point();
            Point p2;
            Point p3;
            int letterX= (x * Controller.celWidth);
            int letterY= (y * Controller.celHeight)- ((y / 2) * Controller.celHeight) +(Controller.celHeight/2);

            if (y % 2 == 0) {
                p1 = new Point((x * Controller.celWidth),
                        (y * (Controller.celHeight)));
                p2 = new Point((x + 1) * Controller.celWidth,
                        (y * (Controller.celHeight)));
                p3 = new Point(((x * Controller.celWidth)),
                        ((y + 1) * (Controller.celHeight)));
                letterX +=Controller.celWidth/4;
         
            } else {
                p1 = new Point(((x - 1) * Controller.celWidth),
                        ((y + 1) * Controller.celHeight) - (Controller.celHeight));
                p2 = new Point((x) * Controller.celWidth,
                        (y * (Controller.celHeight) - (Controller.celHeight)));
                p3 = new Point(((x * Controller.celWidth)),
                        ((y + 1) * Controller.celHeight) - (Controller.celHeight));
               letterX -=Controller.celWidth/2;
                letterY -=Controller.celHeight - (Controller.celHeight/2.5f);
            }




          


            int[] xcor = {p1.x, p2.x, p3.x};

            int[] ycor = {p1.y - ((y / 2) * Controller.celHeight), p2.y - ((y / 2) * Controller.celHeight), p3.y - ((y / 2) * Controller.celHeight)};

            Polygon triangle = new Polygon(xcor, ycor, xcor.length);

            image.fill(triangle);
            image.setColor(Color.BLACK);
            image.draw(triangle);
             image.drawString(Integer.toString(generation), letterX, letterY);


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

            image.drawString(Integer.toString(generation), (x * Controller.celWidth) + (Controller.celWidth / 2), letterY);

        }

    }
}
