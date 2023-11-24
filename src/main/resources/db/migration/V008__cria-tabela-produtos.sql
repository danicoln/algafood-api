CREATE TABLE produto (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(60) NOT NULL,
    descricao TEXT,
    preco DECIMAL(10,2) NOT NULL,
    ativo BOOLEAN NOT NULL,
    data_cadastro DATETIME NOT NULL,
    restaurante_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_produto_restaurante FOREIGN KEY (restaurante_id) REFERENCES restaurante (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;