package com.shpp.p2p.cs.yyefimov.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class SyllableCounting extends TextProgram {

    public void run() {
        /* Repeatedly prompt the user for a word and print out the estimated
         * number of syllables in that word.
         */
        while (true) {
            String word = readLine("Enter a single word: ");
            println("  Syllable count: " + syllablesIn(word));
        }
    }

    /**
     * Given a word, estimates the number of syllables in that word according to the
     * heuristic specified in the handout.
     *
     * @param word A string containing a single word.
     * @return An estimate of the number of syllables in that word.
     */
    private int syllablesIn(String word) {
        int syllables = 0;
        char[] vowels = {'a', 'e', 'i', 'o', 'u', 'y'};
        word = word.toLowerCase();
        //  For every char in word.
        for (int i = 0; i < word.length(); i++) {
            //  Check if it is a vowels.
            if (charOneOf(vowels, word.charAt(i))) {
                //  Check if we not go over string length.
                if (i + 1 < word.length() - 1) {
                    //  Check for duplicate vowels in row.
                    if (!charOneOf(vowels, word.charAt(i + 1)))
                        syllables++;
                } else { //  We are at the end of string.
                    if (word.charAt(i) != 'e') {    //  Check if we are not at start.
                        if (i - 1 >= 0) //  Check for duplicate vowels in row.
                            if (!charOneOf(vowels, word.charAt(i - 1)))
                                syllables++;
                    }   //  If 'e' and not at the end.
                    else if (i != word.length() - 1)
                        syllables++;
                }
            }
        }
        //  In our case we should return at least 1 syllable. It's also mean that you are not allowed
        //  to send string that are not a word (for example "wtf", "123", " "). Every input should
        //  be a word, even if there are no one vowels in it (for example "cwm"
        //  - https://uk.upwiki.one/wiki/English_words_without_vowels).
        if (syllables == 0)
            return 1;
        return syllables;
    }

    //  Method checks if char @c one of @vowels. Return true if yes and false if not.
    private boolean charOneOf(char[] vowels, char c) {
        for (char vowel : vowels) {
            if (c == vowel)
                return true;
        }
        return false;
    }

}