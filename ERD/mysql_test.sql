-- 기존테이블 삭제
DELETE FROM hot_tour_recommend;
DELETE FROM hot_camping_recommendcount;
DELETE FROM hot_post;
ALTER TABLE hot_post AUTO_INCREMENT = 1;
DELETE FROM hot_user_role;
ALTER TABLE hot_user_role AUTO_INCREMENT = 1;
DELETE FROM hot_role;
ALTER TABLE hot_role AUTO_INCREMENT = 1;
DELETE FROM hot_user ;
ALTER TABLE hot_user AUTO_INCREMENT = 1;
DELETE FROM hot_board ;
ALTER TABLE hot_board AUTO_INCREMENT = 1;

-- 샘플 authority
INSERT INTO hot_role (role_name) VALUES
                                    ('ROLE_MEMBER'), ('ROLE_ADMIN');


INSERT INTO hot_user (username, nickname, password, email) VALUES
                                                          ('USER1', '회원1', '$2a$10$6gVaMy7.lbezp8bGRlV2fOArmA3WAk2EHxSKxncnzs28/m3DXPyA2', 'user1@mail.com'),
                                                          ('USER2', '회원2', '$2a$10$7LTnvLaczZbEL0gabgqgfezQPr.xOtTab2NAF/Yt4FrvTSi0Y29Xa',  'user2@mail.com'),
                                                          ('ADMIN1', '관리자1', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi',  'admin1@mail.com'),
                                                          ('USER3', '회원3', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi',  'user31@mail.com')
;


-- 샘플 사용자-권한
INSERT INTO hot_user_role VALUES
                                    (1, 1),
                                    (1, 3),
                                    (2, 3),
                                    (1, 4)
;


SELECT * FROM hot_post;
SELECT * FROM hot_board ORDER BY boardid;

INSERT INTO hot_board (boardname) VALUES
                                      ('관광'),
                                      ('맛집'),
                                      ('캠핑'),
                                      ('숙박'),
                                      ('문화시설'),
                                      ('쇼핑'),
                                      ('레포츠');



-- 샘플 글
INSERT INTO hot_post (userId, boardid,  category,  subject, content, visibility ) VALUES
                                                    (1, 2, '캠핑','제목입니다1', '내용입니다1','PUBLIC'),
                                                    (1, 3, '맛집','제목입니다2', '내용입니다2','PUBLIC'),
                                                    (3, 1, '유아동반','제목입니다3', '내용입니다3','PUBLIC'),
                                                    (3, 4, '축제','제목입니다4', '내용입니다4','PUBLIC')
;



SHOW TABLES;


SELECT TABLE_NAME FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'db907'
  AND TABLE_NAME LIKE 'hot_%'
;


SELECT * FROM hot_role;
SELECT * FROM hot_user ORDER BY uid DESC;
SELECT * FROM hot_user_role;
SELECT * FROM hot_post ORDER BY postId DESC;


SELECT count(*) FROM hot_tour_mysql;  -- 21975
SELECT contentid FROM hot_tour_mysql;


SELECT count(*) FROM hot_tour_mysql;
SELECT * FROM hot_tour_recommend;
SELECT * FROM hot_user;
SELECT * FROM hot_tour_mysql;

SELECT tour_id
FROM hot_tour_recommend
WHERE uid = 3;

# DELETE FROM hot_tour_recommend;
SELECT count(*) FROM hot_tour_mysql;
SELECT FLOOR( 1 + RAND() * 4 ) "uid", FLOOR(1 + RAND() * (SELECT count(*) FROM hot_tour_mysql))  FROM hot_tour_mysql;
replace INTO hot_tour_recommend
(SELECT FLOOR( 1 + RAND() * 4 ), FLOOR(1 + RAND() * (SELECT count(*) FROM hot_tour_mysql))  FROM hot_tour_mysql);

-- 투어SELECT FLOOR( 1 + RAND( ) * 4 );
SELECT count(*) FROM hot_camping;
SELECT * FROM hot_camping;
UPDATE hot_camping
SET viewcnt = viewcnt + 1
WHERE camping_contentid = 100000;

SELECT *
FROM hot_tour_mysql
WHERE contentid = 3056662 AND contenttypeid = 12;

SELECT * FROM hot_camping_recommendcount;
-- 캠핑
-- SELECT FLOOR( 1 + RAND( ) * 4 );
replace INTO hot_camping_recommendcount
    (SELECT FLOOR( 1 + RAND() * 4 ), FLOOR(1 + RAND() * (SELECT count(*) FROM hot_camping))  FROM hot_camping);

UPDATE hot_tour_mysql
SET viewcnt = FLOOR(RAND() * 50) + 1; -- 1부터 50까지의 랜덤 값
--
--
select * from hot_tour_mysql;
select * from hot_tour_recommend;
select * from hot_camping;
select * from hot_camping_recommendcount;
select * from hot_camping;

# 추천수 기준 정렬
SELECT
    count(r.tour_id) "count_tour",
    r.tour_id "tour_id",
    t.title "title"

FROM
    hot_tour_mysql t, hot_tour_recommend r
WHERE
    1 = 1
    AND t.tour_id = r.tour_id
    AND t.areacode = 1
    AND t.contenttypeid = 12
GROUP BY r.tour_id
ORDER BY count_tour DESC
LIMIT 100
;

# 조회수 기준 정렬
SELECT
    count(t.viewcnt) "viewcnt",
    r.tour_id "tour_id",
    t.title "title"

FROM
    hot_tour_mysql t, hot_tour_recommend r ,hot_post p
WHERE
        1 = 1
  AND t.tour_id = r.tour_id
  AND t.areacode = 1
  AND t.contenttypeid = 12
GROUP BY r.tour_id
ORDER BY viewcnt DESC
LIMIT 1000
;


SELECT COUNT(*) FROM hot_camping WHERE doNm = '강원도';



SELECT * FROM hot_camping;

SELECT *
FROM hot_camping
WHERE camping_contentid = 1770 AND doNm = '강원도';

SELECT * FROM hot_tour_mysql;

SELECT
    *
FROM hot_tour_mysql
WHERE
        contenttypeid = 12;


SELECT * FROM hot_tour_mysql WHERE areacode = 5 AND contenttypeid = 32;
SELECT * FROM hot_tour_mysql WHERE areacode = 5 AND contenttypeid = 38;

SELECT * FROM hot_tour_mysql WHERE areacode = 1 AND contenttypeid = 32;


SELECT * FROM hot_tour_mysql;
SELECT * FROM hot_user;
SELECT * FROM hot_tour_recommend;


SELECT COUNT(*) FROM hot_tour_recommend WHERE tour_id = 432 AND uid =1;


SELECT
    count(r.tour_id) "count_tour",
    r.tour_id "tourid",
    t.title "title",
    t.zipcode "zipcode",
    t.addr1 "addr1",
    t.areacode "areacode",
    t.contentid "contentid",
    t.contenttypeid "contenttypeid",
    t.firstimage "firstimage",
    t.mapx "mapx",
    t.mapy "mapy",
    t.sigungucode "sigungucode",
    t.cat1 "cat1",
    t.cat2 "cat2",
    t.cat3 "cat3",
    t.viewcnt "viewcnt"

FROM hot_tour_mysql t, hot_tour_recommend r
WHERE
        1 = 1
  AND t.tour_id = r.tour_id
  AND t.areacode = 1
  AND t.contenttypeid = 12
GROUP BY r.tour_id
ORDER BY count_tour DESC;


SELECT uid "uid", tour_id "id"
FROM hot_tour_recommend
WHERE uid = 1;

SELECT COUNT(tour_id)
FROM hot_tour_recommend
WHERE tour_id = 111;


SELECT * FROM hot_tour_mysql WHERE contentid =2638477;
SELECT viewcnt
FROM hot_tour_mysql
WHERE contentid = 2638477;
