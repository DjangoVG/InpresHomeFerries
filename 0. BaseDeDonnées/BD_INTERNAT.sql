-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 13 jan. 2020 à 21:56
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
-- Base de données :  `bd_internat`
--

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
('98060316654', 'Moussouf', 'Abdel', '03-06-1998', '12-12-2021'),
('98120115429', 'Giovanni', 'Alessandro', '10-10-1999', '12-10-2022'),
('123456789', 'Boraita Amador', 'Manuel', '02-03-1995', '24-05-2018');

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
('134658715', 'Moussafa', 'Abdel', '12-06-2023'),
('154273984', 'Giovanni', 'Alessandro', '14-10-2019'),
('123456789', 'Boraita Amador', 'Manuel', '12-03-2024');

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
('AC-336-DF', 1, 1, 0),
('FD-316-DR', 1, 0, 1),
('FR-445-CD', 1, 1, 1),
('FG-145-DC', 1, 0, 1),
('CV-569-GB', 1, 1, 1);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
