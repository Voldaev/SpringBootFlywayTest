CREATE TABLE offers
(
    id int NOT NULL ,
    offer  varchar(200) UNIQUE NOT NULL , -- название услуги
    duration int NOT NULL , -- продолжительность в минутах
    price int NOT NULL , -- цена в $
    PRIMARY KEY (id, offer)
);