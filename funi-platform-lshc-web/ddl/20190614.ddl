--创建小区表自增长序号使用的序列
create sequence seq_build_serial
increment by 1
start with 1
nomaxvalue
nocycle
nocache;

--创建触发器
CREATE
	OR REPLACE TRIGGER build_serial_no
BEFORE INSERT ON LSHC_BUILD_INFO
FOR each ROW
BEGIN
	SELECT
		SEQ_BUILD_SERIAL.nextval INTO :new.SERIAL_NO
FROM
	dual;
	END build_serial_no;
