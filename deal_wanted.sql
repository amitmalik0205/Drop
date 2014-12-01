-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 01, 2014 at 08:13 AM
-- Server version: 5.5.24-log
-- PHP Version: 5.3.13

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `drop_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `deal_post`
--

CREATE TABLE IF NOT EXISTS `deal_wanted` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `ip_address` varchar(255) DEFAULT NULL,
  `reason_for_deleting` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `deal_category_id` bigint(20) DEFAULT NULL,
  `max_price` decimal(19,2) DEFAULT NULL,
  `tip_amount` decimal(19,2) DEFAULT NULL,  
  `want_new` bit(1) DEFAULT NULL,  
  `want_used` bit(1) DEFAULT NULL,  
  `refurbished_ok` bit(1) DEFAULT NULL,  
  `accept_coupons` bit(1) DEFAULT NULL,  
  `would_buy_online` bit(1) DEFAULT NULL,  
  `would_buy_locally` bit(1) DEFAULT NULL,
	  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `deal_post`
--
ALTER TABLE `deal_wanted`
  ADD CONSTRAINT FOREIGN KEY (`deal_category_id`) REFERENCES `deal_category` (`id`),
  ADD CONSTRAINT  FOREIGN KEY (`user_id`) REFERENCES `user` (`userId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
