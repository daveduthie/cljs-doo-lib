(ns cljs-hi.runner
  (:require
   [cljs-hi.core-test]
   [cljs.test :as t :include-macros true]
   [doo.runner :refer-macros [doo-tests doo-all-tests]]))

(enable-console-print!)
(doo-tests 'cljs-hi.core-test)
