insert into forma_pagamento (id, descricao) values (1, 'Débito');
insert into forma_pagamento (id, descricao) values (2, 'Crédito');
insert into forma_pagamento (id, descricao) values (3, 'Pix');
insert into forma_pagamento (id, descricao) values (4, 'Dinheiro');

insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');


insert into estado (id, nome) values (1, 'São Paulo');
insert into estado (id, nome) values (2, 'Rio de Janeiro');
insert into estado (id, nome) values (3, 'Minas Gerais');

insert into cidade (id, nome, estado_id) values (1, 'Americana', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 3);
insert into cidade (id, nome, estado_id) values (3, 'Hortolândia', 1);
insert into cidade (id, nome, estado_id) values (4, 'Rio de Janeiro', 2);

insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao, endereco_cidade_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_bairro) values (1, 'Charbon', 9.99, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Sun Gertrudez');
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (2, 'Matuto', 9.99, 1, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (3, 'Madero', 7.50, 1, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) values (4, 'Delivery Foods', 15.50, 2, utc_timestamp, utc_timestamp);

insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);

insert into produto (id, descricao, preco, ativo, restaurante_id) values (1, "Drink", 12.99, true, 1)
insert into produto (id, descricao, preco, ativo, restaurante_id) values (2, "Risoto de 4 queijos", 39.99, true, 1)
insert into produto (id, descricao, preco, ativo, restaurante_id) values (3, "Parmegiana de Frango", 29.99, true, 1)

insert into produto (id, descricao, preco, ativo, restaurante_id) values (4, "Drink de Morango", 18.99, true, 2)
insert into produto (id, descricao, preco, ativo, restaurante_id) values (5, "Risoto de Palmito", 49.99, true, 2)
insert into produto (id, descricao, preco, ativo, restaurante_id) values (6, "Parmegiana de Filé Mignon", 39.99, true, 2)