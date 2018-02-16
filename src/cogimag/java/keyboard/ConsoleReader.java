/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cogimag.java.keyboard;
import java.io.BufferedInputStream; 
import java.io.IOException;
/**
 * From Sams Teach Yourself Java by Rogers Cadenhead. Listing 15-4.
 * Not used.
 * @author Michal G. <Michal.G at cogitatummagnumtelae.com>
 */
public class ConsoleReader {
    public static String readln() {
        StringBuilder oneLine = new StringBuilder();
        try (BufferedInputStream inStream = new BufferedInputStream(System.in)) {
            int inputByte;
            char inputChar;
            boolean weitergehen;
            do {
                inputByte = inStream.read();
                inputChar = (char)inputByte;
                weitergehen = (inputByte != -1) & (inputChar != '\n') & (inputChar != '\r');
                if (weitergehen) {
                    oneLine.append(inputChar);
                }                
            }
            while (weitergehen);
//            inStream.close();
            return oneLine.toString();            
        }
        catch (IOException ex) { 
            System.out.println("error reading console " + ex.getMessage());
            return null;
        }        
    }
}
