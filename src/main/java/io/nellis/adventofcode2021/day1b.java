package io.nellis.adventofcode2021;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class day1b {
    public static void main(String[] args) throws IOException {
        var numberOfTimesIncreased = 0;
        Integer previousReadingA = null;
        Integer previousReadingB = null;
        Integer previousReadingC = null;

        File file = new ClassPathResource("day1input.txt").getFile();

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            var currentReading = Integer.valueOf(scanner.nextLine());
//          Used for initial cases
            if (previousReadingA == null && previousReadingB == null && previousReadingC == null) {
                previousReadingA = currentReading;
                continue;
            } else if (previousReadingA != null && previousReadingB == null && previousReadingC == null) {
                previousReadingB = currentReading;
                continue;
            } else if (previousReadingA != null && previousReadingB != null && previousReadingC == null) {
                previousReadingC = currentReading;
                continue;
//                Now that initial set of previous readings are set up, we can do comparisons on a 3 day window
            } else if (previousReadingA != null && previousReadingB != null && previousReadingC != null) {
                if (previousReadingB + previousReadingC + currentReading > previousReadingA + previousReadingB + previousReadingC) {
                    numberOfTimesIncreased++;
                }
//                Shift the window to allow current reading to be C
                previousReadingA = previousReadingB;
                previousReadingB = previousReadingC;
                previousReadingC = currentReading;
            }

        }
        scanner.close();
        System.out.println(numberOfTimesIncreased);
    }
}
