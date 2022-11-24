--liquibase formatted SQL

--changeset davis:1

CREATE TABLE wishes
(
    wish_id INT PRIMARY KEY NOT NULL,
    wish_name VARCHAR(255) NOT NULL,
    wish_description VARCHAR(255) NOT NULL
);