-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 12, 2025 at 11:11 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `musemo`
--

-- --------------------------------------------------------

--
-- Table structure for table `artifact`
--

CREATE TABLE `artifact` (
  `artifactId` varchar(20) NOT NULL,
  `artifactName` varchar(50) NOT NULL,
  `artifactType` varchar(10) NOT NULL,
  `creatorName` varchar(20) NOT NULL,
  `timePeriod` varchar(20) NOT NULL,
  `origin` varchar(20) NOT NULL,
  `condition` varchar(10) NOT NULL,
  `description` text NOT NULL,
  `artifactImage` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `artifact`
--

INSERT INTO `artifact` (`artifactId`, `artifactName`, `artifactType`, `creatorName`, `timePeriod`, `origin`, `condition`, `description`, `artifactImage`) VALUES
('P101', 'Mona Lisa', 'Painting', 'Leonardo da Vinci', 'Renaissance', 'Italy', 'Good', 'The Mona Lisa is a half-length portrait painting by the Italian artist Leonardo da Vinci. Considered an archetypal masterpiece of the Italian Renaissance.', 'MonaLisa.jpg'),
('Poop', 'The Bones of a Dinosaur', 'Relic', 'Asteroid', 'Jurassic Era', 'Jurassic Era', 'Good', 'Dinosaurs were a diverse group of reptiles that roamed the Earth during the Mesozoic Era, which lasted from about 252 to 66 million years ago. They are classified into two main groups based on their hip structure: Saurischia (which includes theropods like Tyrannosaurus rex and sauropodomorphs like Brachiosaurus) and Ornithischia (which includes various herbivorous dinosaurs like Stegosaurus and Triceratops).\r\n\r\n', 'LoadingScreen.jpg'),
('R100', 'Rosetta Stone', 'Relic', 'Ancient Egyptians', '196 BC', 'Egypt', 'Damaged', 'The Rosetta Stone is a stele of granodiorite inscribed with three versions of a decree issued in 196 BC during the Ptolemaic dynasty of Egypt, on behalf of King Ptolemy V Epiphanes.', 'RosettaStone.jpg'),
('S100', 'The Thinker', 'Sculpture', 'Auguste Rodin', '1904 AD', 'France', 'Good', 'The Thinker (French: Le Penseur), by Auguste Rodin, is a bronze sculpture depicting a nude male figure of heroic size, seated on a large rock, leaning forward, right elbow placed upon the left thigh, back of the right hand supporting the chin in a posture evocative of deep thought and contemplation.', 'TheThinker.jpg'),
('S102', 'Veiled Bust', 'Sculpture', 'Unknown', 'Renaissance', 'Greece', 'Excellent', 'The Veiled Bust is a mysterious Object found recently.', 'Bust.jpg'),
('S777', 'Winged Victory', 'Sculpture', 'Unknown', '200-190 BC', 'Greece', 'Damaged', 'It is a masterpiece of Greek sculpture from the Hellenistic era, dating from the beginning of the 2nd century BC (190 BC). It is composed of a statue representing the goddess Niké (Victory), whose head and arms are missing and its base is in the shape of a ship\'s bow.', 'WingedVictory.jpg'),
('T100', 'Test', 'Painting', 'Test', 'Test', 'Test', 'Excellent', 'Test', 'Test.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `bookingId` int(10) NOT NULL,
  `exhibitionId` int(10) NOT NULL,
  `username` varchar(30) NOT NULL,
  `bookingDate` date NOT NULL,
  `bookingTime` time NOT NULL,
  `ticket` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`bookingId`, `exhibitionId`, `username`, `bookingDate`, `bookingTime`, `ticket`) VALUES
(1, 1020, 'Nate01', '2025-04-01', '05:19:33', 'General Ticket'),
(2, 1021, 'Ram123', '2025-04-13', '15:03:25', 'Student Ticket'),
(3, 1025, 'Nate01', '2025-04-13', '07:40:39', 'Children Ticket'),
(4, 1022, 'Alex201', '2025-04-14', '18:42:21', 'Senior Ticket'),
(5, 1020, 'manish', '2025-05-06', '09:00:00', 'General Ticket'),
(6, 1020, 'hinata', '2025-05-07', '11:00:00', 'General Ticket'),
(7, 1022, 'manish', '2025-05-08', '09:00:00', 'Student Ticket'),
(13, 1025, 'manish', '2025-05-08', '12:00:00', 'General Ticket'),
(14, 1020, 'manish', '2025-05-08', '09:00:00', 'General Ticket'),
(18, 1020, 'Nate', '2025-05-10', '09:00:00', 'General Ticket'),
(21, 2001, 'manish', '2025-05-10', '15:00:00', 'General Ticket');

-- --------------------------------------------------------

--
-- Table structure for table `exhibition`
--

CREATE TABLE `exhibition` (
  `exhibitionId` int(10) NOT NULL,
  `exhibitionTitle` varchar(50) NOT NULL,
  `exhibitionDescription` text NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date DEFAULT NULL,
  `exhibitionImage` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `exhibition`
--

INSERT INTO `exhibition` (`exhibitionId`, `exhibitionTitle`, `exhibitionDescription`, `startDate`, `endDate`, `exhibitionImage`) VALUES
(1020, 'Paintings and The History of Art', 'Explore the world of arts and paintings from history itself. ', '2025-04-27', '2025-06-04', '1020.jpg'),
(1021, 'The Great Ancient Egypt', 'Peek into the past of the Great Egyptian civilization with this collection of artifacts from Egypt.', '2025-04-30', '2025-05-23', '1021.jpg'),
(1022, 'The Renaissance Once Again', 'See what Renaissance was all about and relive through the pages of history itself.', '2025-05-01', '2025-06-30', '1022.jpg'),
(1025, 'Wonderful World of Sculpted Beauty', 'Interested in the world of sculpted stone and artistry? This is just the exhibition to see all forms of sculptures by past and present sculptors.', '2025-04-17', '2025-07-02', '1025.jpg'),
(2001, 'Test', 'Test', '2025-05-01', '2025-05-19', 'Test.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `exhibitionartifact`
--

CREATE TABLE `exhibitionartifact` (
  `exhibitionId` int(10) NOT NULL,
  `artifactId` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `exhibitionartifact`
--

INSERT INTO `exhibitionartifact` (`exhibitionId`, `artifactId`) VALUES
(1020, 'P101'),
(1020, 'R100'),
(1021, 'R100'),
(1022, 'P101'),
(1025, 'S100'),
(1025, 'S102'),
(1025, 'S777');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `username` varchar(30) NOT NULL,
  `fullName` varchar(50) NOT NULL,
  `password` varchar(250) NOT NULL,
  `role` varchar(7) NOT NULL DEFAULT 'User',
  `gender` varchar(10) NOT NULL,
  `email` varchar(30) NOT NULL,
  `contact` varchar(20) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `userImage` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`username`, `fullName`, `password`, `role`, `gender`, `email`, `contact`, `dateOfBirth`, `userImage`) VALUES
('Admin01', 'Admin', 'dq6LFqMEZ78jToICQ7t1GKII1DiLH0rZRZA/sejOZjvon0//hwld+/vtHBChfV+6', 'Admin', 'male', 'adminOfMusemo@gmail.com', '9801215523', '2020-09-09', 'Admin.jpg'),
('Alex201', 'Alex Smith', 'pWKqUXOAB/EzAAKleIm8cW4w8EgtsPYxORZzYIEFPITN6xCslVpqHmei/nG25k8ERasdfXKmSDN4+1V6rw==', 'User', 'female', 'lordwar923@gmail.com', '9808566441', '2005-04-01', 'Alexa.png'),
('bims', 'bimbu', 'm6MWYMkru+Sqyp0VMRM+HYEuCKMY0fOnexlGvb45JqR0EZ6ZKf148XKZJKphV1wNoynvhg==sdf', 'User', 'male', 'Ramrami2004@gmail.com', '9808688669', '2006-04-26', 'Bims.jpg'),
('hinata', 'hinata dnksfdkshdfe', 'HOABFlFyNJZlcNtjvajg31n9yy81cyb7+bd5AQWlLsVn30+XjE4fOfveXrDbT+vrXcKRxYPLqhc=', 'User', 'female', 'djviv@gmail.com', '9876543210', '2009-01-07', 'Olivia Muus, 1985 _ Museum of Selfies (Tutt\'Art@ _ Pittura _ Scultura _ Poesia _ Musica _).jpg'),
('manis', 'manishu', 'aExXi0/NKZzYjvOipYfopnYwHXqyn9e7Idj9c5ild0H56WSCpS2awu3s0ED2eEzMwtttkFg=', 'User', 'male', 'dasd@gmail.com', '9808433221', '2006-06-22', 'Manis.jpg'),
('manish', 'Tiku Ramu', 'TwlaXvk8WgTIATK0nzfa6Nyy9K9TKwBuTAh0J4SAqAxf0nX4Q0ajh0FCpkVa239S', 'User', 'male', 'manisha@gmail.com', '9841688770', '2004-06-16', 'Manish.jpg'),
('Moiv', 'Viom Shrestha', 'ovCUywNgJF5hoqq5oLIG67HeVlGowoXkK5TPlorfAe3Cl/qBhKZGJiZ0lMQUeOKN4EI12Wk=', 'User', 'male', 'viomshrest2005@gmail.com', '9808688675', '2006-10-28', 'Viom.jpg'),
('Nate', 'Nathaniel', '1zJX7v9zfn5PFpMXDPBDTbo95/naqKfstlX1iu4npTjcRU9GJLHL1ndrSgLrm/Pr0wch3Q==', 'User', 'male', 'viomshrestha2005@gmail.com', '9808677665', '2007-06-05', 'Test 2.jpg'),
('Nate01', 'Nate Middlestone', 'Y2Fxmh2EnRaMGfdGJYv/mdN1FNeheTg6SBz4JNqQYnh9pSeF0oI6m5B8r+Pkgse5Tvs=sdf', 'User', 'male', 'nate123@gmail.com', '9808688665', '2007-06-23', 'Nate.jpg'),
('Papa', 'Papa Ki Papri', 'w2UYUbnvfKcb7Lb03JUPM+1pdvmBnswfTVvZUOyitY0u9D8wg1c6l2mW14nOwhwVsM/P0+PL2Qe7', 'User', 'male', 'password@gmail.com', '9841231230', '2001-09-11', 'Papa.jpg'),
('Raja', 'Maharaja', 'auuwRvUVzvjSuAJQdWBC2E9uTqaGe88XXj0HxuyR04LJFiBziBXRWpmG6Nqfg+WmdGrUKwM3hDzu', 'User', 'male', 'maharaj@gmail.com', '9843211230', '2001-09-11', 'visitor_icon.png'),
('Ram123', 'Ram Adhikari', 'XqLrAOIqeLaekZIp2n6kmBumI4FZ8z/jDkFP1eaj9c2gdyr4Bfj6keXy7ZoG1ZgQPKVy4Wg=asdf', 'User', 'female', 'Ramram2004@gmail.com', '9841214423', '2002-07-09', 'Ram.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `artifact`
--
ALTER TABLE `artifact`
  ADD PRIMARY KEY (`artifactId`);

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`bookingId`),
  ADD KEY `booking_exhibtion_fk` (`exhibitionId`),
  ADD KEY `booking_user_fk` (`username`);

--
-- Indexes for table `exhibition`
--
ALTER TABLE `exhibition`
  ADD PRIMARY KEY (`exhibitionId`);

--
-- Indexes for table `exhibitionartifact`
--
ALTER TABLE `exhibitionartifact`
  ADD PRIMARY KEY (`exhibitionId`,`artifactId`),
  ADD KEY `artifact_fk` (`artifactId`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`),
  ADD UNIQUE KEY `email` (`email`,`contact`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `bookingId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `exhibition`
--
ALTER TABLE `exhibition`
  MODIFY `exhibitionId` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2002;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `booking_exhibtion_fk` FOREIGN KEY (`exhibitionId`) REFERENCES `exhibition` (`exhibitionId`),
  ADD CONSTRAINT `booking_user_fk` FOREIGN KEY (`username`) REFERENCES `user` (`username`);

--
-- Constraints for table `exhibitionartifact`
--
ALTER TABLE `exhibitionartifact`
  ADD CONSTRAINT `artifact_fk` FOREIGN KEY (`artifactId`) REFERENCES `artifact` (`artifactId`),
  ADD CONSTRAINT `exhibition_fk` FOREIGN KEY (`exhibitionId`) REFERENCES `exhibition` (`exhibitionId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
