package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class ScreenShoter {
    public static void takeAScreenshot(String fileName){
        File scrFile = ((TakesScreenshot) Driver.getInstance()).getScreenshotAs(OutputType.FILE);

        try {
            FileUtils.copyFile(scrFile, new File(".logs\\images\\" + fileName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
