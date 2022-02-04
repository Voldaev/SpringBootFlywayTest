CREATE TABLE clients
(
    id    int PRIMARY KEY ,
    name  varchar(50) NOT NULL ,
    phone varchar(20)  NOT NULL ,
    offer varchar(200) REFERENCES offers (offer) ON DELETE RESTRICT NOT NULL , -- не даст удалить услугу, если есть заказы на нее.
    start date NOT NULL  -- время начала услуги
);