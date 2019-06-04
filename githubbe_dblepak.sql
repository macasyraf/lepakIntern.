-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 04, 2019 at 03:03 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `githubbe_dblepak`
--

-- --------------------------------------------------------

--
-- Table structure for table `companies`
--

CREATE TABLE `companies` (
  `compid` int(10) NOT NULL,
  `compname` varchar(30) NOT NULL,
  `compwebsite` varchar(100) NOT NULL,
  `compcontact` varchar(13) NOT NULL,
  `complocation` varchar(20) NOT NULL,
  `compsalary` varchar(20) NOT NULL,
  `comptask` varchar(20) NOT NULL,
  `compavailability` varchar(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `companies`
--

INSERT INTO `companies` (`compid`, `compname`, `compwebsite`, `compcontact`, `complocation`, `compsalary`, `comptask`, `compavailability`) VALUES
(1, 'Google', 'https://about.google/intl/en/', '0142221422', 'Kuala Lumpur', 'RM6000', 'Software Engineering', 'Available'),
(2, 'Abstrax', 'http://abstraxjingga.com/', '0174442144', 'Pulau Pinang', 'RM2400', 'Technical Support', 'Available'),
(3, 'Logitech', 'https://www.logitech.com/en-my', '0145521521', 'Johor Bharu', 'RM3250', 'Technical Assistant', 'Available'),
(4, 'Apple', 'https://www.apple.com/my/', '0156432166', 'Selangor', 'RM5600', 'System Analyst', 'Available'),
(5, 'Lazada', 'https://www.lazada.com.my/', '0165432155', 'Kuala Lumpur', 'RM5420', 'Programmer', 'Disable');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `matric` varchar(6) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `email`, `password`, `phone`, `matric`) VALUES
('Audiumx', 'officialaudiumx@gmail.com', '123456', '0142594964', '256599');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `companies`
--
ALTER TABLE `companies`
  ADD PRIMARY KEY (`compid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`matric`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `companies`
--
ALTER TABLE `companies`
  MODIFY `compid` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
