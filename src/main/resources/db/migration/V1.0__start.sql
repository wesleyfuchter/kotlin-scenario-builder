create table city (
  id bigint not null,
  name varchar not null
);

alter table city add constraint city_pk
    primary key (id);

create table customer (
    id bigint not null,
    name varchar not null,
    city_id bigint not null
);

alter table customer add constraint customer_pk
    primary key (id);

alter table customer add constraint customer_city_fk
    foreign key (city_id) references city (id);

create index customer_city_idx on customer (city_id asc);

create table product_category (
    id bigint not null,
    name varchar not null
);

alter table product_category add constraint product_category_pk
    primary key (id);

create table product (
    id bigint not null,
    name varchar not null,
    category_id bigint not null
);

alter table product add constraint product_pk
    primary key (id);

alter table product add constraint product_category_fk
    foreign key (category_id) references product_category (id);

create index product_category_idx on product (category_id asc);

create table "order" (
    id bigint not null,
    name varchar not null,
    customer_id bigint not null,
    finished boolean not null default false
);

alter table "order" add constraint order_pk
    primary key (id);

alter table "order" add constraint order_customer_fk
    foreign key (customer_id) references customer (id);

create index order_customer_idx on "order" (customer_id asc);

create table order_product (
    id bigint not null,
    order_id bigint not null,
    product_id bigint not null,
    amount bigint not null
);

alter table order_product add constraint order_product_pk
    primary key (id);

alter table order_product add constraint order_product_order_fk
    foreign key (order_id) references "order" (id);

alter table order_product add constraint order_product_product_fk
    foreign key (product_id) references product (id);

create index order_product_order_idx on order_product (order_id asc);

create index order_product_product_idx on order_product (product_id asc);