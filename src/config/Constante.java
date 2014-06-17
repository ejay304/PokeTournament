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
    public final static int SELECT_CODE = 0;
    public final static int MESSAGE_CODE = 1;
    public final static int VICTORY_CODE = 2;
    public final static int DEFEAT_CODE = 3;    
    public final static int TIME_SLEEP_BETWEEN_ACTION = 800;
    
    public static void init(){
        BOXES[0] = new Point(45, 518);
        BOXES[1] = new Point(194, 518);
        BOXES[2] = new Point(343, 518);
        BOXES[3] = new Point(492, 518);
        BOXES[4] = new Point(641, 518);
        BOXES[5] = new Point(790, 518);
        BOXES[6] = new Point(939, 518);
        BOXES[7] = new Point(1093, 518);
        BOXES[8] = new Point(120, 353);
        BOXES[9] = new Point(418, 353);
        BOXES[10] = new Point(720, 353);
        BOXES[11] = new Point(1020, 353);
        BOXES[12] = new Point(272, 185);
        BOXES[13] = new Point(872, 185);
        BOXES[14] = new Point(568, 25);  
    }

}
