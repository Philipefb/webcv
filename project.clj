(defproject meu-site "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [ring "1.9.6"]
                 [hiccup "1.0.5"]
                 [compojure "1.7.0"]]
  :repl-options {:init-ns meu-site.core}
  :main meu-site.core)
