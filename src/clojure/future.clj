;   Copyright (c) Rich Hickey. All rights reserved.
;   The use and distribution terms for this software are covered by the
;   Eclipse Public License 1.0 (http://opensource.org/licenses/eclipse-1.0.php)
;   which can be found in the file epl-v10.html at the root of this distribution.
;   By using this software in any fashion, you are agreeing to be bound by
;   the terms of this license.
;   You must not remove this notice, or any other, from this software.

(ns clojure.future)

(defn boolean?
  "Return true if x is a Boolean"
  {:added "1.9"}
  [x] (instance? Boolean x))

(defprotocol Inst
  (inst-ms* [inst]))

(extend-protocol Inst
  java.util.Date
  (inst-ms* [inst] (.getTime ^java.util.Date inst)))

(try
  (Class/forName "java.time.Instant")
  (load "core_instant18")
  (catch ClassNotFoundException cnfe))

(defn inst-ms
  "Return the number of milliseconds since January 1, 1970, 00:00:00 GMT"
  {:added "1.9"}
  [inst]
  (inst-ms* inst))

(defn inst?
  "Return true if x satisfies Inst"
  {:added "1.9"}
  [x]
  (satisfies? Inst x))

(defn uuid?
  "Return true if x is a java.util.UUID"
  {:added "1.9"}
  [x] (instance? java.util.UUID x))

(defn ident?
  "Return true if x is a symbol or keyword"
  {:added "1.9"}
  [x] (or (keyword? x) (symbol? x)))

(defn simple-ident?
  "Return true if x is a symbol or keyword without a namespace"
  {:added "1.9"}
  [x] (and (ident? x) (nil? (namespace x))))

(defn qualified-ident?
  "Return true if x is a symbol or keyword with a namespace"
  {:added "1.9"}
  [x] (boolean (and (ident? x) (namespace x) true)))

(defn simple-symbol?
  "Return true if x is a symbol without a namespace"
  {:added "1.9"}
  [x] (and (symbol? x) (nil? (namespace x))))

(defn qualified-symbol?
  "Return true if x is a symbol with a namespace"
  {:added "1.9"}
  [x] (boolean (and (symbol? x) (namespace x) true)))

(defn simple-keyword?
  "Return true if x is a keyword without a namespace"
  {:added "1.9"}
  [x] (and (keyword? x) (nil? (namespace x))))

(defn qualified-keyword?
  "Return true if x is a keyword with a namespace"
  {:added "1.9"}
  [x] (boolean (and (keyword? x) (namespace x) true)))

(defn uri?
  "Return true if x is a java.net.URI"
  {:added "1.9"}
  [x] (instance? java.net.URI x))

(defn int?
  "Return true if x is a fixed precision integer"
  {:added "1.9"}
  [x] (or (instance? Long x)
          (instance? Integer x)
          (instance? Short x)
          (instance? Byte x)))

(defn pos-int?
  "Return true if x is a positive fixed precision integer"
  {:added "1.9"}
  [x] (and (int? x)
           (pos? x)))

(defn neg-int?
  "Return true if x is a negative fixed precision integer"
  {:added "1.9"}
  [x] (and (int? x)
           (neg? x)))

(defn nat-int?
  "Return true if x is a non-negative fixed precision integer"
  {:added "1.9"}
  [x] (and (int? x)
           (not (neg? x))))

(defn double?
  "Return true if x is a Double"
  {:added "1.9"}
  [x] (instance? Double x))

(defn bigdec?
  "Return true if x is a BigDecimal"
  {:added "1.9"}
  [x] (instance? java.math.BigDecimal x))

(defn bytes?
  "Return true if x is a byte array"
  {:added "1.9"}
  [x] (if (nil? x)
        false
        (-> x class .getComponentType (= Byte/TYPE))))

(defn seqable?
  "Return true if the seq function is supported for x"
  {:added "1.9"}
  [x]
  (or (instance? clojure.lang.ISeq x)
      (instance? clojure.lang.Seqable x)
      (nil? x)
      (instance? Iterable x)
      (.. x getClass isArray)
      (instance? CharSequence x)
      (instance? java.util.Map x)))

(defn indexed?
  "Return true if coll implements Indexed, indicating efficient lookup by index"
  {:added "1.9"}
  [coll] (instance? clojure.lang.Indexed coll))

(defn bounded-count
  "If coll is counted? returns its count, else will count at most the first n
  elements of coll using its seq"
  {:added "1.9"}
  [n coll]
  (if (counted? coll)
    (count coll)
    (loop [i 0 s (seq coll)]
      (if (and s (< i n))
        (recur (inc i) (next s))
        i))))

(defn StackTraceElement->vec
  "Constructs a data representation for a StackTraceElement"
  {:added "1.9"}
  [^StackTraceElement o]
  [(symbol (.getClassName o)) (symbol (.getMethodName o)) (.getFileName o) (.getLineNumber o)])

(defn any?
  "Returns true given any argument."
  {:tag Boolean
   :added "1.9"}
  [x] true)

(defn halt-when
  "Returns a transducer that ends transduction when pred returns true
  for an input. When retf is supplied it must be a fn of 2 arguments -
  it will be passed the (completed) result so far and the input that
  triggered the predicate, and its return value (if it does not throw
  an exception) will be the return value of the transducer. If retf
  is not supplied, the input that triggered the predicate will be
  returned. If the predicate never returns true the transduction is
  unaffected."
  {:added "1.9"}
  ([pred] (halt-when pred nil))
  ([pred retf]
     (fn [rf]
       (fn
         ([] (rf))
         ([result]
            (if (and (map? result) (contains? result ::halt))
              (::halt result)
              (rf result)))
         ([result input]
            (if (pred input)
              (reduced {::halt (if retf (retf (rf result) input) input)})
              (rf result input)))))))

