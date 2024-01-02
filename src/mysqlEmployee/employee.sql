-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 02, 2024 at 01:01 AM
-- Server version: 8.2.0
-- PHP Version: 8.2.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `employee`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
CREATE TABLE IF NOT EXISTS `employee` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `FIRSTNAME` varchar(255) DEFAULT NULL,
  `JOBTITLE` varchar(255) DEFAULT NULL,
  `LASTNAME` varchar(255) DEFAULT NULL,
  `SALARY` double DEFAULT NULL,
  `STARTDATE` date DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`ID`, `FIRSTNAME`, `JOBTITLE`, `LASTNAME`, `SALARY`, `STARTDATE`) VALUES
(1, 'Andrea', 'Developer', 'García-González Manchón', 2000, '2002-12-02'),
(2, 'Alex', 'Mechanical Engineer', 'Vilar', 2000, '2023-03-18'),
(3, 'Montse', 'Vet', 'Manchón', 900000000, '2002-12-02'),
(4, 'Tomás', 'Student', 'García', 0, '2000-08-30'),
(5, 'Mar', 'student', 'Lopez', 100, '2004-12-06'),
(6, 'Mar', 'Stude', 'Lopez', 10, '2000-12-03'),
(7, 'María José', 'Teacherr', 'Ortiz Ariza', 2500, '2021-07-03'),
(8, 'Rosa', 'Sales', 'Manchón', 1800, '2005-06-02'),
(9, 'Roser', 'Retired', 'Castro', 1500, '2003-11-30'),
(10, 'Anna', 'Vet', 'Llorens Gutiérrez', 30000, '2000-08-25'),
(12, 'Andrea', 'Boss', 'García', 200000, '2024-01-02');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
