package com.prj.tsi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlerData {

    /**
     * 82쿡 크롤링 데이터 추출 함수(robots.txt 통과)
     * @return VO객체를 담은 list
     * @throws IOException
     */
    public static List<DataVO> cook82GetData() throws IOException {
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
                String dataNum=co_link.get(i).text();
                String writer=user_function.get(i).text(); //작성자
                String writeDate=regdateNumbers.get(i).text(); //작성일자

                String viewCnt=index.get(i).text();
                int vewCntIndex=viewCnt.lastIndexOf(" ");
                viewCnt=viewCnt.substring(vewCntIndex,viewCnt.length()).trim().replace(",","");

                DataVO dataVO=new DataVO(link,writeDate,writer,titleName,"82KOOK",dataNum,Integer.parseInt(viewCnt), commentCount);
                list.add(dataVO);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return list;
    }


    /**
     * 보배드림 크롤링 데이터 추출 함수(robots.txt 통과)
     * @return VO객체를 담은 list
     * @throws IOException
     */
    public static List<DataVO> bobaeGetData() throws IOException {
        List<DataVO> list =new ArrayList<>();
        try {
            Document doc= Jsoup.connect("https://www.bobaedream.co.kr/list?code=best&type=list&page=1").get(); //html 가져오기
            Elements titles=doc.select("#boardlist > tbody > tr > td.pl14 > a"); //제목,링크, 링크안에 숫자가 데이터번호
            Elements commCnt=doc.select("#boardlist > tbody > tr > td.pl14 > a > span > strong "); //댓글 수
            Elements viewCnt=doc.select("#boardlist > tbody > tr > td.count "); //조회 수
            Elements writer=doc.select("#boardlist > tbody > tr > td.author02 > span.author "); //작성자
            Elements writeDate=doc.select("#boardlist > tbody > tr > td.date "); //작성 시간
            Elements index=doc.select("#boardlist > tbody > tr"); //갯수돌리는
            for(int i=0;i<index.size();i++){
                System.out.println("hi");
            }
            //#boardlist > tbody > tr:nth-child(1)
        }catch (IOException e){
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 클리앙 크롤링 데이터 추출 함수
     * @return VO객체를 담은 list
     * @throws IOException
     */
    public static List<DataVO> clienGetData() throws IOException {
        List<DataVO> list =new ArrayList<>();
        try {
            Document doc= Jsoup.connect("https://www.bobaedream.co.kr/list?code=best&type=list&page=2").get(); //html 가져오기
        }catch (IOException e){
            e.printStackTrace();
        }

        return list;
    }


    /**
     * 이토랜드 크롤링 데이터 추출 함수
     * @return VO객체를 담은 list
     * @throws IOException
     */
    public static List<DataVO> etlandGetData() throws IOException {
        List<DataVO> list =new ArrayList<>();
        try {
            Document doc= Jsoup.connect("https://www.bobaedream.co.kr/list?code=best&type=list&page=2").get(); //html 가져오기
        }catch (IOException e){
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 에펨코리아 크롤링 데이터 추출 함수
     * @return VO객체를 담은 list
     * @throws IOException
     */
    public static List<DataVO> fmkGetData() throws IOException {
        List<DataVO> list =new ArrayList<>();
        try {
            Document doc= Jsoup.connect("https://www.bobaedream.co.kr/list?code=best&type=list&page=2").get(); //html 가져오기
        }catch (IOException e){
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 웃긴대학 크롤링 데이터 추출 함수
     * @return VO객체를 담은 list
     * @throws IOException
     */
    public static List<DataVO> humorunivGetData() throws IOException {
        List<DataVO> list =new ArrayList<>();
        try {
            Document doc= Jsoup.connect("https://www.bobaedream.co.kr/list?code=best&type=list&page=2").get(); //html 가져오기
        }catch (IOException e){
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 인스티즈 크롤링 데이터 추출 함수
     * @return VO객체를 담은 list
     * @throws IOException
     */
    public static List<DataVO> instizGetData() throws IOException {
        List<DataVO> list =new ArrayList<>();
        try {
            Document doc= Jsoup.connect("https://www.bobaedream.co.kr/list?code=best&type=list&page=2").get(); //html 가져오기
        }catch (IOException e){
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 인벤 크롤링 데이터 추출 함수
     * @return VO객체를 담은 list
     * @throws IOException
     */
    public static List<DataVO> invenGetData() throws IOException {
        List<DataVO> list =new ArrayList<>();
        try {
            Document doc= Jsoup.connect("https://www.bobaedream.co.kr/list?code=best&type=list&page=2").get(); //html 가져오기
        }catch (IOException e){
            e.printStackTrace();
        }

        return list;
    }

    /**
     * 엠팍 크롤링 데이터 추출 함수
     * @return VO객체를 담은 list
     * @throws IOException
     */
    public static List<DataVO> mlbparkGetData() throws IOException {
        List<DataVO> list =new ArrayList<>();
        try {
            Document doc= Jsoup.connect("https://www.bobaedream.co.kr/list?code=best&type=list&page=2").get(); //html 가져오기
        }catch (IOException e){
            e.printStackTrace();
        }

        return list;
    }


    public static void main(String[] args) throws IOException {
        List<DataVO> list=CrawlerData.cook82GetData();
        System.out.println(list);
    }


}


