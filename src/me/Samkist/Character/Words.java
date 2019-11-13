package me.Samkist.Character;

import me.Samkist.Character.SamUtil.SamArray;

public class Words {
    private String[] wordsArray;

    public String[] getWordsArray() {
        return wordsArray;
    }

    public void setWordsArray(String[] wordsArray) {
        this.wordsArray = wordsArray;
    }

    public SamArray<String> getWordsList() {
        return wordsList;
    }

    public void setWordsList(SamArray<String> wordsList) {
        this.wordsList = wordsList;
    }

    private SamArray<String> wordsList;

    public Words(String[] wordsArray, SamArray<String> wordsList) {
        setWordsArray(wordsArray);
        setWordsList(wordsList);
    }
}
