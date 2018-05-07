(ns cljs-hi.main
  (:require
   [cljs.build.api :as b]
   [doo.core :as doo]))

;; The CLJS build api needs to be told where to find sources
;; Would be nice to have this automatically set based on the JVM classpath
(def test-paths ["src" "test"])
;; Karma seems to need an absolute path to get its scripts tags right
(def asset-path (str (System/getProperty "user.dir") "/out")) 

(def compiler-opts
  {:output-to      "out/testable.js"
   :output-dir     "out"
   :asset-path     asset-path
   :main           'cljs-hi.runner
   :verbose        false ; set to true to see what's being recompiled
   :compiler-stats true
   :cache-analysis true})

(defn watch!
  []
  (b/watch (apply b/inputs test-paths)
           ;; this will be called after recompilation
           (assoc compiler-opts :watch-fn 'cljs-hi.main/doo-test)))

(defn compile!
  []
  (b/build (apply b/inputs test-paths)        
           compiler-opts))

(defn doo-test
  []
  (let [doo-opts {:debug false}] ; set to true to see what doo is up to
    (doo/run-script :chrome-headless compiler-opts doo-opts)))

(defn compile-and-test!
  []
  (compile!)
  (doo-test)
  (System/exit 0))

(defn -main
  [& args]
  (cond
    (some #{"watch"} args) (watch!)
    :else                  (compile-and-test!)))
