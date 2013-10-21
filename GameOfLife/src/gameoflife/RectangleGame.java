/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Hendrik
 */
public class RectangleGame extends IGame {

    public RectangleGame() {
        celType = 0;
    }

    public List<Cel> GetBuren(int x, int y, String selectedVorm) {

        List<Cel> _temp = new ArrayList<Cel>();
        if (x != 0 && y != 0 && x != this.Width - 1 && y != this.Height - 1) {
            _temp.add(Cellen[x + 1][y]);//rechts
            _temp.add(Cellen[x - 1][y]);//links

            _temp.add(Cellen[x + 1][y + 1]);//rechtsboven
            _temp.add(Cellen[x - 1][y + 1]);//linksboven

            _temp.add(Cellen[x + 1][y - 1]);//rechtsonder
            _temp.add(Cellen[x - 1][y - 1]);//linksonder

            _temp.add(Cellen[x][y + 1]);//onder
            _temp.add(Cellen[x][y - 1]);//boven

        } else {
            if (x == 0 && y == 0) {
                _temp.add(Cellen[x + 1][y]);//rechts
                _temp.add(Cellen[x][y + 1]);//onder
                _temp.add(Cellen[x + 1][y + 1]);//rechtsboven

            } else if (x == this.Width - 1 && y == this.Height - 1) {
                _temp.add(Cellen[x][y - 1]);//boven
                _temp.add(Cellen[x - 1][y - 1]);//linksonder
                _temp.add(Cellen[x - 1][y]);//links
            } else if (x == 0 && y == this.Height - 1) {
                _temp.add(Cellen[x][y - 1]);//boven
                _temp.add(Cellen[x + 1][y - 1]);//rechtsonder
                _temp.add(Cellen[x + 1][y]);//rechts

            } else if (x == this.Width - 1 && y == 0) {
                _temp.add(Cellen[x - 1][y]);//links
                _temp.add(Cellen[x][y + 1]);//onder
                _temp.add(Cellen[x - 1][y + 1]);//linksboven
            } else if (x == 0) {
                _temp.add(Cellen[x + 1][y]);//rechts
                _temp.add(Cellen[x + 1][y + 1]);//rechtsboven
                _temp.add(Cellen[x + 1][y - 1]);//rechtsonder
                _temp.add(Cellen[x][y + 1]);//boven
                _temp.add(Cellen[x][y - 1]);//onder
            } else if (x == this.Width - 1) {
                _temp.add(Cellen[x - 1][y]);//links
                _temp.add(Cellen[x - 1][y + 1]);//linksboven
                _temp.add(Cellen[x - 1][y - 1]);//linksonder

                _temp.add(Cellen[x][y + 1]);//boven
                _temp.add(Cellen[x][y - 1]);//onder
            } else if (y == 0) {
                _temp.add(Cellen[x][y + 1]);//onder
                _temp.add(Cellen[x + 1][y]);//rechts
                _temp.add(Cellen[x - 1][y]);//links
                _temp.add(Cellen[x + 1][y + 1]);//rechtsboven
                _temp.add(Cellen[x - 1][y + 1]);//linksboven


            } else if (y == this.Height - 1) {
                _temp.add(Cellen[x][y - 1]);//boven
                _temp.add(Cellen[x + 1][y]);//rechts
                _temp.add(Cellen[x - 1][y]);//links
                _temp.add(Cellen[x + 1][y - 1]);//rechtsonder
                _temp.add(Cellen[x - 1][y - 1]);//linksonder
            }
            if (selectedVorm != "Oasis") {
                /*
                 *         comboBox.addItem("Oasis");
                 comboBox.addItem("Cilinder");
                 comboBox.addItem("Torus");
                 comboBox.addItem("Möbius ring");
                 comboBox.addItem("Klein Fles");
                 */
                switch (selectedVorm) {
                    case "Cilinder":
                        _temp.addAll(GeClininderBuren(x, y));
                        break;
                    case "Torus":
                        _temp.addAll(GetTorus(x, y));
                        break;
                    case "Möbius ring":
                        _temp.addAll(GetMobius(x, y));
                        break;
                    case "Klein Fles":
                        _temp.addAll(GetKlein(x, y));
                        break;
                }
            }
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
                    if (liveCounter == 3) {
                        currentCel.SetCelStatus(true);
                    } else {
                        currentCel.SetCelStatus(false);
                    }
                } else {
                    if (liveCounter == 3 || liveCounter == 2) {
                        currentCel.SetCelStatus(true);
                    } else {
                        currentCel.SetCelStatus(false);
                    }
                }



            }
        }
    }

    private ArrayList<Cel> GeClininderBuren(int x, int y) {
        ArrayList<Cel> _temp = new ArrayList<Cel>();
        if (x == 0) {
            _temp.add(Cellen[this.Width - 1][y]);
            if (y != 0) {
                _temp.add(Cellen[this.Width - 1][y - 1]);
            }
            if (y != this.Height - 1) {
                _temp.add(Cellen[this.Width - 1][y + 1]);
            }
        } else if (x == this.Width - 1) {

            _temp.add(Cellen[0][y]);
            if (y != 0) {
                _temp.add(Cellen[0][y - 1]);
            }
            if (y != this.Height - 1) {
                _temp.add(Cellen[0][y + 1]);
            }
        }
        return _temp;
    }

    private ArrayList<Cel> GetTorus(int x, int y) {
        ArrayList<Cel> _temp = new ArrayList<Cel>();
        _temp.addAll(GeClininderBuren(x, y));
        if (y == 0) {
            int index = this.Height - 1;
            _temp.add(Cellen[x][index]);
            if (x != 0) {
                _temp.add(Cellen[x - 1][index]);
            } else {
                _temp.add(Cellen[this.Width - 1][index]);
            }
            if (x != this.Width - 1) {
                _temp.add(Cellen[x + 1][index]);
            } else {
                _temp.add(Cellen[0][index]);
            }
        } else if (y == this.Height - 1) {
            _temp.add(Cellen[x][0]);
            if (x != 0) {
                _temp.add(Cellen[x - 1][0]);
            } else {
                _temp.add(Cellen[this.Width - 1][0]);
            }
            if (x != this.Width - 1) {
                _temp.add(Cellen[x + 1][0]);
            } else {
                _temp.add(Cellen[0][0]);
            }
        }

        return _temp;
    }

    private ArrayList<Cel> GetMobius(int x, int y) {
        ArrayList<Cel> _temp = new ArrayList<Cel>();
        y = this.Height - 1 - y;
        if (x == 0) {
            _temp.add(Cellen[this.Width - 1][y]);
            if (y != 0) {
                _temp.add(Cellen[this.Width - 1][y - 1]);
            }
            if (y != this.Height - 1) {
                _temp.add(Cellen[this.Width - 1][y + 1]);
            }
        } else if (x == this.Width - 1) {

            _temp.add(Cellen[0][y]);
            if (y != 0) {
                _temp.add(Cellen[0][y - 1]);
            }
            if (y != this.Height - 1) {
                _temp.add(Cellen[0][y + 1]);
            }
        }
        return _temp;
    }

    private ArrayList<Cel> GetKlein(int x, int y) {
        ArrayList<Cel> _temp = new ArrayList<Cel>();
        _temp.addAll(GetMobius(x, y));
        x = this.Width - 1 - x;
        if (y == 0) {
            int index = this.Height - 1;
            _temp.add(Cellen[x][index]);
            if (x != 0) {
                _temp.add(Cellen[x - 1][index]);
            } else {
                _temp.add(Cellen[this.Width - 1][index]);
            }
            if (x != this.Width - 1) {
                _temp.add(Cellen[x + 1][index]);
            } else {
                _temp.add(Cellen[0][index]);
            }
        } else if (y == this.Height - 1) {
            _temp.add(Cellen[x][0]);
            if (x != 0) {
                _temp.add(Cellen[x - 1][0]);
            } else {
                _temp.add(Cellen[this.Width - 1][0]);
            }
            if (x != this.Width - 1) {
                _temp.add(Cellen[x + 1][0]);
            } else {
                _temp.add(Cellen[0][0]);
            }
        }

        return _temp;
    }

    @Override
    void setSelection(int x, int y, boolean[][] selectionCells) {
        int celX = x / Controller.celWidth;
        int celY = y / Controller.celHeight;
        int startCelY = celY ;//- (selectionCells.length / 2);
        int startCelX = celX ;//- (selectionCells[0].length / 2);
        if (startCelY >= 0 && startCelX >= 0) {
            for (int i = 0; i < selectionCells.length; i++) {
                for (int c = 0; c < selectionCells[i].length; c++) {
                    Cellen[startCelX + i][startCelY + c].SetCelStatus(selectionCells[i][c]);
                }
            }
        }

    }
}
