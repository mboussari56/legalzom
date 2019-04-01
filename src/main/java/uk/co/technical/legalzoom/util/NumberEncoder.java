package uk.co.technical.legalzoom.util;

import java.math.BigInteger;

/**
 * 
 * @author moudjibb
 *    
 */

public class NumberEncoder {

    /**
     * Hide characters in a string
     * 
     * @param word
     * @param startIndex
     * @param spliter
     * @return
     */
    public static String hideNumber(BigInteger number) {

        if (number.bitLength() < 16) {
            return String.valueOf(number);
        }


        StringBuilder result = new StringBuilder();
        char words[] = String.valueOf(number).toCharArray();

        for (int i = 0; i < words.length; i++) {
            
            if(i < 4) {
                result.append(words[i]);
            }else {
                result.append("-xxxx-xxxx-xxxx");
                break;
            }
           
        }
        return result.toString();
    }
}
