-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Oct 09, 2013 at 06:31 AM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `listview`
--

-- --------------------------------------------------------

--
-- Table structure for table `newtable`
--

CREATE TABLE IF NOT EXISTS `newtable` (
  `sno` int(255) NOT NULL AUTO_INCREMENT,
  `name1` varchar(255) NOT NULL,
  `image1` varchar(255) NOT NULL,
  PRIMARY KEY (`sno`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `newtable`
--

INSERT INTO `newtable` (`sno`, `name1`, `image1`) VALUES
(1, 'The work', 'http://192.168.1.40/image/howto.jpg'),
(2, 'delicious', 'http://192.168.1.40/image/delicious.jpg'),
(3, 'positive Thinking', 'http://192.168.1.40/image/post.jpg'),
(4, 'Step to sucess', 'http://192.168.1.40/image/steps.jpg'),
(5, 'Quran', 'http://192.168.1.40/image/quran.jpg'),
(6, 'The public domain', 'http://192.168.1.40/image/thepublic.jpg');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
