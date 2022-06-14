(ns app.core
  (:require ["learningcljsstorybook" :as ui]
            [reagent.core :as r]
            [reagent.react-native :as rn]
            [re-frame.core :as rf]
            [app.subs]
            [cljs.core.match :refer [match]]
            [cljs.core.async :refer [go <!]]
            [app.events]))

(def homeScreen (r/adapt-react-class ui/Home))
(def getAddressScreen (r/adapt-react-class ui/GetCep))

(def tasks (r/atom (apply array ["Create new screen on Storybook."
                                 "Use the created screen on CLJS."
                                 "Implement listing on CLJS to render the tasks card."
                                 "Implement an identifier on each task to be able to manipulate it."
                                 "Implement function to mark a task as checked."
                                 "Implement function to delete a task."
                                 "Figure how to update an atom that contains a Javascript array"])))

(defn home [props]
  [homeScreen (merge props {:title "Welcome to the Home Screen"
                            :navigate #(rf/dispatch [:set-screen :second])
                            :tasks @tasks})])

(def responseAPI (r/atom nil))

(defn getAddress [value]
  (go
    (try
      (<! (-> (.fetch js/window (str "https://ws.apicep.com/cep/" (-> value) ".json"))
              (.then #(.json %))
              (.then #(reset! responseAPI %))
              (.then #(js/console.log value))))
      (catch :default e
        e))))

(defn getCep [props]
  [getAddressScreen (merge props {:title "Welcome to the Address finder"
                                  :navigate #(rf/dispatch [:set-screen :first])
                                  :getAddress getAddress
                                  :address @responseAPI})])
;; Navigation 
(defn main* []
  (let [screen @(rf/subscribe [:get-screen])
        view   (match screen
                 :first [home]
                 :second [getCep])]
    [rn/view {:flex 1 :align-items "center" :justify-content "center"}
     view]))

(defn main []
  [main*])

(defn ^:export -main [& args]
  (rf/dispatch-sync [:initialize-db])
  (r/as-element [main]))