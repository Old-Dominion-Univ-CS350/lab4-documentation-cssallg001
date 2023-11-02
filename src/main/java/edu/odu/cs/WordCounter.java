package edu.odu.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class WordCounter implements Iterable<WordCount> {

    Map<String, Integer> counts;
    List<WordCount> lastList;

    public WordCounter() {
        counts = new HashMap<>();
        lastList = null;
    }

    public int numberOfDistinctWords() {
        return counts.size();
    }

    public Iterator<WordCount> iterator() {
        if (lastList == null) {
            lastList = new ArrayList<>();
            fillList();
        }
        return lastList.iterator();
    }

    private void fillList() {
        for (Entry<String, Integer> e : counts.entrySet()) {
            WordCount wc = new WordCount(e.getKey(), e.getValue());
            lastList.add(wc);
        }
        Collections.sort(lastList,
                new Comparator<WordCount>() {

                    @Override
                    public int compare(WordCount left, WordCount right) {
                        return right.count - left.count;
                    }

                });
    }

    public void addInstance(String word) {
        Integer count = counts.get(word);
        if (count == null) {
            counts.put(word, 1);
        } else {
            counts.put(word, count + 1);
        }
    }

    public String toString() {
        return counts.toString();
    }


}
