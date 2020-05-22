(ns wikifier.core
  (:gen-class)
  (:require [clojure.string :as str]
            [clojure.java.io]))

(defn print-help []
  (println "wikifier <input-file> <output-file>"))

(defn convert-file [input-file-name output-file-name]
  (let [lines (str/split-lines (slurp input-file-name))
        title-match (re-matches #"# (.*)" (first lines))
        replace-text (comp (fn [input-str] (str/replace input-str #"<-" "←"))
                           (fn [input-str] (str/replace input-str #"->" "→"))
                           (fn [input-str] (str/replace input-str #"<->" "↔"))
                           (fn [input-str] (str/replace input-str #"\$(.*)\$" "{\\$$1\\$}")))]
    (with-open [output-file (clojure.java.io/writer output-file-name)]
      (doseq [output-line (remove nil?
                                  (flatten [(cond title-match
                                                  (str "(:title " (second title-match) ":)"))
                                            "(:htoc:)"
                                            "(:mathjax:)"
                                            "(:markdown:)"
                                            (map replace-text (rest lines))
                                            "(:markdownend:)"]))]
        (.write output-file (str output-line "\n"))))))

(defn -main
  "Format a markdown file for pmwiki consumption"
  [& args]
  (if (= (count args) 2)
    (convert-file (first args) (second args))
    (print-help)))
