package com.prj.tsi;


public class DataVO {


    private String url; //URL
    private String writingTime; //글쓴시간
    private String writer; // 작성자
    private String titleName; //제목명
    private String communityName; // 커뮤니티명
    private String dataNum; //데이터번호
    private int viewCount; //조회수
    private int commentCount; //댓글수

    public DataVO() {

    }

    public DataVO(String url, String writingTime, String writer, String titleName, String communityName,String dataNum, int viewCount, int commentCount) {
        this.url = url;
        this.writingTime = writingTime;
        this.writer = writer;
        this.titleName = titleName;
        this.communityName = communityName;
        this.dataNum=dataNum;
        this.viewCount = viewCount;
        this.commentCount = commentCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWritingTime() {
        return writingTime;
    }

    public void setWritingTime(String writingTime) {
        this.writingTime = writingTime;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getDataNum() {
        return dataNum;
    }

    public void setDataNum(String dataNum) {
        this.dataNum = dataNum;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    @Override
    public String toString() {
        return "DataVO{" +
                "url='" + url + '\'' +
                ", writingTime='" + writingTime + '\'' +
                ", writer='" + writer + '\'' +
                ", titleName='" + titleName + '\'' +
                ", communityName='" + communityName + '\'' +
                ", dataNum='" + dataNum + '\'' +
                ", viewCount=" + viewCount +
                ", commentCount=" + commentCount +
                '}';
    }
}
