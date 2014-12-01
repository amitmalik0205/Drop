-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 01, 2014 at 09:52 AM
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
-- Table structure for table `deal_category`
--

CREATE TABLE IF NOT EXISTS `deal_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `icon_path` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `picture_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `deal_category`
--

INSERT INTO `deal_category` (`id`, `description`, `icon_path`, `name`, `picture_path`) VALUES
(1, 'Appliances', 'electronics.jpg', 'Appliances', 'electronics.png'),
(2, 'Arts & Crafts', 'arts.jpg', 'Arts & Crafts', 'arts.jpg'),
(3, 'Cars & Trucks', 'cars.jpg', 'Cars & Trucks', 'cars.jpg'),
(4, 'Auto Parts', 'auto_parts.jpg', 'Auto Parts', 'auto_parts.jpg'),
(5, 'Baby or Kid', 'baby.jpg', 'Baby or Kid', 'baby.png'),
(6, 'Beauty or Health', 'beautyhealth.jpg', 'Beauty or Health', 'beautyhealth.png'),
(7, 'Bikes', 'bikes.jpg', 'Bikes', 'bikes.jpg'),
(8, 'Boats', 'boats.jpg', 'Boats', 'boats.jpg'),
(9, 'Motorcycle or ATV', 'motorcycle.jpg', 'Motorcycle or ATV', 'motorcycle.jpg'),
(10, 'Electronics', 'electronics.jpg', 'Electronics', 'electronics.png'),
(11, 'Clothing & Accessories', 'clothing.jpg', 'Clothing & Accessories', 'clothing.png'),
(12, 'Farm & Garden', 'farm.jpg', 'Farm & Garden', 'farm.jpg'),
(13, 'Furniture', 'furniture.jpg', 'Furniture', 'furniture.jpg'),
(14, 'Grocery', 'groceries.png', 'Grocery', 'groceries.png'),
(15, 'Labor', 'business.jpg', 'Labor', 'business.jpg'),
(16, 'Jewellery', 'jewellery.jpg', 'Jewellery', 'jewellery.jpg'),
(17, 'Musical Instruments', 'music.jpg', 'Musical Instruments', 'music.jpg'),
(18, 'Photo & Video', 'photos.jpg', 'Photo & Video', 'photos.jpg'),
(19, 'Sporting Goods', 'sports.jpg', 'Sporting Goods', 'sports.jpg'),
(20, 'Tickets & Travel', 'travel.png', 'Tickets & Travel', 'travel.png'),
(21, 'Toys & Games', 'toys.jpg', 'Toys & Games', 'toys.jpg');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
