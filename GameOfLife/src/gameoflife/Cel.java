/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflife;

/**
 *
 * @author Hendrik
 */
public class Cel {
    private boolean IsAlive;
    
    public void ChangeStatusCel()
    {
      IsAlive=!IsAlive;  
    }
}
