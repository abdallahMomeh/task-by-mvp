package com.abdallahapps.g2mdx_task.model.data.dto;

public class Event {

    private String TITLE;
    private String DESCRIPTION;
    private String LOCATION;
    private long BEGIN_TIME;
    private long END_TIME;

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String DESCRIPTION) {
        this.DESCRIPTION = DESCRIPTION;
    }

    public String getLOCATION() {
        return LOCATION;
    }

    public void setLOCATION(String LOCATION) {
        this.LOCATION = LOCATION;
    }

    public long getBEGIN_TIME() {
        return BEGIN_TIME;
    }

    public void setBEGIN_TIME(long BEGIN_TIME) {
        this.BEGIN_TIME = BEGIN_TIME;
    }

    public long getEND_TIME() {
        return END_TIME;
    }

    public void setEND_TIME(long END_TIME) {
        this.END_TIME = END_TIME;
    }
}
