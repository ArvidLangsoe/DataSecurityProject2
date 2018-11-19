package files;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileLoaderTest {

    @Test
    public void givenFileExistsThenFileILoadedCorrectly() throws IOException {
        FileLoader fl = new FileLoader();
        String[] lines =fl.loadLines("SomeFile.txt");

        for(int i= 0; i<5;i++){
            assertEquals((i+1)+"",lines[i]);
        }
    }
}
