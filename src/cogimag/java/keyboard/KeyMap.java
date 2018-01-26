/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cogimag.java.keyboard;

/**
 *
 * @author Michal G. <Michal.G at cogitatummagnumtelae.com>
 */
public class KeyMap {
    
    
    private class KeyConstruction {
        int keyEventConstant;
        boolean isShifted;
        
        //constructor
        KeyConstruction(int vk_constant, boolean is_shifted) {
            keyEventConstant = vk_constant;
            isShifted = is_shifted;
            
        }
    }
    
}
