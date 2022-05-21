-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 27, 2020 at 07:40 AM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_uni`
--

-- --------------------------------------------------------

--
-- Table structure for table `t_assignments`
--

DROP TABLE IF EXISTS `t_assignments`;
CREATE TABLE IF NOT EXISTS `t_assignments` (
  `assignment_id` int(8) NOT NULL AUTO_INCREMENT,
  `assignment_classid` int(8) DEFAULT NULL,
  `assignment_profid` int(8) DEFAULT NULL,
  `assignment_studentid` int(8) DEFAULT NULL,
  `assignment_type` int(1) DEFAULT NULL,
  `assignment_points` double DEFAULT NULL,
  `assignment_pointspossible` double DEFAULT NULL,
  `assignment_assigneddate` date DEFAULT NULL,
  `assignment_duedate` date DEFAULT NULL,
  `assignment_name` varchar(80) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`assignment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `t_assignments`
--

INSERT INTO `t_assignments` (`assignment_id`, `assignment_classid`, `assignment_profid`, `assignment_studentid`, `assignment_type`, `assignment_points`, `assignment_pointspossible`, `assignment_assigneddate`, `assignment_duedate`, `assignment_name`) VALUES
(3, 2, 2, NULL, 1, NULL, 10, '2020-02-01', '2020-03-01', 'Assignment 1: Do the thing'),
(4, 2, 2, NULL, 1, NULL, 10, '2020-01-29', '2020-04-27', 'Assignment 2: Do it from within the program-boog-a-loo'),
(5, 5, 4, NULL, 1, NULL, 10, '2020-02-01', '2020-05-10', 'Anthropology Assignment 1'),
(7, 5, 4, NULL, 1, NULL, 10, '2020-01-21', '2020-05-21', 'teSTING'),
(8, 5, 4, NULL, 1, NULL, 10, '2020-01-21', '2020-05-10', 'Anthropology: Auto-assign');

-- --------------------------------------------------------

--
-- Table structure for table `t_classes`
--

