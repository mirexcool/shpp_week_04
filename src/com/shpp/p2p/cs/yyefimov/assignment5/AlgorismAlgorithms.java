package com.shpp.p2p.cs.yyefimov.assignment5;

import com.shpp.cs.a.console.TextProgram;

public class AlgorismAlgorithms extends TextProgram {

    public void run() {
        /* Sit in a loop, reading numbers and adding them. */
        while (true) {
            String n1 = readLine("Enter first number:  ");
            String n2 = readLine("Enter second number: ");
            println(n1 + " + " + n2 + " = " + addNumericStrings(n1, n2));
            println();
        }
    }

    /**
     * Given two string representations of nonnegative integers, adds the
     * numbers represented by those strings and returns the result.
     *
     * @param n1 The first number.
     * @param n2 The second number.
     * @return A String representation of n1 + n2
     */
    private String addNumericStrings(String n1, String n2) {
        StringBuilder sb = new StringBuilder();
        int buffer = 0;
        int i = n1.length() - 1;
        int j = n2.length() - 1;
        //  We are going from the end for each string of input.
        //  We will add each number from each string even if string is over(we will add 0)
        //  When both string is over we should check if we need to add saved result from previous iteration.
        while (i >= 0 || j >= 0 || buffer > 0) {
            int num1, num2;
            if (i < 0)
                num1 = 0;
            else num1 = n1.charAt(i) - '0';
            if (j < 0)
                num2 = 0;
            else num2 = n2.charAt(j) - '0';
            int sum = num1 + num2 + buffer;
            //  We are adding two digits, we will add to result string correct result and save data if result go over 10.
            //  Example: '9' + '8' = 17. 17 % 10 = 7 (we add it to result string '7'). 17 / 10 = 1 (we save it for the next calcs)
            sb.append(sum % 10);
            buffer = sum / 10;
            i--;
            j--;
        }
        //  We're putting digits to result from other sid, so we need to reverse it.
        sb.reverse();
        return sb.toString();
    }
}

