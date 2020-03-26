-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  sam. 02 nov. 2019 à 13:01
-- Version du serveur :  5.7.19
-- Version de PHP :  5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `bd_aide_decision`
--

-- --------------------------------------------------------

--
-- Structure de la table `donnees`
--

DROP TABLE IF EXISTS `donnees`;
CREATE TABLE IF NOT EXISTS `donnees` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `PValue` double NOT NULL,
  `Difference` tinyint(4) NOT NULL DEFAULT '0',
  `NumRequete` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `donnees`
--

INSERT INTO `donnees` (`Id`, `PValue`, `Difference`, `NumRequete`) VALUES
(1, 0.4480542742541826, 1, 3),
(2, 0.4480542742541826, 1, 3),
(3, 0.4480542742541826, 1, 3),
(4, 0.08113175260674517, 1, 5);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
