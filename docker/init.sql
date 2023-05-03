CREATE TABLE escola (
id SERIAL PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
endereco VARCHAR(255) NOT NULL
);

CREATE TABLE sala (
id SERIAL PRIMARY KEY,
descricao VARCHAR(255) NOT NULL,
num_sala INTEGER NOT NULL
);

CREATE TABLE aluno (
id SERIAL PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
matricula VARCHAR(20) NOT NULL,
fk_escola INTEGER REFERENCES escola(id),
fk_sala INTEGER REFERENCES sala(id)
);

CREATE TABLE tipo (
id SERIAL PRIMARY KEY,
descricao VARCHAR(255) NOT NULL
);

CREATE TABLE categoria (
id SERIAL PRIMARY KEY,
descricao VARCHAR(255) NOT NULL,
fk_tipo INTEGER REFERENCES tipo(id)
);

CREATE TABLE produto (
id SERIAL PRIMARY KEY,
descricao VARCHAR(255) NOT NULL,
pt_produto DECIMAL(10, 2) NOT NULL,
bonus INTEGER NOT NULL,
fk_id_categoria INTEGER REFERENCES categoria(id)
);

CREATE TABLE responsavel (
id SERIAL PRIMARY KEY,
nome VARCHAR(255) NOT NULL,
ocupacao VARCHAR(255) NOT NULL,
password VARCHAR(255) NOT NULL
);

CREATE TABLE doacao (
id SERIAL PRIMARY KEY,
descricao VARCHAR(255) NOT NULL,
quant_itens INTEGER NOT NULL,
fk_aluno INTEGER REFERENCES aluno(id),
fk_responsavel INTEGER REFERENCES responsavel(id),
fk_produto INTEGER REFERENCES produto(id)
);

CREATE TABLE item (
id SERIAL PRIMARY KEY,
fk_produto INTEGER REFERENCES produto(id),
fk_doacao INTEGER REFERENCES doacao(id)
);

INSERT INTO escola (nome, endereco) VALUES
('Escola Municipal 1', 'Rua A, 123'),
('Escola Estadual 2', 'Avenida B, 456'),
('Escola Particular 3', 'Rua C, 789'),
('Escola Municipal 4', 'Avenida D, 321');

INSERT INTO sala (descricao, num_sala) VALUES
('Sala 1', 101),
('Sala 2', 102),
('Sala 3', 201),
('Sala 4', 202);

INSERT INTO aluno (nome, matricula, fk_escola, fk_sala) VALUES
('João', '0001', 1, 1),
('Maria', '0002', 2, 2),
('Pedro', '0003', 1, 2),
('Ana', '0004', 3, 3),
('Lucas', '0005', 4, 4);

INSERT INTO tipo (descricao) VALUES
('Eletrônico'),
('Vestuário'),
('Livro'),
('Brinquedo');

INSERT INTO categoria (descricao, fk_tipo) VALUES
('Celular', 1),
('Camisa', 2),
('Romance', 3),
('Carrinho', 4);

INSERT INTO produto (descricao, pt_produto, bonus, fk_id_categoria) VALUES
('iPhone 13', 8999.99, 10, 1),
('Polo Ralph Lauren', 299.99, 5, 2),
('O Código da Vinci', 49.99, 2, 3),
('Hot Wheels', 9.99, 1, 4);

INSERT INTO responsavel (nome, ocupacao, password) VALUES ('João da Silva', 'Engenheiro', 'senha123');
INSERT INTO responsavel (nome, ocupacao, password) VALUES ('Maria Santos', 'Advogada', 'abcd456');
INSERT INTO responsavel (nome, ocupacao, password) VALUES ('Carlos Rodrigues', 'Médico', 'senha789');
INSERT INTO responsavel (nome, ocupacao, password) VALUES ('Ana Costa', 'Jornalista', 'senha456');
INSERT INTO responsavel (nome, ocupacao, password) VALUES ('Pedro Oliveira', 'Professor', 'senha1a2b');
INSERT INTO responsavel (nome, ocupacao, password) VALUES ('Sandra Almeida', 'Arquiteta', 'qwe123');
INSERT INTO responsavel (nome, ocupacao, password) VALUES ('Ricardo Sousa', 'Fotógrafo', 'senhaqwe');
INSERT INTO responsavel (nome, ocupacao, password) VALUES ('Juliana Fernandes', 'Vendedora', 'abc123xyz');
INSERT INTO responsavel (nome, ocupacao, password) VALUES ('Fernando Santos', 'Analista de Sistemas', 'senha4567');
INSERT INTO responsavel (nome, ocupacao, password) VALUES ('Luciana Souza', 'Estudante', '1234abcd');


INSERT INTO doacao (descricao, quant_itens, fk_aluno, fk_responsavel, fk_produto) VALUES
('Doação 1', 3, 1, 1, 1),
('Doação 2', 5, 2, 2, 2),
('Doação 3', 1, 3, 3, 3),
('Doação 4', 2, 4, 4, 4);

INSERT INTO item (fk_produto, fk_doacao) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(4, 3),
(1, 4),
(3, 4);