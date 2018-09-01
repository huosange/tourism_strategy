package com.tourism.strategy.tourism_strategy.model;

public class MainBottomData extends SelectData{

    private String name;
    private int img;
    private int selectImg;

    public MainBottomData(String name,int img,int selectImg){
        this.name=name;
        this.img=img;
        this.selectImg=selectImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getSelectImg() {
        return selectImg;
    }

    public void setSelectImg(int selectImg) {
        this.selectImg = selectImg;
    }
}
