CREATE TABLE itmn_equipamento(
    id_equip INTEGER NOT NULL AUTO_INCREMENT,
    hostname VARCHAR(50) NOT NULL,
    ipaddr VARCHAR(15) NOT NULL,
    CONSTRAINT pk_equip PRIMARY KEY(id_equip)
);

CREATE TABLE itmn_alarme(
    id_alarme INTEGER NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(200),

    CONSTRAINT pk_alarme PRIMARY KEY(id_alarme)
);

CREATE TABLE itmn_evento(
    num_seq INTEGER NOT NULL AUTO_INCREMENT,
    data_evt DATE,
    id_alarme INTEGER NOT NULL,
    id_equip INTEGER NOT NULL,
    CONSTRAINT pk_evento PRIMARY KEY (num_seq),
    CONSTRAINT fk_alarme FOREIGN KEY (id_alarme) REFERENCES itmn_alarme (id_alarme),
    CONSTRAINT fk_equip FOREIGN KEY (id_equip) REFERENCES itmn_equipamento(id_equip)
);
