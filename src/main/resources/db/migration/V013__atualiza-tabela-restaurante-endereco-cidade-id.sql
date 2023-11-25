ALTER TABLE restaurante
    ADD COLUMN endereco_cidade_id bigint,
    ADD CONSTRAINT fk_endereco_cidade

    FOREIGN KEY (endereco_cidade_id) REFERENCES cidade (id);