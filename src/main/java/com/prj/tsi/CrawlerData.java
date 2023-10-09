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

        Document doc= Jsoup.connect("https://blossom6729.tistory.com").get();

        Elements titles=doc.select("div.post-item a span.title");

        Elements contents =doc.select("div.post-item a span.excerpt");

        for( int i=0; i<8; i++){
            Element title =titles.get(i);
            Element content =contents.get(i);
        }

        return  list;
    }


}
