package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static String takeScreenshot(WebDriver driver, String name) {
         String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
         String filePath= System.getProperty("user.dir") + "/test-output/screenshots/" + name + "_" + timestamp + ".png";

         File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
         File dest = new File(filePath);

        try {
            Files.createDirectories(dest.getParentFile().toPath());
            Files.copy(src.toPath(), dest.toPath());
            Thread.sleep(2000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return filePath;
    }


}
