package pl.skwarsoft.adventofcode.year2023;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

/**
 * <a href="https://adventofcode.com/2023/day/1">Day 1: Trebuchet?!</a>
 */
@Slf4j
public class Day1 {

    public static void main(String[] args) throws IOException {
        Day1 trebuchet = new Day1();
        String exampleDocument = """
                1abc2
                pqr3stu8vwx
                a1b2c3d4e5f
                treb7uchet""";
        var calibrationValueSum = trebuchet.getCalibrationValueSum(exampleDocument);
        log.info("calibrationValueSum={} for document:{}{}", calibrationValueSum, System.lineSeparator(), exampleDocument);

        var path = Path.of("src/main/resources/year2023/day1_input.txt");
        if (Files.exists(path)) {
            String fullDocument = Files.readString(path);
            calibrationValueSum = trebuchet.getCalibrationValueSum(fullDocument);
            log.info("calibrationValueSum={} for document:{}{}", calibrationValueSum, System.lineSeparator(), fullDocument);
        }
    }

    public int getCalibrationValueSum(String document) {
        Objects.requireNonNull(document, "document is required");
        return document.lines()
                .map(this::getCalibrationValue)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public int getCalibrationValue(String textLine) {
        Objects.requireNonNull(textLine, "textLine is required");
        List<Character> digits = textLine.chars()
                .filter(Character::isDigit)
                .mapToObj(value -> (char) value)
                .toList();
        String joinedNumber = new String(new char[] {digits.getFirst(), digits.getLast()});
        return Integer.parseInt(joinedNumber);
    }
}
