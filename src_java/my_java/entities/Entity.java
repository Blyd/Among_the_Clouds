package my_java.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

/**
 * Created by Travis on 5/15/2015.
 */

public class Entity {
    private Array<Entity> entities = new Array<Entity>();
    private Vector2 position = new Vector2(0,0);
    private boolean drawable = true;

    public Entity(Vector2 position){
        this.position = position;
    }

    public void update(){
        for (int i = 0; i < entities.size; i++) {
            entities.get(i).update();
        }
    }

    public Vector2 getPosition() {return position;}
    public void setPosition(Vector2 position) {this.position = position;}

    public boolean isDrawable() {return drawable;}
    public void setDrawable(boolean drawable) {this.drawable = drawable;}

    public Array<Entity> getEntities() {return entities;}
    public void setEntities(Array<Entity> entities) {this.entities = entities;}
}
