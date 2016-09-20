# clojure-future-spec

A backport of `clojure.spec` for Clojure 1.8.

## Usage

Add this to your project.clj:

```clj
:dependencies [
  [org.clojure/clojure "1.8.0"]
  [clojure-future-spec "1.9.0-alpha12-2"]
  [org.clojure/test.check "0.9.0"] ;; only if need generators
]
```

There’re four main namespaces:

### clojure.spec

Exact copy of clojure.spec from corresponding Clojure 1.9 alpha:

```clj
(require '[clojure.spec :as spec])
```

### clojure.spec.gen

Exact copy of `clojure.spec.gen` from corresponding Clojure 1.9 alpha:

```clj
(require '[clojure.spec.gen :as spec.gen])
```

### clojure.spec.test

Exact copy of `clojure.spec.test` from corresponding Clojure 1.9 alpha:

```clj
(require '[clojure.spec.test :as spec.test])
```

### clojure.future

Copy of all new functions added to `clojure.core` in Clojure 1.9 (like `boolean?`, `int?`, `seqable?` etc):

```clj
(require '[clojure.future :refer :all])
```

## License

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
