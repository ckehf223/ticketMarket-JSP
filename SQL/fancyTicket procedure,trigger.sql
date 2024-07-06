--customer 프로시저
----------------------------------------------------------------------------
--고객 회원가입 프로시저
create or replace procedure ct_signin_proc(
vct_id customer.ct_id%type,
vct_pw customer.ct_pw%type,
vct_name customer.ct_name%type,
vct_age customer.ct_age%type,
vct_birth customer.ct_birth%type,
vct_email customer.ct_email%type,
vct_phone customer.ct_phone%type,
vct_address customer.ct_address%type)
is
begin
    insert into customer (ct_no,ct_id,ct_pw,ct_name,ct_age,ct_birth,ct_email,ct_phone,ct_address)
    values(customer_seq.nextval,vct_id,vct_pw,vct_name,vct_age,vct_birth,vct_email,vct_phone,vct_address);
    commit;
end;
/
----------------------------------------------------------------------------
--회원 정보 수정 프로시저
create or replace procedure ct_update_proc(
vct_pw in customer.ct_pw%type,
vct_email in customer.ct_email%type,
vct_phone in customer.ct_phone%type,
vct_address in customer.ct_address%type,
vct_id in customer.ct_id%type)
is
begin
    update customer set ct_pw=vct_pw,ct_email = vct_email, ct_phone=vct_phone, ct_address=vct_address where ct_id=vct_id;
    commit;
end;
/
---------------------------------------------------------------------
--결제 후 고객 정보 업데이트 프로시저
create or replace procedure cart_payment_customer_proc(
vct_grade in customer.ct_grade%type,
vct_saleratio in customer.ct_saleratio%type,
vct_totalamount in customer.ct_totalamount%type,
vct_mileage in customer.ct_mileage%type,
vct_mileageratio in customer.ct_mileageratio%type,
vct_id in customer.ct_id%type)
is
begin
    update customer 
    set ct_grade=vct_grade,ct_saleratio=vct_saleratio,ct_totalamount=vct_totalamount,ct_mileage=vct_mileage,ct_mileageratio=vct_mileageratio where ct_id=vct_id;
    commit;
end;
/
----------------------------------------------------------------------------
--공연 프로시저
----------------------------------------------------------------------------
--검색한 공연 가져오기 프로시져
CREATE OR REPLACE PROCEDURE search_pfList(
TEXT IN nVARCHAR2,
VCURSOR OUT SYS_REFCURSOR)
is
begin
    OPEN VCURSOR FOR
    select * from performance where (pf_name like '%'||TEXT||'%' or pf_genre like '%'||TEXT||'%' or pf_venue like '%'||TEXT||'%');
end;
/
-----------------------------------------------------------------------------
--관리자 공연리스트 가져오기
CREATE OR REPLACE PROCEDURE get_pfList(
VCURSOR OUT SYS_REFCURSOR)
is
begin
    OPEN VCURSOR FOR
    select  p.pf_no,p.pf_id,p.pf_name,p.pf_genre,p.pf_venue,p.pf_date,p.pf_limitage,p.pf_totalseats,p.pf_price,p.pf_imageurl,p.pf_allowcheck,nvl(pp.quantity,0) quantity
    from performance p left outer join (select pf_id,sum(cart_quantity) as quantity from payment
    group by pf_id) pp on p.pf_id = pp.pf_id order by pf_no asc;
end;
/
----------------------------------------------------------------------------
--관리자 공연정보 수정 프로시저
CREATE OR REPLACE PROCEDURE PF_UPDATE_PROC(
VPF_ID IN performance.pf_id%type,
VPF_NAME IN PERFORMANCE.PF_NAME%TYPE,
VPF_GENRE IN PERFORMANCE.PF_GENRE%TYPE,
VPF_DATE IN PERFORMANCE.PF_DATE%TYPE,
VPF_VENUE IN PERFORMANCE.PF_VENUE%TYPE,
VPF_LIMITAGE IN PERFORMANCE.PF_LIMITAGE%TYPE,
VPF_TOTALSEATS IN PERFORMANCE.PF_TOTALSEATS%TYPE,
VPF_PRICE IN PERFORMANCE.PF_PRICE%TYPE,
VPF_IMAGEURL IN PERFORMANCE.PF_IMAGEURL%TYPE,
VPF_ALLOWCHECK IN PERFORMANCE.PF_ALLOWCHECK%TYPE
)
IS
BEGIN
    update performance 
    set PF_NAME = VPF_NAME,PF_GENRE= VPF_GENRE,PF_DATE=VPF_DATE,PF_VENUE = VPF_VENUE,
    PF_LIMITAGE = VPF_LIMITAGE,PF_TOTALSEATS = VPF_TOTALSEATS, PF_PRICE = VPF_PRICE,PF_IMAGEURL = VPF_IMAGEURL,PF_ALLOWCHECK = VPF_ALLOWCHECK
    WHERE PF_ID=VPF_ID;
    COMMIT;
