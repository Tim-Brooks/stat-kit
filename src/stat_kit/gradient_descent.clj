(ns stat-kit.gradient-descent
  (:require [clojure.math.numeric-tower :refer [expt]]))

(defn- least-squares-function [X y theta]
  (apply + (mapv (fn [x y] (expt (- (apply + (mapv * x theta)) y) 2)) X y)))

(def ^:private loss-functions {:least-squares least-squares-function})

(defn gradient-descent
  [X y & {:keys [loss-function alpha iterations]
      :or   {loss-function :least-squares alpha 0.0001 iterations 10}}]
  (loop [theta (vec (repeat (count (first X)) 0))
         [i & is] (range iterations)]
    (if (nil? i)
      theta
      (recur theta is))))
