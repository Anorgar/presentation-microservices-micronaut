
DROP TABLE if EXISTS pet_type;
DROP TABLE if EXISTS pet;

CREATE TABLE pet_type (
  id  smallint(6) NOT NULL auto_increment,
  type varchar(255),
  PRIMARY KEY (id)
);


CREATE TABLE pet (
  id smallint(6) NOT NULL auto_increment,
  type_id smallint(6),
  stock smallint(6),
  price decimal(5,2),
  name varchar(255),
  PRIMARY KEY (id)
);

INSERT INTO pet_type
(id, type)
VALUES(1, 'chat');


INSERT INTO pet
(id, type_id, stock, price, name)
VALUES(1, 1, 17, 255.99, 'chat persan');
