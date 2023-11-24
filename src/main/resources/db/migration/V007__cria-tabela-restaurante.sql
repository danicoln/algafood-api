CREATE TABLE restaurante (
    id bigint AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    taxa_frete DECIMAL(10, 2) NOT NULL,
    endereco_logradouro VARCHAR(100),
    endereco_numero VARCHAR(6),
    endereco_complemento VARCHAR(100),
    endereco_bairro VARCHAR(100),
    endereco_cep VARCHAR(10),
    cozinha_id bigint NOT NULL,
    data_cadastro DATETIME NOT NULL,
    data_atualizacao DATETIME NOT NULL,
    FOREIGN KEY (cozinha_id) REFERENCES cozinha (id)
);