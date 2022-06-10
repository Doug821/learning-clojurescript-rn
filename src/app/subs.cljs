(ns app.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :get-db
 (fn [db _]
   db))

(rf/reg-sub
 :get-counter
 (fn [db _]
   (:counter db)))

(rf/reg-sub
 :get-result
 (fn [db _]
   (:result db)))



(rf/reg-sub
 :get-screen
 (fn [db _] (:screen db)))