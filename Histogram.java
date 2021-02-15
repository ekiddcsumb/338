/**
 Histogram.java
 Abstract: To identify the characters in a user given .txt file, and display the number of times each
 character appears.
 Emilie Kidd
 2/1/2021
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Histogram {

    public static void main(String[] args)
    {
        // Array holding the possible letter values.
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K'};
        // Array that will hold the count of each letter.
        int[] counters = new int[11];
        // Filename that user types in is stored in variable.
        String filename = getFileName();
        System.out.println("Input filename: " + filename);

        read(letters, counters, filename);

        sort(letters, counters);

        display(letters, counters);
    }

    public static String getFileName()
    {
        // User enters file name.
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the file name:");
        String input = scan.nextLine();
        // Returns file name that was entered.
        return input;
    }

    public static void read(char[] letter, int[] letterCount, String filename)
    {
        // Convert filename into a File.
        File f = new File(filename);
        // A Scanner will be used to read the contents of the file.
        Scanner read = null;
        // Temporary array to hold values in file.
        // Has space for 200 values.
        String[] temp = new String[200];

        try {
            // Attempt to read the contents of the file.
            read = new Scanner(f);
        } catch (FileNotFoundException e)
        {
            // Exception if file isn't found in correct directory.
            System.out.println("Could not find file " + e);
        }

        // need to populate letterCount.
        for (int i = 0; read != null && read.hasNext(); i++)
        {
            // Need to hold chars from file in temp array,
            // then compare that array against the array of
            // all possible letters (the char[] parameter).
             String input = read.nextLine();
             temp[i] = input;
        }

        for (String c : temp)
        {
            // Compares c to each index of letter.
            // Adds to index in letterCount that corresponds with each letter.
            for (int i = 0; i < letter.length; i++)
            {
                // c must not be null and be equal to letter at index i.
                if (c != null && c.charAt(0) == letter[i])
                {
                    letterCount[i]++;
                }
            }
        }
    }


    public static void sort(char[] letter, int[] letterCount)
    {
        // keep letter and letterCount synced, bubble sort.
        // Temporary var to hold current largest char.
        char tempChar;
        int tempInt;
        for (int i = 0; i < letterCount.length - 1; i++)
        {
            if (letter[i] > letter[i+1])
            {
                tempChar = letter[i];
                tempInt = letterCount[i];
                letter[i] = letter[i+1];
                letterCount[i] = letterCount[i+1];
                letter[i+1] = tempChar;
                letterCount[i+1] = tempInt;
            }
        }
    }

    public static void display(char[] letter, int[] count)
    {
        HashMap<Character, Integer> charMap = new HashMap<>();
        int maxCount = 0;

        System.out.println("Char occurrences");
        // For each char in letter and int in count, display them side by side.
        for (int i = 0; i < letter.length; i++) {
            if (count[i] != 0)
            {
                System.out.print(letter[i] + " ");
                System.out.println(count[i] );
            }
            if (maxCount < count[i])
            {
                maxCount = count[i];
            }
        }

        System.out.println();
        for (int j = 0; j < 29; j++){
            System.out.print("=");
        }
        System.out.println();

        // Get max count.
        // Print all letters with max count.
        // Decrement max count until == 1;
        // printf("|%5s|%22s", count, letters)

        for (int i = 0; i < letter.length; i++) {
            if (count[i] != 0)
            {
                // liek if u cri evry tim
                charMap.put(letter[i], count[i]);
            }
        }
        // Print all keys with value == maxCount
        // maxValue--
        while (maxCount > 0)
        {
            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < letter.length; i++)
            {
                if (charMap.get(letter[i]) != null && charMap.get(letter[i]) >= maxCount)
                {
                    stringBuilder.append(" " + letter[i]);
                }
            }
            maxCount--;
            System.out.printf("|%5s|%21s\n", maxCount + 1, stringBuilder );
        }

        for (int j = 0; j < 29; j++){
            System.out.print("-");
        }
        System.out.println();
        System.out.printf("%7s", " ");
        for (int i = 0; i < letter.length; i++) {
            if (count[i] == 0)
            {
                System.out.print(letter[i] + " ");
            }
        }
        for (int i = 0; i < letter.length; i++) {
            if (count[i] != 0)
            {
                System.out.print(letter[i] + " ");
            }
        }
    }
}