END;
/
----------------------------------------------------------------------------
--공연 추가 프로시저
CREATE OR REPLACE PROCEDURE PF_ADD_PROC(
VPF_ID IN performance.pf_id%type,
VPF_NAME IN PERFORMANCE.PF_NAME%TYPE,
VPF_GENRE IN PERFORMANCE.PF_GENRE%TYPE,
VPF_DATE IN PERFORMANCE.PF_DATE%TYPE,
VPF_VENUE IN PERFORMANCE.PF_VENUE%TYPE,
VPF_LIMITAGE IN PERFORMANCE.PF_LIMITAGE%TYPE,
VPF_TOTALSEATS IN PERFORMANCE.PF_TOTALSEATS%TYPE,
VPF_PRICE IN PERFORMANCE.PF_PRICE%TYPE,
VPF_IMAGEURL IN PERFORMANCE.PF_IMAGEURL%TYPE
)
IS
BEGIN
    insert into performance 
    values (performance_seq.nextval,VPF_ID,VPF_NAME,VPF_GENRE,VPF_DATE,VPF_VENUE,VPF_LIMITAGE,VPF_TOTALSEATS,VPF_PRICE,VPF_IMAGEURL,'A',1);
    COMMIT;
END;
/
----------------------------------------------------------------------------
--공연 삭제 하는 것 처럼 공연의 예매허용 NUMBER를 UPDATE 해준다.
CREATE OR REPLACE PROCEDURE PF_DELETE_PROC(
VPF_NO IN PERFORMANCE.PF_NO%TYPE)
IS
BEGIN
    UPDATE PERFORMANCE SET PF_ALLOWCHECK = 0 WHERE PF_NO=VPF_NO;
    commit;
END;
/
----------------------------------------------------------------------------
--공연 판매수량 순 랭킹 10개  가져오기 프로시져
CREATE OR REPLACE PROCEDURE getRank_topTenProc(
VCURSOR OUT SYS_REFCURSOR)
is
begin
    OPEN VCURSOR FOR
    select p.pf_no,p.pf_name,p.pf_imageurl,p.pf_venue,p.pf_date from performance p left outer join (select distinct pf_id, sum(cart_quantity) as quantity from payment 
    group by pf_id order by quantity desc) pp  on p.pf_id = pp.pf_id where rownum <= 10;
