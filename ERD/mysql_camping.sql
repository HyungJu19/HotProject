SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */
DROP TABLE IF EXISTS camping;
/*animalCmgCl 애완동물 동반여부 컬럼 추가할것*/

CREATE TABLE camping
(
    camping_id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    facltNm varchar(100),
    intro varchar(2000),
    induty varchar(20),
    lctCl varchar(20),
    doNm varchar(10),
    sigunguNm varchar(10),
    addr1 varchar(200),
    mapX double,
    mapY double,
    tel varchar(15),
    operPdCl varchar(20),
    operDeCl varchar(10),
    tourEraCl varchar(20),
    firstImageUrl varchar(150),
    posblFcltyCl varchar(200),
    themaEnvrnCl varchar(200),
    recommendCount int
);


select * from camping;










