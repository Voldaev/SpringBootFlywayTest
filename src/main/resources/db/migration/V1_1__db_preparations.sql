CREATE TABLE offers -- таблица услуг
(
    id serial constraint offers_pkey primary key ,
    name  varchar(200) not null , -- название услуги
    duration int not null , -- продолжительность в минутах
    price int not null  -- цена в $
);

CREATE UNIQUE index offers_name on offers using btree( lower(name) );

CREATE TABLE clients -- таблица клиентов
(
    id serial primary key ,
    name  varchar(50) not null , -- имя клиента
    phone varchar(20) not null -- тел номер клиента
);

CREATE UNIQUE index clients_phone on clients using btree( lower(phone) );

CREATE TABLE orders -- таблица заказов
(
 id serial not null constraint orders_pkey primary key ,
 client_id int not null references clients(id),
 offer_id int not null references offers(id),
 date_start timestamptz not null -- время начала выполнения услуги
);