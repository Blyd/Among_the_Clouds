package my_java.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import my_java.render.Render;

import java.util.HashMap;

/**
 * Created by Travis on 5/15/2015.
 */
public class World {
    Render render;

    private Array<Entity> entities;

    public static int drawables_size = 5;
    private HashMap<Integer, Array<Entity_Drawable>> drawables; //Array<Entity_Drawable>

    public World(Render render){
        this.render = render;

        entities = new Array<Entity>();
        drawables = new HashMap<Integer, Array<Entity_Drawable>>();//new Array<Entity_Drawable>();
        for (int i = 0; i < drawables_size; i++){
            drawables.put(i, new Array<Entity_Drawable>());
        }
    }

    public void update(){
        for (int i = 0; i < entities.size; i++){
            recur_update(entities.get(i));
        }
    }

    public void render(){
        Gdx.graphics.getGL20().glClearColor( 0, 0, 0, 1 );
        Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        Render.spriteBatch.setProjectionMatrix(render.camera.worldCamera.combined);
        Render.spriteBatch.begin();

        for (int i = 0; i < drawables_size; i++){
            if (drawables.get(i).size > 0){
                for (int j = 0; j < drawables.get(i).size; j++){
                    drawables.get(i).get(j).getSprite().draw(Render.spriteBatch);
                }
            }
        }

        Render.spriteBatch.end();

        clearDrawables();
    }


    public Array<Entity> getEntities() {return entities;}
    public void setEntities(Array<Entity> entities) {this.entities = entities;}

    private void clearDrawables(){
        for (int i = 0; i < drawables_size; i++){
            drawables.get(i).clear();
        }
    }

    private void recur_update(Entity entity){
        if (entity instanceof Entity_Drawable){
            Entity_Drawable entity_drawable = (Entity_Drawable) entity;
            entity_drawable.update(drawables);
        } else {
            entity.update();
        }


        if (entity.getEntities().size > 0) {
            for (int i = 0; i < entity.getEntities().size; i++){
                recur_update(entity);
            }
        }
    }
}