package pl.skwarsoft.adventofcode.year2023;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class Day1Test {

    private final Day1 trebuchet = new Day1();

    @Test
    void getCalibrationValueSum() {
        String exampleDocument = """
                1abc2
                pqr3stu8vwx
                a1b2c3d4e5f
                treb7uchet""";

        int calibrationValueSum = trebuchet.getCalibrationValueSum(exampleDocument);

        assertThat(calibrationValueSum).isEqualTo(142);
    }

    @Test
    void getCalibrationValue() {
        Map<String, Integer> lines = Map.of("1abc2", 12,
                "pqr3stu8vwx", 38,
                "a1b2c3d4e5f", 15,
                "treb7uchet", 77);

        lines.forEach((textLine, calibrationValue) -> assertThat(trebuchet.getCalibrationValue(textLine)).isEqualTo(calibrationValue));
    }
}