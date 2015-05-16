package my_java.entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

/**
 * Created by Travis on 5/15/2015.
 */
public class Entity_Drawable extends Entity{
    private Sprite sprite;
    private int z_index = 0;
    private float alpha = 1.0f;

    public Entity_Drawable(Vector2 position, Sprite sprite, int z_index){
        super(position);
        super.setDrawable(true);

        this.sprite = sprite;
        this.sprite.setPosition(position.x, position.y);
        this.z_index = z_index;

        //if z_index has been set to a value that doesn't exist, correct it.
        if (this.z_index >= World.drawables_size) {
            this.z_index = World.drawables_size - 1;
        }

    }

    public void update(HashMap<Integer, Array<Entity_Drawable>> drawables){
        super.update();
        drawables.get(getZ_index()).add(this);
    }

    public Sprite getSprite() {return sprite;}
    public void setSprite(Sprite sprite) {this.sprite = sprite;}

    public int getZ_index() {return z_index;}
    public void setZ_index(int z_index) {this.z_index = z_index;}

    public float getAlpha() {return alpha;}
    public void setAlpha(float alpha) {this.alpha = alpha;}
}
