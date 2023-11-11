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
                //System.out.println(index.get(i).text());
                String titleName=titles.get(i).text();//제목
                String url="https://www.bobaedream.co.kr/"+titles.get(i).attr("href");//링크

                String dataNum=url.substring(url.indexOf("No="),url.indexOf("&vdate="));

                String commentCountTemp=commCnt.get(i).text();//댓글 수
                String viewCountTemp=viewCnt.get(i).text();//조회 수
                int commentCount=0;

                try {
                    commentCount=Integer.parseInt(commentCountTemp);
                }catch (NumberFormatException nfe){
                    commentCount=0;
                }

                int viewCount=0;
                try {
                    viewCount=Integer.parseInt(viewCountTemp);
                }catch (NumberFormatException nfe){
                    viewCount=0;
                }


                String writerName=writer.get(i).text();
                String writingTime=writeDate.get(i).text();
                DataVO dataVO=new DataVO(url,writingTime,writerName,titleName,"bobae",dataNum,viewCount,commentCount);
                list.add(dataVO);
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
            Document doc= Jsoup.connect("https://www.clien.net/service/board/park").get(); //html 가져오기
            Elements titles=doc.select("#div_content > div.list_content  > div > div.list_title > a.list_subject"); //제목,링크, 링크안에 숫자가 데이터번호
            Elements viewCnt=doc.select("#div_content > div.list_content  > div  > div.list_hit > span"); //조회 수
            Elements commCnt=doc.select("#div_content > div.list_content  > div  > div.list_title > a.list_reply.reply_symph > span"); //댓글 수
            Elements writer=doc.select("#div_content > div.list_content  > div  > div.list_author > span.nickname"); //작성자
            Elements writeDate=doc.select("#div_content > div.list_content  > div  > div.list_time > span > span"); //작성 시간
            //URL,글쓴시간,작성자,제목명,커뮤니티명,데이터번호,조회수,댓글수
            Elements index=doc.select("#div_content > div.list_content > div"); //갯수돌리는
            for(int i=0;i<index.size();i++){
                String titleName=titles.get(i).text(); //제목
                String viewCount=viewCnt.get(i).text(); //조회수

                int viewCountTemp=0;

                try {
                    viewCountTemp=Integer.parseInt(viewCount);
                }catch (NumberFormatException nfe){
                    viewCountTemp=0;
                }


                String url="https://www.clien.net"+titles.get(i).attr("href"); //url
                String dataNum=url.substring(url.indexOf("park/")+5,url.indexOf("?"));
                Elements temp=index.get(i).select("div.list_title > a.list_reply.reply_symph > span"); //댓글 수
                String commCntTemp=temp.text();
                if(commCntTemp.equals("")){
                  commCntTemp="0";
                }

                int commentCount=0;
                try {
                    commentCount=Integer.parseInt(commCntTemp);
                }catch (NumberFormatException nfe){
                    commentCount=0;
                }


                String writerTemp=writer.get(i).text();
                if(writerTemp.equals("")){
                    writerTemp=writer.select("img").attr("title");
                }

                String writeTime=writeDate.get(i).text(); //글쓴시간

                DataVO dataVO=new DataVO(url,writeTime,writerTemp,titleName,"clien",dataNum,viewCountTemp,viewCountTemp);
                list.add(dataVO);
            }
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
            Document doc= Jsoup.connect("https://www.etoland.co.kr/pages/pop.php?&page=1").get(); //html 가져오기

            Elements index=doc.select("body > main > section > div.board_new2_wrap > ul > li:not( .middle_line):not(.ad):not(.ad):not(.board_new2_title):not(.ad_list)"); //갯수돌리는

            for(int i=0;i<index.size();i++){
                System.out.println(index.get(i).text());
                System.out.println("https://www.etoland.co.kr"+index.get(i).select("div.subject > a ").attr("href"));
                System.out.println(index.get(i).select("div.subject > a ").text());
                //body > main > section > div.board_new2_wrap > ul > li:nth-child(6) > div.subject > a > span
                System.out.println("-----------------------------");
            }

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
        //List<DataVO> list=CrawlerData.cook82GetData();
        //List<DataVO> list=CrawlerData.clienGetData();
        //List<DataVO> list=CrawlerData.bobaeGetData();
        etlandGetData();
        //System.out.println(list);
    }


}


