package hollowmemory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public final class HistoryStore {

    private static final Path HISTORY_FILE = Path.of(
            System.getProperty("user.home"),
            ".hollowmemory",
            "Historial.txt"
    );

    private HistoryStore() {
    }

    public static List<String> readAll() throws IOException {
        ensureExists();
        return new ArrayList<>(Files.readAllLines(HISTORY_FILE, StandardCharsets.UTF_8));
    }

    public static void append(String entry) throws IOException {
        ensureExists();
        Files.writeString(
                HISTORY_FILE,
                entry + System.lineSeparator(),
                StandardCharsets.UTF_8,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND
        );
    }

    public static void clear() throws IOException {
        ensureExists();
        Files.writeString(
                HISTORY_FILE,
                "",
                StandardCharsets.UTF_8,
                StandardOpenOption.TRUNCATE_EXISTING
        );
    }

    public static Path getHistoryFile() throws IOException {
        ensureExists();
        return HISTORY_FILE;
    }

    private static void ensureExists() throws IOException {
        Files.createDirectories(HISTORY_FILE.getParent());
        if (Files.notExists(HISTORY_FILE)) {
            Files.writeString(HISTORY_FILE, "", StandardCharsets.UTF_8, StandardOpenOption.CREATE_NEW);
        }
    }
}
