/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author Hendrik
 */
public class Controller {

    final public static int celWidth = 30;
    final public static int celHeight = 30;
    private IGame game;

    public void SetStatusCel(int X, int Y) {
        game.SetStatusCel(X, Y);
    }

    public void InstellingVeranderd(int CelSoort) {
    }

    public void DrawGame() {
        BufferedImage off_Image =
                new BufferedImage(game.Width * Controller.celWidth, game.Width * Controller.celHeight,
                BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = off_Image.createGraphics();
    }

    IGame GetGame() {
      return game;
    }

    public void DoeBeurt(    String selectedVorm) {
       game.DoeBeurt(     selectedVorm);


    }

    void newGame(int width, int height,IGame game) {
       this.game =game;
      this.game.CreateGame(width, height);
        
    }

    void GebruikerClicked(int x, int y) {
    game.GebruikerClicked( x,  y);   
    }
}
