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
-- Base de données :  `bd_ferries`
--

-- --------------------------------------------------------

--
-- Structure de la table `agents`
--

DROP TABLE IF EXISTS `agents`;
CREATE TABLE IF NOT EXISTS `agents` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `Password` varchar(200) COLLATE latin1_general_ci NOT NULL,
  `TypeAgent` smallint(6) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `agents`
--

INSERT INTO `agents` (`Id`, `Username`, `Password`, `TypeAgent`) VALUES
(1, 'Regis', '1234', 2),
(2, 'Yannis', '1234', 2),
(3, 'admin', '1234', 1);

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `NumClient` int(11) NOT NULL,
  `PasswordClient` varchar(300) COLLATE latin1_general_ci NOT NULL,
  `NomClient` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `PrenomClient` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `AdresseClient` varchar(150) COLLATE latin1_general_ci NOT NULL,
  `EmailClient` varchar(50) COLLATE latin1_general_ci NOT NULL,
  `NewUserPromo` tinyint(1) NOT NULL,
  PRIMARY KEY (`NumClient`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 COLLATE=latin1_general_ci;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`NumClient`, `PasswordClient`, `NomClient`, `PrenomClient`, `AdresseClient`, `EmailClient`, `NewUserPromo`) VALUES
(1, '1234', 'Evrard', 'Regis', 'Rue des orchidées, 166 4030 Liège', 'regis4d@hotmail.com', 1),
(1497837251, '1234', 'Zekri', 'Yannis', 'Rue des poitiers 166, 4030 Liege', 'yannis.zekri@hotmail.com', 0),
(181445079, '1234', 'Amaury', 'Louis', 'Rue des potiers 3, 1000 Bruxelles', 'amaury@gmail.com', 1),
(1690237097, '1234', 'Marchant', 'Lucas', 'Rue des lucas, 1 4000 Liege', 'lucas@skynet.be', 1),
(480664150, '1234', 'Madani', 'Mounawar', 'Rue des mounawars 12, 4521 Liege', 'madani@hotmail.com', 1),
(273036762, '1234', 'Vilvens', 'Claude', 'Rue des vilvens 12, 4052 Hannut', 'vilvens@hotmail.com', 1),
(655066688, 'rifnux-sohQu9-giwpuc', 'Hauseux', 'LoÃ¯c', 'Kinkenweg 35, 4850 Montzen', 'loic.hauseux@me.com', 1),
(261370178, '1234567890', 'Hauseux', 'LoÃ¯c', 'Kinkenweg 35', 'hauseuxloic@gmail.com', 1),
(179531928, '1234', 'Putz', 'Philippe', 'Rue de la foret 16, 4000 Liege', 'philippe.putz@hotmail.com', 1),
(1502293083, 'Waw147', 'Peters', 'LoÃ¯c', 'Rue de la souris Ã  boule 4', 'looc.peters@gmail.be', 1),
(1156165647, '1998', 'Loic', 'Hauseux', 'Aucune', 'lol@ha.be', 1),
(658656142, '1234', 'Calmant', 'Popaul', 'rue des potiers', 'popo@gmail.com', 1);

-- --------------------------------------------------------

--
-- Structure de la table `files`
--

