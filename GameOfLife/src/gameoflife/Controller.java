/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author Hendrik
 */
public class Controller {
       final public static int  celWidth =30;
     final public static  int  celHeight =30;
  private  IGame game;
  
  public void SetStatusCel(int X,int Y)
  {
      game.SetStatusCel(X, Y);
  }
  public void InstellingVeranderd(int CelSoort)
  {
      
  }
  public void DrawGame()
  {
      BufferedImage off_Image =
  new BufferedImage(game.Width*Controller.celWidth, game.Width*Controller.celHeight,
                    BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = off_Image.createGraphics();
  }

    IGame GetGame() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
