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

select * from hot_user;

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




-- 샘플 글
INSERT INTO hot_post (userId, boardid,  category,  title, content, visibility ) VALUES
                                                    (1, 2, '캠핑','제목입니다1', '내용입니다1','PUBLIC'),
                                                    (1, 3, '맛집','제목입니다2', '내용입니다2','PUBLIC'),
                                                    (3, 1, '유아동반','제목입니다3', '내용입니다3','PUBLIC'),
                                                    (3, 4, '축제','제목입니다4', '내용입니다4','PUBLIC')
;

select * from hot_camping where facltNm = '향기로운추억캠핑장';



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

# DELETE FROM hot_tour_recommend;
SELECT count(*) FROM hot_tour_mysql;
SELECT FLOOR( 1 + RAND() * 4 ) "uid", FLOOR(1 + RAND() * (SELECT count(*) FROM hot_tour_mysql))  FROM hot_tour_mysql;
replace INTO hot_tour_recommend
(SELECT FLOOR( 1 + RAND() * 4 ), FLOOR(1 + RAND() * (SELECT count(*) FROM hot_tour_mysql))  FROM hot_tour_mysql);

-- 투어SELECT FLOOR( 1 + RAND( ) * 4 );
SELECT count(*) FROM hot_camping;
SELECT * FROM hot_tour_mysql WHERE contenttypeid =38;
SELECT *
FROM hot_tour_mysql
WHERE contentid = 3056662 AND contenttypeid = 12;

SELECT * FROM hot_camping_recommendcount;
-- 캠핑
-- SELECT FLOOR( 1 + RAND( ) * 4 );
replace INTO hot_camping_recommendcount
    (SELECT FLOOR( 1 + RAND() * 4 ), FLOOR(1 + RAND() * (SELECT count(*) FROM hot_camping))  FROM hot_camping);

select * From hot_camping where firstImageUrl = '';

# update hot_camping
# set firstImageUrl = ''
# where firstImageUrl = 'hi';

    select * from hot_camping;

# UPDATE hot_tour_mysql
# SET viewcnt = FLOOR(RAND() * 50) + 1; -- 1부터 50까지의 랜덤 값
--
--
select * from hot_tour_mysql;
select * from hot_tour_recommend;
select * from hot_camping;
select * from hot_camping_recommendcount;
select * from hot_camping;

select * from hot_user;

select * from hot_test_attachment;

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

SELECT  c.*, count(r.camping_id)  FROM hot_camping c , hot_camping_recommendcount r
WHERE  c.camping_id = r.camping_id
group by r.camping_id
order by count(r.camping_id) DESC
LIMIT 4;

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


SELECT  c.camping_id , count(r.camping_id)  FROM hot_camping c , hot_camping_recommendcount r
WHERE  c.camping_id = r.camping_id
group by r.camping_id
order by count(r.camping_id) DESC
LIMIT 4;

select * from hot_camping_recommendcount;





SELECT * FROM hot_tour_mysql;



SELECT * FROM hot_tour_mysql WHERE areacode = 5 AND contenttypeid = 32;
SELECT * FROM hot_tour_mysql WHERE areacode = 5 AND contenttypeid = 38;

SELECT * FROM hot_tour_mysql WHERE areacode = 1 AND contenttypeid = 32;


SELECT * FROM hot_tour_mysql;
SELECT * FROM hot_user;
SELECT * FROM hot_tour_recommend;

SELECT *
FROM hot_tour_recommend t, hot_camping_recommendCount r
WHERE t.uid = r.uid AND t.uid = 2 AND t.tour_id = 88 OR r.camping_id = '';

SELECT * FROM hot_camping c , hot_camping_recommendcount r
ORDER BY r.camping_id
LIMIT 4;


SELECT count(*) from hot_camping;  -- 1842

SELECT count(*) from hot_camping_recommendcount;  -- 2900

select * from hot_camping;


--
SELECT * FROM hot_camping c , hot_camping_recommendcount r
ORDER BY r.camping_id;

SELECT camping_id, uid, facltNm, intro, induty, lctCl, doNm "area", sigunguNm, addr1, mapX, mapY, tel, operPdCl, operDeCl, firstImageUrl, tourEraCl, posblFcltyCl, themaEnvrnCl, animalCmgCl, camping_contentid "contentId", viewcnt
FROM hot_camping
WHERE
        facltNm LIKE CONCAT('%', '가', '%')
        OR intro LIKE CONCAT('%','가', '%')
        OR induty LIKE CONCAT('%','가', '%')
        OR lctCl LIKE CONCAT('%', '가', '%')
        OR doNm LIKE CONCAT('%', '가', '%')
        OR sigunguNm LIKE CONCAT('%','가', '%')
        OR addr1 LIKE CONCAT('%', '가', '%')
        OR operPdCl LIKE CONCAT('%','가', '%')
        OR operDeCl LIKE CONCAT('%', '가', '%')
        OR tourEraCl LIKE CONCAT('%', '가', '%')
        OR posblFcltyCl LIKE CONCAT('%','가', '%')
        OR themaEnvrnCl LIKE CONCAT('%','가', '%')
        OR animalCmgCl LIKE CONCAT('%', '가', '%');

SELECT *
FROM hot_tour_mysql
WHERE
        title LIKE CONCAT('%','가', '%')
                          OR addr1 LIKE CONCAT('%','가', '%')
;

