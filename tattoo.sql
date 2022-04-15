create table users(
	uid varchar(300) primary key,
    pw varchar(300) not null,
    uname varchar(300) not null,
    ubirth varchar(100) not null,
    uauth int default 0 -- 0이면 일반유저, 1이면 타투이스트 --
);

create table article(
	aid int primary key auto_increment,
    uid varchar(300) not null,
    uname varchar(300) not null,
    title varchar(500) not null,
    acontent varchar(10000) not null,
    part varchar(300) not null,
    leadTime varchar(300) not null,
    addr varchar(300) not null,
    cnt int default 0,
    fav int default 0,
    rcnt int default 0,
    adate TIMESTAMP DEFAULT NOW(),
    filename varchar(1000),
    foreign key (uid) references users(uid) on delete cascade
);

create table comment(
	cid int primary key auto_increment,
    uid varchar(300) not null,
    aid int not null,
    ccontent varchar(1000) not null,
    ccheck int default 0, -- 0이면 일반댓글, 1이면 대댓글 --
    cgroup int,
    cdate TIMESTAMP DEFAULT NOW(),
    foreign key (uid) references users(uid) on delete cascade,
    foreign key (aid) references article(aid) on delete cascade
);

create table fav(
	fid varchar(300) primary key,
    aid int not null,
    uid varchar(300) not null,
    foreign key (aid) references article(aid) on delete cascade,
    foreign key (uid) references users(uid) on delete cascade
);

-- 샘플 데이터 --
insert into users values('admin1','1234','ink 김새롬','1988-03-05', 1);
insert into users values('admin2','1234', '김상수', '1996-12-30', 1);
insert into users values('admin3', '1234', '미래타투 최승환', '1995-11-11', 1);
insert into users values('user1', '1234', '오상희', '2002-05-07', 0);
insert into users values('user2', '1234', '이민환', '2001-11-30', 0);
insert into users values('user3', '1234', '황서윤', '1997-02-03', 0);

insert into article (uid, uname, title, acontent, part, leadTime, addr, fav, rcnt, filename) values ('admin1', 'ink 김새롬', '감성 꽃 타투', '선호하시는 색상으로 분위기 있게 컬러 작업 해드렸습니다^^', '팔', '1시간', '서울특별시 마포구', 115, 2, '1.png');
insert into article (uid, uname, title, acontent, part, leadTime, addr, fav, rcnt, filename) values ('admin2', '김상수', '생일 레터링', '사랑하는 가족의 생일을 레터링으로 예쁘게 새겨보세요', '팔', '2시간', '경기도 안산시 단원구',23, 4, '2.png');
insert into article (uid, uname, title, acontent, part, leadTime, addr, fav, rcnt, filename) values ('admin3', '미래타투 최승환', '곰돌이 타투', '고객님께서 참으로 만족하신 작업이었습니다!', '팔', '3시간', '제주시 오등동', 267,1 ,'3.png');
insert into article (uid, uname, title, acontent, part, leadTime, addr, fav, rcnt, filename) values ('admin1', 'ink 김새롬', '감성 꽃 타투',  '떨어지는 꽃잎이 포인트인 디자인 입니다^^', '쇄골', '그 외', '서울특별시 마포구',21,4 ,'4.png');
insert into article (uid, uname, title, acontent, part, leadTime, addr, fav, rcnt, filename) values ('admin1', 'ink 김새롬', '심플 나비', '간단해서 작업시간이 길지 않았어요! 후기 사진 수락해주신 고객님 정말 감사드립니다~', '쇄골', '1시간', '서울특별시 마포구',17,0 ,'5.png');
insert into article (uid, uname, title, acontent, part, leadTime, addr, fav, rcnt, filename) values ('admin2', '김상수', '강렬한 디자인을 찾으시나요?', '원하시는 사이즈에 맞춰 강렬한 느낌을 줄 수 있는 디자인입니다', '다리', '그 외', '경기도 안산시 단원구',45,2 ,'6.png');
insert into article (uid, uname, title, acontent, part, leadTime, addr, fav, rcnt, filename) values ('admin2', '김상수', '반달 꽃', '모던한 디자인을 찾으신다면 이런 느낌은 어떠신가요?', '발목', '3시간', '경기도 안산시 단원구',1,1 ,'7.png');
insert into article (uid, uname, title, acontent, part, leadTime, addr, fav, rcnt, filename) values ('admin3', '미래타투 최승환', '귀여운 공룡', '이런 도안을 작업할때면 귀여워서 정말 행복하답니다!', '팔', '3시간', '제주시 오등동',6,2 ,'8.png');
insert into article (uid, uname, title, acontent, part, leadTime, addr, fav, filename) values ('admin3', '미래타투 최승환', '심플타투', '귀여우면서 심플한 것 까지 ! 볼수록 귀엽지 않나요? 갑작스런 요청에도 흔쾌히 후기촬영을 보내주신 고객님께 정말 감사드립니다', '손등', '1시간', '제주시 오등동',2, '9.png');


