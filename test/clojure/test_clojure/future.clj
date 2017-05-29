(ns clojure.test-clojure.future
  (:require
    [clojure.spec.alpha :as s]
    [clojure.future :refer :all]
    [clojure.test :refer [deftest is are]]))


(deftest novelty
  (println "--- Testing on" (clojure-version) "---")

  (is (true? (boolean? true))))


(deftest alpha17
  (is (true? (qualified-keyword? :ns/kw))))


;; https://github.com/tonsky/clojure-future-spec/issues/2
(deftest test-conform-keys
  (s/def ::n nat-int?)
  (is (= (s/conform (s/keys :req-un [::n]) {:n 6})
         {:n 6})))
  