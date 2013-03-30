(defproject DJ-Dirge "1.0.0-SNAPSHOT"
  :description "FIXME: write description"
  :dependencies [[org.clojure/clojure "1.5.0"]
		 [overtone "0.8.1"] 
		]
 :dev-dependencies [
        [lein-eclipse "1.0.0"]
    ]
 :jvm-opts ["-Xmx1g" "-server"]
 :repl-init DJ-Dirge.repl
 :main DJ-Dirge.run
)
