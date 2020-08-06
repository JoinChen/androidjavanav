package com.bksx.android_java_nav.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @Author JoneChen
 * @Date 2020\8\3 0003-10:03
 */
public class Movies {

    /**
     * count : 1
     * start : 0
     * total : 28
     * subjects : [{"rating":{"max":10,"average":5.2,"details":{"1":106,"3":182,"2":217,"5":69,"4":52},"stars":"30","min":0},"genres":["剧情","爱情"],"title":"抵达之谜","casts":[{"avatars":{"small":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1506395043.48.jpg","large":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1506395043.48.jpg","medium":"http://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1506395043.48.jpg"},"name_en":"Xian Li","name":"李现","alt":"https://movie.douban.com/celebrity/1324619/","id":"1324619"},{"avatars":{"small":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1510552435.61.jpg","large":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1510552435.61.jpg","medium":"http://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1510552435.61.jpg"},"name_en":"Borui Dong","name":"董博睿","alt":"https://movie.douban.com/celebrity/1364257/","id":"1364257"},{"avatars":{"small":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p38096.jpg","large":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p38096.jpg","medium":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p38096.jpg"},"name_en":"Xuan Gu","name":"顾璇","alt":"https://movie.douban.com/celebrity/1316959/","id":"1316959"}],"durations":["114分钟"],"collect_count":30723,"mainland_pubdate":"2020-07-31","has_video":false,"original_title":"抵达之谜","subtype":"movie","directors":[{"avatars":{"small":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1536132336.46.jpg","large":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1536132336.46.jpg","medium":"http://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1536132336.46.jpg"},"name_en":"Wen Song","name":"宋文","alt":"https://movie.douban.com/celebrity/1397719/","id":"1397719"}],"pubdates":["2018-10-05(釜山电影节)","2020-07-31(中国大陆)"],"year":"2018","images":{"small":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2614691271.jpg","large":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2614691271.jpg","medium":"http://img3.doubanio.com/view/photo/s_ratio_poster/public/p2614691271.jpg"},"alt":"https://movie.douban.com/subject/26871465/","id":"26871465"}]
     * title : 正在上映的电影-北京
     */

    public int count;
    public int start;
    public int total;
    @SerializedName("subjects")
    public List<Movie> movieList;

}
