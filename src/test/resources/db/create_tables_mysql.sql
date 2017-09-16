DROP TABLE IF EXISTS t_credit;
DROP TABLE IF EXISTS t_destcredit;

CREATE TABLE t_credit
    (ID int,
        ACCOUNTID VARCHAR(20),
        NAME VARCHAR(10),
        AMOUNT NUMERIC(10,2),
        DATE VARCHAR(20),
        ADDRESS VARCHAR(128),
        primary key (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
CREATE TABLE t_destcredit
    (ID int,
        ACCOUNTID VARCHAR(20),
        NAME VARCHAR(10),
        AMOUNT NUMERIC(10,2),
        DATE VARCHAR(20),
        ADDRESS VARCHAR(128),
        primary key (ID)
    )
    ENGINE=InnoDB DEFAULT CHARSET=utf8;
