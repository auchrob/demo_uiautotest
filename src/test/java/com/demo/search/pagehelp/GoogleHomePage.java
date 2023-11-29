package com.demo.search.pagehelp;

import org.openqa.selenium.By;

public class GoogleHomePage {
        public static final By imageSearch = By.xpath("//div[@jscontroller='lpsUAf']");

        public static final By imageUpload = By.xpath("//span[@role='button']");

        public static final By searchResultView = By.xpath("//div[contains(text(),'grid')]");

        public static final By third_result = By.xpath("//title[contains(text(),'足球') or contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'soccer')]");

}
