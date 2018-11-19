package files;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

public class FileLoader {

    public String[] loadLines(String filePath) throws IOException {
        String file;
        ClassLoader classLoader = getClass().getClassLoader();
        file= IOUtils.toString(classLoader.getResourceAsStream(filePath));

        return file.split(System.lineSeparator());
    }
}
