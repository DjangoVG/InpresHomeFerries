-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  mar. 03 déc. 2019 à 13:42
-- Version du serveur :  5.7.26
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bd_card`
--

-- --------------------------------------------------------

--
-- Structure de la table `banque`
--

DROP TABLE IF EXISTS `banque`;
CREATE TABLE IF NOT EXISTS `banque` (
  `IdBanque` int(11) NOT NULL,
  `NomBanque` varchar(30) COLLATE latin1_general_ci NOT NULL,
  `Pays` varchar(30) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`IdBanque`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `banque`
--

INSERT INTO `banque` (`IdBanque`, `NomBanque`, `Pays`) VALUES
(1, 'BNP Paribas Fortis', 'Belgique'),
(2, 'ING', 'Belgique'),
(3, 'Belfius', 'Belgique'),
(4, 'Dexia', 'France'),
(5, 'KBC', 'Belgique'),
(6, 'Crédit Agricole', 'France');

-- --------------------------------------------------------

--
-- Structure de la table `carte_de_credit`
--

DROP TABLE IF EXISTS `carte_de_credit`;
CREATE TABLE IF NOT EXISTS `carte_de_credit` (
  `NumCarte` varchar(30) COLLATE latin1_general_ci NOT NULL,
  `NumCompte` varchar(30) COLLATE latin1_general_ci NOT NULL,
  `DateExpiration` date NOT NULL,
  `CodeCVC` smallint(6) NOT NULL,
  `Nom` varchar(100) COLLATE latin1_general_ci NOT NULL,
  `Prenom` varchar(100) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`NumCarte`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `carte_de_credit`
--

INSERT INTO `carte_de_credit` (`NumCarte`, `NumCompte`, `DateExpiration`, `CodeCVC`, `Nom`, `Prenom`) VALUES
('1234-5678-9012-3456', 'BE44363126621221', '2019-10-20', 12, 'Evrard', 'Regis'),
('0123-0123-0123-0123', 'BE87221022556677', '2019-10-24', 12, 'Zekri', 'Yannis');

-- --------------------------------------------------------

--
-- Structure de la table `compte_bancaire`
--

DROP TABLE IF EXISTS `compte_bancaire`;
CREATE TABLE IF NOT EXISTS `compte_bancaire` (
  `NumCompte` varchar(30) COLLATE latin1_general_ci NOT NULL,
  `Solde` int(11) DEFAULT NULL,
  `NomBanque` varchar(30) COLLATE latin1_general_ci NOT NULL,
  `Titulaire` varchar(100) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`NumCompte`),
  UNIQUE KEY `NomBanque` (`NomBanque`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `compte_bancaire`
--

INSERT INTO `compte_bancaire` (`NumCompte`, `Solde`, `NomBanque`, `Titulaire`) VALUES
('BE44363126621221', 467, 'BNP Paribas Fortis', 'Evrard Regis'),
('BE87221022556677', 3000, 'ING', 'Zekri Yannis');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
