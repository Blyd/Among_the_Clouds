package my_java.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.HashMap;

/**
 * Created by Travis on 5/15/2015.
 */

public class Player extends Entity_Drawable{

    public Player(){
        super(new Vector2(400, 300),
                new Sprite(
                        new Texture(
                                Gdx.files.internal("player_sprite_placeholder.png"))),
                4);
    }

    @Override
    public void update(HashMap<Integer, Array<Entity_Drawable>> drawables){
        super.update(drawables);

        super.getPosition().y -= 0.1f;

        super.getSprite().setPosition(super.getPosition().x,super.getPosition().y);
    }
}
