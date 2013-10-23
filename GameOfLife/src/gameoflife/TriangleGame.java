/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.Graphics2D;
import java.util.List;

/**
 *
 * @author Hendrik
 */
public class TriangleGame extends IGame {
    public TriangleGame()
    {
       this.celType=1;
    }
@Override
    void DrawGame(Graphics2D image) {
        for (int x = 0; x < Width; x++) {
            for (int y = 0; y < Height; y++) {
                Cellen[x][y].DrawCel(image, x, y + 3, celType);
            }
        }
    }
    @Override
    public List<Cel> GetBuren(int x, int y,  String selectedVorm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void GebruikerClicked(int x, int y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void DoeBeurt(    String selectedVorm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    void setSelection(int x, int y, boolean[][] selectionCells) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
