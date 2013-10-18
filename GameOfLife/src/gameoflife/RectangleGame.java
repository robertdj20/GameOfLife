/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hendrik
 */
public class RectangleGame extends IGame {

    public RectangleGame() {
        celType = 0;
    }

    public List<Cel> GetBuren(int x, int y) {

        List<Cel> _temp = new ArrayList<Cel>();
        if (x != 0 && y != 0 && x != this.Width - 1 && y != this.Height - 1) {
            _temp.add(Cellen[x + 1][y]);//rechts
            _temp.add(Cellen[x - 1][y]);//links

            _temp.add(Cellen[x + 1][y + 1]);//rechtsboven
            _temp.add(Cellen[x - 1][y + 1]);//linksboven

            _temp.add(Cellen[x + 1][y - 1]);//rechtsonder
            _temp.add(Cellen[x - 1][y - 1]);//linksonder

            _temp.add(Cellen[x][y + 1]);//boven
            _temp.add(Cellen[x][y - 1]);//onder

        } else {
        }


        return _temp;
    }

    @Override
    void GebruikerClicked(int x, int y) {
        int celX = x / Controller.celWidth;
        int celY = y / Controller.celHeight;
        Cellen[celX][celY].SetCelStatus(true);
    }

    @Override
    void DoeBeurt() {
        ArrayList<Cel> _had = new ArrayList<Cel>();
        for (int x = 0; x < this.Width; x++) {
            for (int y = 0; y < this.Height; y++) {
                int liveCounter = 0;
                for (Cel c : this.GetBuren(x, y)) {
                    if (_had.contains(c)) {
                        if (c.getLastStatus()) {
                            liveCounter++;
                        }
                    } else {
                        if (c.getIsAlive()) {
                            liveCounter++;
                         
                        }
                    }

                }
                Cel currentCel = this.Cellen[x][y];
                _had.add(currentCel);
              
                if(currentCel.getIsAlive() == false)
                {
                    if(liveCounter ==3)
                    {
                        currentCel.SetCelStatus(true);
                    }
                    else
                    {
                     currentCel.SetCelStatus(false);
                    }
                }
                else
                {
                    if(liveCounter ==3 ||liveCounter ==2)
                    {
                        currentCel.SetCelStatus(true);
                    } 
                    else
                    {
                        currentCel.SetCelStatus(false);
                    }
                }



            }
        }
    }
}
