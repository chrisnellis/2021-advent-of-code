package io.nellis.adventofcode2021;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class day2b {
    public static void main(String[] args) throws IOException {
        var horizontalPosition = 0;
        var depth = 0;
        var aim = 0;

        File file = new ClassPathResource("day2input.txt").getFile();

        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            var currentReading = scanner.nextLine();
            var commandType = currentReading.split(" ")[0];
            var value = Integer.valueOf(currentReading.split(" ")[1]);
            switch (commandType) {
                case "forward":
                    horizontalPosition = horizontalPosition + value;
                    depth = depth + (aim * value);
                    break;
                case "down":
                    aim = aim + value;
                    break;
                case "up":
                    aim = aim - value;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + commandType);
            }
        }
        scanner.close();
        System.out.println(depth * horizontalPosition);
    }
}
