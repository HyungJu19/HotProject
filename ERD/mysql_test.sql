-- 기존테이블 삭제

DELETE FROM t5_post;
ALTER TABLE t5_post AUTO_INCREMENT = 1;
DELETE FROM t5_user_authorities;
ALTER TABLE t5_user_authorities AUTO_INCREMENT = 1;
DELETE FROM t5_authority;
ALTER TABLE t5_authority AUTO_INCREMENT = 1;
DELETE FROM t5_user ;
ALTER TABLE t5_user AUTO_INCREMENT = 1;


-- 샘플 authority
INSERT INTO t5_authority (name) VALUES
                                    ('ROLE_MEMBER'), ('ROLE_ADMIN');


INSERT INTO t5_user (username, password, name, email) VALUES
                                                          ('USER1', '$2a$10$6gVaMy7.lbezp8bGRlV2fOArmA3WAk2EHxSKxncnzs28/m3DXPyA2', '회원1', 'user1@mail.com'),
                                                          ('USER2', '$2a$10$7LTnvLaczZbEL0gabgqgfezQPr.xOtTab2NAF/Yt4FrvTSi0Y29Xa', '회원2', 'user2@mail.com'),
                                                          ('ADMIN1', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi', '관리자1', 'admin1@mail.com')
;


-- 샘플 사용자-권한
INSERT INTO t5_user_authorities VALUES
                                    (1, 1),
                                    (3, 1),
                                    (3, 2)
;


-- 샘플 글
INSERT INTO t5_post (user_id, subject, content) VALUES
                                                    (1, '제목입니다1', '내용입니다1'),
                                                    (1, '제목입니다2', '내용입니다2'),
                                                    (3, '제목입니다3', '내용입니다3'),
                                                    (3, '제목입니다4', '내용입니다4')
;



SHOW TABLES;


SELECT TABLE_NAME FROM information_schema.TABLES
WHERE TABLE_SCHEMA = 'db907'
  AND TABLE_NAME LIKE 't5_%'
;


SELECT * FROM t5_authority;
SELECT * FROM t5_user ORDER BY id DESC;
SELECT * FROM t5_user_authorities;
SELECT * FROM t5_post ORDER BY id DESC;
