(ns stat-kit.linear-models)

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

(defn linear-regression [X y]
  (validate-data X y)
  (let [theta (vec (repeat (count X) 1))]
    ))