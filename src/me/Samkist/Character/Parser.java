/* Created By: Sam Pizette
 * On Date: 11/12/2019
 * Project Name: Characters
 */
package me.Samkist.Character;

import java.util.ArrayList;

public class Parser {
    String[] splitString;
    String rawString;

    public Parser(String rawString) {
        this.rawString = rawString;
        splitString = splitString("Hello my", " ");
        for(int i = 0; i < splitString.length; i++) {
            System.out.println(splitString[i]);
        }
    }

    private String[] splitString(String s, String delimiter) {
        SamArray<String> arrList = new SamArray<>();
        for(int i = 0; i < s.length(); i++) {
            StringBuilder builder = new StringBuilder("");
            while(i < s.length()) {
                if(("" + s.charAt(i)).equals(delimiter))
                    continue;
                System.out.println("Appending " + s.charAt(i));
                builder.append("" + s.charAt(i));
                i++;
            }
            if(s.toString().length() > 0)
                arrList.add(s.toString());
        }
        String[] arr = new String[arrList.size()];
        for(int i = 0; i < arr.length; i++) {
            System.out.println("Adding " + arrList.get(i));
            arr[i] = arrList.get(i);
        }
        return arr;
    }
}
