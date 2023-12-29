package let.sar.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExerciseController {
    // Путь до файлов json
    static String JSON_PATH = "C:\\sarafan-RestController\\src\\main\\resources\\json_files\\";

    /**
     * Обработчик
     * @param id - имя файла
     */
    public static String GetExercise(String id) throws IOException {
        StringBuilder content = new StringBuilder();
        String filePath = JSON_PATH + id + ".json";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append("\n");
            }
            return content.toString();
        }catch (IOException e){
            return "not found";
        }

    }
}
