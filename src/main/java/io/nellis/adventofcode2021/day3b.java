package io.nellis.adventofcode2021;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class day3b {
    public static void main(String[] args) throws IOException {
        File file = new ClassPathResource("day3input.txt").getFile();

        int oxygenGeneratorRating = 0;
        int c02ScrubberRating = 0;
//        Calculate O2 generator rating
        System.out.println("Oxygen Generator rating:");
        var listOf1s = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < listOf1s.length; i++) {
            var numberOfLines = 0;
            Scanner scanner = new Scanner(file);
            var lastMatchingLine = "";

            while (scanner.hasNextLine()) {
                var currentLine = scanner.nextLine();

                if (currentLine.startsWith(builder.toString())) {
                    lastMatchingLine = currentLine;
                    if (currentLine.charAt(i) == '1') {
                        listOf1s[i]++;
                    }
                    numberOfLines++;
                }
            }
            scanner.close();

            if (numberOfLines == 1) {
                System.out.println(lastMatchingLine);
                oxygenGeneratorRating = Integer.parseInt(lastMatchingLine, 2);
                System.out.println(oxygenGeneratorRating);
                break;
            }

//           Use 2.0 to force floating number division and thus ensure proper checking
            if (listOf1s[i] >= numberOfLines / 2.0) {
                builder.append("1");
            } else {
                builder.append("0");
            }

//        Running this loop without the below case produced no output. The answer in this case must be the string
//        builder, because the final loop had two matching strings: one ending in 0 and the other ending in 1. Because
//        both possibilities existed, whichever value was set to the string builder must be in the list and will
//        therefore be the last remaining value in the list
            if (numberOfLines == 2) {
                System.out.println(builder);
                oxygenGeneratorRating = Integer.parseInt(builder.toString(), 2);
                System.out.println(oxygenGeneratorRating);
            }

        }

//        Reset variables for second run
        listOf1s = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        builder = new StringBuilder();
        System.out.println("C02 Scrubber rating:");

        for (int i = 0; i < listOf1s.length; i++) {
            var numberOfLines = 0;
            Scanner scanner = new Scanner(file);
            var lastMatchingLine = "";

            while (scanner.hasNextLine()) {
                var currentLine = scanner.nextLine();

                if (currentLine.startsWith(builder.toString())) {
                    lastMatchingLine = currentLine;
                    if (currentLine.charAt(i) == '1') {
                        listOf1s[i]++;
                    }
                    numberOfLines++;
                }
            }
            scanner.close();

            if (numberOfLines == 1) {
                System.out.println(lastMatchingLine);
                c02ScrubberRating = Integer.parseInt(lastMatchingLine, 2);
                System.out.println(c02ScrubberRating);
                break;
            }

            if (listOf1s[i] >= numberOfLines / 2.0) {
                builder.append("0");
            } else {
                builder.append("1");
            }
        }

        System.out.println("Final answer:");
        System.out.println(oxygenGeneratorRating * c02ScrubberRating);
    }
}