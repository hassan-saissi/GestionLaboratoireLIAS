-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: lias_db
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `affectation_materiel`
--

DROP TABLE IF EXISTS `affectation_materiel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `affectation_materiel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dateAttribution` date NOT NULL,
  `dateRetour` date DEFAULT NULL,
  `membre_id` bigint NOT NULL,
  `materiel_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_aff_materiel_membre` (`membre_id`),
  KEY `fk_aff_materiel_materiel` (`materiel_id`),
  CONSTRAINT `fk_aff_materiel_materiel` FOREIGN KEY (`materiel_id`) REFERENCES `materiel` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_aff_materiel_membre` FOREIGN KEY (`membre_id`) REFERENCES `membre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `affectation_role`
--

DROP TABLE IF EXISTS `affectation_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `affectation_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dateDebut` date NOT NULL DEFAULT (curdate()),
  `dateFin` date DEFAULT NULL,
  `estActif` tinyint(1) DEFAULT '1',
  `role` enum('MEMBRE_EQUIPE','CHEF_EQUIPE','VICE_DIRECTEUR1','DIRECTEUR','ADMIN') COLLATE utf8mb4_unicode_ci NOT NULL,
  `membre_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_aff_role_membre` (`membre_id`),
  CONSTRAINT `fk_aff_role_membre` FOREIGN KEY (`membre_id`) REFERENCES `membre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `demande_adhesion`
--

DROP TABLE IF EXISTS `demande_adhesion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `demande_adhesion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenom` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cvURL` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `lettreMotivation` text COLLATE utf8mb4_unicode_ci,
  `statutDemande` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'EN_ATTENTE',
  `dateSoumission` date NOT NULL DEFAULT (curdate()),
  `dateDecision` date DEFAULT NULL,
  `traitePar_membre_id` bigint DEFAULT NULL,
  `etablissement` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dm_adhesion_membre` (`traitePar_membre_id`),
  CONSTRAINT `fk_dm_adhesion_membre` FOREIGN KEY (`traitePar_membre_id`) REFERENCES `membre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `demande_materiel`
--

DROP TABLE IF EXISTS `demande_materiel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `demande_materiel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descriptionBesoin` text COLLATE utf8mb4_unicode_ci,
  `quantite` int DEFAULT '1',
  `dateDemande` date NOT NULL,
  `statutDemande` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT 'EN_ATTENTE',
  `membre_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_dm_materiel_membre` (`membre_id`),
  CONSTRAINT `fk_dm_materiel_membre` FOREIGN KEY (`membre_id`) REFERENCES `membre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `discussion`
--

DROP TABLE IF EXISTS `discussion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discussion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `contenu` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `dateEnvoi` datetime NOT NULL,
  `expediteur_membre_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_discussion_membre` (`expediteur_membre_id`),
  CONSTRAINT `fk_discussion_membre` FOREIGN KEY (`expediteur_membre_id`) REFERENCES `membre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `document`
--

DROP TABLE IF EXISTS `document`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `document` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `cheminFichier` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `dateUpload` datetime NOT NULL,
  `typeDocument` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `deposePar_membre_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_document_membre` (`deposePar_membre_id`),
  CONSTRAINT `fk_document_membre` FOREIGN KEY (`deposePar_membre_id`) REFERENCES `membre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `equipe`
--

DROP TABLE IF EXISTS `equipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `equipe` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nomEquipe` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `dateCreation` date NOT NULL DEFAULT (curdate()),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evenement` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `typeEvenement` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dateDebut` datetime DEFAULT NULL,
  `dateFin` datetime DEFAULT NULL,
  `lieu` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `edition` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `historique_affiliation`
--

