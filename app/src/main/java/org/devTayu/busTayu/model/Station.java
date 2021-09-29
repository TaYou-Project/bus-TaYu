package org.devTayu.busTayu.model;

public class Station {
    public String title;
    public String contents;
    public String time;
    public String writer;

    public Station(String title, String contents, String time, int i, int i1) {
        this.title = title;
        this.contents = contents;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
