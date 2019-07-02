create table city (
  id bigint not null primary key,
  name varchar not null
);

create table customer (
    id bigint not null primary key,
    name varchar not null,
    city_id bigint not null
);

create table product_category (
    id bigint not null primary key,
    name varchar not null
);

create table product (
    id bigint not null primary key,
    name varchar not null,
    category_id bigint not null
);

create table "order" (
    id bigint not null primary key,
    name varchar not null,
    customer_id bigint not null,
    finished boolean not null default false
);

create table order_product (
    id bigint not null primary key,
    order_id bigint not null,
    product_id bigint not null,
    amount bigint not null
);
