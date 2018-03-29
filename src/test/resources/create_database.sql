drop database lunchcrunch_main_database;
create database lunchcrunch_main_database;


CREATE TABLE `appointment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `location_id` int(11) NOT NULL,
  `topic` int(11) NOT NULL,
  `date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `appointment_id_uindex` (`id`),
  KEY `appointment_user_id_fk` (`user_id`),
  KEY `appointment_location_id_fk` (`location_id`),
  CONSTRAINT `appointment_location_id_fk` FOREIGN KEY (`location_id`) REFERENCES `location` (`id`),
  CONSTRAINT `appointment_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


CREATE TABLE `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `description` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `location_id_uindex` (`id`),
  KEY `location_user_id_fk` (`user_id`),
  CONSTRAINT `location_user_id_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


CREATE TABLE `topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `topic_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` text,
  `active` tinyint(1) DEFAULT NULL,
  `date_active` date DEFAULT NULL,
  `first_name` text,
  `last_name` text,
  `organization` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_uindex` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


