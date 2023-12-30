-- 샘플 authority
INSERT INTO hot_role (role_name) VALUES
                                     ('ROLE_MEMBER'), ('ROLE_ADMIN');


INSERT INTO hot_user (username, nickname, password, email)
VALUES
    ('USER7', '회원7', '$2a$10$6gVaMy7.lbezp8bGRlV2fOArmA3WAk2EHxSKxncnzs28/m3DXPyA2', 'user7@mail.com'),
    ('USER8', '회원8', '$2a$10$7LTnvLaczZbEL0gabgqgfezQPr.xOtTab2NAF/Yt4FrvTSi0Y29Xa',  'user8@mail.com'),
    ('USER9', '회원9', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi',  'user9@mail.com'),
    ('USER4', '회원4', '$2a$10$6gVaMy7.lbezp8bGRlV2fOArmA3WAk2EHxSKxncnzs28/m3DXPyA2', 'user4@mail.com'),
    ('USER5', '회원5', '$2a$10$7LTnvLaczZbEL0gabgqgfezQPr.xOtTab2NAF/Yt4FrvTSi0Y29Xa',  'user5@mail.com'),
    ('USER6', '회원6', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi',  'user6@mail.com'),
    ('ADMIN3', '관리자3', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi',  'admin3@mail.com'),
    ('ADMIN2', '관리자2', '$2a$10$53OEi/JukSMPr3z5RQBFH.z0TCYSUDPtxf1/8caRyRVdDNdHA9QHi',  'admin2@mail.com')
;

-- 샘플 사용자-권한
INSERT INTO hot_user_role VALUES
#                               (1, 1),
#                               (1, 2),
#                               (1, 3),
#                               (1, 4),
                              (1, 5),
                              (1, 6),
                              (1, 7),
                              (1, 8),
                              (2, 7),
                              (2, 8)
;


SELECT * FROM hot_post;
-- 게시물 샘플데이터
INSERT INTO hot_post (userId, tour_id, camping_id,  category, subject, content, visibility, viewcnt , regDate, title, img )
VALUES

    (ROUND(RAND() * 8 + 1), ROUND(RAND() * 99 + 1), null, 12, '제목입니다1', '내용입니다1', 'public', ROUND(RAND() * 99 + 1), now(), '검색제목입니다1', '이미지입니다1' ),
    (ROUND(RAND() * 8 + 1), ROUND(RAND() * 99 + 1), null, 32, '제목입니다2', '내용입니다2', 'public', ROUND(RAND() * 99 + 1), now(), '검색제목입니다2', '이미지입니다2' ),
    (ROUND(RAND() * 8 + 1), ROUND(RAND() * 99 + 1), null, 39, '제목입니다3', '내용입니다3', 'public', ROUND(RAND() * 99 + 1), now(), '검색제목입니다3', '이미지입니다3' ),
    (ROUND(RAND() * 8 + 1), ROUND(RAND() * 99 + 1), null, 38, '제목입니다4', '내용입니다4', 'public', ROUND(RAND() * 99 + 1), now(), '검색제목입니다4', '이미지입니다4' ),
    (ROUND(RAND() * 8 + 1), ROUND(RAND() * 99 + 1), null, 28, '제목입니다5', '내용입니다5', 'public', ROUND(RAND() * 99 + 1), now(), '검색제목입니다5', '이미지입니다5' ),
    (ROUND(RAND() * 8 + 1), ROUND(RAND() * 99 + 1), null, 15, '제목입니다6', '내용입니다6', 'public', ROUND(RAND() * 99 + 1), now(), '검색제목입니다6', '이미지입니다6' ),
    (ROUND(RAND() * 8 + 1), ROUND(RAND() * 99 + 1), null, 14, '제목입니다7', '내용입니다7', 'public', ROUND(RAND() * 99 + 1), now(), '검색제목입니다7', '이미지입니다7' ),
    (ROUND(RAND() * 8 + 1), null, ROUND(RAND() * 99 + 1), 40, '제목입니다8', '내용입니다8', 'public', ROUND(RAND() * 99 + 1), now(), '검색제목입니다8', '이미지입니다8' )
;

-- 투어 추천수 샘플데이터
replace INTO hot_tour_recommend
    (SELECT FLOOR( 1 + RAND() * 30 ), FLOOR(1 + RAND() * (SELECT count(*) FROM hot_tour_mysql))  FROM hot_tour_mysql);

-- 캠핑 추천수 샘플데이터
replace INTO hot_camping_recommendCount
    (SELECT FLOOR( 1 + RAND() * 30 ), FLOOR(1 + RAND() * (SELECT count(*) FROM hot_camping))  FROM hot_camping);


-- 투어 조회수 샘플데이터
update hot_tour_mysql set viewcnt=ROUND(RAND() * 99 + 1) ;


-- 캠핑 조회수 샘플데이터
update hot_camping set viewcnt=ROUND(RAND() * 99 + 1) ;
