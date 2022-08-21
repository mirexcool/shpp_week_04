package com.shpp.p2p.cs.yyefimov.assignment5;

import com.shpp.cs.a.console.TextProgram;

import java.io.*;
import java.util.ArrayList;
import java.util.Queue;

public class Assignment5Part4 extends TextProgram {
    //  Path to input csv file.
    private static final String INPUT_FILE_PATH = "src\\com\\shpp\\p2p\\cs\\yyefimov\\assignment5\\food-origins.csv";
    //  Index of column to show.
    private static final int COLUMN_INDEX = 0;
    //  Char what will make a quotation.
    private static final char CHAR_QUOTATION = '\"';


    @Override
    public void run() {
        ArrayList<String> result = extractColumn(INPUT_FILE_PATH, COLUMN_INDEX);

        if (result != null) {
            for (String str : result) {
                println(str);
            }
        }
    }

    //  Method will read csv file and get data from column.
    private ArrayList<String> extractColumn(String filename, int columnIndex) {
        ArrayList<String> data = new ArrayList<>();
        File file = new File(filename);

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;

            while ((str = br.readLine()) != null) {
                data.add(str);
            }
            br.close();
        } catch (IOException e) {
            println("Problem with file: " + e.getLocalizedMessage());
            return null;
        }
        return getColumnsFromFile(data, columnIndex);
    }

    //  Method will get column data from each string of csv file.
    private ArrayList<String> getColumnsFromFile(ArrayList<String> file, int columnIndex) {
        ArrayList<String> result = new ArrayList<>();

        for (String str : file) {
            result.add(getColumnsFromString(str, columnIndex));
        }
        return result;
    }

    //  Method will get each column from string what separated by ','.
    private String getColumnsFromString(String str, int columnIndex) {
        ArrayList<String> result = new ArrayList<>();
        boolean notEnd = checkForEndLine(str);
        int i = -1;

        while (notEnd) {
            int colEnd = getEndIndex(str);
            if (colEnd + 1 == str.length() && str.charAt(0) == CHAR_QUOTATION) // If it was quotation we should cut it right.
                result.add(normString(str.substring(0, colEnd + 1)));
            else result.add(normString(str.substring(0, colEnd)));

            i++;
            if (colEnd + 1 > str.length())
                str = str.substring(colEnd);
            else str = str.substring(colEnd + 1);
            notEnd = checkForEndLine(str);
        }
        //  Check for empty line.
        if (i != columnIndex)
            result.add("");
        return result.get(columnIndex);
    }

    private String normString(String str) {
        str = removeQutation(str);
        str = str.replace("\"\"", "\""); // Removing double CHAR_QUOTATION.
        return str;
    }

    //  We need to remove CHAR_QUOTATION from each side of string.
    private String removeQutation(String str) {
        if (str.length() > 1)
            if (str.charAt(0) == CHAR_QUOTATION)
                return str.substring(1, str.length() - 1);
        return str;
    }

    //  Method checking if string length > 0.
    private boolean checkForEndLine(String str) {
        return str.length() > 0;
    }

    //  Method will return index until we met ',', or quotation end.
    private int getEndIndex(String str) {
        //  Quotation.
        if (str.charAt(0) == CHAR_QUOTATION) {
            return getQuotationEnd(str);
        }
        int i = str.indexOf(',');
        //  Regular data until ','.
        if (i >= 0)
            return i;
        return str.length();
    }

    //  Method will find the end of quotation.
    private int getQuotationEnd(String str) {
        int i = 1;  // We start from 1 index, because CHAR_QUOTATION is zero index.
        while (i < str.length()) {
            if (str.charAt(i) == CHAR_QUOTATION) {
                if (i + 1 < str.length()) {
                    if (str.charAt(i + 1) == CHAR_QUOTATION)
                        i += 2; //  We skip '""' double CHAR_QUOTATION
                    else {
                        return i + 1; // Skip one char.
                    }
                } else break ; // We find the end.
            } else i++;
        }
        return i;
    }

}