insert into comment (uid, aid, ccontent) values ('user1',1,'손목에 예쁜 꽃이 폈네요!!');
insert into comment (uid, aid, ccontent) values ('admin1',2,'오 느낌 있는데요?');
insert into comment (uid, aid, ccontent) values ('user3',2, '뭔가 뭉클하다ㅠㅠ');
insert into comment (uid, aid, ccontent) values ('user2',6,'허벅지에 작업 문의 드리고 싶습니다!');
insert into comment (uid, aid, ccontent) values ('user1',4,'와 역시 믿고보는 새롬님 작업이네요ㅠㅠ');
insert into comment (uid, aid, ccontent) values ('user2',4,'예쁘게 작업 해주셔서 정말 감사합니다 새롬님!!!');
insert into comment (uid, aid, ccontent) values ('user1',3,'앗 귀엽다...');
insert into comment (uid, aid, ccontent) values ('user2',8,'그림에서 망충미가 뿜어져 나오고 있어요ㅜㅜㅜㅜ');
insert into comment (uid, aid, ccontent) values ('user3',7,'작업시간 정확히 3시간인가요??');

insert into comment (uid, aid, ccontent, ccheck, cgroup) values ('admin1',1,'예쁘게 봐주셔서 감사합니다!',1,1);
insert into comment (uid, aid, ccontent, ccheck, cgroup) values ('admin2',2, '그쵸? 시계 모양으로 작업해봤어요 :)',1,2);
insert into comment (uid, aid, ccontent, ccheck, cgroup) values ('admin1',2,'다른 부위에도 작업 가능한가요?',1,2);
insert into comment (uid, aid, ccontent, ccheck, cgroup) values ('admin1',4,'아ㅠㅠㅠ매번 좋은 댓글 정말 감사드려요 덕분에 게시글 올릴 힘이 납니다ㅎㅎ',1,5);
insert into comment (uid, aid, ccontent, ccheck, cgroup) values ('admin2',6,'010-2342-5354 연락처로 편하게 문자 남겨주세요 !',1,4);
insert into comment (uid, aid, ccontent, ccheck, cgroup) values ('admin1',4,'만족하신 것 같아 정말 다행입니다~~ㅎㅎ',1,6);
insert into comment (uid, aid, ccontent, ccheck, cgroup) values ('user1',8 ,'실제로 보면 더 귀여워요ㅠㅠ',1,8);

insert into fav values('user1_1', 1, 'user1');
insert into fav values('user1_2', 2, 'user1');
insert into fav values('user1_3', 3, 'user1');
insert into fav values('user1_4', 4, 'user1');
insert into fav values('user1_5', 5, 'user1');
insert into fav values('user1_6', 6, 'user1');
insert into fav values('user1_7', 7, 'user1');
insert into fav values('user1_8', 8, 'user1');
insert into fav values('user1_9', 9, 'user1');
insert into fav values('user2_9', 9, 'user2');
insert into fav values('user2_5', 5, 'user2');
insert into fav values('user2_4', 4, 'user2');
insert into fav values('user2_3', 3, 'user2');
insert into fav values('user3_6', 6, 'user3');
insert into fav values('user3_4', 4, 'user3');
insert into fav values('user3_3', 3, 'user3');
insert into fav values('user3_8', 8, 'user3');
insert into fav values('admin2_1', 1, 'admin2');
insert into fav values('admin1_2', 2, 'admin1');
insert into fav values('admin3_3', 3, 'admin3');
insert into fav values('admin1_3', 3, 'admin1');
insert into fav values('admin2_3', 3, 'admin2');

-- 테이블 관리 --
select * from users;
select * from article;
select * from comment;
select * from fav;

drop table fav;
drop table comment;
drop table article;
drop table users;
