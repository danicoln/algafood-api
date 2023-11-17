insert into forma_pagamento (id, descricao) values (1, 'Débito');
insert into forma_pagamento (id, descricao) values (2, 'Crédito');
insert into forma_pagamento (id, descricao) values (3, 'Pix');
insert into forma_pagamento (id, descricao) values (4, 'Dinheiro');

insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into restaurante (id, nome, taxa_frete, cozinha_id) values (1, 'Matuto', 9.99, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (2, 'Madero', 7.50, 1);
insert into restaurante (id, nome, taxa_frete, cozinha_id) values (3, 'Delivery Foods', 15.50, 2);

insert into estado (id, nome) values (1, 'São Paulo');
insert into estado (id, nome) values (2, 'Rio de Janeiro');
insert into estado (id, nome) values (3, 'Minas Gerais');

insert into cidade (id, nome, estado_id) values (1, 'Americana', 1);
insert into cidade (id, nome, estado_id) values (2, 'Belo Horizonte', 3);
insert into cidade (id, nome, estado_id) values (3, 'Hortolândia', 1);
insert into cidade (id, nome, estado_id) values (4, 'Rio de Janeiro', 2);


insert into permissao (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permissao (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3);