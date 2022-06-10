(ns app.events
  (:require [re-frame.core :as rf]
            [app.db :refer [app-db]]))

(rf/reg-event-db
 :initialize-db
 (fn [_ _]
   app-db))

(rf/reg-event-db
 :inc-counter
 (fn [db _]
   (update db :counter inc)))

(rf/reg-event-db
 :addition
 (fn [db]
   (update db :result inc)))



(rf/reg-event-db :set-screen
                 (fn [db [_ value]]
                   (assoc db :screen value)))
