(ns dactyl-keyboard.generator
  (:refer-clojure :exclude [use import])
  (:require [scad-clj.scad :refer :all]
            [scad-clj.model :refer :all]
            [dactyl-keyboard.manuform :as dm]
            [dactyl-keyboard.lightcycle :as dl]
            [dactyl-keyboard.common :as common]))

(defn generate-case-dl [confs is-right?]
  (write-scad (if is-right?
                (dl/dactyl-top-right confs)
                (dl/dactyl-top-left confs))))

(defn generate-json-dm [confs is-right?]
  (let [stagger-index  (get confs :configuration-stagger-index [0 0 0])
        stagger-middle (get confs :configuration-stagger-middle [0 2.8 -6.5])
        stagger-ring   (get confs :configuration-stagger-ring [0 0 0])
        stagger-pinky  (get confs :configuration-stagger-pinky [0 -13 6])]
    {:keys      {:columns         (get confs :configuration-ncols)
                 :rows            (get confs :configuration-nrows)
                 :thumb-count     (get confs :configuration-thumb-count)
                 :last-row        (get confs :configuration-last-row-count)
                 :switch-type     (get confs :configuration-switch-type)
                 :inner-column    (get confs :configuration-use-inner-column?)
                 :hide-last-pinky (get confs :configuration-hide-last-pinky?)}
     :curve     {:alpha       (get confs :configuration-alpha)
                 :pinky-alpha (get confs :configuration-pinky-alpha)
                 :beta        (get confs :configuration-beta)
                 :centercol   (get confs :configuration-centercol)
                 :tenting     (get confs :configuration-tenting-angle)
                 :rotate-x     (get confs :configuration-rotate-x-angle)}
     :connector {:external  (get confs :configuration-use-external-holder?)
                 :trrs      (get confs :configuration-use-trrs?)
                 :micro-usb (get confs :configuration-use-promicro-usb-hole?)}
     :form      {:hotswap          (get confs :configuration-use-hotswap?)
                 :thumb-cluster-offset-x   (get confs :configuration-thumb-cluster-offset-x)
                 :thumb-cluster-offset-y   (get confs :configuration-thumb-cluster-offset-y)
                 :thumb-cluster-offset-z   (get confs :configuration-thumb-cluster-offset-z)
                 :custom-thumb-tenting   (get confs :configuration-custom-thumb-tenting?)
                 :thumb-tenting-x  (get confs :configuration-custom-thumb-tenting-x)
                 :thumb-tenting-y  (get confs :configuration-custom-thumb-tenting-y)
                 :thumb-tenting-z  (get confs :configuration-custom-thumb-tenting-z)
                 :custom-thumb-offsets   (get confs :configuration-custom-thumb-offsets?)
                 :thumb-top-right-offset-x   (get confs :configuration-thumb-top-right-offset-x)
                 :thumb-top-right-offset-y   (get confs :configuration-thumb-top-right-offset-y)
                 :thumb-top-right-offset-z   (get confs :configuration-thumb-top-right-offset-z)
                 :stagger          (get confs :configuration-stagger?)
                 :stagger-index-y  (second stagger-index)
                 :stagger-index-z  (last stagger-index)
                 :stagger-middle-y (second stagger-middle)
                 :stagger-middle-z (last stagger-middle)
                 :stagger-ring-y   (second stagger-ring)
                 :stagger-ring-z   (last stagger-ring)
                 :stagger-pinky-y  (second stagger-pinky)
                 :stagger-pinky-z  (last stagger-pinky)
                 :wide-pinky       (get confs :configuration-use-wide-pinky?)
                 :height-offset    (get confs :configuration-z-offset)
                 :wire-post        (get confs :configuration-use-wire-post?)
                 :screw-inserts    (get confs :configuration-use-screw-inserts?)}
     :misc      {:keycaps    (get confs :configuration-show-caps?)
                 :right-side is-right?
                 :case       true}}))

(defn generate-json-dl [confs is-right?]
  {:keys      {:columns         (get confs :configuration-ncols)
               :num-row         (get confs :configuration-use-numrow?)
               :last-row        (get confs :configuration-use-lastrow?)
               :switch-type     (get confs :configuration-switch-type)
               :thumb-count     (get confs :configuration-thumb-count)
               :hide-last-pinky (get confs :configuration-hide-last-pinky?)}
   :curve     {:alpha         (get confs :configuration-alpha)
               :beta          (get confs :configuration-beta)
               :tenting       (get confs :configuration-tenting-angle)
               :thumb-alpha   (get confs :configuration-thumb-alpha)
               :thumb-beta    (get confs :configuration-thumb-beta)
               :thumb-tenting (get confs :configuration-thumb-tenting-angle)}
   :connector {:external (get confs :configuration-use-external-holder?)}
   :form      {:hotswap         (get confs :configuration-use-hotswap?)
               :thumb-offset-x  (get confs :configuration-thumb-offset-x)
               :thumb-offset-y  (get confs :configuration-thumb-offset-y)
               :thumb-offset-z  (get confs :configuration-thumb-offset-z)
               :wide-pinky      (get confs :configuration-use-wide-pinky?)
               :z-offset        (get confs :configuration-z-offset)
               :manuform-offset (get confs :configuration-manuform-offset?)
               :border          (get confs :configuration-use-border?)
               :thick-wall      (get confs :configuration-thick-wall?)}
   :misc      {:right-side    is-right?
               :screw-inserts (get confs :configuration-use-screw-inserts?)}})

(defn generate-plate-dl [confs is-right?]
  (write-scad (if is-right?
                (dl/dactyl-plate-right confs)
                (dl/dactyl-plate-left confs))))

(defn generate-case-dm [confs is-right?]
  (write-scad (if is-right?
                (dm/model-right confs)
                (dm/model-left confs))))

(defn generate-plate-dm [confs is-right?]
  (write-scad (if is-right?
                (dm/plate-right confs)
                (dm/plate-left confs))))

(defn generate-hotswap [confs is-right?]
  (write-scad (common/hotswap-holder-dk true false)))
