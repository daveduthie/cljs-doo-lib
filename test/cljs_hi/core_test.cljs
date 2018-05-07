(ns cljs-hi.core-test
  (:require [cljs-hi.core :as sut]
            [cljs.test :as t :include-macros true]))

(t/deftest exponent-test
  (t/testing "Test 2^4"
    (t/is (= (sut/exponent 2 4) 16)))
  (t/testing "Test 2^5"
    (t/is (= (sut/exponent 2 5) 30)))) ; FIXME

