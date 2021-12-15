package io.nellis.adventofcode2021;

import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class day4b {
    public static void main(String[] args) throws IOException {
        File file = new ClassPathResource("day4input.txt").getFile();
        List<Integer> numbersToBeDrawn;

        var winningNumber = 0;
        var sumOfUnmarkedNumbersOnWinningBoard = 0;

//        List of 2D Matrices to capture bingo boards
        List<List<List<Integer>>> bingoBoards = new ArrayList<>();

        Scanner scanner = new Scanner(file);

        int lineNumber = 0;

        String rawNumberInput = "";

        List<List<Integer>> workingBoard = new ArrayList<>();

        while (scanner.hasNextLine()) {
            lineNumber++;
            if (lineNumber == 1) {
                rawNumberInput = scanner.nextLine();
                continue;
            }

            String currentLine = scanner.nextLine();

            if (lineNumber % 6 == 3 || lineNumber % 6 == 4 || lineNumber % 6 == 5 || lineNumber % 6 == 0) {
                List<Integer> rowForBingoCard = new ArrayList<>();
                rowForBingoCard.add(Integer.valueOf(currentLine.substring(0, 2).trim()));
                rowForBingoCard.add(Integer.valueOf(currentLine.substring(3, 5).trim()));
                rowForBingoCard.add(Integer.valueOf(currentLine.substring(6, 8).trim()));
                rowForBingoCard.add(Integer.valueOf(currentLine.substring(9, 11).trim()));
                rowForBingoCard.add(Integer.valueOf(currentLine.substring(12, 14).trim()));
                workingBoard.add(rowForBingoCard);
            } else if (lineNumber % 6 == 1) {
                List<Integer> rowForBingoCard = new ArrayList<>();
                rowForBingoCard.add(Integer.valueOf(currentLine.substring(0, 2).trim()));
                rowForBingoCard.add(Integer.valueOf(currentLine.substring(3, 5).trim()));
                rowForBingoCard.add(Integer.valueOf(currentLine.substring(6, 8).trim()));
                rowForBingoCard.add(Integer.valueOf(currentLine.substring(9, 11).trim()));
                rowForBingoCard.add(Integer.valueOf(currentLine.substring(12, 14).trim()));
                workingBoard.add(rowForBingoCard);
                bingoBoards.add(workingBoard);
                workingBoard = new ArrayList<>();
            }
        }
        scanner.close();

        numbersToBeDrawn = Arrays.stream(rawNumberInput.split(",")).map(Integer::valueOf)
                .collect(Collectors.toList());

        List<Integer> winningBoardIDs = new ArrayList<>();
        Integer winningScore = null;

        for (Integer numberDrawn : numbersToBeDrawn) {
            for (int i1 = 0; i1 < bingoBoards.size(); i1++) {
                if (winningBoardIDs.contains(i1)) {
                    continue;
                }
                List<List<Integer>> bingoBoard = bingoBoards.get(i1);

                boolean isWinningBoard = false;

                for (List<Integer> rowOfBingoBoard : bingoBoard) {
                    Integer markAsNull = null;

                    for (int k = 0; k < rowOfBingoBoard.size(); k++) {
                        if (Objects.equals(rowOfBingoBoard.get(k), numberDrawn)) {
                            markAsNull = k;
                            break;
                        }
                    }

                    if (markAsNull != null) {
                        rowOfBingoBoard.set(markAsNull, null);

                        // check for winning board
                        Integer[] nullsPerColumn = new Integer[]{0, 0, 0, 0, 0};
                        for (int i = 0; i < bingoBoard.size(); i++) {

                            List<Integer> checkedBingoBoardRow = bingoBoard.get(i);
                            var nullsInRow = 0;

                            for (int j = 0; j < checkedBingoBoardRow.size(); j++) {

                                if (checkedBingoBoardRow.get(j) == null) {
                                    nullsInRow++;
                                    nullsPerColumn[j]++;
                                }

                                if (nullsInRow == 5 || nullsPerColumn[j] == 5) {
                                    // set winning board
                                    isWinningBoard = true;
                                }
                            }

                        }


                        break;
                    }

                }

                if (isWinningBoard) {
                    var sumOfUnmarkedNumbers = 0;
                    for (List<Integer> rowOfBingoBoard : bingoBoard) {
                        for (Integer unmarkedNumberValue : rowOfBingoBoard) {
                            if (unmarkedNumberValue != null) {
                                sumOfUnmarkedNumbers = sumOfUnmarkedNumbers + unmarkedNumberValue;
                            }
                        }
                    }

                    winningBoardIDs.add(i1);
                    System.out.println(String.format("Number Drawn: %s \t Board ID: %s\t Winning Score: %s", numberDrawn, i1, sumOfUnmarkedNumbers * numberDrawn));
                }
            }

        }

        System.out.println("Final answer:");
        System.out.println(winningScore);
    }
}
