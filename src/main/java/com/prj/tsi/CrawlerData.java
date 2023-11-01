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
            Document doc= Jsoup.connect("https://www.82cook.com/entiz/enti.php?bn=15").get();
            //System.out.println(doc);
            Elements titles=doc.select("#bbs > table > tbody > tr > td.title"); //제목
            Elements user_function=doc.select("#bbs > table > tbody > tr > td.user_function"); //작성자
            Elements regdateNumbers=doc.select("#bbs > table > tbody > tr > td.regdate.numbers"); //작성일자
            Elements numbers=doc.select("#bbs > table > tbody > tr > td.numbers"); //조회수
            Elements link=doc.select("#bbs > table > tbody > tr > td.numbers > a"); //링크

            Elements index=doc.select("#bbs > table > tbody > tr"); //제목
            System.out.println(index.get(3));

            for(Element element: index){
                System.out.println(element.text());
                System.out.println("--------------");
            }

        }catch (IOException e){
            e.printStackTrace();
        }

        //가져올 데이터 태그 #bbs > table > tbody > tr:nth-child(1)
        //Elements titles=doc.select("div.post-item a span.title");
        //Elements contents =doc.select("div.post-item a span.excerpt");

        return list;
    }

    public static void main(String[] args) throws IOException {
        getData();
    }


}


