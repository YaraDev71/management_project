<h1>Projet : Application Spring Boot avec intégration Keycloak </h1>
<h3>Description</h3>
<p>Ce projet est une application Spring Boot permettant de gérer des projets, des tâches et des activités avec l'authentification et l'autorisation des utilisateurs fournies par Keycloak . L'application permet à différents rôles d'utilisateur (par exemple, SuperAdmin, Manager, Developer) de gérer et d'accéder aux ressources en fonction de leurs autorisations.</p>
<h3>Caractéristiques</h3>
<p>Points de terminaison API sécurisés avec OAuth2 et JWT via Keycloak.<br>
Opérations CRUD pour les projets, les tâches, les activités et les ressources.<br>
Contrôle d'accès basé sur les rôles (RBAC) pour les utilisateurs.<br>
Gestion des utilisateurs via Keycloak, y compris les rôles tels que SuperAdmin , Manager , Développeur , etc.<br>
Intégration avec la base de données MySQL pour la persistance des données.<br>
Authentification basée sur des jetons et gestion des jetons d'actualisation.</p>
<h3>Technologies utilisées</h3>
<p>Spring Boot : Cadre d'application de base.<br>
Keycloak : Gestion des identités et des accès (IAM) pour sécuriser l'application.<br>
MySQL : base de données pour stocker les données de projet, de tâche et d'activité.<br>
JWT : jeton Web JSON pour l'authentification API sécurisée.<br>
Facteur : utilisé pour tester les points de terminaison de l'API.<br>
Lombok : simplifie la création d'objets Java avec des annotations.
</p>