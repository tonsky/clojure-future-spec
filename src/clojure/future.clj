(ns clojure.future)

(when (and (= 1 (:major *clojure-version*))
           (< (:minor *clojure-version*) 9))
  (load "future_impl"))