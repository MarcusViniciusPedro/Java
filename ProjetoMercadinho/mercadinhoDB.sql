
CREATE DATABASE mercadinho;

USE mercadinho;

CREATE TABLE produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    preco DECIMAL(10, 2) NOT NULL,
    quantidade INT NOT NULL
);

SELECT * FROM mercadinho.produtos;

CREATE TABLE fornecedores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cnpj VARCHAR(18) NOT NULL,
    nome_fantasia VARCHAR(100) NOT NULL,
    telefone VARCHAR(20)
);

SELECT * FROM mercadinho.fornecedores;