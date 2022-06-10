(ns app.core
  (:require ["learningcljsstorybook" :as ui]
            [reagent.core :as r]
            [reagent.react-native :as rn]
            [re-frame.core :as rf]
            [app.subs]
            [app.events]
            [cljs.core.match :refer [match]]))

;; (def result (r/atom 0))
;; (def input1 (r/atom 1))
;; (def input2 (r/atom 1))

(def homeScreen (r/adapt-react-class ui/Home))
(def card (r/adapt-react-class ui/Card))
;; (def operators (r/adapt-react-class ui/OperatorsContainer))
;; (def calculator (r/adapt-react-class ui/Calculator))

;; (defn input [value]
;;   [(r/adapt-react-class ui/Input)
;;    {:text (str value)}])

;; HADLERS
;; (defn handle-plus []
;;   #(rf/dispatch [:addition]))

;; (defn handle-minus []
;;   (js/console.info @(rf/subscribe [:get-result])))
;; (defn handle-multiply []
;;   (js/console.info (+ @input1 @input2)))

(defn tasks [props]
  [rn/view {:styles {:padding 12}}
   [card (merge props {:title "Task 1"})]
   [card (merge props {:title "Task 2"})]
   [card (merge props {:title "Task 3"})]
   [card (merge props {:title "Task 4"})]])


(defn home [props]
  [homeScreen (merge props {:title "Welcome to the Home Screen"
                            :navigate #(rf/dispatch [:set-screen :second])
                            :children tasks})
   [tasks]])


;;  [rn/button {:title "-->"
;;              :on-press #(rf/dispatch [:set-screen :second])}]
;; [rn/button {:title "<--"
;;             :on-press #(rf/dispatch [:set-screen :first])}]

(defn blank [props]
  [homeScreen (merge props {:title "Welcome to the Blank Screen" :navigate #(rf/dispatch [:set-screen :first])})])
;; Navigation 
(defn main* []
  (let [screen @(rf/subscribe [:get-screen])
        view   (match screen
                 :first [home]
                 :second [blank])]
    [rn/view {:flex 1 :align-items "center" :justify-content "center"}
     view]))

(defn main []
  [main*])

(defn ^:export -main [& args]
  (rf/dispatch-sync [:initialize-db])
  (r/as-element [main]))