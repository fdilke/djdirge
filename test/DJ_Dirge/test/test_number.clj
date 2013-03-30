(ns DJ-Dirge.test.test-number
  (:use [DJ-Dirge.number])
  (:use [clojure.test])
)

(deftest test-factorize 
  (are [x y] (= (factorize x) y)
     1 []
     3 [3]
     12 [2 2 3]
     1600 [2 2 2 2 2 2 5 5]
))

(deftest test-mobius
  (are [x y] (= (mobius x) y)
     1 1
     3 -1
     4 0
     6 1
     18 0
     27 0
     30 -1
))

(deftest test-phi
  (are [x y] (= (phi x) y)
     1 1
     3 2
     4 2
     6 2
     18 6
     27 18
     30 8
))

(deftest test-gcd
  (are [a b d] (= (gcd a b) d)
     0 1 1
     1 1 1
     2 6 2
     6 2 2
     12 5 1
     4 6 2
     18 12 6
))

(deftest test-cyclotomic
  (are [n p] (= (cyclotomic n) p)
     2 [1 1]
     3 [1 1 1]
     6 [1 -1 1]
     105 [1 1 1 0 0 -1 -1 -2 -1 -1 0 0 1 1 1 1 1 1 0 0 -1 0 -1 0  
          -1 ; note symmetry 
          0 -1 0 -1 0 0 1 1 1 1 1 1 0 0 -1 -1 -2 -1 -1 0 0 1 1 1]
))
