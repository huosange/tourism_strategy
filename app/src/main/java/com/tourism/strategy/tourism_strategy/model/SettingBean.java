package com.tourism.strategy.tourism_strategy.model;

public class SettingBean {

    private int icon;
    private String name;

    public SettingBean(int icon,String name){
        this.icon=icon;
        this.name=name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
