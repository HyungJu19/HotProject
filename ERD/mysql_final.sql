SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS test_attachment;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS camping_recommendCount;
DROP TABLE IF EXISTS camping;
DROP TABLE IF EXISTS friendship;
DROP TABLE IF EXISTS postcard;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS schedule_info;
DROP TABLE IF EXISTS tour_recommend;
DROP TABLE IF EXISTS tour_mysql;
DROP TABLE IF EXISTS user;




/* Create Tables */

CREATE TABLE board
(
    boardid int NOT NULL AUTO_INCREMENT,
    boardname varchar(50) NOT NULL,
    PRIMARY KEY (boardid),
    UNIQUE (boardname)
);


CREATE TABLE camping
(
    camping_id int NOT NULL AUTO_INCREMENT,
    facltNm varchar(100),
    lineintro longtext,
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
    contentId varchar(100),
    PRIMARY KEY (camping_id),
    UNIQUE (contentId)
);


CREATE TABLE camping_recommendCount
(
    uid bigint NOT NULL,
    camping_id int NOT NULL,
    PRIMARY KEY (uid, camping_id)
);


CREATE TABLE comment
(
    tour_id int NOT NULL AUTO_INCREMENT,
    uid bigint NOT NULL,
    postId int NOT NULL,
    test_comment text NOT NULL,
    regdate datetime DEFAULT now(),
    PRIMARY KEY (tour_id)
);


CREATE TABLE friendship
(
    user_id1 bigint NOT NULL,
    user_id2 bigint NOT NULL,
    friendship_date datetime DEFAULT now(),
    PRIMARY KEY (user_id1, user_id2)
);


CREATE TABLE post
(
    postId int NOT NULL AUTO_INCREMENT,
    userId bigint NOT NULL,
    boardid int NOT NULL,
    tour_id int NOT NULL,
    camping_id int NOT NULL,
    category varchar(50) NOT NULL,
    subject varchar(50) NOT NULL,
    content text NOT NULL,
    visibility varchar(20) NOT NULL CHECK (visibility IN ('PUBLIC', 'FRIENDS', 'PRIVATE')),
    viewcnt int DEFAULT 0,
    regDate datetime DEFAULT now(),
    PRIMARY KEY (postId)
);


CREATE TABLE postcard
(
    postcard_id int NOT NULL AUTO_INCREMENT,
    uid bigint NOT NULL,
    region varchar(200),
    travel_date datetime,
    PRIMARY KEY (postcard_id)
);


CREATE TABLE role
(
    user_id int NOT NULL,
    role_name varchar(255) NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE (role_name)
);


CREATE TABLE schedule_info
(
    schedule_id int NOT NULL AUTO_INCREMENT,
    uid bigint NOT NULL,
    content_list_id varchar(250),
    start_day datetime,
    end_day datetime,
    result_day datetime,
    time varchar(200),
    total_time varchar(200),
    detail varchar(250),
    PRIMARY KEY (schedule_id)
);


CREATE TABLE test_attachment
(
    id int NOT NULL AUTO_INCREMENT,
    postId int NOT NULL,
    sourcename varchar(100) NOT NULL,
    filename varchar(100) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE tour_mysql
(
    tour_id int NOT NULL AUTO_INCREMENT,
    title varchar(200),
    zipcode varchar(100),
    addr1 varchar(200),
    areacode varchar(100),
    contentid varchar(100),
    contenttypeid varchar(100),
    firstimage text,
    mapx double,
    mapy double,
    sigungucode varchar(100),
    cat1 varchar(10),
    cat2 varchar(10),
    cat3 varchar(10),
    PRIMARY KEY (tour_id),
    UNIQUE (contentid)
);


CREATE TABLE tour_recommend
(
    uid bigint NOT NULL,
    tour_id int NOT NULL,
    PRIMARY KEY (uid, tour_id)
);


CREATE TABLE user
(
    uid bigint NOT NULL,
    username varchar(50) NOT NULL,
    password varchar(300) NOT NULL,
    nickname varchar(50) NOT NULL,
    phonenumber varchar(50) NOT NULL,
    email varchar(100) NOT NULL,
    gender tinyint(1) NOT NULL check(gender = 0 or gender = 1),
    regDate datetime DEFAULT now(),
    PRIMARY KEY (uid),
    UNIQUE (username),
    UNIQUE (nickname),
    UNIQUE (phonenumber),
    UNIQUE (email)
);


CREATE TABLE user_role
(
    user_id int NOT NULL,
    uid bigint NOT NULL,
    PRIMARY KEY (user_id, uid)
);



/* Create Foreign Keys */

ALTER TABLE post
    ADD FOREIGN KEY (boardid)
        REFERENCES board (boardid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE camping_recommendCount
    ADD FOREIGN KEY (camping_id)
        REFERENCES camping (camping_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE post
    ADD FOREIGN KEY (camping_id)
        REFERENCES camping (camping_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE comment
    ADD FOREIGN KEY (postId)
        REFERENCES post (postId)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE test_attachment
    ADD FOREIGN KEY (postId)
        REFERENCES post (postId)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE user_role
    ADD FOREIGN KEY (user_id)
        REFERENCES role (user_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE post
    ADD FOREIGN KEY (tour_id)
        REFERENCES tour_mysql (tour_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE tour_recommend
    ADD FOREIGN KEY (tour_id)
        REFERENCES tour_mysql (tour_id)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE camping_recommendCount
    ADD FOREIGN KEY (uid)
        REFERENCES user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE comment
    ADD FOREIGN KEY (uid)
        REFERENCES user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE friendship
    ADD FOREIGN KEY (user_id2)
        REFERENCES user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE friendship
    ADD FOREIGN KEY (user_id1)
        REFERENCES user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE post
    ADD FOREIGN KEY (userId)
        REFERENCES user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE postcard
    ADD FOREIGN KEY (uid)
        REFERENCES user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE schedule_info
    ADD FOREIGN KEY (uid)
        REFERENCES user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE tour_recommend
    ADD FOREIGN KEY (uid)
        REFERENCES user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;


ALTER TABLE user_role
    ADD FOREIGN KEY (uid)
        REFERENCES user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;

select * from camping;
select * from tour_mysql;
select * from user;
select * from post;
select * from role;
select * from friendship;
select * from schedule_info;
select * from postcard;
select * from board;
select * from comment;


