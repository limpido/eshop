create database if not exists gamelib;
use gamelib;
drop table if exists game;
create table if not exists game (
                         gameId int not null auto_increment,
                         title varchar(50),
                         developer varchar(50),
                         price decimal(10, 2),
                         qtySold int,
                         primary key (gameId)
                     );
insert into game (title, developer, price, qtySold) values ('It Takes Two', 'Hazelight Studios', 49.90 , 50);
insert into game (title, developer, price, qtySold) values ('MineCraft', 'Mojang', 26.99 , 50);
insert into game (title, developer, price, qtySold) values ('Hollow Knight', 'Team Cherry', 14.90, 200);
insert into game (title, developer, price, qtySold) values ('Don''t Starve', 'Klei Entertainment', 49.90 , 50);
insert into game (title, developer, price, qtySold) values ('Life is Strange', 'Dontnod Entertainment', 24.90, 50);
insert into game (title, developer, price, qtySold) values ('Stardew Valley', 'Eric Barone', 14.90, 200);
insert into game (title, developer, price, qtySold) values ('RimWorld', 'Ludeon Studios', 23.90, 66);
insert into game (title, developer, price, qtySold) values ('Baba is You', 'Hempuli Oy', 12, 40);
insert into game (title, developer, price, qtySold) values ('Human Fall Flat', 'No Brakes Games', 14.90, 99);
insert into game (title, developer, price, qtySold) values ('Oxygen Not Included', 'Klei Entertainment', 14.90, 30);
insert into game (title, developer, price, qtySold) values ('The Stanley Parable', 'Galactic Cafe', 14.90, 30);
insert into game (title, developer, price, qtySold) values ('Disco Elysium', 'ZA/UM', 14.90, 30);


select * from game;

