(ns stat-kit.linear-models
  (:require [stat-kit.gradient-descent :as descent]
            [clojure.core.matrix :as m]))

(defn- x-and-y-row-dimensions-match [X y]
  (let [x-rows (count X)
        y-rows (count y)]
    (assert (= x-rows y-rows)
            (format "X and y rows count must match. X: %s rows Y: %s rows"
                    x-rows y-rows))))

(defn- x-must-be-matrix [X]
  (when-let [first-row (first X)]
    (assert (vector? first-row) "X must be matrix composed of row vectors")
    (let [ncol (count first-row)]
      (reduce (fn [row-num row]
                (assert (= ncol (count row))
                        (format "Row %s has %s columns, %s expected"
                                row-num
                                (count row)
                                ncol))
                (inc row-num))
              1
              (rest X)))))

(defn- validate-data [X y]
  (x-and-y-row-dimensions-match X y)
  (x-must-be-matrix X))

(defn- center-data [X y])

(defn linear-regression
  [X y & options]
  (validate-data X y)
  (let [X-with-bias (->> X
                         m/transpose
                         (m/join (m/broadcast 1 [1 (first (m/shape X))]))
                         m/transpose)]
    (let [XT (m/transpose X-with-bias)]
      (m/mmul (m/inverse (m/mmul XT X-with-bias)) XT y))))