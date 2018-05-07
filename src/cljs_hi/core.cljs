(ns cljs-hi.core
  (:require
   react-dom))

(defn ^:export exponent
  [n pow]
  (reduce * (repeat pow n)))

(if-let [root (.getElementById js/document "app")]
  (.render js/ReactDOM
           (.createElement js/React "h2" nil "Hello, React!")
           root))
