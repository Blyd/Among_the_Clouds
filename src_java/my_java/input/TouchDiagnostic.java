package my_java.input;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Travis on 3/7/2015.
 */
public class TouchDiagnostic {
    public OrthographicCamera camera;
    public OrthographicCamera worldCamera;
    private Vector3 unproject = new Vector3(0,0,0);

    private boolean touchPressed;
    private boolean touchDragged;
    private boolean touchReleased;

    private Vector2 coordinatePressed;
    private Vector2 coordinateReleased;
    private Vector2 coordinateDragged;
    private Vector2 coordinateHover;

    private Vector2 coordinatePressed_raw;
    private Vector2 coordinateReleased_raw;
    private Vector2 coordinateDragged_raw;
    private Vector2 coordinateHover_raw;

    public enum ActionTypes {
        Pressed,
        Dragged,
        Released
    }
    public Array<ActionTypes> postFrameAction = new Array<ActionTypes>();

    //swipe
    private PointDragDisplacement pdd;
    private double swipeGravity = 0.1f;
    private Vector2 followTouchVector = new Vector2(0,0);
    private Vector2 swipeVector = new Vector2(0,0);

    //flick
    boolean flicked = false;

    TouchDiagnostic(Input input, OrthographicCamera camera) {
        this.camera = camera;

        pdd = new PointDragDisplacement(input);

        coordinatePressed = new Vector2(0,0);
        coordinateReleased = new Vector2(0,0);
        coordinateDragged = new Vector2(0,0);
        coordinateHover = new Vector2(0,0);

        coordinatePressed_raw = new Vector2(0,0);
        coordinateReleased_raw = new Vector2(0,0);
        coordinateDragged_raw = new Vector2(0,0);
        coordinateHover_raw = new Vector2(0,0);
    }

    void update(){
        if (touchReleased()) {
            addPostFrameAction(ActionTypes.Released);
        } else if (touchDragged()) {
            addPostFrameAction(ActionTypes.Dragged);
        } else if (touchPressed()) {
            addPostFrameAction(ActionTypes.Pressed);
        }

        //System.out.println(postFrameAction);

        clearPostFrameAction();


        if(touchReleased){
            coordinatePressed = new Vector2(-1,-1);
            coordinateReleased = new Vector2(-1,-1);
            coordinateDragged = new Vector2(-1,-1);
            coordinateHover = new Vector2(-1,-1);
        }

        touchPressed = false;
        touchDragged = false;
        touchReleased = false;
    }

    public void setCoordinatePressed(float x, float y){
        unproject.x = x;
        unproject.y = y;
        Vector3 cam = camera.unproject(unproject);

        this.coordinatePressed.x = cam.x;
        this.coordinatePressed.y = cam.y;
    }
    public void setCoordinatePressed_raw(float x, float y){
        this.coordinatePressed_raw.x = x;
        this.coordinatePressed_raw.y = y;
    }

    public void setCoordinateReleased(float x, float y){
        unproject.x = x;
        unproject.y = y;
        Vector3 cam = camera.unproject(unproject);

        this.coordinateReleased.x = cam.x;
        this.coordinateReleased.y = cam.y;
    }
    public void setCoordinateReleased_raw(float x, float y){
        this.coordinateReleased_raw.x = x;
        this.coordinateReleased_raw.y = y;
    }

    public void setCoordinateDragged(float x, float y){
        unproject.x = x;
        unproject.y = y;
        Vector3 cam = camera.unproject(unproject);

        this.coordinateDragged.x = cam.x;
        this.coordinateDragged.y = cam.y;
    }
    public void setCoordinateDragged_raw(float x, float y){
        this.coordinateDragged_raw.x = x;
        this.coordinateDragged_raw.y = y;
    }

    public void setCoordinateHover(float x, float y){
        unproject.x = x;
        unproject.y = y;
        Vector3 cam = camera.unproject(unproject);

        this.coordinateHover.x = cam.x;
        this.coordinateHover.y = cam.y;
    }
    public void setCoordinateHover_raw(float x, float y){
        this.coordinateHover_raw.x = x;
        this.coordinateHover_raw.y = y;
    }

    public Vector2 getCoordinatePressed() {
        return coordinatePressed;
    }
    public Vector2 getCoordinateReleased() {
        return coordinateReleased;
    }
    public Vector2 getCoordinateDragged(){ return coordinateDragged; }
    public Vector2 getCoordinateHover(){ return coordinateHover; }

    public Vector2 getCoordinatePressed_raw() {
        return coordinatePressed_raw;
    }
    public Vector2 getCoordinateReleased_raw() {
        return coordinateReleased_raw;
    }
    public Vector2 getCoordinateDragged_raw(){ return coordinateDragged_raw; }
    public Vector2 getCoordinateHover_raw(){ return coordinateHover_raw; }

    public boolean touchReleased() {
        return touchReleased;
    }

    public void setTouchReleased(boolean touchReleased) {
        this.touchReleased = touchReleased;
    }

    public boolean touchPressed() {
        return touchPressed;
    }

    public boolean touchDragged(){ return touchDragged; }

    public void setTouchPressed(boolean touchPressed) {
        this.touchPressed = touchPressed;
    }

    public void setTouchDragged(boolean touchDragged) {this.touchDragged = touchDragged;}

    public OrthographicCamera getCamera() {return this.camera;}
    public void setCamera(OrthographicCamera camera) {this.camera = camera;}

    public void update_pdd (int x) {
        pdd.update(x);
    }

    public PointDragDisplacement getPdd() {
        return pdd;
    }

    public Vector2 followTouch () {
        //TODO pdd is being updated within the game loop, but perhaps it should be updated internally from this object?
        //pdd.update();
        followTouchVector.x = pdd.getTranslationVector().x;
        followTouchVector.y = pdd.getTranslationVector().y;
        return followTouchVector;
    }

    public Vector2 swipe () {
        return swipeVector;
    }

    public void addPostFrameAction (ActionTypes actionType) {
        if (postFrameAction.size == 0) {
            postFrameAction.add(actionType);
        } else if (postFrameAction.size == 1) {
            postFrameAction.add(actionType);
        } else {
            postFrameAction.set(0, postFrameAction.get(1));
            postFrameAction.set(1, actionType);
        }
    }

    public void clearPostFrameAction () {
        flicked = false;

        if (postFrameAction.size == 2) {
            if (postFrameAction.get(0) == ActionTypes.Dragged
                    && postFrameAction.get(1) == ActionTypes.Released) {
                flicked = true;
            }

            //postFrameAction.clear();
            //postFrameAction.size = 0;
        }
    }
}
