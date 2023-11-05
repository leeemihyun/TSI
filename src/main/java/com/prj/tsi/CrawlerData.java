package com.prj.tsi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlerData {

    public static List<DataVO> getData() throws IOException {
        List<DataVO> list =new ArrayList<>();

        try{
            Document doc= Jsoup.connect("https://www.82cook.com/entiz/enti.php?bn=15").get(); //html 가져오기
            Elements titles=doc.select("#bbs > table > tbody > tr > td.title"); //제목
            Elements user_function=doc.select("#bbs > table > tbody > tr > td.user_function"); //작성자
            Elements regdateNumbers=doc.select("#bbs > table > tbody > tr > td.regdate.numbers"); //작성일자
            Elements co_link=doc.select("#bbs > table > tbody > tr > td.numbers > a"); //링크

            Elements index=doc.select("#bbs > table > tbody > tr"); //제목
            for(int i=3; i<index.size()-3;i++){

                String titleTemp=titles.get(i).text();
                int tiIndex=titleTemp.lastIndexOf(" ");
                String titleName=titleTemp.substring(0,tiIndex);
                String commCnt=titleTemp.substring(tiIndex,titleTemp.length()).trim().replace(",","");
                int commentCount=0;
                try {
                  commentCount=Integer.parseInt(commCnt);
                }catch (NumberFormatException nfe){
                  commentCount=0;
                }
                String link="https://www.82cook.com/entiz/"+co_link.get(i).attr("href"); //링크
                String writer=user_function.get(i).text(); //작성자
                String writeDate=regdateNumbers.get(i).text(); //작성일자

                String viewCnt=index.get(i).text();
                int vewCntIndex=viewCnt.lastIndexOf(" ");
                viewCnt=viewCnt.substring(vewCntIndex,viewCnt.length()).trim().replace(",","");

                DataVO dataVO=new DataVO(link,writeDate,writer,titleName,"82KOOK",Integer.parseInt(viewCnt), commentCount);
                list.add(dataVO);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return list;
    }

    public static void main(String[] args) throws IOException {
        List<DataVO> list=CrawlerData.getData();
        System.out.println(list);
    }


}


