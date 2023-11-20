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

CREATE TABLE RelacionmentoJogadorEsquema(
    Id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    Posicao VARCHAR(50),
    IdJogador INT,
    FOREIGN KEY (IdJogador) REFERENCES Jogador(Id)
    IdEsquema INT,
    FOREIGN KEY (IdEsquema) REFERENCES EsquemaTatico(Id)
)
CREATE TABLE Profissional (
    Id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    Nome VARCHAR(255) NOT NULL,
    DataDeNascimento DATE ,
    Nacionalidade VARCHAR(50),
    NotaGeral DOUBLE,
    Imagem VARCHAR(255)
);
SELECT Id FROM Profissional ORDER BY Id DESC FETCH FIRST 1 ROW ONLY;
CREATE TABLE Jogador (
    Id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    Posicao VARCHAR(50),
    IdProfissional INT,
    FOREIGN KEY (IdProfissional) REFERENCES Profissional(Id)
);
CREATE TABLE TREINADOR (
    Id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    IdProfissional INT,
    FOREIGN KEY (IdProfissional) REFERENCES Profissional(Id),
    Especialidade VARCHAR(50)
);

CREATE TABLE Time (
    Id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    Nome VARCHAR(255) NOT NULL,
    Sigla VARCHAR(10),
    Pais VARCHAR(50),
    Liga VARCHAR(50),
    IdUsuario INT,
    CorUniforme VARCHAR(20),
    FOREIGN KEY (IdUsuario) REFERENCES Usuario(Id)

);

CREATE TABLE RelacionamentoTimeProfissional(
   Id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
   IdProfissional INT,
   IdTime INT,
   FOREIGN KEY (IdProfissional) REFERENCES Profissional(Id),
   FOREIGN KEY (IdTime) REFERENCES Time(Id)
);

CREATE TABLE EsquemaTatico (
    Id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    IdTime INT,
    Tipo VARCHAR(20), 
    Nome VARCHAR(255),
    Formacao VARCHAR(20),
    TaticaEspecifica VARCHAR(50),
    FOREIGN KEY (IdTime) REFERENCES Time(Id)
);

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
    FOREIGN KEY (IDEsquemaOfensivoMandante) REFERENCES EsquemaTatico(Id),
    FOREIGN KEY (IDEsquemaOfensivoVisitante) REFERENCES EsquemaTatico(Id),
    FOREIGN KEY (IDEsquemaDefensivoMandante) REFERENCES EsquemaTatico(Id),
    FOREIGN KEY (IDEsquemaDefensivoVisitante) REFERENCES EsquemaTatico(Id),
    FOREIGN KEY (IDUsuario) REFERENCES Usuario(Id)
);





