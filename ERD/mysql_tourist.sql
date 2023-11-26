DROP TABLE IF EXISTS tourist;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS cultural;
DROP TABLE IF EXISTS festival;
DROP TABLE IF EXISTS sports;
DROP TABLE IF EXISTS lodgment;
DROP TABLE IF EXISTS shopping;

#관광지
CREATE TABLE tourist (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(255),
                        addr1 VARCHAR(255),
                        areacode VARCHAR(255),
                        contentid VARCHAR(255) UNIQUE ,
                        contenttypeid VARCHAR(255),
                        firstimage VARCHAR(255),
                        mapx VARCHAR(255),
                        mapy VARCHAR(255),
                        sigungucode VARCHAR(255),
                        zipcode VARCHAR(255),
                        cat1 VARCHAR(255),
                        cat2 VARCHAR(255),
                        cat3 VARCHAR(255),
                        recommendCount INT DEFAULT 0
);


# 음식점
CREATE TABLE restaurant(
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           title VARCHAR(255),
                           addr1 VARCHAR(255),
                           areacode VARCHAR(255),
                           contentid VARCHAR(255) UNIQUE ,
                           contenttypeid VARCHAR(255),
                           firstimage VARCHAR(255),
                           mapx VARCHAR(255),
                           mapy VARCHAR(255),
                           sigungucode VARCHAR(255),
                           zipcode VARCHAR(255),
                           cat1 VARCHAR(255),
                           cat2 VARCHAR(255),
                           cat3 VARCHAR(255),
                           recommendCount INT DEFAULT 0

);

# 문화시설
CREATE TABLE cultural(
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255),
                         addr1 VARCHAR(255),
                         areacode VARCHAR(255),
                         contentid VARCHAR(255) UNIQUE ,
                         contenttypeid VARCHAR(255),
                         firstimage VARCHAR(255),
                         mapx VARCHAR(255),
                         mapy VARCHAR(255),
                         sigungucode VARCHAR(255),
                         zipcode VARCHAR(255),
                         cat1 VARCHAR(255),
                         cat2 VARCHAR(255),
                         cat3 VARCHAR(255),
                         recommendCount INT DEFAULT 0
);

# 축제행사
CREATE TABLE festival(
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255),
                         addr1 VARCHAR(255),
                         areacode VARCHAR(255),
                         contentid VARCHAR(255) UNIQUE ,
                         contenttypeid VARCHAR(255),
                         firstimage VARCHAR(255),
                         mapx VARCHAR(255),
                         mapy VARCHAR(255),
                         sigungucode VARCHAR(255),
                         zipcode VARCHAR(255),
                         cat1 VARCHAR(255),
                         cat2 VARCHAR(255),
                         cat3 VARCHAR(255),
                         recommendCount INT DEFAULT 0
);
# 레포츠
CREATE TABLE sports(
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255),
                         addr1 VARCHAR(255),
                         areacode VARCHAR(255),
                         contentid VARCHAR(255) UNIQUE ,
                         contenttypeid VARCHAR(255),
                         firstimage VARCHAR(255),
                         mapx VARCHAR(255),
                         mapy VARCHAR(255),
                         sigungucode VARCHAR(255),
                         zipcode VARCHAR(255),
                         cat1 VARCHAR(255),
                         cat2 VARCHAR(255),
                         cat3 VARCHAR(255),
                         recommendCount INT DEFAULT 0
);
# 숙박
CREATE TABLE lodgment(
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(255),
                       addr1 VARCHAR(255),
                       areacode VARCHAR(255),
                       contentid VARCHAR(255) UNIQUE ,
                       contenttypeid VARCHAR(255),
                       firstimage VARCHAR(255),
                       mapx VARCHAR(255),
                       mapy VARCHAR(255),
                       sigungucode VARCHAR(255),
                       zipcode VARCHAR(255),
                       cat1 VARCHAR(255),
                       cat2 VARCHAR(255),
                       cat3 VARCHAR(255),
                       recommendCount INT DEFAULT 0
);
# 쇼핑
CREATE TABLE shopping(
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         title VARCHAR(255),
                         addr1 VARCHAR(255),
                         areacode VARCHAR(255),
                         contentid VARCHAR(255) UNIQUE ,
                         contenttypeid VARCHAR(255),
                         firstimage VARCHAR(255),
                         mapx VARCHAR(255),
                         mapy VARCHAR(255),
                         sigungucode VARCHAR(255),
                         zipcode VARCHAR(255),
                         cat1 VARCHAR(255),
                         cat2 VARCHAR(255),
                         cat3 VARCHAR(255),
                         recommendCount INT DEFAULT 0
);





















#관광지
SELECT * FROM tourist;
#음식점
SELECT * FROM restaurant;
#문화시설
SELECT * FROM cultural;
#축제행사
SELECT * FROM festival;
#레포츠
SELECT * FROM sports;
#숙박
SELECT * FROM lodgment;
#쇼핑
SELECT * FROM shopping;

SELECT COUNT(*) FROM tourist WHERE areacode=1;
SELECT COUNT(*) FROM tourist WHERE areacode=2;
SELECT COUNT(*) FROM tourist WHERE areacode=3;
SELECT COUNT(*) FROM tourist WHERE areacode=4;
SELECT COUNT(*) FROM tourist WHERE areacode=5;
SELECT COUNT(*) FROM tourist WHERE areacode=6;
SELECT COUNT(*) FROM tourist WHERE areacode=7;
SELECT COUNT(*) FROM tourist WHERE areacode=8 ;
SELECT COUNT(*) FROM tourist WHERE areacode=31 ;
SELECT COUNT(*) FROM tourist WHERE areacode=32 ;
SELECT COUNT(*) FROM tourist WHERE areacode=33 ;
SELECT COUNT(*) FROM tourist WHERE areacode=34 ;
SELECT COUNT(*) FROM tourist WHERE areacode=35 ;
SELECT COUNT(*) FROM tourist WHERE areacode=36 ;
SELECT COUNT(*) FROM tourist WHERE areacode=37 ;
SELECT COUNT(*) FROM tourist WHERE areacode=38 ;
SELECT COUNT(*) FROM tourist WHERE areacode=39 ;
SELECT COUNT(*) FROM tourist;
