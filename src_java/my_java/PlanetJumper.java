package my_java;

import clojure.lang.IFn;
import com.badlogic.gdx.ApplicationListener;
import my_java.entities.Player;
import my_java.entities.World;
import my_java.render.Render;

/**
 * Created by Travis on 5/12/2015.
 */
public class PlanetJumper implements ApplicationListener {
    //java
    Render render;

    World world;

    //clojure
    //private IFn create;
    //private IFn update;
    //private IFn render;
    //my_java.render java_render;

    public PlanetJumper(/*IFn create, IFn update, IFn render*/) {
        //java

        //clojure
        //this.create = create;
        //this.update = update;
        //this.render = render;
        //java_render = new my_java.render();
    }

    @Override
    public void create() {
        //java
        render = new Render();
        world = new World(render);
        world.getEntities().add(new Player());

        //clojure
        //create.invoke();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        world.update();
        world.render();

        //clojure
        //update.invoke();
        //render.invoke();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        Render.dispose();
    }
}
