package datviet.FlyBeau.model;

/**
 * Created by NamNgo on 12/04/2016.
 */
public class Home {
    public String name;
    public String time;
    public int imageSRC;
    public int avatarSRC;

    public Home() {
    }

    public Home(String name, String time, int imageSRC, int avatarSRC) {
        this.name = name;
        this.time = time;
        this.imageSRC = imageSRC;
        this.avatarSRC = avatarSRC;
    }
}