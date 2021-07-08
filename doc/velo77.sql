-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3307
-- Généré le : jeu. 08 juil. 2021 à 09:39
-- Version du serveur :  10.4.13-MariaDB
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `velo77`
--

-- --------------------------------------------------------

--
-- Structure de la table `item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE IF NOT EXISTS `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) COLLATE utf8_bin NOT NULL,
  `description` varchar(256) COLLATE utf8_bin NOT NULL,
  `price` double NOT NULL,
  `size` varchar(128) COLLATE utf8_bin NOT NULL,
  `collection` varchar(16) COLLATE utf8_bin NOT NULL,
  `gamme` varchar(32) COLLATE utf8_bin NOT NULL,
  `brand` varchar(64) COLLATE utf8_bin NOT NULL,
  `type` varchar(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `item`
--

INSERT INTO `item` (`id`, `name`, `description`, `price`, `size`, `collection`, `gamme`, `brand`, `type`) VALUES
(1, 'velo', 'ce velo vtt est juste incroyable', 5.123, 'S,XS,M,L', 'men', 'VTT', 'bmc', 'normal'),
(2, 'velo2', 'ce velo vtt est juste incroyable', 5.123, 'S,XS,M,L', 'men', 'course', 'fervelo', 'electric'),
(3, 'velo LOOK', 'ce velo vtt est juste incroyable disigné pour femme', 800.45, 'S,XS,M,L', 'women', 'ville', 'colnago', 'normal'),
(4, 'tarmac', 'ce velo est fait pour la competetion insane !!', 6000.5, 'XS,S,M,L', 'men', 'route', 'specialized', 'normal'),
(5, 'newTarmac', 'ce velo est fait pour la competetion insane tarmac !!', 6000.5, 'XS,S,M,L', 'men', 'route', 'specialized', 'normal');

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

DROP TABLE IF EXISTS `panier`;
CREATE TABLE IF NOT EXISTS `panier` (
  `idPanier` int(11) NOT NULL AUTO_INCREMENT,
  `idUser` int(11) NOT NULL,
  `idItem` int(11) NOT NULL,
  `nbItem` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`idPanier`),
  KEY `fk_item` (`idItem`),
  KEY `fk_user2` (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `panier`
--

INSERT INTO `panier` (`idPanier`, `idUser`, `idItem`, `nbItem`) VALUES
(1, 1, 1, 2);

-- --------------------------------------------------------

--
-- Structure de la table `session`
--

DROP TABLE IF EXISTS `session`;
CREATE TABLE IF NOT EXISTS `session` (
  `token` varchar(256) COLLATE utf8_bin NOT NULL,
  `expirationDate` date NOT NULL,
  `idUser` int(11) NOT NULL,
  PRIMARY KEY (`token`),
  KEY `fk_user` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `session`
--

INSERT INTO `session` (`token`, `expirationDate`, `idUser`) VALUES
('d1bb1bc7200c76cdaf980b6f3e7d9856950c48fff82e1b8987ad88a3dd578292', '2021-07-09', 7),
('eb74ad3fbf6877e02023e3b77c4b63972db1d22df65b9466854b4a40d6fb7502', '2021-06-17', 7),
('f219e1bcc91f2e357e7eb1406b126eac22d96b0aa5435ef49e4ad1e92f6bdeb0', '2021-07-22', 7),
('f4954a28a5874cca0164481b9a98adc17a05fb8011b78cf6266459cd32232d68', '2021-07-14', 7);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `idUser` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(32) COLLATE utf8_bin NOT NULL,
  `lastname` varchar(32) COLLATE utf8_bin NOT NULL,
  `email` varchar(128) COLLATE utf8_bin NOT NULL,
  `password` varchar(512) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`idUser`, `firstname`, `lastname`, `email`, `password`) VALUES
(1, 'noé', 'pigeau', 'npigeau@gmail.com', '2bbe0c48b91a7d1b8a6753a8b9cbe1db16b84379f3f91fe115621284df7a48f1cd71e9beb90ea614c7bd924250aa9e446a866725e685a65df5d139a5cd180dc9'),
(7, 'bg', 'de la street', 'email', 'd404559f602eab6fd602ac7680dacbfaadd13630335e951f097af3900e9de176b6db28512f2e000b9d04fba5133e8b1c6e8df59db3a8ab9d60be4b97cc9e81db'),
(8, 'bg', 'de la street', 'email123', 'd404559f602eab6fd602ac7680dacbfaadd13630335e951f097af3900e9de176b6db28512f2e000b9d04fba5133e8b1c6e8df59db3a8ab9d60be4b97cc9e81db');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `panier`
--
ALTER TABLE `panier`
  ADD CONSTRAINT `fk_item` FOREIGN KEY (`idItem`) REFERENCES `item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_user2` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `session`
--
ALTER TABLE `session`
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`idUser`) REFERENCES `user` (`idUser`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
