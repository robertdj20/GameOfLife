/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.util.List;

/**
 *
 * @author Hendrik
 */
public class HexagonGame extends IGame{
    
    public HexagonGame()
    {
        celType = 2;
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
