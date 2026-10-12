-- 1. Criação do Banco de Dados
CREATE DATABASE burgerclown_db;
USE burgerclown_db;

-- 2. Tabela de Clientes
-- Totalmente alinhada com o seu ClienteDAO (nome, idade, cpf, email, telefone, senha)
CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade VARCHAR(10),                -- VARCHAR para aceitar seu cliente.getIdade() como String
    cpf VARCHAR(14) UNIQUE NOT NULL,
    email VARCHAR(100),
    telefone VARCHAR(20),
    senha VARCHAR(255) NOT NULL,
    data_cadastro DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 3. Tabela de Empregados
-- Totalmente alinhada com o seu EmpregadoDAO (nome, idade, cpf, telefone, senha, cargo)
CREATE TABLE empregado (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade VARCHAR(10),                -- VARCHAR para aceitar seu empregado.getIdade() como String
    cpf VARCHAR(14) UNIQUE NOT NULL,
    telefone VARCHAR(20),
    senha VARCHAR(255) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    data_admissao DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 4. Tabela de Reservas
-- Totalmente alinhada com o seu ReservaDAO 
-- (dataReserva, tipoEvento, prefMesa, qtdPessoa, horaReserva, observacao)
CREATE TABLE reserva (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dataReserva VARCHAR(20) NOT NULL,  -- Mantido como VARCHAR para o seu statement Java
    tipoEvento VARCHAR(100),
    prefMesa VARCHAR(50),
    qtdPessoa VARCHAR(10) NOT NULL,    -- VARCHAR para evitar conflito se o Java enviar String
    horaReserva VARCHAR(20) NOT NULL,
    observacao VARCHAR(255)
);

SELECT * FROM cliente;
SELECT * FROM empregado;
SELECT * FROM reserva;

TRUNCATE TABLE cliente;
TRUNCATE TABLE empregado;
TRUNCATE TABLE reserva;