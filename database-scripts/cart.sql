CREATE DATABASE IF NOT EXISTS gamelib; -- Creates only if it does not exists
SHOW DATABASES;
USE gamelib;

drop table if exists cart;

CREATE TABLE IF NOT EXISTS cart (
    uid INT NOT NULL,
    gameId INT NOT NULL,
    qtyOrdered INT NOT NULL,
    PRIMARY KEY (uid, gameId)
);

select * from Cart;
