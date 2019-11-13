/* Created By: Sam Pizette
 * On Date: 11/12/2019
 * Project Name: Characters
 */
package me.Samkist.Character;

import me.Samkist.Character.SamUtil.SamArray;
import me.Samkist.Character.SamUtil.SamKV;

class Parser {

    Parser(String rawString) {
        rawString = rawString.replace("\t", "").trim().replaceAll("^ +| +$|( )+", " ");
        if(Character.toString(rawString.charAt(rawString.length()-1)).matches("\\p{Punct}"))
            rawString = rawString.substring(0, rawString.length()-1);
        String[] splitStringLowercase = splitString(rawString.toLowerCase(), " ");
        Words words = getWords(splitStringLowercase);
        SamArray<SamKV<String, Integer>> occurrences = getOccurrences(words, splitStringLowercase);
        for(SamKV<String, Integer> occ : occurrences) {
            System.out.println(occ.getKey() + " : " + occ.getValue());
        }
    }

    private SamArray<SamKV<String, Integer>> getOccurrences(Words w, String[] s) {
        SamArray<SamKV<String, Integer>> occ = new SamArray<>();
        for(String sw : w.getWordsArray()) {
            occ.add(new SamKV<>(sw, 0));
        }

        for(SamKV<String, Integer> o : occ) {
            for(String string : s) {
                if(string.equalsIgnoreCase(o.getKey()))
                    o.setValue(o.getValue() +1);
            }
        }
        return occ;
    }

    private String[] splitString(String s, String delimiter) {
        SamArray<String> arrList = new SamArray<>();
        for(int i = 0; i < s.length(); i++) {
            StringBuilder builder = new StringBuilder();
            while(i < s.length() && !(Character.toString(s.charAt(i))).equals(delimiter)) {
                builder.append(s.charAt(i));
                i++;
            }
            arrList.add(builder.toString());
        }
        String[] arr = new String[arrList.size()];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = arrList.get(i);
        }
        return arr;
    }

    private Words getWords(String[] ss) {
        SamArray<String> arrList = new SamArray<>();
        for (String s : ss) {
            if (!arrList.contains(s))
                arrList.add(s);
        }
        String[] arr = new String[arrList.size()];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = arrList.get(i);
        }
        return new Words(arr, arrList);
    }
}
