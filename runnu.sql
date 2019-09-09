-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 09, 2019 at 02:29 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `runnu`
--

-- --------------------------------------------------------

--
-- Table structure for table `highscore`
--

CREATE TABLE `highscore` (
  `player_id` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  `time` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `medal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `highscore`
--

INSERT INTO `highscore` (`player_id`, `score`, `time`, `level`, `medal`) VALUES
(1, 27880, 93, 1, 3),
(1, 7000, 120, 2, 2),
(1, 0, 0, 3, 0),
(2, 30520, 82, 1, 3),
(2, 7000, 120, 2, 2),
(2, 0, 0, 3, 0);

-- --------------------------------------------------------

--
-- Table structure for table `player_information`
--

CREATE TABLE `player_information` (
  `player_id` int(11) NOT NULL,
  `user_name` varchar(15) COLLATE utf8_bin NOT NULL,
  `password` varchar(32) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `player_information`
--

INSERT INTO `player_information` (`player_id`, `user_name`, `password`) VALUES
(1, 'Devesh', 'cec00531e4bafa71cb855056bcea8a0d'),
(2, 'Rohit', 'b753b5bf371166148ba5c80b6de7b882');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `player_information`
--
ALTER TABLE `player_information`
  ADD PRIMARY KEY (`player_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
