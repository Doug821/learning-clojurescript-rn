;; (ns sample-app.ui
;;   (:require [app.events]
;;             [app.subs]
;;             [cljs.core.match :refer [match]]
;;             [re-frame.core :as rf]
;;             [reagent.core :as r]
;;             [reagent.react-native :as rn]
;;             [sample-app.home :as home]
;;             [sample-app.secondary :as secondary]))

;; (defn main* []
;;   (let [screen @(rf/subscribe [:get-screen])
;;         view   (match screen
;;                  :first [home/home]
;;                  :second [secondary/secondary])]
;;     [rn/view {:flex 1 :align-items "center" :justify-content "center"}
;;      view]))


;; (defn main []
;;   [main*])

;; (defn ^:export -main [& args]
;;   (rf/dispatch-sync [:initialize-db])
;;   (r/as-element [main]))


;; (defn main []
;;   [(with-meta main* {:component-did-mount (fn []
;;                                             (global-font/applyGlobal "ApercuPro-Regular")
;;                                             (splash-screen/hide))})])