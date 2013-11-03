/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 *
 * @author Hendrik
 */
public class MainWindow extends javax.swing.JFrame {

    Controller controller;
    GameView g;
    Timer t;
    JButton startBtn;
    JButton selectFigurBtn;
    JButton newBtn;
    JComboBox comboBox;
    String selectedVorm;
    JPanel mainPanel;
    JScrollPane scroll;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        controller = new Controller();
        controller.newGame(10, 10, new TriangleGame());
        //       setContentPane(new GameView());
        g = new GameView(controller);
        scroll = new JScrollPane(g);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //this.add(scroll);
        mainPanel = new JPanel(new FlowLayout());

        t = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.DoeBeurt(selectedVorm);
                g.repaint();
            }
        });

        JPanel topBar = new JPanel();
        startBtn = new JButton("Start");
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!t.isRunning()) {
                    t.start();
                    selectFigurBtn.setEnabled(false);
                    startBtn.setText("Stop");
                } else {
                    t.stop();
                    selectFigurBtn.setEnabled(true);
                    startBtn.setText("Start");
                }
            }
        });
        newBtn = new JButton("New game");
        newBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateNewGame();
            }
        });
        comboBox = new JComboBox();
        selectedVorm = "Oasis";
        comboBox.addItem("Oasis");
        comboBox.addItem("Cilinder");
        comboBox.addItem("Torus");
        comboBox.addItem("Möbius ring");
        comboBox.addItem("Klein Fles");
        selectFigurBtn = new JButton("Selecteer gedeelte");
        selectFigurBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.selectingFigure = true;
            }
        });


        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedVorm = comboBox.getSelectedItem().toString();
            }
        });
        topBar.add(newBtn);

        topBar.add(comboBox);
        topBar.add(startBtn);
        topBar.add(selectFigurBtn);
        mainPanel.add(topBar);
        mainPanel.add(scroll);
        setContentPane(mainPanel);
        setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

    }

    void CreateNewGame() {
        ArrayList<String> _temp = new ArrayList<String>();
        for (int number = 25; number <= 200; number += 25) {
            _temp.add(number + " x" + number);
        }
        String heightWidth = (String) JOptionPane.showInputDialog(
                this, null,
                "Hooghte en Breedte",
                JOptionPane.PLAIN_MESSAGE,
                null,
                _temp.toArray(),
                _temp.get(0));

        String[] options = new String[]{"Rectangle", "Triangle", "Hexagon"};
        String Kind = (String) JOptionPane.showInputDialog(
                this, null,
                "Soort",
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                "Rectangle");
        IGame _tempGame;
        switch (Kind) {
            case "Rectangle":
                _tempGame = new RectangleGame();
                break;
            case "Triangle":
                _tempGame = new TriangleGame();
                break;
            case "Hexagon":
                _tempGame = new HexagonGame();
                break;
            default:
                _tempGame = new RectangleGame();
                break;
        }
        String number = heightWidth.substring(0, 3);
        number = number.trim();
        int heightWithNumber = Integer.parseInt(number);
        controller = new Controller();
        controller.newGame(heightWithNumber, heightWithNumber, _tempGame);
        //       setContentPane(new GameView());
        g = new GameView(controller);
                g.setPreferredSize(new Dimension((heightWithNumber * Controller.celWidth)+150, (heightWithNumber * Controller.celHeight)+150));
        mainPanel.remove(scroll);
        scroll = new JScrollPane(g);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        mainPanel.add(scroll);
        g.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWindow().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
