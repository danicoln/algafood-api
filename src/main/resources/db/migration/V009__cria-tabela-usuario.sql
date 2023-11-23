CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL,
    senha VARCHAR(12) NOT NULL,
    data_cadastro DATETIME NOT NULL,
    PRIMARY KEY (id)
) engine=InnoDB default charset=utf8;