package files;

import com.google.gson.Gson;
import permissions.Permissions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class JsonConverter {

    private static Gson gson = new Gson();

    public static void writeFile(String filepath, HashMap<String, ArrayList<Permissions>> object){
        String jsonObject = gson.toJson(object);

        try (PrintStream out = new PrintStream(new FileOutputStream(filepath))) {
            out.print(jsonObject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, ArrayList<Permissions>> retrieveFromFile(String filepath){

        Scanner scanner = null;
        try {
            scanner = new Scanner( new File(filepath) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String fileText = scanner.useDelimiter("\\A").next();
        scanner.close();

        HashMap<String, ArrayList<Permissions>> result = gson.fromJson(fileText, HashMap.class);

        return result;
    }


}
