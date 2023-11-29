package com.demo.search.utils;

import com.demo.search.utils.imagediff.FingerPrint;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import javax.imageio.ImageIO;
import javax.net.ssl.SSLContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class HttpImage {

    public InputStream convertUrlImage(String url) throws IOException {
//        String dire = System.getProperty("user.dir");
//         FingerPrint fp1 = new FingerPrint(ImageIO.read(new File(dire + File.separator + "res"+ File.separator + "autoit_tool"+ File.separator + "123.jpg")));
//        com.demo.search.utils.imagediff.FingerPrint fp1 = new com.demo.search.utils.imagediff.FingerPrint(ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\234.jpg")));
//        BufferedImage sourceImg = null;
//        String a="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTHCQL3iEGmEEe_aWOFsJ_R19cuYgmJ3Kmq5dBAUR93vDzJJhjz";
//        String b="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSLyjwx_eylXpV5MC8S-xeJ5HpY8KIxgGRFNCvol67rfx4xy-UO";
//        String c="https://t15.baidu.com/it/u=998359119,2863684996&fm=224&app=112&f=JPEGw=500&h=500";

        InputStream source=null;

        try {
            SSLContext sslContext=sslContext = new SSLContextBuilder().loadTrustMaterial(null, (certificate, authType) -> true).build();
            CloseableHttpClient httpClient = HttpClients.custom().setSSLContext(sslContext)
                    .setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            httpGet.setHeader("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93 Safari/537.36");
            HttpResponse response = httpClient.execute(httpGet);
            response.getStatusLine();
            response.getEntity();
            source = response.getEntity().getContent();
            //sourceImg = ImageIO.read(source);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        return source;
//        com.demo.search.utils.imagediff.FingerPrint fp2 =new FingerPrint(sourceImg);
//        System.out.println(fp1.toString(true));
//        System.out.printf("sim=%f",fp1.compare(fp2));
    }
}
