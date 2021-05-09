package ddt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Dealing with the easiest type of files ,
 * properties file which basically contains a key
 * and value such a hash tables and so on  ...
 */
public class PropertiesFileManager {

    public static Properties loadPropertiesData(String filePath) {
        try {
        FileInputStream fis;
        Properties properties;

            fis = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(fis);
            return properties;
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
            System.out.println("File Not Found ");

        } catch (IOException e) {
            e.printStackTrace();
        }
       return null ;
    }
    /**
     *
     * @param filePath file source in the local device
     * @param propertyKeys the keys in the specified properties file you need their values
     * @param rowNum how many user or many rows to return 2D array
     * @return 2D Object array represent the the user credentials
     */
    public static Object[][] getProperties(String filePath, String[] propertyKeys , int rowNum) {
        Object resultData[][] = new Object [rowNum][propertyKeys.length];
        /*[1][6]
        * f   l   Mo    66 66 6
        * */


        try {

            for (int i = 0; i < rowNum; i++) {
                /* Load Properties */
                for (int j = 0; j < propertyKeys.length; j++) {    /*propertyKeys[1] = "FirsrName"*/
                    resultData[i][j] = loadPropertiesData(filePath).getProperty(propertyKeys[j]);
                }
            }
            return resultData;
        } catch (NullPointerException nullPtrExp) {
            nullPtrExp.printStackTrace();
            return null;
        }
    }
}
