package controllers;

import helpers.SortedProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class PropertiesController {

    public SortedProperties getProperties(String filename){

        SortedProperties properties = new SortedProperties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        FileInputStream input = null;
        try {
            input = new FileInputStream(Objects.requireNonNull(classLoader.getResource("user.accounts.properties")).getPath().
                    replace("/C:", "C:").replace("target/test-classes", "src/main/resources"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            assert input != null;
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }

}
