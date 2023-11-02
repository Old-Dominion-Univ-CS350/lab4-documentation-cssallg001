package edu.odu.cs;

public class WordCount {
    public String word;
    public int count;

    public WordCount (String word, int count) {
        this.word = word;
        this.count = count;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof WordCount)) {
            return false;
        }
        WordCount wc = (WordCount)obj;
        return count == wc.count && word.equals(wc.word);
    }
}
