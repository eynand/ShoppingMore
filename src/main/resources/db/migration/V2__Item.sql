create table products (
  product_id varchar (255) not null,
  name varchar (255) not null,
  description varchar (255),
  category varchar (255),
  user_id varchar (255),
  price double not null,
  amount double not null,
  PRIMARY KEY (product_id)
);

create table users (
  user_id varchar (255) not null,
  username varchar (255) not null,
  password varchar (255) not null,
  first_name varchar (255) not null,
  last_name varchar (255) not null,
  email varchar (255) not null,
  shopping_cart_id varchar (255),
  order_id varchar (255),
  PRIMARY KEY (user_id)
);

create table cart_items (
  cart_item_id varchar (255) not null,
  shopping_cart_id varchar (255) not null,
  product_id varchar (255) not null,
  PRIMARY KEY (cart_item_id)
);

create table shopping_carts (
  shopping_cart_id varchar (255) not null,
  PRIMARY KEY (shopping_cart_id)
);

create table orders (
  order_id varchar (255) not null,
  user_id varchar (255) not null,
  status varchar (255) not null,
  PRIMARY KEY (order_id)
);

create table products_orders (
  order_id varchar (255) not null,
  product_id varchar (255) not null
);

--insert into users(user_id,username,password,first_name,last_name,email) values('5f48911c-0d36-4faa-970d-61051f2117f0','eynan','$2y$12$N9fGXM87tLpJrlSnDqst2exkal9njUbJ2e4339btfAgt2xHunG19q','eynan','drori','eynand@hotmail.com');