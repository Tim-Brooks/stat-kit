(ns stat-kit.gradient-descent
  (:require [clojure.math.numeric-tower :refer [expt]]))

(defn- least-squares-function [X y theta]
  (apply + (mapv (fn [x y] (expt (- (apply + (mapv * x theta)) y) 2)) X y)))

(defn- point [X-row y theta]
  (let [y-delta (- y (apply + (mapv (fn [x t] (* x t))
                                    X-row
                                    theta)))]
    (mapv #(* % y-delta) X-row)))

(defn- least-squares-partial [X Y theta N alpha]
  (* alpha
     (/ -2 N)
     (apply + (mapv (fn [X-row y]
                      (apply + (point X-row y theta))) X Y))))

(defn- step [])

(def ^:private loss-functions {:least-squares {:cost-function      least-squares-function
                                               :partial-derivative least-squares-partial}})

(defn gradient-descent
  [X Y theta & {:keys [loss-function alpha iterations]
                :or   {loss-function :least-squares alpha 0.0001 iterations 100}}]
  (let [X-with-intercept (vec (for [x-row X] (vec (cons 1 x-row))))]
    (loop [thetas theta
           [i & is] (range iterations)]
      (if (nil? i)
        thetas
        (recur (mapv #(- % (least-squares-partial
                             X-with-intercept Y thetas (count X-with-intercept) alpha))
                     thetas)
               is)))))
