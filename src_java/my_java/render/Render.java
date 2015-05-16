package my_java.render;

import clojure.lang.PersistentHashMap;
import clojure.lang.PersistentVector;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Travis on 12/4/2014.
 */

public class Render {
    public Camera camera;
    public static SpriteBatch spriteBatch;
    public static ShapeRenderer shapeRenderer;
    //static Box2DDebugRenderer box2DDebugRenderer;
    //static FrameBuffer frameBuffer;
    //static BitmapFont font = new BitmapFont();

    public static FreeTypeFontGenerator generator;
    //FreeTypeFontGenerator.FreeTypeBitmapFontData font15 = generator.generateData(15);
    public static FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    public static Map<String, BitmapFont> font;

    public static Map<String, Color> color;

    public Render(){
        camera = new Camera();
        reset();
    }

    public static void reset(){
        //GdxNativesLoader.load();
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        //box2DDebugRenderer = new Box2DDebugRenderer();

        /*

        try {
            frameBuffer = new FrameBuffer(Pixmap.Format.RGB888, AppManager.WIDTH, AppManager.HEIGHT, false);
        } catch (Exception e) {
            //fbo could not be created
        }

        */

        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        font = new HashMap<String, BitmapFont>();
        color = new HashMap<String, Color>();
        //generateFont();
        setColor();
    }

    static void generateFont(){
        generator = new FreeTypeFontGenerator(Gdx.files.internal("data/Crysta.ttf"));

        Array<Integer> sizes = new Array<Integer>();
        sizes.add(16);
        sizes.add(20);
        sizes.add(32);
        sizes.add(42);
        sizes.add(64);
        sizes.add(128);

        for(Integer s:sizes){
            parameter.size = (int)s;
            font.put(s.toString(), generator.generateFont(parameter));
        }

        //dispose generator after all fonts have been created
        generator.dispose();
        generator = null;
    }

    public static void setColor() {
        color.put("white", new Color(1.0f, 1.0f, 1.0f, 1.0f));
        color.put("white-50%", new Color(1.0f, 1.0f, 1.0f, 0.5f));
        color.put("black", new Color(0.0f, 0.0f, 0.0f, 1.0f));
        color.put("black-50%", new Color(0.0f, 0.0f, 0.0f, 0.5f));
        color.put("orange", new Color(1.0f, 0.5f, 0.0f, 1.0f));
        color.put("red", new Color(1.0f, 0.0f, 0.0f, 1.0f));
        color.put("blue", new Color(0.0f, 0.5f, 1.0f, 1.0f));
        color.put("pink", new Color(1.0f, 0.0f, 1.0f, 1.0f));
        color.put("green", new Color(0.0f, 1.0f, 0.0f, 1.0f));
    }

    static public void dispose(){
        spriteBatch.dispose();
        shapeRenderer.dispose();
        //box2DDebugRenderer.dispose();
        for (BitmapFont value : font.values()) {
            value.dispose();
        }
    }

    static public Sprite screenToTexture (int x, int y, int width, int height) {
        TextureRegion frameBufferRegion = ScreenUtils.getFrameBufferTexture(x, y, width, height);
        return new Sprite(frameBufferRegion);
    }

    //static public void drawShapes(Array<>, OrthographicCamera camera){
    //    shapeRenderer.setProjectionMatrix(camera.combined);
        //
    //}

    //TODO
    //store MyCamera object within render due to the fact it's a pawn in rendering
    //mechanics.

    static public void setProjectionMatrix_Sprite(OrthographicCamera camera){
        spriteBatch.setProjectionMatrix(camera.combined);
    }
    static public void setProjectionMatrix_Shape(OrthographicCamera camera){
        shapeRenderer.setProjectionMatrix(camera.combined);
    }

    static public void setDrawColor(){}

    static public void drawCircle(float x, float y, float r){
        shapeRenderer.circle(x, y, r);
    }
}
