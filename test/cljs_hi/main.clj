(ns cljs-hi.main
  (:require
   [cljs.build.api :as b]
   [doo.core :as doo]))

(def test-paths ["src" "test"])
;; Karma seems to need an absolute path to get its scripts tags right
(def asset-path (str (System/getProperty "user.dir") "/out")) 

(def compiler-opts
  {:output-to      "out/testable.js"
   :output-dir     "out"
   :asset-path     asset-path
   :main           'cljs-hi.runner
   :verbose        false            ; set to true to see what's being recompiled
   :compiler-stats true
   :cache-analysis true})

(defn compile!
  []
  (b/build (apply b/inputs test-paths) ; have to tell compiler where to find its sources
           compiler-opts))             ; would have thought deps.edn aliases would be used

(defn watch
  []
  (b/watch (apply b/inputs test-paths)
           compiler-opts))

(defn doo-test
  []
  (let [doo-opts {:debug false}]
    (doo/run-script :chrome compiler-opts doo-opts)
    (System/exit 0)))

(defn compile-and-test
  []
  (compile!)
  (doo-test))

(defn -main
  [& args]
  (cond
    (some #{"watch"} args) (watch)
    (some #{"test"} args)  (doo-test)
    :else                  (compile-and-test)))
