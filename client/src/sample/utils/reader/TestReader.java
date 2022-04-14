package sample.utils.reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import sample.entity.test.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestReader {
    public final static String[] SUPPORTED_FORMAT = new String[] { "*.json"};
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public Test open(File file) throws IOException {
        FileReader reader = new FileReader(file);
        String result = "";
        int c;
        while((c=reader.read())!=-1){
            result += (char) c;
        }
        Test test = gson.fromJson(result, Test.class);
        return test;
    }
}
