create database shop;
use shop;
drop table clothes;

-- 옷
CREATE TABLE clothes(
id INT auto_increment,
name VARCHAR(25),
price INT,
category VARCHAR(25),
PRIMARY KEY (id)
);

-- 사이즈에 대한 재고
CREATE TABLE stocks(
id INT,
size VARCHAR(25),
stock INT,
PRIMARY KEY (id, size),
FOREIGN KEY (id) REFERENCES clothes(id)
ON DELETE CASCADE
);

select * from clothes;
select * from stocks;