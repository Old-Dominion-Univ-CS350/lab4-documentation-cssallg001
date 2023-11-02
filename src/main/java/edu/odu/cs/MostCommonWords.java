package edu.odu.cs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class MostCommonWords implements Iterable<WordCount> {

    private WordCounter wordCounter;
    public MostCommonWords(ArrayList<File> inputs) {
        wordCounter = new WordCounter();
        for (File input: inputs) {
            readAndCount(input);
        }
    }

    private void readAndCount(File inputFile) {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(inputFile)))) {
            while (scanner.hasNext()) {
                String rawWord = scanner.next();
                String word = WordFilter.filter(rawWord);
                if (word.length() > 0) {
                    wordCounter.addInstance(word);
                }
            }
            
        } catch (IOException ex) {
            // Ignore
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        ArrayList<File> inputs = new ArrayList<>();
        for (int i = 1; i < args.length; ++i) {
            inputs.add(new File(args[i]));
        }
        new MostCommonWords(inputs).tallyWords(n);
    }

    private void tallyWords(int n) {
        int i = 0;
        System.out.println ("The " + n + " most common words are:");
        for (WordCount wc: this) {  
            System.out.println("  " + wc.word + "\t" + wc.count);
            ++i;
            if (i >= n) {
                break;
            }
        }
    }

    @Override
    public Iterator<WordCount> iterator() {
        return wordCounter.iterator();
    }
    
}
