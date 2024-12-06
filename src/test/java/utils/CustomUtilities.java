package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class CustomUtilities {
    private static final String ALPHANUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvwxyz"
            + "0123456789";

    private static final Random random = new Random();
    private static String propertyValue;

    public static String getConfigProperty(String propertyName) {
        Properties prop = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") +
                    "/src/test/resources/properties/config.properties");
            prop.load(fileInputStream);
            propertyValue = prop.getProperty(propertyName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyValue;
    }

    public static String generateRandomAlphanumeric(int length) {
        StringBuilder result = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            // Select a random character from the ALPHANUMERIC_CHARACTERS
            int randomIndex = random.nextInt(ALPHANUMERIC_CHARACTERS.length());
            result.append(ALPHANUMERIC_CHARACTERS.charAt(randomIndex));
        }

        return result.toString();
    }

}
