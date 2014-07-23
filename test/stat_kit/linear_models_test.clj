(ns stat-kit.linear-models-test
  (:require [clojure.test :refer :all]
            [stat-kit.linear-models :as models]))

(deftest linear-regression
  (testing "Single variable linear regression model fit"
    (is (= 0 (linear-regression [[0] [1] [2]] [0 1 2])))))
