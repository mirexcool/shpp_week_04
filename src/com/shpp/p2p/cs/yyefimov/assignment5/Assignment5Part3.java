package com.shpp.p2p.cs.yyefimov.assignment5;

import acm.util.RandomGenerator;
import com.shpp.cs.a.console.TextProgram;
import com.shpp.cs.a.graphics.WindowProgram;

import java.io.*;
import java.util.ArrayList;

public class Assignment5Part3 extends TextProgram {
    //  Path to dictionary file.
    private static final String INPUT_FILE_PATH = "src\\com\\shpp\\p2p\\cs\\yyefimov\\assignment5\\en-dictionary.txt";

    @Override
    public void run() {
        File file = new File(INPUT_FILE_PATH);
        ArrayList<String> words;

        words = readDictionary(file);
        if (words.size() > 0)
            readLineAndCheckWords(words);
        else println("Dictionary is empty.");
    }

    //  Method reads data from console, getting result and printing it.
    private void readLineAndCheckWords(ArrayList<String> words) {
        ArrayList<String> res;

        while (true) {
            res = compareWordDictionary(words, readLine("Input three letters: "));
            if (res != null){
                println("Next words found:");
                for (String str: res) {
                    println(str);
                }
            }
            else println("Not find.");
        }
    }

    //  Method will check input and get words what we can to fold from input line.
    private ArrayList<String> compareWordDictionary(ArrayList<String> words, String line) {
        line = line.toLowerCase();

        if (validLine(line))
            return (getWordFromLine(words, line));
        return null;
    }

    /*  Method will check three input letters for each word from dictionary (@words), and if we can
        fold word from these letters, we will put it to result.
     */
    private ArrayList<String> getWordFromLine(ArrayList<String> words, String line) {
        String sub;
        ArrayList<String> foundWords = new ArrayList<>();
        //  For each word from dictionary.
        for (String word : words) {
            //  Get first entering of first char.
            int i = word.indexOf(line.charAt(0));

            if (i >= 0) {   // Entry has taken place.
                if (i + 1 < word.length()) { // If we still have what to check.
                    sub = word.substring(i + 1); // Cut everything before first char.
                    i = sub.indexOf(line.charAt(1)); // Get first entering of second char.
                    if (i >= 0) { // Entry has taken place.
                        if (i + 1 < sub.length()) { // If we still have what to check.
                            sub = sub.substring(i + 1); // Cut everything before second char.
                            i = sub.indexOf(line.charAt(2)); //  Get first entering of third char.
                            if (i >= 0) // Entry has taken place.
                                foundWords.add(word); // We can put this word to result.
                        }
                    }
                }
            }
        }
        if (foundWords.size() > 0) // If we found at least one word.
            return foundWords;
        return null;
    }

    //  Method checks if only letters was in input.
    private boolean validLine(String line) {
        if (line.length() != 3)
            return false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (!Character.isLetter(c))
                return false;
        }
        return true;
    }

    //  Method reading each line of file and put it to ArrayList @words.
    private ArrayList<String> readDictionary(File file) {
        ArrayList<String> words = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            while ((str = br.readLine()) != null) {
                words.add(str);
            }
            br.close();
        } catch (IOException e) {
            println("Problem with file: " + e.getLocalizedMessage());
        }
        return words;
    }
}
