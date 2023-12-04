SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS test_attachment;
DROP TABLE IF EXISTS post;
DROP TABLE IF EXISTS board;
DROP TABLE IF EXISTS friendship;
DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS user;




/* Create Tables */

CREATE TABLE board
(
    boardid int NOT NULL,
    boardname varchar(50),
    PRIMARY KEY (boardid)
);


CREATE TABLE comment
(
    id int NOT NULL AUTO_INCREMENT,
    uid int NOT NULL,
    postId int NOT NULL,
    test_comment text NOT NULL,
    regdate datetime DEFAULT now(),
    PRIMARY KEY (id)
);


CREATE TABLE friendship
(
    user_id1 int NOT NULL,
    user_id2 int NOT NULL,
    friendship_date datetime DEFAULT now(),
    PRIMARY KEY (user_id1, user_id2)
);


CREATE TABLE post
(
    postId int NOT NULL AUTO_INCREMENT,
    userId int NOT NULL,
    boardid int NOT NULL,
    category varchar(50) NOT NULL,
    subject varchar(50) NOT NULL,
    content text NOT NULL,
    visibility varchar(20) NOT NULL CHECK (visibility IN ('PUBLIC', 'FRIENDS', 'PRIVATE')),
    viewcnt int DEFAULT 0,
    regDate datetime DEFAULT now(),
    PRIMARY KEY (postId),
    UNIQUE (boardid)
);


CREATE TABLE role
(
    user_id int NOT NULL,
    role_name varchar(255) NOT NULL,
    PRIMARY KEY (user_id),
    UNIQUE (role_name)
);


CREATE TABLE test_attachment
(
    id int NOT NULL AUTO_INCREMENT,
    postId int NOT NULL,
    sourcename varchar(100) NOT NULL,
    filename varchar(100) NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE user
(
    uid int NOT NULL AUTO_INCREMENT,
    username varchar(50) NOT NULL,
    password varchar(50) NOT NULL,
    nickname varchar(50) NOT NULL,
    phonenumber varchar(50) NOT NULL,
    email varchar(100) NOT NULL,
    regDate datetime DEFAULT now(),
    birthdate varchar(8) CHECK (birthdate REGEXP '^[0-9]{8}$'),
    provider varchar(50),
    providerId varchar(50),
    PRIMARY KEY (uid),
    UNIQUE (username),
    UNIQUE (nickname),
    UNIQUE (phonenumber),
    UNIQUE (email)
);


CREATE TABLE user_role
(
    user_id int NOT NULL,
    uid int NOT NULL,
    PRIMARY KEY (user_id, uid)
);



/* Create Foreign Keys */

ALTER TABLE post
    ADD FOREIGN KEY (boardid)
        REFERENCES board (boardid)
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


ALTER TABLE user_role
    ADD FOREIGN KEY (uid)
        REFERENCES user (uid)
        ON UPDATE RESTRICT
        ON DELETE RESTRICT
;




select * from comment;
select * from test_attachment;
select * from post;
select * from board;
select * from  friendship;
select * from user_role;
select * from role;
select * from user;