package pl.skwarsoft.adventofcode.util;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

@UtilityClass
public class FileUtils {

    public static String readFromResources(String filePath) throws URISyntaxException, IOException {
        var url = FileUtils.class.getClassLoader().getResource(filePath);
        if (url == null) {
            throw new IllegalArgumentException("Invalid file path: " + filePath);
        }
        return Files.readString(Path.of(url.toURI()));
    }

}
