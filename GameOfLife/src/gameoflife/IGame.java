/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.Graphics2D;

/**
 *
 * @author Hendrik
 */
public class IGame {

    Cel Cellen[][];
   int Width ,Height;
        protected int celType;
    public void CreateGame(int width, int height) {
        this.Width = width;
        this.Height = height;

        Cellen = new Cel[width][height];
        
        for(int x =0;x<width;x++)
        {
            for(int y = 0;y<height;y++)
            {
                Cellen[x][y] = new Cel();
            }
        }
    }

    void DrawGame(Graphics2D image) {
         for(int x =0;x<Width;x++)
        {
            for(int y = 0;y<Height;y++)
            {
                Cellen[x][y].DrawCel(image,x,y,celType);
            }
        }
    }

    void SetStatusCel(int X, int Y) {
        Cellen[X][Y].ChangeStatusCel();
    }
}
