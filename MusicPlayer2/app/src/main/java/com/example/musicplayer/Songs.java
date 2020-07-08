package com.example.musicplayer;

public class Songs {
    String id, name, singer, linkAnh, linkMp3 ;
    double dur;

    public Songs() {
    }

    public Songs(String id, String name, String singer, String linkAnh, String linkMp3, double dur) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.linkAnh = linkAnh;
        this.linkMp3 = linkMp3;
        this.dur=dur;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getLinkAnh() {
        return linkAnh;
    }

    public void setLinkAnh(String linkAnh) {
        this.linkAnh = linkAnh;
    }

    public String getLinkMp3() {
        return linkMp3;
    }

    public void setLinkMp3(String linkMp3) {
        this.linkMp3 = linkMp3;
    }

    public double getDur() {
        return dur;
    }

    public void setDur(float dur) {
        this.dur = dur;
    }
}
