package com.demo.search.utils.imagediff;

import com.demo.search.utils.HttpImage;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;

public class KeyWordCompare {
    public static Logger logger = Logger.getLogger(KeyWordCompare.class);

    public boolean compareKewords (List<String> keyWordList, double exp){
        double same=0d,count=0d;

        for(String str:keyWordList){
           if(str.contains("足球")
                ||str.contains("运动")
                ||str.toLowerCase().contains("soccer")
                   ||str.contains("世界杯")   ){
               same++;
           }
           count++;
       }
        double fami=same/count;
        logger.info("=====Word percentages of familiarity  "+fami);
        return  fami >= exp? true: false;
    }
}
