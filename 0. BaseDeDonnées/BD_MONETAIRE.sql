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
-- Base de données :  `bd_monetaire`
--

-- --------------------------------------------------------

--
-- Structure de la table `coursmonetairetable`
--

DROP TABLE IF EXISTS `coursmonetairetable`;
CREATE TABLE IF NOT EXISTS `coursmonetairetable` (
  `UniteMonetaireBase` varchar(20) COLLATE latin1_general_cs NOT NULL,
  `UniteMonetaireResult` varchar(20) COLLATE latin1_general_cs NOT NULL,
  `CoursMonetaire` float NOT NULL,
  PRIMARY KEY (`UniteMonetaireBase`,`UniteMonetaireResult`,`CoursMonetaire`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_cs;

--
-- Déchargement des données de la table `coursmonetairetable`
--

INSERT INTO `coursmonetairetable` (`UniteMonetaireBase`, `UniteMonetaireResult`, `CoursMonetaire`) VALUES
('Dollar US', 'Franc Suisse', 0.97),
('Dollar US', 'Livre Sterling', 0.77),
('Dollar US', 'Yen', 109.5),
('Euro', 'Dollar US', 1.11),
('Euro', 'Franc Suisse', 1.08),
('Euro', 'Livre Sterling', 0.85),
('Euro', 'Yen', 121.76),
('Franc Suisse', 'Livre Sterling', 0.79),
('Franc Suisse ', 'Yen', 112.59),
('Livre Sterling', 'Yen', 143.53);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
