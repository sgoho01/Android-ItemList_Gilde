package com.android.song.itemlistglide;


import java.util.Date;

/**
 * 리스트뷰의 아이템을 담기 위한 데이터 Class(DTO)
 */
public class Item {

    private String image_url;
    private String name;
    private String content;
    private Date date;

    public Item(String image_url, String name, String content, Date date) {
        this.image_url = image_url;
        this.name = name;
        this.content = content;
        this.date = date;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
