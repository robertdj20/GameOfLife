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
            
        }

        if (x % 2 == 0) {
            try {
                result.add(Cellen[x - 1][y - 1]); //LinksBoven
                result.add(Cellen[x + 1][y - 1]); //RechtsBoven
            } catch (Exception e) {
                
            }
        } else {
            try {
                result.add(Cellen[x - 1][y + 1]); //LinksOnder
                result.add(Cellen[x + 1][y + 1]); //RechtsOnder
            } catch (Exception e) {
                
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

        try {
            if (x == 0 && this.Width - 1 % 2 != 0) {
                result.add(Cellen[this.Width - 1][y]);
                result.add(Cellen[this.Width - 1][y - 1]);

            } else if (x == this.Width - 1 && x % 2 != 0) {
                result.add(Cellen[0][y]);
                result.add(Cellen[0][y + 1]);
            }

        } catch (Exception e) {
            
        }

        return result;
    }

    public List<Cel> GetTorus(int x, int y) {
        List<Cel> result = new ArrayList<>();

        result.addAll(this.GetCilinder(x, y));

        try {

            if (y == 0) {
                result.add(Cellen[x][this.Height - 1]);
                if (x % 2 == 0) {
                    result.add(Cellen[x - 1][this.Height - 1]);
                    result.add(Cellen[x + 1][this.Height - 1]);
                }
            } else if (y == this.Height - 1) {
                result.add(Cellen[x][0]);
                if (x % 2 != 0) {
                    result.add(Cellen[x - 1][0]);
                    result.add(Cellen[x + 1][0]);
                }
            }
        } catch (Exception e) {
            
        }

        return result;
    }

    public List<Cel> GetMobius(int x, int y) {
        List<Cel> result = new ArrayList<>();

        try {
            if (y == 0) {
                int X = (this.Width - 1) - x;
                result.add(Cellen[X][this.Height - 1]);
                result.add(Cellen[X + 1][this.Height - 1]);
                result.add(Cellen[X - 1][this.Height - 1]);
            } else if (y == this.Height - 1) {
                int X = (this.Width - 1) - x;
                result.add(Cellen[X][0]);
                result.add(Cellen[X + 1][0]);
                result.add(Cellen[X - 1][0]);
            }
        } catch (Exception e) {
            
        }

        return result;
    }

    public List<Cel> GetKlein(int x, int y) {
        List<Cel> result = new ArrayList<>();

        result.addAll(this.GetMobius(x, y));
        
        try{
            if(x == 0){
                int Y = (this.Height -1) - y;
                if(this.Width -1 % 2 != 0){
                    result.add(Cellen[this.Width -1][Y]);
                    result.add(Cellen[this.Width -1][Y +1]);
                }
            }
            else if(x == this.Width -1){
                int Y = (this.Height -1) - y;
                if(this.Width -1 % 2 != 0){
                    result.add(Cellen[0][Y]);
                    result.add(Cellen[0][Y - 1]);
                }
            }
        }
        catch(Exception e){
            
        }
        
        return result;
    }

    @Override
    void GebruikerClicked(int x, int y) {
        //System.out.println("x: " +x +" y: " +y);
        int celX = Math.round(x / Controller.celWidth);
        int celY = Math.round(y / Controller.celHeight);

        //System.out.println("x: " +celX +" y: " +celY);
        //celX = Math.round(celX / 2);
        celX *= 2;
        celY -= 3;

        //System.out.println("x: " +celX +" y: " +celY +"\n\n");
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
