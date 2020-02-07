create table item (
  item_id varchar (255) not null,
  name varchar (255) not null,
  description varchar (255),
  price double not null,
  amount double not null,
  PRIMARY KEY (item_id)
);