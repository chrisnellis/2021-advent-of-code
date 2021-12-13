package io.nellis.adventofcode2021;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class day3a {
    public static void main(String[] args) throws IOException {
//        Intuition: Input is 12 columns long. For each column in the file, count the number of 1s that appear for later analysis
        var listOf1s = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        var numberOfLines = 0;

        File file = new ClassPathResource("day3input.txt").getFile();

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            var currentLine = scanner.nextLine();
            numberOfLines++;
            for (int i = 0; i < currentLine.length(); i++) {
                if (currentLine.charAt(i) == '1') {
                    listOf1s[i]++;
                }
            }
        }
        scanner.close();

        System.out.println("Gamma rate:");
        StringBuilder gammaRate = new StringBuilder();
        for (int i = 0; i < listOf1s.length; i++) {
//           If this passes the conditional, there were more 1s than 0s in that column
            if (listOf1s[i] > numberOfLines / 2) {
                gammaRate.append("1");
            } else {
                gammaRate.append("0");
            }
        }
        System.out.println(gammaRate);
        int gammaValue = Integer.parseInt(gammaRate.toString(), 2);
        System.out.println("In decimal: " + gammaValue);

        System.out.println("");
        System.out.println("Epsilon rate:");
        StringBuilder epsilonRate = new StringBuilder();
        for (int i = 0; i < listOf1s.length; i++) {
            if (listOf1s[i] < numberOfLines / 2) {
                epsilonRate.append("1");
            } else {
                epsilonRate.append("0");
            }
        }

        System.out.println(epsilonRate);
        int epsilonValue = Integer.parseInt(epsilonRate.toString(), 2);
        System.out.println("In decimal: " + epsilonValue);

        System.out.println("\n\nFinal Answer:");
        System.out.println(gammaValue * epsilonValue);
    }
}
