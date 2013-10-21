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
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Hendrik
 */
public class GameView extends JPanel {

    Controller controller;
    IGame game;
    BufferedImage grid;
    Color bgColor = Color.gray;
    public boolean selectingFigure = false;
    boolean selectionCells[][];

    public GameView(Controller c) {
        controller = c;
        game = c.GetGame();
        this.setPreferredSize(new Dimension(100 * Controller.celWidth, 100 * Controller.celHeight));
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (!selectingFigure) {
                    controller.GebruikerClicked(evt.getPoint().x, evt.getPoint().y);
                    //    game = controller.GetGame();
                    repaint();
                } else {
                    if (game.selectionp1 == null) {
                        game.selectionp1 = new Point(evt.getPoint().x, evt.getPoint().y);
                    } else if (game.selectionp2 == null) {
                        game.selectionp2 = new Point(evt.getPoint().x, evt.getPoint().y);
                        if (game.selectionp1.x == game.selectionp2.x && game.selectionp1.y == game.selectionp2.y) {
                            JOptionPane.showMessageDialog(null, "Geen geldige selectie gemaakt!", "Fout", JOptionPane.WARNING_MESSAGE);
                            selectingFigure = false;
                            game.selectionp1 = null;
                            game.selectionp2 = null;

                        } else {

                            int width = game.selectionp1.x - game.selectionp2.x;

                            if (width < 0) {
                                width = -width;
                            }

                            int height = game.selectionp1.y - game.selectionp2.y;
                            if (height < 0) {
                                height = -height;
                            }

                            int beginX, eindX, beginY, eindY = 0;
                            if (game.selectionp1.x < game.selectionp2.x) {
                                beginX = game.selectionp1.x;
                                eindX = game.selectionp2.x;

                            } else {
                                beginX = game.selectionp2.x;
                                eindX = game.selectionp1.x;
                            }
                            if (game.selectionp1.y < game.selectionp2.y) {
                                beginY = game.selectionp1.y;
                                eindY = game.selectionp2.y;

                            } else {
                                beginY = game.selectionp2.y;
                                eindY = game.selectionp1.y;
                            }

                            int aantalCellWidth = width / Controller.celWidth;
                            int aantalCellHeight = height / Controller.celHeight;
                            aantalCellWidth += 1;
                            aantalCellHeight += 1;
                            selectionCells = new boolean[aantalCellWidth][aantalCellHeight];
                            int celX = game.selectionp1.x / Controller.celWidth;
                            int celY = game.selectionp1.y / Controller.celHeight;
                            for (int x = 0; x < aantalCellWidth; x++) {
                                for (int y = 0; y < aantalCellHeight; y++) {
                                    selectionCells[x][y] = game.Cellen[x + celX][y + celY].getIsAlive();
                                }
                            }
                            int b = 0;
                        }
                    } else {
                        game.setSelection(evt.getPoint().x, evt.getPoint().y, selectionCells);
                        selectingFigure = false;
                        game.selectionp1 = null;
                        game.selectionp2 = null;
                        repaint();
                        
                    }
                }
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
            }
        });
    }

    public void paintComponent(Graphics g) // directe (inline) override van deze methode
    {
        super.paintComponents(g);                // moet altijd met Swing componenten
        Graphics2D g2d = (Graphics2D) g;         // nu extra functionaliteit:

        int w = this.getWidth();                // een grid met met altijd 100 vlakken (10 * 10)
        int h = this.getHeight();
        grid = (BufferedImage) (this.createImage(w, h)); // elke keer opnieuw!
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
        if (game != null) {
            game.DrawGame(g2d);
        }

    }
}
