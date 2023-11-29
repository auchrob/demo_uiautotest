package com.demo.search.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

import static com.demo.search.utils.BaseCase.driver;
import static com.demo.search.utils.BaseCase.logger;

public class WebUtil {
    public static Logger logger = Logger.getLogger(WebUtil.class);


    public static String getWindowHandle(WebDriver driver){
        String hanle = driver.getWindowHandle();
        return hanle;
    }

    public static void switchWindowHandle(String sr){
        try {
            driver.switchTo().window(sr);
            logger.info("切换窗口"+sr+"成功");
        } catch (Exception e) {
            logger.error("切换窗口"+sr+"失败");
        }
    }

    public static boolean switchToWindow(WebDriver driver){
        boolean flag = false;
        try {
            String currentHandle = driver.getWindowHandle();
            Set<String> handles = driver.getWindowHandles();
            for (String s : handles) {
                if (s.equals(currentHandle))
                    continue;
                else {
                    driver.switchTo().window(s);
                }
            }
        } catch (NoSuchWindowException e) {
            logger.info("Window cound not found!", e.fillInStackTrace());
            flag = false;
        }
        return flag;
    }
    public static void scrollToButton(WebDriver driver){
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    public static void scrollToUp(WebDriver driver){
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
    }

    public static void scrollToElement(WebDriver driver, WebElement webElement) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
