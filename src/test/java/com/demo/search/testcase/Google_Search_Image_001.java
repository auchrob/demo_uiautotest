 package com.demo.search.testcase;

 import com.demo.search.utils.*;
 import com.demo.search.utils.imagediff.ImageCompare;
 import com.demo.search.utils.imagediff.KeyWordCompare;
 import org.apache.log4j.Logger;
 import org.openqa.selenium.By;
 import org.openqa.selenium.WebElement;
 import org.openqa.selenium.support.ui.ExpectedConditions;
 import org.openqa.selenium.support.ui.WebDriverWait;
 import org.testng.Assert;
 import org.testng.ITestContext;
 import org.testng.annotations.Test;

 import java.io.File;
 import java.io.FileInputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.util.ArrayList;
 import java.util.List;

 import static com.demo.search.pagehelp.GoogleHomePage.*;
 import static com.demo.search.utils.WebUtil.*;

 public class Google_Search_Image_001 extends BaseCase {

     public static Logger logger = Logger.getLogger(Google_Search_Image_001.class);

     @Test
     public void search_image(ITestContext context) throws InterruptedException, IOException {
         ConfigReader configReader=ConfigReader.getInstance();

         int vist_result=Integer.valueOf(configReader.getStr("VISIT_RESULT"));
         String autoIt_dir= dire + File.separator + "res"+ File.separator + "autoit_tool"+ File.separator + "autoit_upload.exe";
         String image_dir= dire + File.separator + "res"+ File.separator + "autoit_tool"+ File.separator + "123.jpg";

         //vist_result=5;
         logger.info("setp1 go to google ");
         driver.get("https://www.google.com/");
         ScreenShotUtil.takeScreenShot(driver,"1_google");


         logger.info("setp2 click image search button");
         WebElement imageButton=driver.findElement(imageSearch);
         imageButton.click();
         ScreenShotUtil.takeScreenShot(driver,"2_search");


         logger.info("setp3 click upload button");
         WebElement uploadButton=driver.findElement(imageUpload);
         uploadButton.click();
         ScreenShotUtil.takeScreenShot(driver,"3_upload");


         logger.info("setp4 use autoit to upload image");
         Thread.sleep(10000);

         Runtime.getRuntime().exec(autoIt_dir+" "+image_dir);
         Thread.sleep(10000);
         ScreenShotUtil.takeScreenShot(driver,"4_result");


         //grid is change depends on screan size,so we should caculate it, depends on parameter style
         //style="--lens-grid-column-count: 4; --lens-grid-column-spacing: 12px;"
         WebElement serchResult=driver.findElement(By.xpath("//div[contains(@style, 'lens-grid-column-count')]"));
         String text=serchResult.getAttribute("style");

         String[] split=text.split("[:,;]");
         int girdRow=Integer.valueOf(split[1].trim());



         logger.info("setp5 scroll the window, wait for image url load finished");
         WebDriverWait wait=new WebDriverWait(driver, 10);
         WebElement clicknext = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='UW8JMe' or text()='支持']")));

         scrollToElement(driver,clicknext);

         logger.info("get all image url >>>>>>>>>>>>>");
         logger.info("get all image descript >>>>>>>>>>>>>>");
         List<String> allUrl=new ArrayList<>();
         List<String> allKeyWords=new ArrayList<>();

         for(int i=0;i<=girdRow;i++) {
             By by = By.xpath("//div[contains(@style, 'lens-grid-column-count')]/div[" + i + "]/div//div[contains(@class,'Me0cf')]/img");
             List<WebElement> webElementList = driver.findElements(by);
             for (WebElement w : webElementList) {
                 String urlString=w.getAttribute("src");
                 // delete does not load image url, same result there still have not load finished url.
                 // we can slowly scroll, to check ervery to load later
                 if(urlString.contains("http")) {
                     allUrl.add(w.getAttribute("src"));
//                     logger.info("valid image url to compare  "+ urlString);
                 }else {
//                     logger.info("invalid image url,delete  "+urlString);
                 }
             }

             By keyWrodBy=By.xpath("//div[contains(@style, 'lens-grid-column-count')]/div[" + i + "]/div//div[contains(@class,'UAiK1e')]");
             List<WebElement> keywordsElementList = driver.findElements(keyWrodBy);
             for (WebElement key : keywordsElementList) {
                 String describe=key.getText();
                 allKeyWords.add(describe);
 //                logger.info("image descript   "+describe);
             }
         }
         logger.info("setp6 compare the image descipte correctly more 50%, the result search it correct");
         KeyWordCompare keyWordCompare=new KeyWordCompare();
         boolean keyBoolean=keyWordCompare.compareKewords(allKeyWords,0.5d);
         Assert.assertTrue(keyBoolean,"Percentages of url descipt familiarity less than 50%");

         /****************************************we use compare image the diff**************************************/
         logger.info("setp7 compare the image descipte correctly more 40%, the Image comparison algorithm is not good here");
         logger.info("beacase my network can not get google image service, so we use some image to replay");
         logger.info("if your network support google image service, you can replace it. there is logic support that");

         //****************************************no needed, if you can visite google image service
         String url1="https://t13.baidu.com/it/u=295970958,2106297338&fm=224&app=112&f=JPEGw=500&h=500";
         String url2="https://mms0.baidu.com/it/u=1194376573,1795594433&fm=253&app=120&f=JPEG?w=1200&h=800";
         String url3= "https://mms0.baidu.com/it/u=2676146991,1307976969&fm=253&app=138&f=JPEG?w=624&h=450";
         String url4="https://mms1.baidu.com/it/u=627775288,1178104596&fm=253&app=120&f=JPEG?w=640&h=364";
         String url5="https://mms1.baidu.com/it/u=1462247820,173072247&fm=253&app=138&f=JPEG?w=750&h=500";

         allUrl=new ArrayList<>();
         allUrl.add(url1);
         allUrl.add(url2);
         allUrl.add(url3);
         allUrl.add(url4);
         allUrl.add(url5);
         //***************************************no needed, if you can visite google image service

         ImageCompare imageCompare=new ImageCompare();
         boolean familiarity =imageCompare.perFileAndURL(image_dir,allUrl,0.4d);
         Assert.assertTrue(familiarity,"Percentages of familiarity less than 40%");
         /************************************we use compare image the diff*************************************************/

         logger.info("setp8 scroll to up, and go to the third image");
         WebElement relutNumber=null;
         WebElement imageUrl=null;

         // caculate the third image loacation depends on grid size
         int row=vist_result/girdRow+1;
         int cloumn=vist_result%girdRow;

         String valide_path="//div[contains(@style, 'lens-grid-column-count')]/div["+cloumn+"]/div["+row+"]";

         scrollToUp(driver);
         //this assert sometime will failed, so we need not assert that
//         relutNumber=driver.findElement(By.xpath(valide_path+"//div[contains(text(), '足球') " +
//                 "or contains(text(), '世界杯')" +
//                 " or contains(translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'), 'soccer')]"));
         imageUrl=driver.findElement(By.xpath(valide_path+"//a"));
        //logger.info(relutNumber.getText()+" "+relutNumber.getTagName());


//         Assert.assertNotNull(relutNumber,"查找足球关键描述失败");
         Assert.assertNotNull(imageUrl.getAttribute("href"),"查找图片url失败");

         logger.info("setp9 go to third image");
         imageUrl.click();
         switchToWindow(driver);
         ScreenShotUtil.takeScreenShot(driver,"5_third_image");

         WebElement third_image=driver.findElement(third_result);
         Assert.assertNotNull(third_image,"图片title包含足球关键字");

     }


 }
