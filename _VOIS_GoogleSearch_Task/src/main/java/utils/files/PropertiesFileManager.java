package utils.files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Dealing with the easiest type of helpers.files ,
 * properties file which basically contains a key
 * and value such hash tables and arrays ...
 *
 * @author Kareem Mohamed
 * @version 1.0
 * @since 18/6/2021
 *
 *
 */
public class PropertiesFileManager {
    private  FileInputStream fis;
    private  Properties properties;


    public PropertiesFileManager(String filePath) {
        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        properties = new Properties();
        try {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * @param propertyKeys the keys in the specified properties file you need their values
     * @return 2D Object array represent the the user credentials
     */
    public  Object[][] getProperties(String[] propertyKeys ) {
        Object resultData[][] = new Object [1][propertyKeys.length];
        try {

            for (int i = 0; i < 1; i++) {
                /* Load Properties */
                for (int j = 0; j < propertyKeys.length; j++) {
                    resultData[i][j] = properties.getProperty(propertyKeys[j]);
                }
            }
            return resultData;
        } catch (NullPointerException nullPtrExp) {
            System.out.println("Call loadPropertiesData first Please!! ");
            return null;
        }
    }

    /**
     * get the value of a specified key
     * @param key
     * @return value of the key in the properties file
     */
    public  String getPropertyValue(String key){
        return properties.getProperty(key) ;
    }
}
