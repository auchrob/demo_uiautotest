package com.demo.search.utils.imagediff;

import com.demo.search.testcase.Google_Search_Image_001;
import com.demo.search.utils.HttpImage;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;

import javax.imageio.ImageIO;
import javax.net.ssl.SSLContext;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class ImageCompare {
    public static Logger logger = Logger.getLogger(ImageCompare.class);

    public static void main(String[] args) {
        String url1="https://t13.baidu.com/it/u=295970958,2106297338&fm=224&app=112&f=JPEGw=500&h=500";
        String url2="https://mms0.baidu.com/it/u=1194376573,1795594433&fm=253&app=120&f=JPEG?w=1200&h=800";
        String url3= "https://mms0.baidu.com/it/u=2676146991,1307976969&fm=253&app=138&f=JPEG?w=624&h=450";
        String url4="https://mms1.baidu.com/it/u=627775288,1178104596&fm=253&app=120&f=JPEG?w=640&h=364";
        String url5="https://mms1.baidu.com/it/u=1462247820,173072247&fm=253&app=138&f=JPEG?w=750&h=500";
        String dogurl="https://ns-strategy.cdn.bcebos.com/ns-strategy/upload/fc_big_pic/part-00061-320.jpg";
        ImageCompare imageCompare=new ImageCompare();
        HttpImage image=new HttpImage();

        try {
            imageCompare.imagePHashCompare(image.convertUrlImage(url1),image.convertUrlImage(url2));
            imageCompare.imagePHashCompare(image.convertUrlImage(url1),image.convertUrlImage(dogurl));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean perFileAndURL (String image_dir, List<String> allUrl, double exp){

        InputStream original= null;
        try {
            original = new FileInputStream(new File(image_dir));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ImageCompare imageCompare=new ImageCompare();
        HttpImage httpImage=new HttpImage();

        double same=0d;
        double count=0d;
        for(String str : allUrl){
            InputStream urlStream= null;
            try {
                urlStream = httpImage.convertUrlImage(str);
                int pre = imageCompare.imagePHashCompare(original,urlStream);
                if(pre<=25){
                    same++;
                }
                count++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        double fami=same/count;
        logger.info("===== Percentages of familiarity  "+fami);
        return  fami >= exp? true: false;
    }

    public int imagePHashCompare(InputStream first,InputStream second) throws IOException {
        ImagePHash p = new ImagePHash();
        String image1;
        String image2;
        int sorce=-1;
        try {
            image1 = p.getHash(first);
            image2 = p.getHash(second);
            sorce =p.distance(image1, image2);
            System.out.println("1:1 Score is " + sorce);
            return sorce;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sorce;
    }
}
