CREATE TABLE offers
(
    id int NOT NULL ,
    offer  varchar(200) UNIQUE NOT NULL , -- название услуги
    duration int NOT NULL , -- продолжительность в минутах
    price int NOT NULL , -- цена в $
    PRIMARY KEY (id, offer)
);


-- todo прочитай про тип serial
CREATE TABLE offers
(
    id serial NOT NULL constraint offers_pkey primary key ,
    name  varchar(200) NOT NULL , -- название услуги
    duration int NOT NULL , -- продолжительность в минутах
    price int NOT NULL  -- цена в $
);

create unique index offers_name on offers using btree( lower(name) );

CREATE TABLE clients
(
    id    serial PRIMARY KEY ,
    name  varchar(50) NOT NULL ,
    phone varchar(20)  NOT NULL
);

create unique index clients_phone on clients using btree( lower(phone) );

create table orders (
 id serial not null constraint orders_pkey primary key ,
 client_id int not null references clients(id),
 offer_id int not null references offers(id),
 date_start timestamptz not null
);


select o.* from orders o inner join offers of on o.offer_id = of.id
where date_start + concat(of.duration,' minutes')::interval > current_timestamp;

create sequence test_id_seq start with 1 increment by 1;

create table test (
    id int primary key default nextval('test_id_seq')
)