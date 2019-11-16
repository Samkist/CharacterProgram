/* Created By: Sam Pizette
 * On Date: 11/12/2019
 * Project Name: Characters
 */
package me.Samkist.Character;

import me.Samkist.Character.SamUtil.SamArray;
import me.Samkist.Character.SamUtil.SamKV;

class Parser {

    Parser(String rawString, CharacterGUI gui) {
        gui.clearFields();
        try {
            errorCheck(rawString);
        } catch(StringIndexOutOfBoundsException e) {
            gui.messageBox(e.getMessage());
            return;
        }
        rawString = rawString.replace("\t", "").trim().replaceAll("^ +| +$|( )+", " ");
        if(Character.toString(rawString.charAt(rawString.length()-1)).matches("\\p{Punct}"))
            rawString = rawString.substring(0, rawString.length()-1);
        String[] splitStringLowercase = splitString(rawString.toLowerCase(), " ");
        removePunctuation(splitStringLowercase);
        Words words = getWords(splitStringLowercase);
        SamArray<SamKV<String, Integer>> occurrences = getOccurrences(words, splitStringLowercase);
        SamArray<SamKV<String, Integer>> charOccurrences = getCharOccurrences(getUniqueCharacters(splitStringLowercase), splitStringLowercase);
        gui.addCharOccurrence(getCharacterCount(splitStringLowercase) + " characters");
        gui.addWordOccurrence(splitStringLowercase.length + " words");
        occurrences.forEach(kv -> gui.addWordOccurrence(kv.getKey() + " : " + kv.getValue()));
        charOccurrences.forEach(kv -> gui.addCharOccurrence(kv.getKey() + " : " + kv.getValue()));
    }

    private int getCharacterCount(String[] strings) {
        int count = 0;
        for(String s : strings) {
            for(char c : s.toCharArray()) {
                count++;
            }
        }
        return count;
    }

    private void removePunctuation(String[] strings) {
        for(int i = 0; i < strings.length; i++) {
            if(Character.toString(strings[i].charAt(strings[i].length()-1)).matches("\\p{Punct}")) {
                strings[i] = strings[i].substring(0, strings[i].length()-1);
            }
        }
    }

    private void errorCheck(String s) throws StringIndexOutOfBoundsException {
        if(s.length() == 0)
            throw new StringIndexOutOfBoundsException("String is empty");
    }

    private SamArray<SamKV<String, Integer>> getCharOccurrences(SamArray<String> chars, String[] strings) {
        SamArray<SamKV<String, Integer>> occ = new SamArray<>();
        for(String c : chars) {
            occ.add(new SamKV<>(c, 0));
        }

        for(SamKV<String, Integer> o : occ) {
            for(String string : strings) {
                for(char c : string.toCharArray()) {
                    if(Character.toString(c).equalsIgnoreCase(o.getKey()))
                        o.setValue(o.getValue()+1);
                }
            }
        }
        return occ;
    }

    private SamArray<String> getUniqueCharacters(String[] strings) {
        SamArray<String> chars = new SamArray<>();
        for(String string : strings) {
            for(char c : string.toCharArray()) {
                if(!chars.contains(Character.toString(c)))
                    chars.add(Character.toString(c));
            }
        }
        return chars;
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
