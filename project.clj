(defproject clojure-future-spec "1.9.0-beta4"
  :description "Backport of clojure.spec for Clojure 1.8"
  :url "https://github.com/tonsky/clojure-future-spec"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :test-paths ["test" "test18"]

  :profiles {
    :1.9 {
      :dependencies [[org.clojure/clojure "1.9.0-beta4"
                       :exclusions [org.clojure/specs.alpha
                                    org.clojure/core.specs.alpha]]]
      :test-paths ^:replace ["test" "test19"]
    }
  }
  
  :aliases {"test-all" ["do" ["test"] ["with-profile" "1.9" "test"]]}
  )


