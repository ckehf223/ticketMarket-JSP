create table customer(
    ct_no number not null,
    ct_id varchar2(15) not null,
    ct_pw varchar2(60) not null,
    ct_name nvarchar2(8) not null,
    ct_age number not null,
    ct_birth varchar2(50) not null,
    ct_email varchar2(50) not null,
    ct_phone char(11) not null,
    ct_address nvarchar2(100) not null,
    ct_grade varchar2(8) default 'SILVER' not null,
    ct_saleratio number default 0.03 not null,
    ct_totalamount number default 0,
    ct_mileage number default 0,
    ct_mileageratio number default 0.04 not null
);
alter table customer add constraint customer_PK primary key (ct_no);
alter table customer add constraint customer_id_UK unique (ct_id);

create sequence customer_seq
    start with 1
    increment by 1;
desc customer;
--------------------------------------------------------------------------
create table performance(
    pf_no number not null,
    pf_id char(13) not null,
    pf_name nvarchar2(50) not null,
    pf_genre nvarchar2(10) not null,
    pf_date nvarchar2(50) not null,
    pf_venue nvarchar2(20) not null,
    pf_limitage number default 0 not null,
    pf_totalseats number not null,
    pf_price number not null,
    pf_imageurl varchar2(100) not null,
    pf_pageurl varchar2(100) not null,
    pf_allowCheck number(1) default 1
);
alter table performance add constraint performance_PK primary key (pf_no);
alter table performance add constraint performance_id_UK unique (pf_id);

create sequence performance_seq
    start with 1
    increment by 1;
desc performance;

-----------------------------------------------------------------------------
create table seat(
    seat_no number not null,
    pf_id char(13) not null,
    seat_location varchar2(100) not null
);

alter table seat add constraint seat_PK primary key (seat_no);
alter table seat add constraint performance_seat_FK foreign key (pf_id) 
REFERENCES performance (pf_id) on delete cascade;

create sequence seat_seq
    start with 1
    increment by 1;
    
desc seat;

-----------------------------------------------------------------------------
create table cart(
    cart_no number not null,
    ct_id varchar2(15) not null,
    pf_id char(13) not null,
    cart_quantity number not null,
    cart_totalprice number not null,
    seat_location varchar2(100) not null,
    payment_check number(1) default 0 not null
);

alter table cart add constraint cart_PK primary key (cart_no);
alter table cart add constraint performance_cart_FK foreign key (pf_id) 
REFERENCES performance (pf_id) on delete set null;
alter table cart add constraint customer_cart_FK foreign key (ct_id) 
REFERENCES customer (ct_id) on delete SET NULL;

create sequence cart_seq
    start with 1
    increment by 1;
 
-----------------------------------------------------------------------------
--결제내역
create table payment(
    payment_no number,
    cart_no number not null,
    ct_id varchar2(15) not null,
    pf_id char(13) not null,
    cart_quantity number not null,
    seat_location varchar2(30) not null,
    cart_totalprice number not null,
    delivery_address nvarchar2(100) not null,
    delivery_name nvarchar2(10) not null,
    delivery_message nvarchar2(100),
    delivery_date varchar2(50) not null
);
alter table payment add constraint payment_pf primary key (payment_no);
alter table payment add constraint cart_payment_fk 
foreign key (cart_no) references  cart (cart_no) on delete cascade;
alter table payment add constraint customer_payment_fk 
foreign key (ct_id) references  customer (ct_id) on delete cascade;
alter table payment add constraint performance_payment_fk 
foreign key (pf_id) references  performance (pf_id) on delete cascade;
create sequence payment_seq
    start with 1 
    increment by 1;   
    
------------------------------------------------------------------------------
--공지사항
CREATE TABLE NOTICE(
    NO NUMBER NOT NULL,
    ID VARCHAR2(20) NOT NULL,
    TITLE NVARCHAR2(500) NOT NULL,
    CONTENT VARCHAR2(4000),
    REGDATE TIMESTAMP NOT NULL,
    HITS NUMBER DEFAULT 0 NOT NULL
    );
    
ALTER TABLE NOTICE ADD CONSTRAINT NOTICE_PK PRIMARY KEY(NO);
CREATE SEQUENCE NOTICE_SEQ
    START WITH 1
    INCREMENT BY 1
    NOMAXVALUE
    NOCACHE;
    
------------------------------------------------------------------------------
--공지사항의 댓글
CREATE TABLE NOTICE_COMMENT(
    NO NUMBER NOT NULL,
    N_NUM NUMBER NOT NULL,
    ID VARCHAR2(20) NOT NULL,
    CONTENT VARCHAR2(500) NOT NULL,
    REGDATE TIMESTAMP NOT NULL
);

ALTER TABLE NOTICE_COMMENT ADD CONSTRAINT NOTICE_COMMENT_PK PRIMARY KEY(no);
ALTER TABLE NOTICE_COMMENT ADD CONSTRAINT NOTICE_COMMENT_FK FOREIGN KEY(N_NUM) REFERENCES NOTICE (NO) ON DELETE CASCADE;

commit;

------------------------------------------------------------------------------
--관리자 테이블
CREATE TABLE ADMIN(
    ID VARCHAR2(20) NOT NULL,
    PW VARCHAR2(70) NOT NULL
);
ALTER TABLE ADMIN ADD CONSTRAINT ADMIN_PK PRIMARY KEY (ID);
select * from admin;
-----------------------------------------------------------------------------
