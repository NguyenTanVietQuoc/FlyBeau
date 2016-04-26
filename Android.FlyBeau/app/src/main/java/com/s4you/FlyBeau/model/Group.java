package com.s4you.FlyBeau.model;

/**
 * Created by NamNgo on 11/04/2016.
 */
public class Group {
    public String groupName;
    public String numberPeople;
    public int imageSRC;
    public Group() {
    }

    public Group(String groupName, String numberPeople, int imageSRC) {
        this.groupName = groupName;
        this.numberPeople = numberPeople;
        this.imageSRC = imageSRC;
    }
}
