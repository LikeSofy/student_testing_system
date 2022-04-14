package sample.utils.saver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import sample.entity.test.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestSaver {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public void save(Test test, File file) throws IOException {
        String result = gson.toJson(test);
        FileWriter writer = new FileWriter(file, false);
        writer.write(result);
        writer.flush();
    }
}
