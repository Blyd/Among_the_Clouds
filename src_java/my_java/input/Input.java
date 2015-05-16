package my_java.input;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Travis on 12/2/2014.
 */
public class Input {
    public OrthographicCamera camera;
    public OrthographicCamera worldCamera;
    private Vector3 unproject = new Vector3(0,0,0);
    private String keys;
    private ConcurrentMap keyPressedMap;
    private ConcurrentMap keyActiveMap;
    private ConcurrentMap keyReleasedMap;
    private boolean keyTyped = false;

    private boolean backKeyReleased = false;

    Array keySymbols;

    public Array<TouchDiagnostic> touchDiagnostic;

    Input(OrthographicCamera camera) {

        keys = "WASDZXROP";

        this.camera = camera;


        touchDiagnostic = new Array<TouchDiagnostic>();
        //support for up to 5 simultaneous input
        for (int i = 0; i < 5; i++) {
            touchDiagnostic.add(new TouchDiagnostic(this, camera));
        }



        keyPressedMap = new ConcurrentHashMap();
        keyActiveMap = new ConcurrentHashMap();
        keyReleasedMap = new ConcurrentHashMap();

        keySymbols = new Array<Character>();
        for(int i = 0; i < keys.length(); i++){
            keySymbols.add(keys.charAt(i));
        }

        for(Object k : keySymbols){
            keyPressedMap.putIfAbsent(k, false);
            keyActiveMap.putIfAbsent(k, false);
            keyReleasedMap.putIfAbsent(k, false);
        }
    }

    boolean keyPressed(char k){
        return (Boolean)keyPressedMap.get(k);
    }
    boolean keyActive(char k){
        return (Boolean)keyActiveMap.get(k);
    }
    boolean keyReleased(char k){
        return (Boolean)keyReleasedMap.get(k);
    }

    void update(){
        for(int i = 0; i < keys.length(); i++){
            keyPressedMap.put(keys.charAt(i), false);
            keyReleasedMap.put(keys.charAt(i), false);
        }
        keyTyped = false;
        backKeyReleased = false;
        for (TouchDiagnostic td : touchDiagnostic) {
            td.update();
        }
    }

    public Map getKeyPressedMap() {
        return keyPressedMap;
    }

    public Map getKeyActiveMap() {
        return keyActiveMap;
    }

    public Map getKeyReleasedMap() {
        return keyReleasedMap;
    }

    public boolean isKeyTyped() {
        return keyTyped;
    }

    public void setKeyTyped(boolean keyTyped) {
        this.keyTyped = keyTyped;
    }

    public boolean isBackKeyReleased() {
        return backKeyReleased;
    }

    public void setBackKeyReleased(boolean backKeyReleased) {
        this.backKeyReleased = backKeyReleased;
    }
}
