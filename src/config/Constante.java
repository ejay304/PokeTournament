/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package config;

import java.awt.Point;

/**
 * Constante
 * 
 * @since   __
 * @author  Alain FRESCO
 * @version 1.0
 */
public class Constante {
    
    public static final String RESSOURCES = "/ressources/";
    public static final Point[] BOXES = new Point[15];
    
    public static void init(){
        BOXES[0] = new Point(45, 518);
        BOXES[1] = new Point(194, 518);
        BOXES[2] = new Point(343, 518);
        BOXES[3] = new Point(492, 518);
        BOXES[4] = new Point(641, 518);
        BOXES[5] = new Point(790, 518);
        BOXES[6] = new Point(939, 518);
        BOXES[7] = new Point(1093, 518);
        BOXES[8] = new Point(120, 365);
        BOXES[9] = new Point(418, 365);
        BOXES[10] = new Point(720, 365);
        BOXES[11] = new Point(1020, 365);
        BOXES[12] = new Point(272, 195);
        BOXES[13] = new Point(872, 195);
        BOXES[14] = new Point(568, 25);  
    }

}