end;
/
--------------------------------------------------------------------------------
--카트 프로시저
--------------------------------------------------------------------------------
--예매내역 확인 프로시저
CREATE OR REPLACE PROCEDURE CART_LIST(
VCT_ID IN CUSTOMER.CT_ID%TYPE,
VCURSOR OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN VCURSOR FOR
    select p.pf_no,P.pf_id,P.pf_name,P.pf_venue,P.pf_date,p.pf_genre,p.pf_imageurl ,C.seat_location,c.cart_quantity,c.cart_totalprice,c.cart_no
    from cart C inner join performance P on C.pf_id = P.pf_id where C.ct_id=VCT_ID and payment_check=0;
END;
/
----------------------------------------------------------------------------
--공연 예매 프로시저
create or replace procedure cart_add_proc(
vct_id in cart.ct_id%type,
vpf_id in cart.pf_id%type,
vcq in cart.cart_quantity%type,
vct in cart.cart_totalprice%type,
vsl in cart.seat_location%type)
is
begin
    insert into cart values(cart_seq.nextval,vct_id,vpf_id,vcq,vct,vsl,0);
    commit;
end;
/
---------------------------------------------------------------------------
--예매내역 지정 삭제 프로시저
CREATE OR REPLACE PROCEDURE CART_DELETEONE_PROC(
VCT_NO IN CART.CART_NO%TYPE)
IS
BEGIN
    DELETE FROM CART WHERE CART_NO = VCT_NO;
    COMMIT;
END;
/
-------------------------------------------------------------------------
--예매내역 전체 삭제 프로시저
create or replace procedure cart_delete_proc(
vct_id customer.ct_id%type)
is
begin
    delete from cart where ct_id = vct_id and payment_check=0;
    commit;
end;
/
----------------------------------------------------------------------------
--예매공연 결제하기 프로시저
create or replace procedure cart_payment_proc(
vct_id in cart.ct_id%type)
is
begin
    update cart set payment_check=1 where ct_id = vct_id and payment_check=0;
    commit;
end;
/
--------------------------------------------------------------------------------
--seat 트리거
--------------------------------------------------------------------------------
--공연 예매시 좌석 정보 저장 트리거
create or replace trigger cart_add_seat_trg
    after insert on cart
    for each row
begin
    insert into seat values(seat_seq.nextval,:new.pf_id,:new.seat_location);
end;
/
--------------------------------------------------------------------------------
--결제내역 
----------------------------------------------------------------------------
--결제후 결제내역 insert 프로시저
create or replace procedure add_payment(
vcart_no in payment.cart_no%type,
vct_id in payment.ct_id%type,
vpf_id in payment.pf_id%type,
vcart_quantity in payment.cart_quantity%type,
vseat_location in payment.seat_location%type,
vcart_totalprice in payment.cart_totalprice%type,
vdelivery_address in payment.delivery_address%type,
vdelivery_name in payment.delivery_name%type,
vdelivery_message in payment.delivery_message%type,
vdelivery_date in payment.delivery_date%type)
is
begin
    insert into payment 
    values(payment_seq.nextval,vcart_no,vct_id,vpf_id,vcart_quantity,vseat_location,
    vcart_totalprice,vdelivery_address,vdelivery_name,vdelivery_message,vdelivery_date);
end;
/
----------------------------------------------------------------------------
--결제내역 확인 프로시저
CREATE OR REPLACE PROCEDURE PAYMENT_LIST(
VCT_ID IN CUSTOMER.CT_ID%TYPE,
VCURSOR OUT SYS_REFCURSOR)
IS
BEGIN
    OPEN VCURSOR FOR
    select P.pf_id,P.pf_name,P.pf_venue,P.pf_date,p.pf_genre,p.pf_imageurl,
    C.seat_location,c.cart_quantity,c.cart_totalprice,c.delivery_address,
    c.delivery_name,nvl(c.delivery_message,' ') delivery_message, c.delivery_date
    from payment c inner join performance P on C.pf_id = P.pf_id where C.ct_id=VCT_ID;
END;
/
----------------------------------------------------------------------------
--공지사항 프로시저
----------------------------------------------------------------------------
--관리자 공지사항 추가 프로시저
CREATE OR REPLACE PROCEDURE ADD_NOTICE_PROC(
VTITLE IN NOTICE.TITLE%TYPE,
VCONTENT IN NOTICE.CONTENT%TYPE)
IS
BEGIN
    INSERT INTO NOTICE VALUES(NOTICE_SEQ.NEXTVAL,VTITLE,VCONTENT,SYSDATE,0);
END;
/
----------------------------------------------------------------------------
--관리자 공지사항 수정 프로시저
CREATE OR REPLACE PROCEDURE UPDATE_NOTICE_PROC(
VNUM IN NOTICE.NO%TYPE,
VTITLE IN NOTICE.TITLE%TYPE,
VCONTENT IN NOTICE.CONTENT%TYPE)
IS
BEGIN
    UPDATE NOTICE SET TITLE=VTITLE,CONTENT=VCONTENT WHERE NO=VNUM;
END;
/
----------------------------------------------------------------------------
--관리자 공지사항 삭제 프로시저
CREATE OR REPLACE PROCEDURE UPDATE_NOTICE_PROC(
VNUM IN NOTICE.NO%TYPE,
VTITLE IN NOTICE.TITLE%TYPE,
VCONTENT IN NOTICE.CONTENT%TYPE)
IS
BEGIN
    UPDATE NOTICE SET TITLE=VTITLE,CONTENT=VCONTENT WHERE NO=VNUM;
END;
/
-------------------------------------------------------------------------------
--댓글 프로시저
----------------------------------------------------------------------------
--새로운 댓글 추가 프로시저
CREATE OR REPLACE PROCEDURE ADD_COMMENT(
VN_NUM IN NOTICE_COMMENT.N_NUM%TYPE,
VID IN NOTICE_COMMENT.ID%TYPE,
VCONTENT IN NOTICE_COMMENT.CONTENT%TYPE,
VNO OUT NOTICE_COMMENT.NO%TYPE
)
IS
BEGIN
    INSERT INTO notice_comment VALUES (comment_seq.nextval,VN_NUM,VID,VCONTENT,sysdate) RETURNING NO INTO VNO;
END;
/
------------------------------------------------------------------------------
--관리자 프로시저
-----------------------------------------------------------------------------
--관리자 대쉬보드 상위 3개 공연 가져오기
CREATE OR REPLACE PROCEDURE get_rankPf_proc(
VCURSOR OUT SYS_REFCURSOR)
is
begin
    OPEN VCURSOR FOR
    select p.pf_name,p.pf_imageurl from performance p,(select distinct pf_id, sum(cart_totalPrice) as price from payment 
    group by pf_id order by price desc) pp where p.pf_id = pp.pf_id and rownum <= 3;
end;
/