DROP TABLE IF EXISTS `files`;
CREATE TABLE IF NOT EXISTS `files` (
  `NumFile` varchar(20) NOT NULL,
  `CapaciteMaxFile` int(11) NOT NULL,
  `CapaciteActuelFile` int(11) NOT NULL,
  `Matricule` varchar(20) NOT NULL,
  PRIMARY KEY (`NumFile`,`Matricule`),
  KEY `File$Navire$FK` (`Matricule`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `files`
--

INSERT INTO `files` (`NumFile`, `CapaciteMaxFile`, `CapaciteActuelFile`, `Matricule`) VALUES
('1', 15, 15, '1'),
('1', 300, 50, '2'),
('2', 20, 20, '1'),
('3', 15, 15, '1'),
('2', 200, 199, '2'),
('1', 150, 21, '3'),
('2', 150, 0, '3'),
('1', 5, 5, '4'),
('2', 25, 0, '4'),
('1', 100, 16, '5'),
('2', 300, 300, '5');

-- --------------------------------------------------------

--
-- Structure de la table `navires`
--

DROP TABLE IF EXISTS `navires`;
CREATE TABLE IF NOT EXISTS `navires` (
  `Matricule` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(200) NOT NULL,
  `Capacites` varchar(100) NOT NULL,
  PRIMARY KEY (`Matricule`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `navires`
--

INSERT INTO `navires` (`Matricule`, `Nom`, `Capacites`) VALUES
(1, 'Black Pearl', '50'),
(2, 'Titanic', '500'),
(3, 'Jackdaw', '300'),
(4, 'Le Hollandais volant', '30'),
(5, 'L\'arche de Noé', '400');

-- --------------------------------------------------------

--
-- Structure de la table `ports`
--

DROP TABLE IF EXISTS `ports`;
CREATE TABLE IF NOT EXISTS `ports` (
  `IdPorts` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(200) NOT NULL,
  `NombresTerminauxTot` int(11) NOT NULL,
  `NombresTerminauxOccupes` int(11) NOT NULL,
  PRIMARY KEY (`IdPorts`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `ports`
--

INSERT INTO `ports` (`IdPorts`, `Nom`, `NombresTerminauxTot`, `NombresTerminauxOccupes`) VALUES
(1, 'Anvers', 5, 0),
(2, 'Ajaccio', 2, 0),
(3, 'Altissia', 3, 0),
(4, 'Rio', 3, 0),
(5, 'Nice', 3, 0),
(6, 'Caen', 3, 0),
(7, 'Montpellier', 7, 2),
(9, 'LIEGE', 10, 0);

-- --------------------------------------------------------

--
-- Structure de la table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
CREATE TABLE IF NOT EXISTS `reservations` (
  `IdReservations` varchar(20) NOT NULL,
  `IdTraversee` varchar(20) NOT NULL,
  `NumeroClient` int(20) NOT NULL,
  `Payer` tinyint(1) NOT NULL,
  `PassCheckIN` tinyint(1) NOT NULL,
  `NbrVoyageur` int(11) DEFAULT NULL,
  `AnneeReservations` int(11) DEFAULT '2019',
  `NumFacture` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`IdReservations`),
  KEY `Reservation$Traversee$FK` (`IdTraversee`),
  KEY `Reservation$Voyageurs$FK` (`NumeroClient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `reservations`
--

INSERT INTO `reservations` (`IdReservations`, `IdTraversee`, `NumeroClient`, `Payer`, `PassCheckIN`, `NbrVoyageur`, `AnneeReservations`, `NumFacture`) VALUES
('20110915-RES01', '20190918-TR48', 1, 0, 1, 5, 2011, ''),
('20110915-RES02', '20190918-TR26', 2, 1, 1, 2, 2011, ''),
('20110915-RES03', '20190918-TR03', 3, 0, 1, 3, 2011, ''),
('20191018-RES86022', '20190918-TR26', 2, 0, 0, 3, 2019, ''),
('20191020-RES1896', '20190918-TR48', 2, 0, 0, 4, 2019, ''),
('20191021-RES49424', '20190918-TR26', 2, 0, 0, 4, 2019, ''),
('20191021-RES73653', '20190918-TR48', 2, 0, 0, 1, 2019, ''),
('20191022-RES4169', '20190918-TR48', 2, 0, 0, 6, 2019, ''),
('20191022-RES74845', '20190918-TR48', 2, 0, 0, 3, 2019, ''),
('20191023-RES3472', '20190918-TR13', 2, 0, 1, 3, 2019, ''),
('20191023-RES3489', '20190918-TR48', 1, 1, 0, 4, 2019, ''),
('20191023-RES3559', '20190918-TR412', 2, 0, 1, 2, 2019, ''),
('20191023-RES4912', '20190918-TR48', 2, 1, 1, 3, 2019, ''),
('20191023-RES6952', '20190918-TR03', 2, 1, 0, 1, 2019, ''),
('20191023-RES7692', '20190918-TR112', 2, 1, 0, 4, 2019, ''),
('20191023-RES782', '20190918-TR13', 2, 0, 0, 6, 2019, ''),
('20191023-RES8600', '20190918-TR69', 2, 0, 0, 3, 2019, ''),
('20191024-RES1129', '20190918-TR49', 1, 1, 1, 3, 2019, ''),
('20191024-RES5236', '20190918-TR03', 2, 1, 1, 5, 2019, ''),
('20191024-RES5964', '20190918-TR03', 2, 1, 1, 7, 2019, ''),
('20191024-RES8085', '20190918-TR48', 2, 1, 1, 2, 2019, ''),
('20191025-RES1276', '20190918-TR26', 2, 1, 1, 5, 2019, ''),
('20191025-RES2049', '20190918-TR03', 2, 1, 0, 3, 2019, ''),
('20191025-RES3830', '20190918-TR03', 2, 1, 0, 6, 2019, ''),
('20191025-RES5453', '20190918-TR03', 2, 1, 1, 2, 2019, ''),
('20191025-RES6393', '20190918-TR03', 2, 1, 0, 2, 2019, ''),
('20191025-RES8037', '20190918-TR03', 2, 1, 0, 3, 2019, ''),
('20191025-RES9766', '20190918-TR03', 2, 1, 0, 5, 2019, ''),
('20191127-RES1350', '20190918-TR03', 1, 1, 0, 3, 2019, ''),
('20191127-RES1618', '20190918-TR03', 1, 1, 0, 2, 2019, ''),
('20191127-RES2528', '20190918-TR26', 1, 1, 0, 2, 2019, ''),
('20191127-RES271', '20190918-TR26', 1, 1, 0, 3, 2019, ''),
('20191127-RES3212', '20190918-TR03', 1, 1, 0, 4, 2019, ''),
('20191127-RES3562', '20190918-TR03', 1, 1, 0, 3, 2019, ''),
('20191127-RES3596', '20190918-TR03', 1, 1, 0, 3, 2019, ''),
('20191127-RES3663', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191127-RES3671', '20190918-TR49', 1, 1, 0, 0, 2019, ''),
('20191127-RES37', '20190918-TR03', 1, 1, 0, 0, 2019, ''),
('20191127-RES4057', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191127-RES4226', '20190918-TR48', 1, 1, 0, 0, 2019, ''),
('20191127-RES4419', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191127-RES4460', '20190918-TR03', 1, 1, 0, 0, 2019, ''),
('20191127-RES4645', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191127-RES5105', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191127-RES6164', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191127-RES6361', '20190918-TR112', 1, 1, 0, 0, 2019, ''),
('20191127-RES7563', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191127-RES758', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191127-RES7639', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191127-RES7763', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191127-RES7788', '20190918-TR03', 1, 1, 0, 0, 2019, ''),
('20191127-RES7994', '20190918-TR03', 1, 1, 0, 0, 2019, ''),
('20191127-RES8298', '20190918-TR03', 1, 1, 0, 0, 2019, ''),
('20191127-RES882', '20190918-TR03', 1, 1, 0, 0, 2019, ''),
('20191127-RES8859', '20190918-TR03', 1, 1, 0, 0, 2019, ''),
('20191127-RES9234', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191127-RES9751', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191128-RES4294', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191128-RES4775', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191128-RES4893', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191128-RES5585', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191128-RES6461', '20190918-TR03', 1, 1, 0, 0, 2019, ''),
('20191128-RES7317', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191128-RES777', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191128-RES8194', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191128-RES887', '20190918-TR03', 1, 1, 0, 0, 2019, ''),
('20191129-RES4599', '20190918-TR03', 1, 1, 0, 0, 2019, ''),
('20191129-RES8947', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191129-RES9594', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20191129-RES9969', '20190918-TR26', 1, 1, 0, 0, 2019, ''),
('20200113-RES1409', '20190918-TR26', 1, 1, 0, NULL, 2019, ''),
('20200113-RES1548', '20190918-TR26', 1, 1, 0, NULL, 2019, ''),
('20200113-RES2298', '20190918-TR49', 1, 1, 0, NULL, 2019, ''),
('20200113-RES2359', '20190918-TR26', 1, 1, 0, NULL, 2019, ''),
('20200113-RES3741', '20190918-TR48', 1, 1, 0, NULL, 2019, ''),
('20200113-RES3802', '20190918-TR26', 1, 1, 0, NULL, 2019, ''),
('20200113-RES4085', '20190918-TR26', 1, 1, 1, NULL, 2019, 'Facture_Evrard'),
('20200113-RES6310', '20190203-TR28', 2, 1, 0, NULL, 2019, ''),
('20200113-RES6583', '20190203-TR28', 2, 1, 0, NULL, 2019, ''),
('20200113-RES7434', '20190918-TR26', 1, 1, 0, NULL, 2019, ''),
('20200113-RES7821', '20190918-TR03', 1, 1, 0, NULL, 2019, ''),
('20200113-RES8547', '20190918-TR26', 1, 1, 0, NULL, 2019, ''),
('20200113-RES9870', '20190918-TR26', 1, 1, 0, NULL, 2019, '');

-- --------------------------------------------------------

--
-- Structure de la table `traversees`
--

DROP TABLE IF EXISTS `traversees`;
CREATE TABLE IF NOT EXISTS `traversees` (
  `IdTraversees` varchar(20) NOT NULL,
  `DateDepart` datetime NOT NULL,
  `PortDepart` varchar(200) NOT NULL,
  `PortArrivee` varchar(200) NOT NULL,
  `Matricule` int(11) NOT NULL,
  `Prix` int(11) NOT NULL,
  PRIMARY KEY (`IdTraversees`),
  KEY `Traversee$navires$FK` (`Matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `traversees`
--

INSERT INTO `traversees` (`IdTraversees`, `DateDepart`, `PortDepart`, `PortArrivee`, `Matricule`, `Prix`) VALUES
('20190203-TR28', '2019-12-20 13:00:00', 'Calais', 'New-York', 2, 50),
('20190306-TR74', '2020-01-16 10:00:00', 'Nagoya', 'Richards Bay', 4, 33),
('20190918-TR03', '2019-10-03 09:00:00', 'Alaska', 'Rio', 3, 31),
('20190918-TR1110', '2019-11-28 18:00:00', 'Richards Bay', 'Calais', 1, 28),
('20190918-TR112', '2019-11-06 21:30:00', 'Anvers', 'Perpignan', 1, 10),
('20190918-TR13', '2019-11-14 12:00:00', 'Anvers', 'Montpellier', 5, 120),
('20190918-TR19', '2019-11-28 18:00:00', 'Shanghai', 'Richards Bay', 2, 84),
('20190918-TR192', '2019-12-26 10:00:00', 'Hong Kong', 'Richards Bay', 2, 68),
('20190918-TR26', '2019-08-10 06:00:00', 'Alaska', 'Calais', 2, 56),
('20190918-TR412', '2019-10-30 22:15:00', 'Rio', 'Nice', 4, 100),
('20190918-TR458', '2019-11-11 21:45:00', 'Hong Kong', 'Shanghai', 1, 80),
('20190918-TR48', '2019-10-09 08:45:00', 'Londres', 'Calais', 1, 27),
('20190918-TR49', '2019-10-26 07:45:00', 'Calais', 'Anvers', 3, 60),
('20190918-TR69', '2019-11-29 18:00:00', 'Liege', 'Calais', 4, 14),
('20190918-TR781', '2019-11-30 20:00:00', 'New-York', 'Nagoya', 3, 70),
('20190923-TR7844', '2020-02-11 21:30:00', 'New-York', 'Shanghai', 3, 110),
('20191115-TR989', '2019-11-28 18:00:00', 'Nagoya', 'Calais', 1, 45),
('20191212-TR149', '2019-11-11 14:00:00', 'Rotterdam', 'Hong Kong', 5, 165);

-- --------------------------------------------------------

--
-- Structure de la table `voyageurs`
--

DROP TABLE IF EXISTS `voyageurs`;
CREATE TABLE IF NOT EXISTS `voyageurs` (
  `NumeroClient` int(11) NOT NULL,
  `Nom` varchar(200) NOT NULL,
  `Prenom` varchar(200) NOT NULL,
  `Adresse` varchar(200) NOT NULL,
  `Mail` varchar(200) NOT NULL,
  `Age` int(11) NOT NULL,
  PRIMARY KEY (`NumeroClient`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `voyageurs`
--

INSERT INTO `voyageurs` (`NumeroClient`, `Nom`, `Prenom`, `Adresse`, `Mail`, `Age`) VALUES
(1, 'Zekri', 'Yannis', 'Peteermans, Seraing', 'yanniszekri@hotmail.com', 20),
(2, 'Evrard', 'Regis', 'Rue du béton, Liège', 'evrardregis@gmail.com', 35),
(3, 'Admin', 'admin', 'Rue des Potiers, Bruxelles', 'adminadmin@gmail.com', 54);

-- --------------------------------------------------------

--
-- Structure de la table `voyageurs_accompagnants`
--

DROP TABLE IF EXISTS `voyageurs_accompagnants`;
CREATE TABLE IF NOT EXISTS `voyageurs_accompagnants` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nom` varchar(200) NOT NULL,
  `Prenom` varchar(200) NOT NULL,
  `Adresse` varchar(200) NOT NULL,
  `NumeroClient` varchar(15) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `voyageurs_accompagnants$Voyageurs$FK` (`NumeroClient`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
