Maven+testng+selenium

1. code in /src/test/java/com/demo/search
2. run GoogleSearch.xml in /src/test/java/com/demo/search
3. screenshort in /images
4. logs in /demotest.log
5. use Atuoit help to upload file, autoit uplaod scirpt /res/autoit_tool/autoit_upload.exe.
6. upload file /res/autoit_tool/123.jpg
6  VISIT_RESULT=3 in /res/properties/config.properties
7. chromedriver in /res/web_dir/chromedriver.exe
8. testng result in /test-output/google_search_by_image.html

for all search result, we use two way to compare that
1. compare the image descript words, if the percentage more than 50%, we think the scearch is correctly
2. use algorithm compare the impage,if the percentage more than 40%, we think the scearch is correctly
   there are three image comparison algorithm, we use hashing algorithm. It seems the accuracy is very low
   we need find a better one.

 Image comparison algorithm
    Using histogram principle to achieve similarity comparison of image content
    mean hashing to achieve similarity comparison of image content
    and Hamming distance algorithm to achieve similarity comparison of image content

In the image comparsion part, i cannot visite google service form IDE. I got follow error:
Connect to encrypted-tbn2.gstatic.com:443 [encrypted-tbn2.gstatic.com/172.217.160.110] failed: Connection timed out: connect

I debug it can work use baidu HTTPS url. I think it's domestic network problem. I use fake url to replay that part.
If your network support google image service, you can you can comment this section. there is logic support that.See detail in code,


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
         allUrl.add(dogurl);
         //***************************************no needed, if you can visite google image service