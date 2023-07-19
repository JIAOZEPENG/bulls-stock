drop table if exists t_stocks;

CREATE TABLE T_STOCKS (
    ID BIGINT AUTO_INCREMENT,
    name VARCHAR(64),
    price DOUBLE,
    create_time TIMESTAMP,
    update_time TIMESTAMP,
    PRIMARY KEY(ID)
);