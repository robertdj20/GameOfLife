/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 *
 * @author Hendrik
 */
public class MainWindow extends javax.swing.JFrame {

    Controller controller;
GameView g ;
   Timer t;
    JButton startBtn;
    JButton selectFigurBtn;
            JComboBox comboBox ;
    String selectedVorm;
    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();
        controller = new Controller();
        controller.newGame(100, 100, new RectangleGame());
        //       setContentPane(new GameView());
         g = new GameView(controller);
        JScrollPane scroll = new JScrollPane(g);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //this.add(scroll);
        JPanel mainPanel = new JPanel(new FlowLayout());

       t = new Timer(2000, new ActionListener() {
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
          if(!t.isRunning())
          {
                           t.start();
                           selectFigurBtn.setEnabled(false);
                           startBtn.setText("Stop");
          }
          else
          {
              t.stop();
                 selectFigurBtn.setEnabled(true);
                  startBtn.setText("Start");
          }
            }
        });
         comboBox = new JComboBox();
        selectedVorm= "Oasis";
        comboBox.addItem("Oasis");
        comboBox.addItem("Cilinder");
        comboBox.addItem("Torus");
         comboBox.addItem("Möbius ring");
        comboBox.addItem("Klein Fles");
        selectFigurBtn = new JButton("Selecteer gedeelte");
        selectFigurBtn.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
               g.selectingFigure=true;
            }
        });
        
        
        comboBox.addActionListener(new ActionListener(){


            @Override
            public void actionPerformed(ActionEvent e) {
               selectedVorm = comboBox.getSelectedItem().toString();
            }
        });
        topBar.add(comboBox);
        topBar.add(startBtn);
        topBar.add(selectFigurBtn);
        mainPanel.add(topBar);
        mainPanel.add(scroll);
        setContentPane(mainPanel);
        setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

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
