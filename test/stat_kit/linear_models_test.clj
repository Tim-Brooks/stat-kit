(ns stat-kit.linear-models-test
  (:require [clojure.test :refer :all]
            [stat-kit.linear-models :as models]
            [clojure.core.matrix :as m]))

(deftest linear-regression
  (testing "Single variable linear regression model fit"
    (is (= 0 1))))
