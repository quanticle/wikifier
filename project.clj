(defproject wikifier "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "GNU Public License"
            :url "https://www.gnu.org/licenses/gpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.10.0"]]
  :plugins [[lein-bin "0.3.5"]]
  :bin {:name "wikifier"}
  :main ^:skip-aot wikifier.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
