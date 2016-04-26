package com.s4you.FlyBeau.model;

/**
 * Created by NamNgo on 12/04/2016.
 */
public class Home {
    public String name;
    public String time;
    public String descrip;
    public String group;
    public int imageSRC;
    public int avatarSRC;

    public Home() {
    }

    public Home(String name, String group, String descrip, String time,  int imageSRC, int avatarSRC) {
        this.name = name;
        this.time = time;
        this.descrip = descrip;
        this.group = group;
        this.imageSRC = imageSRC;
        this.avatarSRC = avatarSRC;
    }
}
