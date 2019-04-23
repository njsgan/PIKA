-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 18, 2019 at 03:19 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pikapos`
--

-- --------------------------------------------------------

--
-- Table structure for table `company`
--

CREATE TABLE `company` (
  `name` varchar(255) NOT NULL,
  `address` varchar(500) NOT NULL,
  `logo` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `fax` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `company`
--

INSERT INTO `company` (`name`, `address`, `logo`, `phone`, `fax`) VALUES
('PiKA Company', 'PiKA Avenue 69, Canada, United States', 'D:\\PATH_TO_LOGO\\logo.png', '+1-(515)-303-2505', '+1-(515)-505-2303');

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `id` int(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` int(255) NOT NULL,
  `stock` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`id`, `code`, `name`, `price`, `stock`) VALUES
(1, '4515701372029', 'Termos Miniso', 130000, 100),
(2, '97811189780859000', 'Financial Accounting 3e : IFRS Edition', 200000, 250),
(3, '8993838005069', 'Giant Faical Tissue', 15000, 1000);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `id` int(255) NOT NULL,
  `UID` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` varchar(500) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `point` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`id`, `UID`, `name`, `address`, `phone`, `point`) VALUES
(1, 'PIKAPOS123456789', 'JetGlad', 'Jl. Bahagia Sampai Janur Kuning Melengkung', '+6285773687363', 2000),
(2, 'PIKAPOS123456780', 'Jason Yonathan Hwang', 'Jl. Duri Nirmala ', '+6285700000000', 0),
(3, 'PIKAPOS123456788', 'Randy Wenkz', 'Jelambar', '+6285625433545', 10);

-- --------------------------------------------------------

--
-- Table structure for table `settings`
--

CREATE TABLE `settings` (
  `store` varchar(255) NOT NULL,
  `address` varchar(10000) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `fax` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `id` int(255) NOT NULL,
  `trxID` varchar(255) NOT NULL,
  `itemID` longtext NOT NULL,
  `itemQTY` longtext NOT NULL,
  `itemPRICE` longtext NOT NULL,
  `payment` enum('1','2','3') NOT NULL,
  `sales` varchar(255) NOT NULL,
  `member` varchar(100) DEFAULT NULL,
  `cardNum` varchar(100) DEFAULT NULL,
  `approvalCode` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactions`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(255) NOT NULL,
  `UID` varchar(255) NOT NULL,
  `username` varchar(200) NOT NULL,
  `fname` varchar(200) NOT NULL,
  `lname` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  `address` varchar(500) NOT NULL,
  `age` int(100) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `status` enum('1','2') NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `UID`, `username`, `fname`, `lname`, `password`, `address`, `age`, `phone`, `status`) VALUES
(1, 'PKPSCS1234567890', 'cashier', 'Amelia', 'Nur Ikwan', '098f6bcd4621d373cade4e832627b4f6', 'Jl. Jalan Setiap Hari', 27, '085327656565', '1'),
(2, 'PKPSSP1234567890', 'supervisor', 'Rangga', 'Huang', '098f6bcd4621d373cade4e832627b4f6', 'Jl. Untuk Cepat Kaya', 30, '0857000707', '2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
