DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS author;
CREATE TABLE customer
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    lastname VARCHAR(255),
    address VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE author
(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    lastname VARCHAR(255),
    PRIMARY KEY (id)
);