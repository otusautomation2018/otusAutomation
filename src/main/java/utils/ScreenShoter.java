package utils;

import data.CommonForTheSiteData;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenShoter {
    private static final String DIRECTORY_OF_SCREENSHOTS = "images\\";
    private static final String EXTENSION_OF_SCREENSHOTS = ".png";

    public static void makeAScreenshot(String fileName){
        File scrFile = ((TakesScreenshot) Driver.getInstance()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(CommonForTheSiteData.LOG_DIRECTORY + DIRECTORY_OF_SCREENSHOTS + fileName + EXTENSION_OF_SCREENSHOTS));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
