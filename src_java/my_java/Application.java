package my_java;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import my_java.Config;
import my_java.PlanetJumper;

/**
 * Created by Travis on 5/15/2015.
 */
public class Application {
    public static void main(String args[]){
        Config config = new Config();
        new LwjglApplication(new PlanetJumper(), config);
    }
}
