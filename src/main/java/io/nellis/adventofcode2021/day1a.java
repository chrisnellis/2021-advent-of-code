package io.nellis.adventofcode2021;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class day1a {
    public static void main(String[] args) throws IOException {
        var numberOfTimesIncreased = 0;
        Integer previousReading = null;

        File file = new ClassPathResource("day1input.txt").getFile();

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            var currentReading = Integer.valueOf(scanner.nextLine());
            if (previousReading != null) {
                if (currentReading > previousReading) {
                    numberOfTimesIncreased++;
                }
            }
            previousReading = currentReading;
        }
        scanner.close();
        System.out.println(numberOfTimesIncreased);
    }
}