drop database lunchcrunch_test_database;
create database lunchcrunch_test_database;
use lunchcrunch_test_database;


CREATE TABLE user
(
  id           INT AUTO_INCREMENT
    PRIMARY KEY,
  `key`        TEXT       NULL,
  active       TINYINT(1) NULL,
  date_active  DATETIME   NULL,
  first_name   TEXT       NULL,
  last_name    TEXT       NULL,
  organization TEXT       NULL,
  CONSTRAINT user_id_uindex
  UNIQUE (id)
)
  ENGINE = InnoDB;


CREATE TABLE location
(
  id          INT AUTO_INCREMENT
    PRIMARY KEY,
  user_id     INT  NOT NULL,
  description TEXT NULL,
  CONSTRAINT location_id_uindex
  UNIQUE (id),
  CONSTRAINT location_user_id_fk
  FOREIGN KEY (user_id) REFERENCES user (id)
)
  ENGINE = InnoDB;

CREATE INDEX location_user_id_fk
  ON location (user_id);


CREATE TABLE topic
(
  id          INT AUTO_INCREMENT
    PRIMARY KEY,
  description TEXT NULL,
  CONSTRAINT topic_id_uindex
  UNIQUE (id)
)
  ENGINE = InnoDB;


CREATE TABLE appointment
(
  id          INT AUTO_INCREMENT
    PRIMARY KEY,
  user_id     INT      NOT NULL,
  location_id INT      NOT NULL,
  topic_id    INT      NOT NULL,
  date_time   DATETIME NULL,
  CONSTRAINT appointment_id_uindex
  UNIQUE (id),
  CONSTRAINT appointment_user_id_fk
  FOREIGN KEY (user_id) REFERENCES user (id),
  CONSTRAINT appointment_location_id_fk
  FOREIGN KEY (location_id) REFERENCES location (id),
  CONSTRAINT appointment_topic_id_fk
  FOREIGN KEY (topic_id) REFERENCES topic (id)
)
  ENGINE = InnoDB;

CREATE INDEX appointment_user_id_fk
  ON appointment (user_id);

CREATE INDEX appointment_location_id_fk
  ON appointment (location_id);

CREATE INDEX appointment_topic_id_fk
  ON appointment (topic_id);