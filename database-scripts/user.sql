CREATE DATABASE IF NOT EXISTS gamelib; -- Creates only if it does not exists
SHOW DATABASES;
USE gamelib;

DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user (
    uid INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(20) NOT NULL,
    token VARCHAR(100) NOT NULL
);

SHOW tables;
DESCRIBE user;
