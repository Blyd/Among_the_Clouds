package my_java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

/**
 * Created by Travis on 5/12/2015.
 */
public class Config extends LwjglApplicationConfiguration {
    public static int WIDTH = 800;
    public static int HEIGHT = 400;
    public Config(){
        this.title = "Planet Jumper";
        this.width = WIDTH;
        this.height = HEIGHT;
    }
}
