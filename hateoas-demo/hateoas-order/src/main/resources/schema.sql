drop table if exists T_ORDER;

CREATE TABLE T_ORDER (
    ID BIGINT AUTO_INCREMENT,
    user VARCHAR(64),
    stock_name varchar(64),
    volume int,
    price DOUBLE,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    PRIMARY KEY(ID)
);