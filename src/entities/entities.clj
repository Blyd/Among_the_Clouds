(ns entities.entities
  (:require [entities.player :as player]))

(defn entities
  []
  {
   :debug? true
   :game-world [
                player/player
                ]
   })

(comment
  :game-world {
               :player player/player
               }
  )