(ns entities.player
  (:import (com.badlogic.gdx.graphics Color)))

(defn player-update
  [this entities]
  (let [x (first (:pos this))
        y (second (:pos this))]
    (println (:pos this))
    (assoc this :pos [(inc x)(inc y)])))

(defn player-render
  [this entities]
  (if (:debug? entities)
    (println "DEBUG!!!")))

(def player
  (let [size 50]
    {
     :pos [100 100]
     :size size
     :debug-info { :circle {:radius size
                                :color  (new Color 0 1 0 1)}} ;TODO remove new object

     :render-info {:projection-type :sprite
                   :camera-type :world}

     :update player-update
     :render player-render
     }))