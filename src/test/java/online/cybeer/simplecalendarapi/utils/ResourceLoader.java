package online.cybeer.simplecalendarapi.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Vladyslav Tkachenko
 * @since 2025/04/07
 */
public class ResourceLoader {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ResourceLoader() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static <T> T loadResource(String path, Class<T> valueType) {
        try (InputStream inputStream = getResourceAsStream(path)) {
            return objectMapper.readValue(inputStream, valueType);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load resource: " + path, e);
        }
    }

    public static String loadResourceAsString(String path) {
        try (InputStream inputStream = getResourceAsStream(path)) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load resource as string: " + path, e);
        }
    }

    private static InputStream getResourceAsStream(String path) {
        InputStream inputStream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
        if (inputStream == null) {
            throw new IllegalArgumentException("Resource not found: " + path);
        }
        return inputStream;
    }
}
