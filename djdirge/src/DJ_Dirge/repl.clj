(ns DJ-Dirge.repl (:use
    clojure.repl                    
    overtone.live)
)

(def uh (sample "src/samples/uh.wav"))
(uh) 

(doseq [n (range 100 200 20)] (do 
    (definst foo [] (saw n)) 
    (foo) 
    (Thread/sleep 100) 
    (kill foo))
)

(println "Welcome to DJ Dirge")
