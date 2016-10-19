package com.zhiyi.test.section;

import com.zhiyi.community.dao.CommunityMapper;
import com.zhiyi.section.model.Community;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-core.xml")
public class CommunityAnalysisTest {
    @Autowired
    private CommunityMapper communityMapper;

    @Test
    public void testSaveDeliverySection() throws Exception {
        /*double lat = 31.266139d;  //31.266139,121.351737
        double lng = 121.351737d;*/

        //double lng= 121.351737d;
        //double lat = 31.266139d;  //31.266139,121.351737

        //List<Community> list = communityMapper.searchByCoordinates(lat, lng);

        //System.out.println(list.size());

        String primaryPath = "/Users/zhoutaotao/Downloads/new result coordinates/无联华中学.txt";
        List<String> primaryList = Files.readAllLines(Paths.get(primaryPath), Charset.defaultCharset());

        Map<String,String> coordinatesMap = new HashMap<String,String>();
        for(String primaryLine :primaryList){
            if(primaryLine.contains("address")){
                String[] coordinatesArray = primaryLine.split("####");
                String key = coordinatesArray[0].substring(coordinatesArray[0].indexOf("address") + 8);

                coordinatesMap.put(key, coordinatesArray[1]);
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        Set<String> keySet = coordinatesMap.keySet();
        for(String key:keySet){
            String lnglat  = coordinatesMap.get(key);
            String[] coordinatesArray = lnglat.split(",");
            double latitude = Double.parseDouble(coordinatesArray[0]);
            double longitude = Double.parseDouble(coordinatesArray[1]) ;
            List<Community> communityList = communityMapper.searchByCoordinates(latitude, longitude);

            for(Community community:communityList){
                stringBuffer.append(lnglat).append("####")
                        .append(community.getCommunityName()).append("####")
                        .append(community.getAddress()).append("####")
                        .append(community.getHouseholds()).append("####")
                        .append(community.getModifyYears()).append("####")
                        .append(community.getPrices()).append("\n");
            }
        }

        Files.write(Paths.get("/Users/zhoutaotao/Downloads/new result coordinates/小区数据.txt"), stringBuffer.toString().getBytes());

    }


    @Test
    public void testSave() throws Exception {

        String primaryPath = "/Users/zhoutaotao/Downloads/new result coordinates/遗漏小区.txt";
        List<String> primaryList = Files.readAllLines(Paths.get(primaryPath), Charset.defaultCharset());

        Map<String,String> coordinatesMap = new HashMap<String,String>();
        for(String primaryLine :primaryList){
            if(primaryLine.contains("address")){
                String[] coordinatesArray = primaryLine.split("####");
                String key = coordinatesArray[0].substring(coordinatesArray[0].indexOf("address") + 8);

                coordinatesMap.put(key, coordinatesArray[1]);
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        Set<String> keySet = coordinatesMap.keySet();
        for(String key:keySet){
            String lnglat  = coordinatesMap.get(key);
            String[] coordinatesArray = lnglat.split(",");
            double latitude = Double.parseDouble(coordinatesArray[0]);
            double longitude = Double.parseDouble(coordinatesArray[1]) ;
            List<Community> communityList = communityMapper.searchByCoordinates(latitude, longitude);

            for(Community community:communityList){
                stringBuffer.append(lnglat).append("####")
                        .append(community.getCommunityName()).append("####")
                        .append(community.getAddress()).append("####")
                        .append(community.getHouseholds()).append("####")
                        .append(community.getModifyYears()).append("####")
                        .append(community.getPrices()).append("\n");
            }
        }

        Files.write(Paths.get("/Users/zhoutaotao/Downloads/小区数据1.txt"), stringBuffer.toString().getBytes());

    }
}
