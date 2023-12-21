SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS hot_attachment;
DROP TABLE IF EXISTS hot_comment;
DROP TABLE IF EXISTS hot_post;
DROP TABLE IF EXISTS hot_board;
DROP TABLE IF EXISTS hot_camping_recommendCount;
DROP TABLE IF EXISTS hot_camping;
DROP TABLE IF EXISTS hot_friendship;
DROP TABLE IF EXISTS hot_postcard;
DROP TABLE IF EXISTS hot_user_role;
DROP TABLE IF EXISTS hot_role;
DROP TABLE IF EXISTS hot_schedule_info;
DROP TABLE IF EXISTS hot_tour_recommend;
DROP TABLE IF EXISTS hot_tour_mysql;
DROP TABLE IF EXISTS hot_user;








CREATE TABLE hot_camping
(
    camping_id int NOT NULL AUTO_INCREMENT,
    uid int NOT NULL default 0,
    facltNm varchar(100),
    intro mediumtext,
    induty varchar(50),
    lctCl varchar(50),
    doNm varchar(50),
    sigunguNm varchar(50),
    addr1 varchar(200),
    mapX double,
    mapY double,
    tel varchar(50),
    operPdCl varchar(50),
    operDeCl varchar(50),
    tourEraCl varchar(50),
    firstImageUrl varchar(200),
    posblFcltyCl text,
    themaEnvrnCl text,
    animalCmgCl varchar(50),
    camping_contentid varchar(100),
    viewcnt int default 0,
    PRIMARY KEY (camping_id),
    UNIQUE (camping_contentid)
);


CREATE TABLE hot_camping_recommendCount
(
    uid int NOT NULL,
    camping_id int NOT NULL,
    PRIMARY KEY (uid, camping_id)
);


CREATE TABLE hot_tour_mysql
(
    tour_id int NOT NULL AUTO_INCREMENT,
    uid int NOT NULL default 0,
    title varchar(200),
    zipcode varchar(100),
    addr1 varchar(200),
    areacode varchar(100),
    contentid varchar(100),
    contenttypeid  varchar(100),
    firstimage text,
    mapx double,
    mapy double,
    sigungucode varchar(100),
    cat1 varchar(10),
    cat2 varchar(10),
    cat3 varchar(10),
    viewcnt int default 0,
    PRIMARY KEY (tour_id),
    UNIQUE (contentid)

);


CREATE TABLE hot_tour_recommend
(
    uid int NOT NULL,
    tour_id int NOT NULL,
    PRIMARY KEY (uid, tour_id)
);


CREATE TABLE hot_comment
(
    tour_id int NOT NULL AUTO_INCREMENT,
    uid int NOT NULL,
    postId int NOT NULL,
    test_comment text NOT NULL,
    regdate datetime DEFAULT now(),
    PRIMARY KEY (tour_id)
);


CREATE TABLE hot_friendship
(
    user_id1 int NOT NULL,
    user_id2 int NOT NULL,
    friendship_date datetime DEFAULT now(),
    PRIMARY KEY (user_id1, user_id2)
);


CREATE TABLE hot_post
(
    postId int NOT NULL AUTO_INCREMENT,
    userId int NOT NULL,
    tour_id int,
    camping_id int,
    category varchar(50) NOT NULL,
    subject varchar(50) NOT NULL,
    img varchar(50),
    content text NOT NULL,
    title varchar(50),
    visibility varchar(20) NOT NULL CHECK (visibility IN ('PUBLIC', 'FRIENDS', 'PRIVATE')),
    viewcnt int DEFAULT 0,
    regDate datetime DEFAULT now(),
    PRIMARY KEY (postId)
);


CREATE TABLE hot_postcard
(
    postcard_id int NOT NULL AUTO_INCREMENT,
    uid int NOT NULL,
    region varchar(200),
    travel_date datetime,
    PRIMARY KEY (postcard_id)
);


CREATE TABLE hot_role
(
    role_id int NOT NULL AUTO_INCREMENT,
    role_name varchar(255) NOT NULL,
    PRIMARY KEY (role_id),
    UNIQUE (role_name)
);



