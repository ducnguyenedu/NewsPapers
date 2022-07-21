/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.util.Base64;

/**
 *
 * @author duchi
 */
public class News {

    private int id;
    private String title;
    private String writer;
    private Date date;
    private String content;
    private byte[] image;
    private String encode = new String();

    public News() {
    }

    private void encodeImage() {
         this.encode = Base64.getEncoder().encodeToString(this.image);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
        encodeImage();
    }

    public String getEncode() {
         encodeImage();
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
        encodeImage();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
