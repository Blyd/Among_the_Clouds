package my_java.entities;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Travis on 5/15/2015.
 */
public class Entity_nonDrawable extends Entity{
    public Entity_nonDrawable(Vector2 position){
        super(position);
        super.setDrawable(false);
    }
}
