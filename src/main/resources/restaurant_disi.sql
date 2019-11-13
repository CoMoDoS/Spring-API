CREATE SCHEMA `restaurant` ;
use restaurant;


CREATE TABLE `restaurant`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `type` VARCHAR(10) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC));

CREATE TABLE `restaurant`.`menu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `ingredients` VARCHAR(250) NULL,
  `price` DECIMAL(10) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

CREATE TABLE `restaurant`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user` INT NOT NULL,
  `total_price` DECIMAL(10) NULL,
  `order_date` DATETIME NULL,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `user_order_f_idx` (`user` ASC),
  CONSTRAINT `user_order_f`
  FOREIGN KEY (`user`)
  REFERENCES `restaurant`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

CREATE TABLE `restaurant`.`order_Menu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `menu_id` INT NOT NULL,
  `quantity` DECIMAL(10) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `menu_order_fk_idx` (`menu_id` ASC),
  INDEX `order_Menu_f_idx` (`order_id` ASC),
  CONSTRAINT `menu_order_fk`
  FOREIGN KEY (`menu_id`)
  REFERENCES `restaurant`.`menu` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `order_Menu_f`
  FOREIGN KEY (`order_id`)
  REFERENCES `restaurant`.`order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);




insert into user (email, name, password, type) values ('ilie@gmail.com', 'ilie', 'neag', 'admin' );
insert into user (email, name, password, type) values ('radu@gmail.com', 'radu', 'nechiti', 'bucatar' );
insert into user (email, name, password, type) values ('cristi@gmail.com', 'cristi', 'barb', 'bucatar' );
insert into user (email, name, password, type) values ('alex@gmail.com', 'alex', 'barbu', 'user' );
insert into user (email, name, password, type) values ('andrei@gmail.com', 'andrei', 'bursuc', 'user' );
insert into user (email, name, password, type) values ('silva@gmail.com', 'silva', 'man', 'user' );
insert into user (email, name, password, type) values ('bianca@gmail.com', 'bianca', 'goia', 'user' );

select * from user;


insert into menu (name, ingredients, price) values ('shaormica', 'lipie+pui+rosii+varza+morocv+sos', 15);
insert into menu (name, ingredients, price) values ('pizza salami', 'salam+sunca+masline+ciuperci+mozzarella+sos', 18);
insert into menu (name, ingredients, price) values ('pizza diavola', 'salam picant+ardei iute+masline+ciuperci+sos picant', 17);
insert into menu (name, ingredients, price) values ('pui la gratar', 'piept de pui', 10);
insert into menu (name, ingredients, price) values ('meniu cascaval', 'cascaval+salata+cartofi praiti+sos', 21);
insert into menu (name, ingredients, price) values ('cartofi prajiti', 'crtofi+sos', 8);
insert into menu (name, ingredients, price) values ('meniu aripioare', 'aripi pui+cartofi praiti+sos', 20);
insert into menu (name, ingredients, price) values ('sandwich prosciutto', 'chifla rustica+crema unt+prosciutto crudo+mozzarella', 14);
insert into menu (name, ingredients, price) values ('salata', 'mix salata+rosii cherry+masline+branz feta+ulei masline', 12);
select * from menu;

insert into restaurant.orderr (user, total_price, order_date, status) values (4, 25, now(), 'in progress' );
insert into restaurant.orderr (user, total_price, order_date, status) values (5, 25, now(), 'in progress' );
insert into restaurant.orderr (user, total_price, order_date, status) values (6, 40, now(), 'in progress' );
insert into restaurant.orderr (user, total_price, order_date, status) values (7, 25, now(), 'done' );
insert into restaurant.orderr (user, total_price, order_date, status) values (4, 25, now(), 'done' );
insert into restaurant.orderr (user, total_price, order_date, status) values (5, 25, now(), 'done' );

select * from restaurant.orderr;

insert into order_Menu (order_id, menu_id, quantity) values (1,1,1);
insert into order_Menu (order_id, menu_id, quantity) values (1,4,1);
insert into order_Menu (order_id, menu_id, quantity) values (2,3,1);
insert into order_Menu (order_id, menu_id, quantity) values (2,5,2);
insert into order_Menu (order_id, menu_id, quantity) values (3,7,1);
insert into order_Menu (order_id, menu_id, quantity) values (3,9,3);
insert into order_Menu (order_id, menu_id, quantity) values (4,2,1);
insert into order_Menu (order_id, menu_id, quantity) values (5,6,1);
insert into order_Menu (order_id, menu_id, quantity) values (6,8,1);

select * from order_Menu;