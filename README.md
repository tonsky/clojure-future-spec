# clojure-future-spec

A backport of `clojure.spec` for Clojure 1.8.

## Usage

Add this to your project.clj:

```clj
:dependencies [
  [org.clojure/clojure "1.8.0"]
  [clojure-future-spec "1.9.0-alpha16-1"]
  [org.clojure/test.check "0.9.0"] ;; only if you need generators
]
```

If you'll be using generators, make sure you have `test.check`, too:

```clj
:dependencies [
  ...
  [org.clojure/test.check "0.9.0"]
]
```

There’re four main namespaces:

### clojure.spec.alpha

Exact copy of clojure.spec.alpha from corresponding Clojure Spec alpha:

```clj
(require '[clojure.spec.alpha :as spec])
```

### clojure.spec.gen.alpha

Exact copy of `clojure.spec.gen.alpha` from corresponding Clojure Spec alpha:

```clj
(require '[clojure.spec.gen.alpha :as spec.gen])
```

### clojure.spec.test.alpha

Exact copy of `clojure.spec.test.alpha` from corresponding Clojure Spec alpha:

```clj
(require '[clojure.spec.test.alpha :as spec.test])
```

### clojure.future

Copy of all new functions added to `clojure.core` in Clojure 1.9 (like `boolean?`, `int?`, `seqable?` etc):

```clj
(require '[clojure.future :refer :all])
```

## License

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
