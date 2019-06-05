create table category (
  id text not null primary key,
  name text not null
);

create table transaction (
  id text not null primary key,
  value decimal not null,
  type text not null,
  date timestamp not null,
  category_id text not null
);

alter table transaction add constraint fk_transaction_category
  foreign key (category_id) references category (id);

create index transaction_category_idx on transaction (category_id asc);

create index transaction_type_idx on transaction (type asc);