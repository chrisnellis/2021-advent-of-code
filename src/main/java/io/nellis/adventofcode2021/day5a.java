package io.nellis.adventofcode2021;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class day5a {
    public static void main(String[] args) throws IOException {
        File file = new ClassPathResource("day5input.txt").getFile();
        Scanner scanner = new Scanner(file);
        var greatestXCoordinate = 0;
        var greatestYCoordinate = 0;

        while (scanner.hasNextLine()) {
            String workingLine = scanner.nextLine();
            String[] stringParts = workingLine.split(" -> ");
            Integer workingFirstXCoordinate = Integer.valueOf(stringParts[0].split(",")[0]);
            Integer workingFirstYCoordinate = Integer.valueOf(stringParts[0].split(",")[0]);
            Integer workingSecondXCoordinate = Integer.valueOf(stringParts[1].split(",")[0]);
            Integer workingSecondYCoordinate = Integer.valueOf(stringParts[1].split(",")[0]);

            if (workingFirstXCoordinate > greatestXCoordinate) {
                greatestXCoordinate = workingFirstXCoordinate;
            }

            if (workingFirstYCoordinate > greatestYCoordinate) {
                greatestYCoordinate = workingFirstYCoordinate;
            }

            if (workingSecondXCoordinate > greatestXCoordinate) {
                greatestXCoordinate = workingSecondXCoordinate;
            }

            if (workingSecondYCoordinate > greatestYCoordinate) {
                greatestYCoordinate = workingSecondYCoordinate;
            }
        }
        scanner.close();

        System.out.println(greatestXCoordinate);
        System.out.println(greatestYCoordinate);

    }
}
