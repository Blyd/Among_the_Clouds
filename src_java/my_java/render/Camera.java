package my_java.render;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import my_java.Config;

/**
 * Created by Travis on 12/7/2014.
 */
public class Camera {
    public OrthographicCamera worldCamera;
    //OrthographicCamera box2dCamera;
    public OrthographicCamera guiCamera;
    public OrthographicCamera bgCamera;

    public Camera(){
        //static reset


        //box2dCamera = new OrthographicCamera();
        //box2dCamera.setToOrtho(false, my_java.Config.WIDTH / Game_Vortel.PPM, my_java.Config.HEIGHT / Game_Vortel.PPM);
        //box2dCamera.setToOrtho(false, my_java.Config.WIDTH, my_java.Config.HEIGHT);a
        worldCamera = new OrthographicCamera();
        worldCamera.setToOrtho(false, Config.WIDTH, Config.HEIGHT);

        //box2dCamera.zoom = 1.0f;
        worldCamera.zoom = 1.0f;

        guiCamera = new OrthographicCamera();
        guiCamera.setToOrtho(false, Config.WIDTH, Config.HEIGHT);

        bgCamera = new OrthographicCamera();
        bgCamera.setToOrtho(false, Config.WIDTH, Config.HEIGHT);

        //box2dCamera.update();
        worldCamera.update();
    }

    static void debug_screenCenter(OrthographicCamera camera, Vector3 color){
        Render.shapeRenderer.setColor(color.x, color.y, color.z, 1);
        //x
        Render.shapeRenderer.line(
                0 + camera.position.x - camera.viewportWidth/2,
                camera.viewportHeight/2 + camera.position.y - camera.viewportHeight/2,
                camera.viewportWidth + camera.position.x - camera.viewportWidth/2,
                camera.viewportHeight/2 + camera.position.y - camera.viewportHeight/2

        );
        //y
        Render.shapeRenderer.line(
                camera.viewportWidth/2 + camera.position.x - camera.viewportWidth/2,
                camera.viewportHeight + camera.position.y - camera.viewportHeight/2,
                camera.viewportWidth/2 + camera.position.x - camera.viewportWidth/2,
                0 + camera.position.y - camera.viewportHeight/2
        );
    }
}