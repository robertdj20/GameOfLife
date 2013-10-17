/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Hendrik
 */
public class GameView extends JPanel{
       
    Controller controller;
    IGame game ;
                  BufferedImage grid;   
                      Color bgColor = Color.gray;
    public GameView()
    {
        game = new HexagonGame();
        game.CreateGame(100, 100);
        game.SetStatusCel(99, 99);
       this.setPreferredSize(new Dimension(100*Controller.celWidth, 100*Controller.celHeight));

    }
    
    
       
    
      public void paintComponent(Graphics g)      // directe (inline) override van deze methode
     {     
          super.paintComponents(g);                // moet altijd met Swing componenten
            Graphics2D g2d = (Graphics2D)g;         // nu extra functionaliteit:
            
            int w = this.getWidth();                // een grid met met altijd 100 vlakken (10 * 10)
            int h = this.getHeight();
            grid = (BufferedImage)(this.createImage(w, h)); // elke keer opnieuw!
            Graphics2D gc = grid.createGraphics();
            gc.setColor(bgColor);
         /*   for (int x = 0; x < w; x += w / 10)     
                  gc.drawLine(x, 0, x, h);
            for (int y = 0; y < h; y += h / 10) 
                  gc.drawLine(0, y, w, y);
*/
       //     g2d.setStroke(new BasicStroke(2.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND)); 
                    // breedte van de lijnstukken, afgeronde eindpunten
            g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                                    RenderingHints.VALUE_ANTIALIAS_ON));    
                    // achtergrond eerst 
            g2d.drawImage(grid, null, 0, 0);
            if (game != null)
            {     game.DrawGame(g2d);
            }
    
    }   
}

