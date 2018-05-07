(ns cljs-hi.main
  (:require
   [cljs.build.api :as b]
   [doo.core :as doo]))

(def dir (System/getProperty "user.dir"))

(def test-paths ["src" "test"])

(def compiler-opts
  {:output-to      "out/testable.js"
   :output-dir     "out"
   :asset-path     (str dir "/out") ; Karma seems to need an absolute path
   :main           'cljs-hi.runner
   :verbose        false            ; set to true to see what's being recompiled
   :compiler-stats true
   :cache-analysis true})

(defn compile-for-test
  []
  (b/build (apply b/inputs test-paths) ; have to tell compiler where to find its sources
           compiler-opts))             ; would have thought deps.edn aliases would be used

(defn doo-test
  []
  (let [doo-opts {:debug true}]
    (doo/run-script :chrome compiler-opts doo-opts)))

(defn compile-and-run
  []
  (compile-for-test)
  (doo-test))

(defn -main
  [& args]
  (compile-and-run)
  (println ::ok)
  (System/exit 0))
