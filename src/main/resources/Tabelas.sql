CREATE TABLE Usuario (
    Id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    Nome VARCHAR(50) NOT NULL,
    Email VARCHAR(255) NOT NULL,
    DataDeNascimento DATE NOT NULL,
    Senha VARCHAR(20) NOT NULL,
    Ativo INT NOT NULL
);

INSERT INTO Usuario (Nome, Email, DataDeNascimento, Senha, Ativo) 
VALUES ('João da Silva', 'joao.silva@email.com', '1990-05-15', 'senha123', 1);

INSERT INTO Usuario (Nome, Email, DataDeNascimento, Senha, Ativo)
VALUES ('Maria da Silva', 'maria.silva@email.com', '1985-12-10', 'senha456', 1);

SELECT * FROM Usuario