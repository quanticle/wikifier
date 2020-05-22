(ns wikifier.core
  (:gen-class)
  (:require [clojure.string :refer [split-lines replace]]
            [clojure.java.io]))

(defn print-help []
  (println "wikifier <input-file> <output-file>"))

(defn convert-file [input-file-name output-file-name]
  (let [lines (split-lines (slurp input-file-name))
        title-match (re-matches #"# (.*)" (first lines))]
    (with-open [output-file (clojure.java.io/writer output-file-name)]
      (doseq [output-line (remove nil?
                                  (flatten [(cond title-match
                                                  (str "(:title " (second title-match) ":)"))
                                            "(:htoc:)"
                                            "(:mathjax:)"
                                            "(:markdown:)"
                                            (map (fn [line] (replace (replace
                                                                      (replace (replace line #"\$(.*)\$" "{\\$$1\\$}")
                                                                               #"<->" "↔") #"->" "→") #"<-" "←")) (rest lines))
                                            "(:markdownend:)"]))]
        (.write output-file (str output-line "\n"))))))

(defn -main
  "Format a markdown file for pmwiki consumption"
  [& args]
  (if (= (count args) 2)
    (convert-file (first args) (second args))
    (print-help)))
