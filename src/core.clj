(ns planet-jump.core
  (:import (my_java Config PlanetJumper)
           (com.badlogic.gdx.backends.lwjgl LwjglApplication)
           (my_java.render Render))
  (:require [entities.entities :as entities]))

(defn -main
  []
  (new LwjglApplication
       (new PlanetJumper
            ;planet-jump-create
            ;planet-jump-update
            ;planet-jump-render
            )
       (new Config)))

(-main)