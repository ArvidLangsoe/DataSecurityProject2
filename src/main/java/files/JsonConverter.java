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

    private Gson gson = new Gson();


    public HashMap<String, ArrayList<Permissions>> retrieveFromFile(String filePath){
        ClassLoader classLoader = getClass().getClassLoader();
        Scanner scanner = null;
        scanner = new Scanner(classLoader.getResourceAsStream(filePath));

        String fileText = scanner.useDelimiter("\\A").next();
        scanner.close();

        HashMap<String, ArrayList<Permissions>> result = gson.fromJson(fileText, HashMap.class);

        return result;
    }


}
