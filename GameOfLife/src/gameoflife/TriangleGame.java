/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hendrik
 */
public class TriangleGame extends IGame {

    public TriangleGame() {
        this.celType = 1;
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
    public List<Cel> GetBuren(int x, int y, String selectedVorm) {
        List<Cel> result = new ArrayList<>();
        try {
            result.add(Cellen[x + 1][y]); //Rechts
            result.add(Cellen[x - 1][y]); //Links

            result.add(Cellen[x][y - 1]); //Boven
            result.add(Cellen[x][y + 1]); //Onder
        } catch (Exception e) {
            //Exception gevangen. Maakt niets, index bestond niet ofzo
        }

        if (x % 2 == 0) {
            try {
                result.add(Cellen[x - 1][y - 1]); //LinksBoven
                result.add(Cellen[x + 1][y - 1]); //RechtsBoven
            } catch (Exception e) {
                //Exception gevangen. Maakt niets, index bestond niet ofzo
            }
        } else {
            try {
                result.add(Cellen[x - 1][y + 1]); //LinksOnder
                result.add(Cellen[x + 1][y + 1]); //RechtsOnder
            } catch (Exception e) {
                //Exception gevangen. Maakt niets, index bestond niet ofzo
            }
        }

        if (selectedVorm.equals("Oasis") == false) {
            switch (selectedVorm) {
                case "Cilinder":
                    result.addAll(GetCilinder(x, y));
                    break;
                case "Torus":
                    result.addAll(GetTorus(x, y));
                    break;
                case "Möbius ring":
                    result.addAll(GetMobius(x, y));
                    break;
                case "Klein Fles":
                    result.addAll(GetKlein(x, y));
                    break;
            }
        }
        return result;
    }

    public List<Cel> GetCilinder(int x, int y) {
        List<Cel> result = new ArrayList<>();

        return result;
    }

    public List<Cel> GetTorus(int x, int y) {
        List<Cel> result = new ArrayList<>();

        return result;
    }

    public List<Cel> GetMobius(int x, int y) {
        List<Cel> result = new ArrayList<>();

        return result;
    }

    public List<Cel> GetKlein(int x, int y) {
        List<Cel> result = new ArrayList<>();

        return result;
    }

    @Override
    void GebruikerClicked(int x, int y) {
        int celX = x / Controller.celWidth;
        int celY = y / Controller.celHeight;
        celY -= 3;
        celX += 2;
        try {
            Cellen[celX][celY].SetCelStatus(true);
        } catch (Exception e) {
            System.out.println("Index bestond niet");
        }

    }

    @Override
    void DoeBeurt(String selectedVorm) {
        ArrayList<Cel> _had = new ArrayList<Cel>();
        for (int x = 0; x < this.Width; x++) {
            for (int y = 0; y < this.Height; y++) {
                int liveCounter = 0;
                for (Cel c : this.GetBuren(x, y, selectedVorm)) {
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
//e 3,5/2
                if (currentCel.getIsAlive() == false) {
                    if (liveCounter == 2) {
                        currentCel.SetCelStatus(true);
                    } else {
                        currentCel.SetCelStatus(false);
                    }
                } else {
                    if (liveCounter == 3 || liveCounter == 3) {
                        currentCel.SetCelStatus(true);
                    } else {
                        currentCel.SetCelStatus(false);
                    }
                }

            }
        }
    }

    @Override
    void setSelection(int x, int y, boolean[][] selectionCells) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
