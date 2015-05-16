package my_java.input;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import my_java.Config;
import my_java.render.Render;

/**
 * Created by Travis on 2/27/2015.
 */
public class PointDragDisplacement {
    private Input input;
    private Vector2 translationVector_0 = new Vector2(0,0);
    private Vector2 lastPos_0 = new Vector2(0,0);
    private Vector2 updatedPos_0 = new Vector2(0,0);
    private boolean clickActive_0 = false;

    PointDragDisplacement(Input input) {
        this.input = input;
    }

    public void update (int x) {
        //for (int i = 0; i < 5; i++) {
            if (input.touchDiagnostic.get(x).touchPressed()) {
                clickActive_0 = true;
            }


            if (clickActive_0) {
                lastPos_0 = input.touchDiagnostic.get(x).getCoordinatePressed().cpy();
                //updatedPos_0 = input.getCoordinatePressed().cpy();
                if (input.touchDiagnostic.get(x).touchDragged()) {
                    clickActive_0 = false;
                    updatedPos_0 = input.touchDiagnostic.get(x).getCoordinateDragged().cpy();
                    translationVector_0 = new Vector2(updatedPos_0.cpy().sub(lastPos_0.cpy()));

                }
                return;
            }


            clickActive_0 = false;

            if (input.touchDiagnostic.get(x).touchDragged()) {
                updatedPos_0 = input.touchDiagnostic.get(x).getCoordinateDragged().cpy();
                translationVector_0 = new Vector2(updatedPos_0.cpy().sub(lastPos_0.cpy()));
                //debug();
                lastPos_0 = updatedPos_0.cpy();
            }

            if (!(input.touchDiagnostic.get(x).touchDragged())){
                //updatedPos_0 = lastPos_0.cpy();
                translationVector_0 = new Vector2(updatedPos_0.cpy().sub(lastPos_0.cpy()));
                //debug();
                //lastPos_0 = updatedPos_0.cpy();
            }
        //}
    }

    public Vector2 getTranslationVector () {
        return translationVector_0;
    }

    public void debug (OrthographicCamera camera) {
        //System.out.println(translationVector_0);

        for (int i = 0; i < 5; i++) {
            //System.out.println(input.touchDiagnostic.get(i).getPdd().getTranslationVector());
        }

        for (int i = 0; i < 5; i++) {
            //System.out.println(input.touchDiagnostic.get(i).touchPressed());
        }

        Render.spriteBatch.setProjectionMatrix(camera.combined);
        Render.spriteBatch.begin();
        Render.font.get("32").draw(
                Render.spriteBatch,
                "".toUpperCase(),
                0,
                Config.HEIGHT/2);
        Render.spriteBatch.end();

        Render.shapeRenderer.setProjectionMatrix(camera.combined);
        Render.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Render.shapeRenderer.line(lastPos_0.x, lastPos_0.y, updatedPos_0.x, updatedPos_0.y);
        Render.shapeRenderer.end();

        Render.shapeRenderer.setColor(Render.color.get("white"));
        Render.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Render.shapeRenderer.rect(lastPos_0.x - 10, lastPos_0.y - 10, 10, 10);
        Render.shapeRenderer.end();

        Render.shapeRenderer.setColor(Render.color.get("orange"));
        Render.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        Render.shapeRenderer.rect(updatedPos_0.x - 10, updatedPos_0.y - 10, 10, 10);
        Render.shapeRenderer.end();
        Render.shapeRenderer.setColor(Render.color.get("white"));
    }
}
