-- 기존테이블 삭제

DELETE FROM hot_post;
ALTER TABLE hot_post AUTO_INCREMENT = 1;
DELETE FROM hot_user_role;
ALTER TABLE hot_user_role AUTO_INCREMENT = 1;
DELETE FROM hot_role;
ALTER TABLE hot_role AUTO_INCREMENT = 1;
DELETE FROM hot_user ;
ALTER TABLE hot_user AUTO_INCREMENT = 1;


-- 샘플 authority
INSERT INTO hot_role (role_name) VALUES
                                    ('ROLE_MEMBER'), ('ROLE_ADMIN');


INSERT INTO hot_user (username,name,nickname, password,email) VALUES
                                                          ('USER1', '회원1','회원11' ,'$2a$10$6gVaMy7.lbezp8bGRlV2fOArmA3WAk2EHxSKxncnzs28/m3DXPyA2', 'user1@mail.com'),
                                                          ('USER2', '회원2','회원22' ,'$2a$10$7LTnvLaczZbEL0gabgqgfezQPr.xOtTab2NAF/Yt4FrvTSi0Y29Xa',  'user2@mail.com'),
                                                          ('ADMIN1', '관리자1', '관리자11','$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi',  'admin1@mail.com')
;


-- 샘플 사용자-권한
INSERT INTO hot_user_role VALUES
                                    (1, 1),
                                    (3, 1),
                                    (3, 2)
;


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
