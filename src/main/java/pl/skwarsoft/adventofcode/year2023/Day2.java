package pl.skwarsoft.adventofcode.year2023;

import lombok.extern.slf4j.Slf4j;
import pl.skwarsoft.adventofcode.util.FileUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <a href="https://adventofcode.com/2023/day/2">Day 2: Cube Conundrum</a>
 */
@Slf4j
public class Day2 {

    public static final int RED_CUBES_SUM = 12;
    public static final int GREEN_CUBES_SUM = 13;
    public static final int BLUE_CUBES_SUM = 14;

    public static void main(String[] args) throws URISyntaxException, IOException {
        Day2 cubeConundrum = new Day2();
        var fullDocument = FileUtils.readFromResources("year2023/day2_input.txt");
        int possibleGamesIndexSum = cubeConundrum.getPossibleGamesIndexSum(fullDocument);
        log.info("possibleGamesIndexSum={}", possibleGamesIndexSum);
    }

    public int getPossibleGamesIndexSum(String document) {
        Objects.requireNonNull(document, "document is required");
        List<Game> games = document.lines()
                .map(Game::valueOf)
                .toList();
        return getPossibleGamesIndexSum(games);
    }

    private int getPossibleGamesIndexSum(List<Game> games) {
        List<List<Game>> allCombinations = getAllCombinations(games, 0);
        allCombinations.forEach(games1 -> {
            List<Integer> idxList = games1.stream()
                    .map(Game::index)
                    .toList();
            log.info("" + idxList);
        });
        return 0;
    }

    private List<List<Game>> getAllCombinations(List<Game> games, int level) {
        List<List<Game>> all = new ArrayList<>();
        for (int i = level; i < games.size(); i++) {
            List<Game> thisList = new ArrayList<>();
            thisList.add(games.get(i));
            all.add(thisList);
            all.addAll(getAllCombinations(games.subList(i, games.size()), ++level));
        }
        return all;
    }

    private GameScore checkGameScore(List<Game> games) {
        int redCount = 0;
        int greenCount = 0;
        int blueCount = 0;
        for (Game game : games) {
            for (Bag bag : game.bags()) {
                redCount += bag.redCubesCount();
                greenCount += bag.greenCubesCount();
                blueCount += bag.blueCubesCount();
            }
        }
        if ((redCount == RED_CUBES_SUM) && (greenCount == GREEN_CUBES_SUM) && (blueCount == BLUE_CUBES_SUM)) {
            return GameScore.WIN;
        } else if ((redCount > RED_CUBES_SUM) || (greenCount > GREEN_CUBES_SUM) || (blueCount > BLUE_CUBES_SUM)) {
            return GameScore.TOO_MANY;
        } else {
            return GameScore.TOO_LITTLE;
        }
    }

    record Game(int index, List<Bag> bags) {
        static Game valueOf(String line) {
            Matcher matcher = Pattern.compile("Game (\\d+):([\\s\\S]+)").matcher(line);
            if (matcher.matches()) {
                int gameIdx = Integer.parseInt(matcher.group(1));
                String[] bagsStr = matcher.group(2).split(";");
                List<Bag> bagList = Arrays.stream(bagsStr)
                        .map(Bag::valueOf)
                        .toList();
                log.info("Game {} loaded {} bags", gameIdx, bagList.size());
                return new Game(gameIdx, bagList);
            }
            throw new IllegalArgumentException("Invalid format: " + line);
        }
    }

    record Bag(int redCubesCount, int greenCubesCount, int blueCubesCount) {
        static Bag valueOf(String line) {
            Matcher matcher = Pattern.compile("(\\d+) (\\w+),?").matcher(line);
            int redCount = 0;
            int greenCount = 0;
            int blueCount = 0;
            while (matcher.matches()) {
                int count = Integer.parseInt(matcher.group(1));
                String color = matcher.group(2);
                switch (color) {
                    case "red":
                        redCount = count;
                        break;
                    case "green":
                        greenCount = count;
                        break;
                    case "blue":
                        blueCount = count;
                        break;
                    default:
                        throw new IllegalArgumentException("Unsupported color " + color);
                }
            }
            return new Bag(redCount, greenCount, blueCount);
        }
    }

    enum GameScore {
        TOO_LITTLE, WIN, TOO_MANY
    }

}
