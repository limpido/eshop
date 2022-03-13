create database if not exists gamelib;
use gamelib;
drop table if exists game;
create table if not exists game (
    gameId int not null auto_increment,
    title varchar(50),
    genre varchar(100),
    developer varchar(50),
    price decimal(10, 2),
    qtySold int,
    image_path VARCHAR(255),
    primary key (gameId));
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1001', 'It Takes Two', 'adventure', 'Hazelight Studios', 49.90 , 50, 'frontend\src\assets\img\ittakestwo.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1002', 'MineCraft', 'survival', 'Mojang', 26.99 , 50, 'frontend\src\assets\img\minecraft.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1003', 'Hollow Knight', 'action', 'Team Cherry', 14.90, 200, 'frontend\src\assets\img\hollow knight.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1004', 'Dont Starve', 'survival', 'Klei Entertainment', 49.90 , 50, 'frontend\src\assets\img\dont starve.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1005', 'Life is Strange', 'adventure', 'Dontnod Entertainment', 24.90, 50, 'frontend\src\assets\img\lifeIsStrange.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1006', 'Stardew Valley', 'survival', 'Eric Barone', 14.90, 200, '\frontend\src\assets\img\StarDewValley.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1007', 'RimWorld', 'survival', 'Ludeon Studios', 23.90, 66, '\frontend\src\assets\img\RimWorld.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1008', 'Baba is You', 'puzzle', 'Hempuli Oy', 12, 40, '\frontend\src\assets\img\babaIsYou.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1009', 'Human Fall Flat', 'puzzle', 'No Brakes Games', 14.90, 99, '\frontend\src\assets\img\HumanFallFlat.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1010', 'Oxygen Not Included', 'survival', 'Klei Entertainment', 14.90, 30, '\frontend\src\assets\img\oxygennotincluded.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1011', 'The Stanley Parable', 'adventure', 'Galactic Cafe', 14.90, 30, '\frontend\src\assets\img\StanlyParable.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1012', 'Disco Elysium', 'puzzel', 'ZA/UM', 14.90, 30, '\frontend\src\assets\img\aleksander-rostov-disco-elyisum.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1013', 'Pokemon: legends of Arceus', 'adventure', 'GameFreak', 59.90 , 50, '\frontend\src\assets\img\arcus.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1014', 'EldenRing', 'Action', 'FromSotwear Inc', 79.90 , 50, '\frontend\src\assets\img\EldenRing.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1015', 'Little Nightmares', 'Adventure', 'Trasier Studios', 29.90, 200, '\frontend\src\assets\img\littleNightmares.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1016', 'Little Nightmares II', 'adventure', 'Trasier Studios', 39.90, 50, '\frontend\src\assets\img\littleNightmaresII.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1017', 'Alice: Madness Returns', 'action', 'Spicy Horse Games', 24.90, 50, '\frontend\src\assets\img\alice.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1018', 'Sekiro: Shadows Die Twice', 'action', 'FromSoftware Inc', 69.90, 200, '\frontend\src\assets\img\sekiro.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1019', 'Team Fortress 2', 'action', 'Valve', 00.00, 66, '\frontend\src\assets\img\Tf2_standalonebox.jpg');
insert into game (gameID, title, genre, developer, price, qtySold, image_path) values ('1020', 'Pokemon: Briliant Diamond & Shining Pearl', 'Adventure', 'ILCA', 119.90, 40, '\frontend\src\assets\img\PokemonDiamond.jpg');

select * from game;

