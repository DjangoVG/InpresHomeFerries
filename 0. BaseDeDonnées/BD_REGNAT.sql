-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 13 jan. 2020 à 21:57
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
-- Base de données :  `bd_regnat`
--

-- --------------------------------------------------------

--
-- Structure de la table `garde`
--

DROP TABLE IF EXISTS `garde`;
CREATE TABLE IF NOT EXISTS `garde` (
  `Id_Garde` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `NomGarde` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `PrenomGarde` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `PasswordGarde` varchar(150) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`Id_Garde`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `garde`
--

INSERT INTO `garde` (`Id_Garde`, `NomGarde`, `PrenomGarde`, `PasswordGarde`) VALUES
('Regis', 'Evrard', 'Regis', '1509442'),
('Yannis', 'Zekri', 'Yannis', '92668751');

-- --------------------------------------------------------

--
-- Structure de la table `individus`
--

DROP TABLE IF EXISTS `individus`;
CREATE TABLE IF NOT EXISTS `individus` (
  `NumRegistreNational` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `Nom` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `Prenom` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `DateNaissance` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `DateMaxValidite` varchar(20) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`NumRegistreNational`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `individus`
--

INSERT INTO `individus` (`NumRegistreNational`, `Nom`, `Prenom`, `DateNaissance`, `DateMaxValidite`) VALUES
('98060316502', 'Evrard', 'Regis', '03-06-1998', '12-12-2021'),
('98120115423', 'Zekri', 'Yannis', '10-10-1998', '12-10-2022'),
('123456789', 'Hauseux', 'Loic', '02-03-1998', '24-05-2018');

-- --------------------------------------------------------

--
-- Structure de la table `permis`
--

DROP TABLE IF EXISTS `permis`;
CREATE TABLE IF NOT EXISTS `permis` (
  `NumPermis` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `Nom` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `Prenom` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `DateMaxValidite` varchar(20) COLLATE latin1_general_ci NOT NULL,
  PRIMARY KEY (`NumPermis`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `permis`
--

INSERT INTO `permis` (`NumPermis`, `Nom`, `Prenom`, `DateMaxValidite`) VALUES
('134658712', 'Evrard', 'Regis', '12-06-2023'),
('154273984', 'Zekri', 'Yannis', '14-10-2019'),
('123456789', 'Hauseux', 'Loic', '12-03-2025');

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

DROP TABLE IF EXISTS `vehicule`;
CREATE TABLE IF NOT EXISTS `vehicule` (
  `Id_Immatriculation` varchar(20) COLLATE latin1_general_ci NOT NULL,
  `Assurance` tinyint(1) NOT NULL,
  `Proprietaire` tinyint(1) NOT NULL,
  `CarteVerte` tinyint(1) NOT NULL,
  PRIMARY KEY (`Id_Immatriculation`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`Id_Immatriculation`, `Assurance`, `Proprietaire`, `CarteVerte`) VALUES
('1-LGF-452', 1, 1, 0),
('1-FRT-489', 1, 0, 1),
('1-PVK-526', 1, 1, 1),
('1-ABC-159', 1, 0, 1),
('1-VRF-789', 1, 1, 1),
('1-LVO-157', 1, 1, 1),
('OUFTI', 0, 1, 1),
('1-ACC-458', 1, 1, 1),
('GNI-916', 1, 1, 0),
('1-GFE-189', 1, 1, 1),
('1-GFA-665', 1, 1, 1),
('1-VSS-653', 1, 1, 1),
('1-AZE-104', 1, 1, 0);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
