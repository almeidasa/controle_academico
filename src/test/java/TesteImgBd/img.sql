CREATE DATABASE img;

\c img;

CREATE TABLE imagem (
	cod SERIAL NOT NULL,
	foto bytea
);