package main.java.com.trabalhoa3.delivery.database.seed;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import main.java.com.trabalhoa3.delivery.util.ConvertToCsv;

public class Seeder {
    private final Map<String, String[]> files = new HashMap<>();
    private final String FILE_EXTENSION_TYPE = ".csv";

    public Seeder() {
        this.files.put("User", new String[] { "id", "name", "email", "password" });
        this.createFiles();
    }

    private void createFiles() {
        Path path = Paths.get("src", "main", "java", "com", "trabalhoa3", "delivery", "database", "files");

        this.files.forEach((fileName, fileHeaders) -> {
            Path filePath = Paths.get(path.toAbsolutePath().toString(),
                    fileName.concat(this.FILE_EXTENSION_TYPE));

            if (Files.exists(filePath))
                return;
            try {
                Files.createFile(filePath);

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            try (BufferedWriter writer = Files.newBufferedWriter(filePath,
                    StandardCharsets.UTF_8)) {
                writer.write(ConvertToCsv.convert(fileHeaders));
                writer.write("\n");

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
