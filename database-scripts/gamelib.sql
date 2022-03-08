create database if not exists gameLib;
use gameLib;
drop table if exists game;
create table game (
                         gameId int,
                         title varchar(50),
                         developer varchar(50),
                         price decimal(10, 2),
                         qtySold int,
                         primary key (gameId)
                     );
insert into game values (1001, 'It Takes Two', 'Hazelight Studios', 49.90 , 50);
insert into game values (1002, 'MineCraft', 'Mojang', 26.99 , 50);
insert into game values (1003, 'Hollow Knight', 'Team Cherry', 14.90, 200);
insert into game values (1004, 'Don''t Starve', 'Klei Entertainment', 49.90 , 50);
insert into game values (1005, 'Life is Strange', 'Dontnod Entertainment', 24.90, 50);
insert into game values (1006, 'Stardew Valley', 'Eric Barone', 14.90, 200);
insert into game values (1006, 'RimWorld', 'Ludeon Studios', 23.90, 66);
insert into game values (1006, 'Baba is You', 'Hempuli Oy', 12, 40);
insert into game values (1006, 'Human Fall Flat', 'No Brakes Games', 14.90, 99);
insert into game values (1006, 'Oxygen Not Included', 'Klei Entertainment', 14.90, 30);
insert into game values (1006, 'The Stanley Parable', 'Galactic Cafe', 14.90, 30);
insert into game values (1006, 'Disco Elysium', 'ZA/UM', 14.90, 30);


select * from game;