SELECT
    count(r.camping_id) "count_camping",
    r.camping_id "camping_id",
    t.camping_id "campingid",
    t.facltNm "facltNm",
    t.addr1 "addr1",
    t.induty "induty",
    t.lctCl "lctCl",
    t.doNm "area",
    t.sigunguNm "sigunguNm",
    t.intro "intro",
    t.mapX "mapX",
    t.mapY "mapY",
    t.tel "tel",
    t.operPdCl "operPdCl",
    t.operDeCl "operDeCl",
    t.tourEraCl "tourEraCl",
    t.firstImageUrl "firstImageUrl",
    t.posblFcltyCl "posblFcltyCl",
    t.themaEnvrnCl "themaEnvrnCl",
    t.animalCmgCl "animalCmgCl",
    t.camping_contentid "contentId",
    t.viewcnt "viewcnt"

FROM hot_camping t, hot_camping_recommendCount r
WHERE
        1 = 1
  AND t.camping_id = r.camping_id
  AND t.doNm = '서울시'

GROUP BY r.camping_id
ORDER BY count_camping DESC;
SELECT * FROM hot_tour_mysql WHERE contenttypeid = 32 AND areacode = 1;
SELECT * FROM hot_tour_recommend;
SELECT
    count(r.tour_id) "count_tour",
    r.tour_id "tour_id",
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
  AND t.contenttypeid = 32
GROUP BY r.tour_id
ORDER BY count_tour DESC;

select * from hot_tour_mysql t, hot_tour_recommend r where 1=1 AND t.areacode = 1
    ANd contenttypeid = 32
    ;

SELECT
    count(r.tour_id) "count_tour"
    ,r.tour_id


FROM hot_tour_mysql t, hot_tour_recommend r
WHERE

  t.tour_id = r.tour_id
  AND t.areacode = 1
  AND t.contenttypeid = 32
GROUP BY r.tour_id
;
select * from hot_tour_mysql ;

SELECT COUNT(*)
FROM hot_camping
WHERE
        facltNm LIKE CONCAT('%', '소형견', '%')
        OR intro LIKE CONCAT('%', '소형견', '%')
        OR induty LIKE CONCAT('%', '소형견', '%')
        OR lctCl LIKE CONCAT('%', '소형견', '%')
        OR doNm LIKE CONCAT('%', '소형견', '%')
        OR sigunguNm LIKE CONCAT('%', '소형견', '%')
        OR addr1 LIKE CONCAT('%', '소형견', '%')
        OR operPdCl LIKE CONCAT('%', '소형견', '%')
        OR operDeCl LIKE CONCAT('%', '소형견', '%')
        OR tourEraCl LIKE CONCAT('%', '소형견', '%')
        OR posblFcltyCl LIKE CONCAT('%', '소형견', '%')
        OR themaEnvrnCl LIKE CONCAT('%', '소형견', '%')
        OR animalCmgCl LIKE CONCAT('%', '소형견', '%');

SELECT COUNT(*)
FROM hot_tour_mysql
WHERE
        title LIKE CONCAT('%', '부천', '%')
        or addr1 LIKE CONCAT('%', '부천', '%');

SELECT
    count(r.tour_id) "co",
    r.tour_id "tour_id",
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
GROUP BY r.tour_id,  t.viewcnt
ORDER BY  DESC
;

SELECT
    count(r.tour_id) "co",
    r.tour_id "tour_id",
    t.tour_id "id",
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
    count(t.viewcnt) "viewcnt"

FROM hot_tour_mysql t, hot_tour_recommend r
WHERE
        1 = 1
  AND t.tour_id = r.tour_id
  AND t.areacode = 33
  AND t.contenttypeid = 12
GROUP BY r.tour_id, t.viewcnt
ORDER BY viewcnt DESC;


-- -------------------------------------

SELECT
    count(r.tour_id) "co",
    r.tour_id "id",
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
GROUP BY r.tour_id, t.viewcnt
ORDER BY co DESC;
-- LIMIT 20 OFFSET 0;


SELECT
    count(r.camping_id) "co",
    r.camping_id "camping_id",
    t.camping_id "campingid",
    t.facltNm "facltNm",
    t.addr1 "addr1",
    t.induty "induty",
    t.lctCl "lctCl",
    t.doNm "area",
    t.sigunguNm "sigunguNm",
    t.intro "intro",
    t.mapX "mapX",
    t.mapY "mapY",
    t.tel "tel",
    t.operPdCl "operPdCl",
    t.operDeCl "operDeCl",
    t.tourEraCl "tourEraCl",
    t.firstImageUrl "firstImageUrl",
    t.posblFcltyCl "posblFcltyCl",
    t.themaEnvrnCl "themaEnvrnCl",
    t.animalCmgCl "animalCmgCl",
    t.camping_contentid "contentId",
    t.viewcnt "viewcnt"

FROM hot_camping t, hot_camping_recommendCount r
WHERE
        1 = 1
  AND t.camping_id = r.camping_id
  AND t.doNm = '경기도'
GROUP BY r.camping_id, t.viewcnt
ORDER BY viewcnt DESC;

select * from hot_camping where doNm = '충청남도';
SELECT * from hot_post;

select * from hot_board;

SELECT *
FROM hot_post
where
        category LIKE CONCAT('%', 3, '%')
                             OR title LIKE CONCAT('%', 3, '%')
                                                  OR content LIKE CONCAT('%', 3, '%')
                                                                         OR visibility LIKE CONCAT('%', 3, '%');

