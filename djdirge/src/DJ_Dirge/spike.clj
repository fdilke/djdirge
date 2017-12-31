(ns DJ-Dirge.spike)

(defn doub [n]
  (* 2 n)
)

(println "teehee")
(doub 15)

(defn my-rhythm [length sequence] (let [
   metro (metronome 120)
   hack (ref 0)
   swinger (fn [beat] 
      (doseq [[sound offset mult] sequence]
        (at (metro (+ offset beat)) (sound mult 0 0 0.5))
   ) (apply-at (metro (+ length beat)) @hack (+ length beat) [])
   )] (dosync (ref-set hack swinger)) (swinger (metro))
))

'(my-rhythm 2 [[uh 0.5 0.8] [uh 1.7 1.1]])

(defn swinger [beat]
  (at (metro beat) (o-hat))
  (at (metro (inc beat)) (c-hat))
  (at (metro (+ 1.65 beat)) (c-hat))
  (apply-at (metro (+ 2 beat)) #'swinger (+ 2 beat) []))

; define a metronome at a given tempo, expressed in beats per minute.
(def metro (metronome 120))