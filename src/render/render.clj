(ns render.render
  (:import (my_java.render Render Camera)
           (com.badlogic.gdx.graphics.glutils ShapeRenderer$ShapeType)
           (com.badlogic.gdx.graphics OrthographicCamera)
           (com.badlogic.gdx.graphics.g2d SpriteBatch)))

(defn set-projection-matrix
  [camera camera-type render-type]
  (.setProjectionMatrix (if (= render-type :sprite)
                          Render/spriteBatch
                          Render/shapeRenderer)
                        (.combined (if (= camera-type :world)
                                     (.worldCamera camera)
                                     (.guiCamera camera)))))
;(.renderCamera (.camera render))


(defn draw-shape
  [shapes ^Render render]
  (.setProjectionMatrix Render/shapeRenderer (.combined (.worldCamera (.camera render))))
  (.begin Render/shapeRenderer ShapeRenderer$ShapeType/Line)
  (doseq [shape shapes]
    (.setColor Render/shapeRenderer 1 1 1 1)
    (Render/drawCircle 400 200 100))
  (.end Render/shapeRenderer))