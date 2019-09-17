DROP TABLE IF EXISTS customer;

CREATE TABLE customer
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    lastname VARCHAR(255),
    address VARCHAR(255),
    PRIMARY KEY (id)
);