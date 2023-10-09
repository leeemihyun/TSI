package com.prj.tsi;

import lombok.Data;


@Data
public class DataVO {


    private String url; //URL
    private String writingTime; //글쓴시간
    private String writer; // 작성자
    private String titleName; //제목명
    private String communityName; // 커뮤니티명
    private String dataNum; //데이터번호
    private int viewCount; //조회수
    private int commentCount; //댓글수

}
