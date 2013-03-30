(ns DJ-Dirge.number)

(defn factorize [n]
  (loop [i 2
         n n
         result []]
    (if (<= i (/ n i))
      (if (zero? (rem n i))
        (recur i (/ n i) (conj result i))
        (recur (inc i) n result))
      (if (> n 1)
        (conj result n)
        result))))

(defn mobius [n] (let [
      exps (vals (frequencies (factorize n)))
   ] (if (some #(> % 1) exps) 0
     (if (even? (count exps)) 1 -1)
)))

(defn phi [n] (apply * (apply concat
    (for [[p n] (frequencies (factorize n))]
       (conj (repeat (dec n) p) (dec p))
))))

(defn gcd [a b] (loop [a a b b]
    (cond (< a b)    (recur b a)  ; so assume a >= b
          (= b 0)    a
          :default   (recur b (mod a b))
)))

(defn cyclotomic
  "Compute the nth cyclotomic polynomial using the Grytczuk-Tropak recurrence.
  Note, this only works for n square-free, but for current purposes that's ok."
  [n] (let [
    d (phi n)
    mu-n (mobius n)
    T (for [i (range (inc d))] (let [
         q (gcd n i)
      ] (* (mobius q) (phi q)))) ; note don't really need T[0]
  ] (loop [m 1 poly [1]]
       (if (> m d) poly (let [
          sum (map * poly (for [l (range m)] (nth T (- m l))))
          ] (recur (inc m) (conj poly
             (* -1 mu-n (/ (apply + sum) m))                               
)))))))
