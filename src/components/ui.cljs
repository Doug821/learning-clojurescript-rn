(ns components.ui
  (:require [reagent.core :as r]
            ["learningcljsstorybook" :as ui]))

(def button (r/adapt-react-class ui/Button))
(def input (r/adapt-react-class ui/Input))
(def card (r/adapt-react-class ui/Card))