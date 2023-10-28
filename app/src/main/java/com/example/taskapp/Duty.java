package com.example.taskapp;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;

public class Duty implements Serializable {
    private boolean completed;
    private String dutyName, dutyDesc, dutyType;
    private int flag, dutyDefImg;
    private Bitmap dutyImg;

    public Duty() {}

    public Duty(boolean completed, String dutyName, String dutyDesc, String dutyType, int flag, Bitmap dutyImg) {
        this.completed = completed;
        this.dutyName = dutyName;
        this.dutyDesc = dutyDesc;
        this.dutyType = dutyType;
        this.flag = flag;
        this.dutyImg = dutyImg;
    }

    public Duty(boolean completed, String dutyName, String dutyDesc, String dutyType, int flag, int dutyDefImg) {
        this.completed = completed;
        this.dutyName = dutyName;
        this.dutyDesc = dutyDesc;
        this.dutyType = dutyType;
        this.flag = flag;
        this.dutyDefImg = dutyDefImg;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getDutyDesc() {
        return dutyDesc;
    }

    public void setDutyDesc(String dutyDesc) {
        this.dutyDesc = dutyDesc;
    }

    public String getDutyType() {
        return dutyType;
    }

    public void setDutyType(String dutyType) {
        this.dutyType = dutyType;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getDutyDefImg() {
        return dutyDefImg;
    }

    public void setDutyDefImg(int dutyDefImg) {
        this.dutyDefImg = dutyDefImg;
    }

    public Bitmap getDutyImg() {
        return dutyImg;
    }

    public void setDutyImg(Bitmap dutyImg) {
        this.dutyImg = dutyImg;
    }
}
