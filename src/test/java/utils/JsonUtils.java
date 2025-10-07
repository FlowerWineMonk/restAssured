package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

public class JsonUtils {

    public static String readJsonFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON file: " + filePath, e);
        }
    }

    public static void compareJson(String expected, String actual, boolean strict) {
        JSONCompareMode mode = strict ? JSONCompareMode.STRICT : JSONCompareMode.LENIENT;
        try {
            JSONAssert.assertEquals(new JSONObject(expected), new JSONObject(actual), mode);
        } catch (AssertionError e) {
            throw new AssertionError("JSON comparison failed:\n" + e.getMessage());
        }
    }
}
