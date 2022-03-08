CREATE DATABASE IF NOT EXISTS gamelib; -- Creates only if it does not exists
SHOW DATABASES;
USE gamelib;

drop table if exists Cart;

CREATE TABLE IF NOT EXISTS cart (
    uid INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(50) NOT NULL,
    qty INT NOT NULL,
    totalCost DECIMAL(10, 2) NOT NULL
);

select * from Cart;
