--liquibase formatted sql

--changeset anatoly:v00
drop table resource

--changeset anatoly:v01
create table product
(
    id   uuid not null primary key,
    name varchar(256)
)

--changeset anatoly:v02
drop table product;
create table resource
(
    id   uuid not null primary key,
    name varchar(256)
)

--changeset anatoly:v03
create table basket
(
    id  uuid not null primary key
);
create table product_count
(
    product_id uuid not null,
    basket_id uuid not null,
    products_count int,
    primary key(product_id, basket_id)
)
