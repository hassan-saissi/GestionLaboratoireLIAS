# 🏛️ Gestion Laboratoire LIAS

Application web Java EE (Servlets / JSP) développée pour la gestion administrative, le suivi des équipes, des publications scientifiques et de l'espace membre du laboratoire de recherche **LIAS**.

---

## 🚀 Fonctionnalités Principales

### 🌐 Espace Public (`/index`)
* **Présentation des équipes :** Consultation dynamique des équipes de recherche du laboratoire et de leurs descriptions.
* **Publications scientifiques :** Affichage des dernières publications de recherche enregistrées.
* **Demande d'adhésion :** Formulaire permettant aux chercheurs ou étudiants extérieurs de soumettre une demande d'intégration au laboratoire.

### 🔐 Authentification & Sécurité (`/login`)
* Connexion sécurisée des membres via email et mot de passe.
* Gestion des rôles applicatifs (`ADMIN`, `DIRECTEUR`, `MEMBRE`) stockés dans la table d'association `affectation_role`.
* Injection dynamique du rôle en session (`userRole`) et de l'objet membre (`userSession`) pour sécuriser l'affichage de la barre de navigation (`navbar.jsp`).

### 👤 Espace Membre (`/member/espace`)
* Consultation des informations personnelles et du statut académique (permanent, associé, doctorant, etc.).
* Suivi des publications personnelles et des équipements/matériels.

### ⚙️ Espace Administratif & Direction (`/admin/*`)
* **Tableau de bord administrateur :** Statistiques globales et gestion des comptes utilisateurs.
* **Validation des demandes :** Interface de traitement des demandes d'adhésion et de matériel.

---

## 🛠️ Stack Technique

* **Langage :** Java (JDK 8 ou supérieur)
* **Technologies Web :** Java EE / Jakarta EE (Servlets, JSP, JSTL)
* **Serveur d'application :** Apache Tomcat 9.0+
* **Base de données :** MySQL / MariaDB (via JDBC)
* **Architecture :** Modèle-Vue-Contrôleur (MVC) avec pattern DAO (Data Access Object)

---

## 📂 Architecture du Projet

```text
GestionLaboratoireLIAS/
│
├── src/main/java/com/lias/
│   ├── dao/             # Classes d'accès aux données (MembreDAO, EquipeDAO, PublicationDAO...)
│   ├── models/          # Objets métier / JavaBeans (Membre, Equipe, Publication...)
│   └── servlets/        # Contrôleurs HTTP (LoginServlet, IndexServlet, AdminDashboardServlet...)
│
├── src/main/webapp/
│   ├── WEB-INF/
│   │   └── jsp/         # Vues protégées (admin/, member/, public/)
│   ├── index.jsp        # Redirection ou point d'entrée public
│   └── ...
│
└── pom.xml              # Configuration Maven (si applicable) 