CREATE TABLE hot_schedule_info
(
    schedule_id int NOT NULL AUTO_INCREMENT,
    uid int NOT NULL,
    content_list_id varchar(250),
    start_day datetime,
    end_day datetime,
    result_day datetime,
    time varchar(200),
    total_time varchar(200),
    detail varchar(250),
    PRIMARY KEY (schedule_id)
);


CREATE TABLE hot_user
(
    uid int NOT NULL AUTO_INCREMENT,
    username varchar(50) NOT NULL,
    name varchar(50) NOT NULL,
    password varchar(300) NOT NULL,
    nickname varchar(50) NOT NULL,
    email varchar(100) NOT NULL,
    regDate datetime DEFAULT now(),
    provider varchar(50),
    providerId varchar(50),
    PRIMARY KEY (uid),
    UNIQUE (username),
    UNIQUE (nickname),
    UNIQUE (email)
);


CREATE TABLE hot_user_role
(
    role_id int NOT NULL,
    uid int NOT NULL,
    PRIMARY KEY (role_id, uid)
);

/* 승원 */
CREATE TABLE hot_attachment
(
    id int NOT NULL AUTO_INCREMENT,
    postId int NOT NULL,
    sourcename varchar(100) NOT NULL,
    filename varchar(100) NOT NULL,
    url varchar(100),
    PRIMARY KEY (id)


);
/* 승원 */

/* Create Foreign Keys */




ALTER TABLE hot_camping_recommendCount
    ADD FOREIGN KEY (camping_id)
        REFERENCES hot_camping (camping_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE hot_tour_recommend
    ADD FOREIGN KEY (tour_id)
        REFERENCES hot_tour_mysql (tour_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE hot_post
    ADD FOREIGN KEY (camping_id)
        REFERENCES hot_camping (camping_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;
# FOREIGN KEY (uid) REFERENCES hot_user (uid)
#         ON UPDATE RESTRICT
#            ON DELETE CASCADE

ALTER TABLE hot_attachment
    ADD FOREIGN KEY (postId)
        REFERENCES hot_post (postId)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE hot_comment
    ADD FOREIGN KEY (postId)
        REFERENCES hot_post (postId)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE hot_user_role
    ADD FOREIGN KEY (role_id)
        REFERENCES hot_role (role_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE hot_post
    ADD FOREIGN KEY (tour_id)
        REFERENCES hot_tour_mysql (tour_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE hot_camping
    ADD FOREIGN KEY (uid)
        REFERENCES hot_user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE hot_camping_recommendCount
    ADD FOREIGN KEY (uid)
        REFERENCES hot_user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE hot_comment
    ADD FOREIGN KEY (uid)
        REFERENCES hot_user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE hot_friendship
    ADD FOREIGN KEY (user_id2)
        REFERENCES hot_user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE hot_friendship
    ADD FOREIGN KEY (user_id1)
        REFERENCES hot_user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE hot_post
    ADD FOREIGN KEY (userId)
        REFERENCES hot_user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;



ALTER TABLE hot_postcard
    ADD FOREIGN KEY (uid)
        REFERENCES hot_user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE hot_schedule_info
    ADD FOREIGN KEY (uid)
        REFERENCES hot_user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE hot_tour_mysql
    ADD FOREIGN KEY (uid)
        REFERENCES hot_user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE hot_tour_recommend
    ADD FOREIGN KEY (uid)
        REFERENCES hot_user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE hot_user_role
    ADD FOREIGN KEY (uid)
        REFERENCES hot_user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;




ALTER TABLE hot_tour_mysql MODIFY COLUMN uid INT NULL;
ALTER TABLE hot_camping MODIFY COLUMN uid INT NULL;







select * from hot_attachment;
select * from hot_camping;
select * from hot_tour_mysql;
select * from hot_user;
select * from hot_post;
select * from hot_role;
select * from hot_friendship;
select * from hot_schedule_info;
select * from hot_postcard;
select * from hot_comment;
SELECT * FROM hot_user_role;
select * from hot_tour_recommend;

