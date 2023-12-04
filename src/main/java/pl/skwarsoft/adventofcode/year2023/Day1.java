package pl.skwarsoft.adventofcode.year2023;

import lombok.extern.slf4j.Slf4j;
import pl.skwarsoft.adventofcode.util.FileUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;

/**
 * <a href="https://adventofcode.com/2023/day/1">Day 1: Trebuchet?!</a>
 */
@Slf4j
public class Day1 {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Day1 trebuchet = new Day1();
        var fullDocument = FileUtils.readFromResources("year2023/day1_input.txt");
        int calibrationValueSum = trebuchet.getCalibrationValueSum(fullDocument);
        log.info("calibrationValueSum={}", calibrationValueSum);
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
        String joinedNumber = new String(new char[]{digits.getFirst(), digits.getLast()});
        return Integer.parseInt(joinedNumber);
    }
}