DROP TABLE IF EXISTS `t_classes`;
CREATE TABLE IF NOT EXISTS `t_classes` (
  `class_id` int(8) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(80) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `class_credithours` int(1) DEFAULT NULL,
  `class_profid` int(8) DEFAULT NULL,
  `class_location` varchar(80) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `class_startdate` date DEFAULT NULL,
  `class_enddate` date DEFAULT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `t_classes`
--

INSERT INTO `t_classes` (`class_id`, `class_name`, `class_credithours`, `class_profid`, `class_location`, `class_startdate`, `class_enddate`) VALUES
(2, 'Java Fundamentals', 3, 2, '999', '2020-01-21', '2020-05-05'),
(3, 'Python: Languages not snakes!', 3, 2, '2130 King Hall', '2020-01-29', '2020-01-29'),
(4, 'Pentesting: Security Expertise', 4, 3, '999', '2020-01-21', '2020-01-21'),
(5, 'Anthropology of Computer Programming: By Harrison Jones', 5, 4, '999', '2020-01-21', '2020-01-21'),
(6, 'Magic Frield Trips Everyday: By The Frizz', 2, 5, '999', '2020-01-21', '2020-01-21');

-- --------------------------------------------------------

--
-- Table structure for table `t_profs`
--

DROP TABLE IF EXISTS `t_profs`;
CREATE TABLE IF NOT EXISTS `t_profs` (
  `prof_id` int(8) NOT NULL AUTO_INCREMENT,
  `prof_fname` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prof_lname` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prof_dob` date NOT NULL,
  `prof_phone` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prof_address` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prof_email` varchar(80) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`prof_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `t_profs`
--

INSERT INTO `t_profs` (`prof_id`, `prof_fname`, `prof_lname`, `prof_dob`, `prof_phone`, `prof_address`, `prof_email`) VALUES
(2, 'Jennifer', 'King', '1971-11-30', '5553777066', '2145 S. South St. Toledo OH 43619', 'Jennifer.king@email.domain'),
(3, 'Albus', 'Dumbeldor', '1564-12-24', '555101010101', '1 Hogwarts #1 London England', 'Albus.Dumbledor@email.domain'),
(4, 'Harrison', 'Jones', '1899-07-01', '6099218500', '36 University Pl, Prinston, NJ, 08540', 'Harrison.Jones@Indiana.Ford'),
(5, 'Mrs. ', 'Frizzle', '1994-09-10', '5556768934', '1. Scool Way Magicland #####', 'Mrs.Frizzle@email.domain');

-- --------------------------------------------------------

--
-- Table structure for table `t_studentassignment`
--

DROP TABLE IF EXISTS `t_studentassignment`;
CREATE TABLE IF NOT EXISTS `t_studentassignment` (
  `assignmentID` int(8) NOT NULL AUTO_INCREMENT,
  `assignment_sid` int(11) DEFAULT NULL,
  `assignment_pid` int(11) DEFAULT NULL,
  `assignment_pearned` int(11) DEFAULT NULL,
  `assignment_ppossible` int(11) DEFAULT NULL,
  `assignment_assid` int(11) DEFAULT NULL,
  `assignment_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`assignmentID`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `t_studentassignment`
--

INSERT INTO `t_studentassignment` (`assignmentID`, `assignment_sid`, `assignment_pid`, `assignment_pearned`, `assignment_ppossible`, `assignment_assid`, `assignment_type`) VALUES
(1, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `t_students`
--

DROP TABLE IF EXISTS `t_students`;
CREATE TABLE IF NOT EXISTS `t_students` (
  `student_id` int(8) NOT NULL AUTO_INCREMENT,
  `student_fname` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `student_lname` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `student_dob` date NOT NULL,
  `student_address` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `student_phone` varchar(80) COLLATE utf8mb4_unicode_ci NOT NULL,
  `student_email` varchar(80) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `student_classid` int(80) DEFAULT NULL,
  `student_classid1` int(80) DEFAULT NULL,
  `student_classid2` int(80) DEFAULT NULL,
  `student_classid3` int(80) DEFAULT NULL,
  `student_classid4` int(80) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=MyISAM AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `t_students`
--

INSERT INTO `t_students` (`student_id`, `student_fname`, `student_lname`, `student_dob`, `student_address`, `student_phone`, `student_email`, `student_classid`, `student_classid1`, `student_classid2`, `student_classid3`, `student_classid4`) VALUES
(3, 'Alexander', 'King', '1991-10-17', '3838 W Sesame St. #209 Toledo Ohio 43623', '5559773252', 'Alexander.King2@email.domain', 2, 3, 4, 5, 6),
(4, 'Jackie', 'Navarre', '1987-11-14', '3838 W Sesame St. Toledo OH 43623', '5559772180', 'Jackie.Navarre@email.domain', 2, 3, NULL, NULL, NULL),
(5, 'Tom', 'Smith', '2000-01-01', '1234 Wallaby Way Toledo OH 43623', '5552440212', 'Tom.Smith@email.domain', 2, NULL, NULL, NULL, NULL),
(6, 'Timmy', 'Little', '2020-01-01', '420 Wallaby Way Toledo OH 43605', '5558675309', 'Timmy.Little@email.domain', 2, NULL, NULL, NULL, NULL),
(7, 'Nicholas', 'King', '2001-10-21', '903 Bittler St. Toledo OH 43605', '5556979227', 'Nick.King@email.domain', 2, NULL, NULL, NULL, NULL),
(8, 'Miranda', 'King', '1999-04-29', '69 Butts Dr. Sylvania OH 43623', '5556660666', 'Miranda.King@email.domain', 2, NULL, NULL, NULL, NULL),
(9, 'Jennifer', 'Navarre', '1999-04-29', '894 Jipe-Jipe', '5552222222', 'Jennifer.Navarre@email.domain', 2, NULL, NULL, NULL, NULL),
(12, 'Rickity', 'Cricket', '1940-11-02', '1 Park Ave #5 Perrysberg OH 43616', '5553443786', 'Rickity.Cricket@email.domain', 2, NULL, NULL, NULL, NULL),
(18, 'Suzie', 'Que', '1966-05-13', '42 Clearwater Revival Way. Toledo OH 43605', '5553035489', 'Suzie.Que@email.domain', 2, NULL, NULL, NULL, NULL),
(19, 'Linus', 'Torvalds', '1969-12-28', '1 Letterman Drive Build D Suite D4700 San Francisco CA 94129', '4517239709', 'info@linuxfoundation.org', 2, NULL, NULL, NULL, NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
