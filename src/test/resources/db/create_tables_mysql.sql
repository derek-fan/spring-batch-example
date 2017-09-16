DROP TABLE IF EXISTS t_credit;
DROP TABLE IF EXISTS t_destcredit;

CREATE TABLE t_credit
    (ID VARCHAR(10),
        ACCOUNTID VARCHAR(20),
        NAME VARCHAR(10),
        AMOUNT NUMERIC(10,2),
        DATE VARCHAR(20),
        ADDRESS VARCHAR(128),
        primary key (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
CREATE TABLE t_destcredit
    (ID VARCHAR(10),
        ACCOUNTID VARCHAR(20),
        NAME VARCHAR(10),
        AMOUNT NUMERIC(10,2),
        DATE VARCHAR(20),
        ADDRESS VARCHAR(128),
        primary key (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE t_credit ADD PRIMARY KEY (`ID`);
ALTER TABLE t_destcredit ADD PRIMARY KEY (`ID`);