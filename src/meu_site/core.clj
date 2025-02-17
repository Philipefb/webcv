(ns meu-site.core
  (:require [ring.adapter.jetty :as jetty]
            [hiccup.page :refer [html5 include-css]]
            [compojure.core :refer [defroutes GET]]
            [compojure.route :as route]
            [ring.middleware.resource :refer [wrap-resource]]
            [ring.middleware.content-type :refer [wrap-content-type]]))

(def perfil-info
  {:nome "Philipe Bittencourt"
   :titulo "Desenvolvedor de Software"
   :sobre "Breve descrição sobre você e suas habilidades principais."
   :experiencia [{:cargo "Engenheiro de software"
                 :empresa "Empresa XYZ"
                 :periodo "2020 - Presente"
                 :descricao "Descrição das suas responsabilidades"}]
   :educacao [{:curso "Bacharel em Ciência da Computação"
               :instituicao "Universidade XYZ"
               :ano "2015-2019"}]
   :habilidades ["Clojure" "JavaScript" "Python" "Kotlin"]})

(defn pagina-curriculo []
  (html5
    [:head
     [:title (str (:nome perfil-info) " - Currículo")]
     [:style "
      body { 
        font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
        margin: 0; 
        padding: 0;
        background: linear-gradient(135deg, #f5f7fa 0%, #e4e8eb 100%);
        min-height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
      }
      .profile { 
        text-align: center;
        padding: 20px;
        display: flex;
        flex-direction: column;
        align-items: center;
      }
      .profile-img { 
        width: 100px;
        height: 100px;
        border-radius: 100%; 
        margin-bottom: 20px; 
        object-fit: cover;
        box-shadow: 0 2px 8px rgba(0,0,0,0.08);
      }
      .social-buttons { 
        display: flex; 
        gap: 12px; 
        justify-content: center;
        margin-top: 15px;
      }
      .social-button { 
        padding: 8px 20px; 
        border-radius: 20px; 
        text-decoration: none;
        color: white;
        font-size: 14px;
        font-weight: 500;
        transition: all 0.2s ease;
        min-width: 90px;
      }
      .github { 
        background: #24292e;
      }
      .linkedin { 
        background: #0077b5;
      }
      .social-button:hover { 
        transform: translateY(-1px);
        opacity: 0.9;
        box-shadow: 0 2px 8px rgba(0,0,0,0.1);
      }
     "]]
    [:body
     [:div.container
      [:div.profile
       [:img.profile-img {:src "/profile/profile.png" :alt "Foto de Perfil"}]
       [:h1 (:nome perfil-info)]
       [:h2 (:titulo perfil-info)]
       [:div.social-buttons
        [:a.social-button.github {:href "https://github.com/Philipefb" :target "_blank"} "GitHub"]
        [:a.social-button.linkedin {:href "https://www.linkedin.com/in/philipefb/" :target "_blank"} "LinkedIn"]]
       [:p (:sobre perfil-info)]]
      
      [:h2 "Experiência"]
      (for [exp (:experiencia perfil-info)]
        [:div.experiencia
         [:h3 (:cargo exp)]
         [:p (:empresa exp) " | " (:periodo exp)]
         [:p (:descricao exp)]])
      
      [:h2 "Educação"]
      (for [edu (:educacao perfil-info)]
        [:div.educacao
         [:h3 (:curso edu)]
         [:p (:instituicao edu) " | " (:ano edu)]])
      
      [:h2 "Habilidades"]
      [:ul
       (for [skill (:habilidades perfil-info)]
         [:li skill])]]]))

(defroutes app-routes
  (GET "/" [] (pagina-curriculo)) 
  (route/resources "/")
  (route/not-found "Página não encontrada"))

(def app
  (-> app-routes
      (wrap-resource "public")
      (wrap-content-type)))

(defn -main []
  (jetty/run-jetty app {:port 3000 :join? true})
  (println "Servidor rodando em http://localhost:3000"))
