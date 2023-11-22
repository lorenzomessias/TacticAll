-- Tabela Usuario
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

SELECT * FROM Usuario;

-- Tabela RelacionmentoJogadorEsquema
CREATE TABLE RelacionamentoJogadorEsquema(
    Id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    Posicao VARCHAR(50),
    IdJogador INT,
    FOREIGN KEY (IdJogador) REFERENCES Jogador(Id) ON DELETE CASCADE,
    IdEsquema INT,
    FOREIGN KEY (IdEsquema) REFERENCES EsquemaTatico(Id) ON DELETE CASCADE
);

-- Tabela Profissional
CREATE TABLE Profissional (
    Id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    Nome VARCHAR(255) NOT NULL,
    DataDeNascimento DATE ,
    Nacionalidade VARCHAR(50),
    NotaGeral DOUBLE,
    Imagem VARCHAR(255)
);

SELECT Id FROM Profissional ORDER BY Id DESC FETCH FIRST 1 ROW ONLY;

-- Tabela Jogador
CREATE TABLE Jogador (
    Id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    Posicao VARCHAR(50),
    IdProfissional INT,
    FOREIGN KEY (IdProfissional) REFERENCES Profissional(Id) ON DELETE CASCADE
);

-- Tabela TREINADOR
CREATE TABLE TREINADOR (
    Id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    IdProfissional INT,
    FOREIGN KEY (IdProfissional) REFERENCES Profissional(Id) ON DELETE CASCADE,
    Especialidade VARCHAR(50)
);

-- Tabela Time
CREATE TABLE Time (
    Id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    Nome VARCHAR(255) NOT NULL,
    Sigla VARCHAR(10),
    Pais VARCHAR(50),
    Liga VARCHAR(50),
    IdUsuario INT,
    CorUniforme VARCHAR(20),
    FOREIGN KEY (IdUsuario) REFERENCES Usuario(Id) ON DELETE CASCADE
);

-- Tabela RelacionamentoTimeProfissional
CREATE TABLE RelacionamentoTimeProfissional(
   Id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
   IdProfissional INT,
   IdTime INT,
   FOREIGN KEY (IdProfissional) REFERENCES Profissional(Id) ON DELETE CASCADE,
   FOREIGN KEY (IdTime) REFERENCES Time(Id) ON DELETE CASCADE
);

-- Tabela EsquemaTatico
CREATE TABLE EsquemaTatico (
    Id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    IdTime INT,
    Tipo VARCHAR(20), 
    Nome VARCHAR(255),
    Formacao VARCHAR(20),
    TaticaEspecifica VARCHAR(50),
    FOREIGN KEY (IdTime) REFERENCES Time(Id) ON DELETE CASCADE
);

-- Tabela Simulacao
CREATE TABLE Simulacao (
    Id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    IdEsquemaOfensivoMandante INT,
    IdEsquemaOfensivoVisitante INT,
    IdEsquemaDefensivoMandante INT,
    IdEsquemaDefensivoVisitante INT,
    IdUsuario INT,
    GolsTimeVisitante INT,
    GolsTimeMandante INT,
    DataSimulacao DATE,
    FOREIGN KEY (IDEsquemaOfensivoMandante) REFERENCES EsquemaTatico(Id) ON DELETE CASCADE,
    FOREIGN KEY (IDEsquemaOfensivoVisitante) REFERENCES EsquemaTatico(Id) ON DELETE CASCADE,
    FOREIGN KEY (IDEsquemaDefensivoMandante) REFERENCES EsquemaTatico(Id) ON DELETE CASCADE,
    FOREIGN KEY (IDEsquemaDefensivoVisitante) REFERENCES EsquemaTatico(Id) ON DELETE CASCADE,
    FOREIGN KEY (IDUsuario) REFERENCES Usuario(Id) ON DELETE CASCADE
);

SELECT * FROM Profissional

SELECT * FROM TIME

SELECT * FROM RELACIONAMENTOTIMEPROFISSIONAL


SELECT j.*, p.*
FROM Jogador j
INNER JOIN Profissional p ON j.IdProfissional = p.Id
INNER JOIN RelacionamentoTimeProfissional r ON p.Id = r.IdProfissional
WHERE r.IdTime = 2

SELECT * FROM ESQUEMATATICO


SELECT * FROM RelacionamentoJogadorEsquema

SELECT * FROM EsquemaTatico

SELECT * FROM TIME