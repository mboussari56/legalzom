package uk.co.technical.legalzoom.util;

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
    public static String hideString(String word, String spliter) {

        if ((word == null || word.isEmpty()) || (spliter == null || spliter.isEmpty())) {
            return word;
        }

        if (!word.contains(spliter)) {
            return word;
        }

        String words[] = word.split(spliter);

        if (words.length < 2) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        result.append(words[0]);

        for (int i = 1; i < words.length; i++) {
            result.append(spliter);
            for (int j = 0; j < words[i].length(); j++) {
                result.append("x");
            }

        }
        return result.toString();
    }
}