DROP TABLE IF EXISTS `historique_affiliation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historique_affiliation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dateDebut` date NOT NULL,
  `dateFin` date DEFAULT NULL,
  `membre_id` bigint NOT NULL,
  `equipe_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_hs_aff_membre` (`membre_id`),
  KEY `fk_hs_aff_equipe` (`equipe_id`),
  CONSTRAINT `fk_hs_aff_equipe` FOREIGN KEY (`equipe_id`) REFERENCES `equipe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_hs_aff_membre` FOREIGN KEY (`membre_id`) REFERENCES `membre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `materiel`
--

DROP TABLE IF EXISTS `materiel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materiel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codeInventaire` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `typeMateriel` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `designation` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dateArrivage` date DEFAULT NULL,
  `etat` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `codeInventaire` (`codeInventaire`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `membre`
--

DROP TABLE IF EXISTS `membre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `membre` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nom` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prenom` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `photoURL` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(150) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `biography` text COLLATE utf8mb4_unicode_ci,
  `centresInteret` text COLLATE utf8mb4_unicode_ci,
  `etablissementOrigine` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `laboratoireOrigine` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dateNaissance` date DEFAULT NULL,
  `dateEmbauche` date DEFAULT NULL,
  `dateAffiliation` date DEFAULT NULL,
  `compteActif` tinyint(1) DEFAULT '1',
  `statut` enum('PERMANENT','ASSOCIE','DOCTORANT','RETRAITE','ANCIEN_MEMBRE') COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'DOCTORANT',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `message` text COLLATE utf8mb4_unicode_ci NOT NULL,
  `dateNotification` datetime NOT NULL,
  `typeNotification` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `estLue` tinyint(1) DEFAULT '0',
  `membre_id` bigint NOT NULL,
  `evenement_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_notification_membre` (`membre_id`),
  KEY `fk_notification_evenement` (`evenement_id`),
  CONSTRAINT `fk_notification_evenement` FOREIGN KEY (`evenement_id`) REFERENCES `evenement` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_notification_membre` FOREIGN KEY (`membre_id`) REFERENCES `membre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `organisateur_evenement`
--

DROP TABLE IF EXISTS `organisateur_evenement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `organisateur_evenement` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `roleOrganisation` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `dateAffectation` date DEFAULT NULL,
  `membre_id` bigint NOT NULL,
  `evenement_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_organisateur_evenement` (`evenement_id`),
  KEY `fk_organisateur_membre` (`membre_id`),
  CONSTRAINT `fk_organisateur_evenement` FOREIGN KEY (`evenement_id`) REFERENCES `evenement` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_organisateur_membre` FOREIGN KEY (`membre_id`) REFERENCES `membre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `partenaire`
--

DROP TABLE IF EXISTS `partenaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partenaire` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nomPartenaire` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `objetConvention` text COLLATE utf8mb4_unicode_ci,
  `dateDebut` date DEFAULT NULL,
  `dateFin` date DEFAULT NULL,
  `fichierConventionURL` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `evenement_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_partenaire_evenement` (`evenement_id`),
  CONSTRAINT `fk_partenaire_evenement` FOREIGN KEY (`evenement_id`) REFERENCES `evenement` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proces_verbal`
--

DROP TABLE IF EXISTS `proces_verbal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proces_verbal` (
  `id` bigint NOT NULL,
  `titreReunion` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ordreDuJour` text COLLATE utf8mb4_unicode_ci,
  `dateReunion` datetime DEFAULT NULL,
  `evenement_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_proces_evenement` (`evenement_id`),
  CONSTRAINT `fk_proces_evenement` FOREIGN KEY (`evenement_id`) REFERENCES `evenement` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `publication`
--

DROP TABLE IF EXISTS `publication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publication` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `titre` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `resume` text COLLATE utf8mb4_unicode_ci,
  `annee` int NOT NULL,
  `typePublication` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `auteur_membre_id` bigint NOT NULL,
  `equipe_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_publication_membre` (`auteur_membre_id`),
  KEY `fk_publication_equipe` (`equipe_id`),
  CONSTRAINT `fk_publication_equipe` FOREIGN KEY (`equipe_id`) REFERENCES `equipe` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_publication_membre` FOREIGN KEY (`auteur_membre_id`) REFERENCES `membre` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rapport_annuel`
--

DROP TABLE IF EXISTS `rapport_annuel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rapport_annuel` (
  `id` bigint NOT NULL,
  `annee` int NOT NULL,
  `dateGeneration` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'lias_db'
--
/*!50003 DROP PROCEDURE IF EXISTS `generate_fk` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'IGNORE_SPACE,ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `generate_fk`(

    in tab_name varchar(40),

    in fk_name varchar(40),

    in col_name varchar(40),

    in ref_tab_name varchar(40),

    in ref_col_name varchar(40)

)
begin

    set @sql = concat(

        'alter table `',tab_name,'` ',

        'add constraint `',fk_name,'` ',

        'foreign key (`',col_name,'`) ',

        'references `',ref_tab_name,'`(`',ref_col_name,'`) ',

        'on delete cascade on update cascade'

    );



    prepare stmt from @sql;

    execute stmt;

    deallocate prepare stmt;

    

    select concat('generation successefully !') as 'message';



end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-10-24 16:51:56
