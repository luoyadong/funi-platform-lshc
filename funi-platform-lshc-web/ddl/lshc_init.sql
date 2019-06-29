/*
 Navicat Premium Data Transfer

 Source Server         : EIS
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 172.29.251.206:1521
 Source Schema         : EISTRONG

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 29/06/2019 16:38:49
*/


-- ----------------------------
-- Table structure for LSHC_BUILD_INFO
-- ----------------------------
DROP TABLE "EISTRONG"."LSHC_BUILD_INFO";
CREATE TABLE "EISTRONG"."LSHC_BUILD_INFO" (
  "ID" VARCHAR2(200 BYTE) NOT NULL ,
  "SERIAL_NO" NUMBER(8) ,
  "REGION" VARCHAR2(100 BYTE) ,
  "STREET" VARCHAR2(100 BYTE) ,
  "PROJECT_NAME" VARCHAR2(100 BYTE) ,
  "MAP_CODE" VARCHAR2(100 BYTE) ,
  "ADDRESS" VARCHAR2(255 BYTE) ,
  "COMMON" VARCHAR2(128 BYTE) ,
  "VERSION" NUMBER(5) ,
  "CREATE_TIME" TIMESTAMP(6) ,
  "CREATE_ID" VARCHAR2(50 BYTE) ,
  "UPDATE_TIME" TIMESTAMP(6) ,
  "UPDATE_ID" VARCHAR2(50 BYTE) ,
  "DELETED" NUMBER(1) ,
  "ISVALIDE" VARCHAR2(2 BYTE) ,
  "BUILD_NAME" VARCHAR2(255 BYTE) ,
  "COMMUNITY_NAME" VARCHAR2(255 BYTE) 
)
TABLESPACE "TBS_EISTRONG"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "EISTRONG"."LSHC_BUILD_INFO"."ID" IS 'id';
COMMENT ON COLUMN "EISTRONG"."LSHC_BUILD_INFO"."SERIAL_NO" IS '序号';
COMMENT ON COLUMN "EISTRONG"."LSHC_BUILD_INFO"."REGION" IS '区县';
COMMENT ON COLUMN "EISTRONG"."LSHC_BUILD_INFO"."STREET" IS '街道';
COMMENT ON COLUMN "EISTRONG"."LSHC_BUILD_INFO"."PROJECT_NAME" IS '项目名称';
COMMENT ON COLUMN "EISTRONG"."LSHC_BUILD_INFO"."MAP_CODE" IS '楼栋地图编号';
COMMENT ON COLUMN "EISTRONG"."LSHC_BUILD_INFO"."ADDRESS" IS '实际地址';
COMMENT ON COLUMN "EISTRONG"."LSHC_BUILD_INFO"."COMMON" IS '备用';
COMMENT ON COLUMN "EISTRONG"."LSHC_BUILD_INFO"."VERSION" IS '版本号';
COMMENT ON COLUMN "EISTRONG"."LSHC_BUILD_INFO"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_BUILD_INFO"."CREATE_ID" IS '创建者id';
COMMENT ON COLUMN "EISTRONG"."LSHC_BUILD_INFO"."UPDATE_TIME" IS '修改时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_BUILD_INFO"."UPDATE_ID" IS '修改者id';
COMMENT ON COLUMN "EISTRONG"."LSHC_BUILD_INFO"."DELETED" IS '逻辑删除';
COMMENT ON COLUMN "EISTRONG"."LSHC_BUILD_INFO"."ISVALIDE" IS '数据是否有效';
COMMENT ON COLUMN "EISTRONG"."LSHC_BUILD_INFO"."BUILD_NAME" IS '楼栋名称';
COMMENT ON COLUMN "EISTRONG"."LSHC_BUILD_INFO"."COMMUNITY_NAME" IS '社区名称';
COMMENT ON TABLE "EISTRONG"."LSHC_BUILD_INFO" IS '楼栋信息';

-- ----------------------------
-- Table structure for LSHC_DICTIONARY
-- ----------------------------
DROP TABLE "EISTRONG"."LSHC_DICTIONARY";
CREATE TABLE "EISTRONG"."LSHC_DICTIONARY" (
  "ID" VARCHAR2(200 BYTE) DEFAULT SYS_GUID()  NOT NULL ,
  "NAME" VARCHAR2(200 BYTE) ,
  "FORMER_NAME" VARCHAR2(200 BYTE) ,
  "CONFIG_TYPE" VARCHAR2(200 BYTE) ,
  "PID" NUMBER ,
  "ISONLINE" NUMBER ,
  "NOTE" VARCHAR2(2000 BYTE) 
)
TABLESPACE "TBS_EISTRONG"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Table structure for LSHC_ENT_INFO
-- ----------------------------
DROP TABLE "EISTRONG"."LSHC_ENT_INFO";
CREATE TABLE "EISTRONG"."LSHC_ENT_INFO" (
  "ID" VARCHAR2(200 BYTE) NOT NULL ,
  "HC_ID" VARCHAR2(200 BYTE) ,
  "ENT_NAME" VARCHAR2(200 BYTE) ,
  "SEX" VARCHAR2(10 BYTE) ,
  "ENT_TYPE" VARCHAR2(50 BYTE) ,
  "ENT_NATION" VARCHAR2(20 BYTE) ,
  "ENT_NATIVE" VARCHAR2(64 BYTE) ,
  "TEL" VARCHAR2(15 BYTE) ,
  "MARRIAGE_STATUS" VARCHAR2(10 BYTE) ,
  "ID_TYPE" VARCHAR2(50 BYTE) ,
  "ID_NO" VARCHAR2(50 BYTE) ,
  "CAREER" VARCHAR2(64 BYTE) ,
  "VERSION" NUMBER(5) ,
  "CREATE_TIME" TIMESTAMP(6) ,
  "CREATE_ID" VARCHAR2(50 BYTE) ,
  "UPDATE_TIME" TIMESTAMP(6) ,
  "UPDATE_ID" VARCHAR2(50 BYTE) ,
  "DELETED" NUMBER(1) ,
  "ISVALIDE" VARCHAR2(2 BYTE) 
)
TABLESPACE "TBS_EISTRONG"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."ID" IS 'id';
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."HC_ID" IS '普查编号';
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."ENT_NAME" IS '姓名';
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."SEX" IS '性别';
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."ENT_TYPE" IS '人员类别';
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."ENT_NATION" IS '民族';
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."ENT_NATIVE" IS '籍贯';
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."TEL" IS '联系电话';
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."MARRIAGE_STATUS" IS '婚姻状态';
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."ID_TYPE" IS '证件类型';
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."ID_NO" IS '证件号码';
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."CAREER" IS '职业';
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."VERSION" IS '版本号';
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."CREATE_ID" IS '创建者id';
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."UPDATE_TIME" IS '修改时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."UPDATE_ID" IS '修改者id';
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."DELETED" IS '逻辑删除';
COMMENT ON COLUMN "EISTRONG"."LSHC_ENT_INFO"."ISVALIDE" IS '数据是否有效';
COMMENT ON TABLE "EISTRONG"."LSHC_ENT_INFO" IS '人口信息';

-- ----------------------------
-- Table structure for LSHC_FILE
-- ----------------------------
DROP TABLE "EISTRONG"."LSHC_FILE";
CREATE TABLE "EISTRONG"."LSHC_FILE" (
  "ID" VARCHAR2(200 BYTE) NOT NULL ,
  "HC_ID" VARCHAR2(200 BYTE) ,
  "RIGHT_NO" VARCHAR2(64 BYTE) ,
  "FILE_NAME" VARCHAR2(255 BYTE) ,
  "FILE_SIZE" NUMBER(10,2) ,
  "FILE_TYPE" VARCHAR2(10 BYTE) ,
  "URL" VARCHAR2(255 BYTE) ,
  "SUBMIT_DATE" TIMESTAMP(6) ,
  "USER_NAME" VARCHAR2(128 BYTE) ,
  "UNIT_NAME" VARCHAR2(128 BYTE) ,
  "VERSION" NUMBER(5) ,
  "CREATE_TIME" TIMESTAMP(6) ,
  "CREATE_ID" VARCHAR2(50 BYTE) ,
  "UPDATE_TIME" TIMESTAMP(6) ,
  "UPDATE_ID" VARCHAR2(50 BYTE) ,
  "DELETED" NUMBER(1) ,
  "ISVALIDE" VARCHAR2(2 BYTE) 
)
TABLESPACE "TBS_EISTRONG"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "EISTRONG"."LSHC_FILE"."ID" IS 'id';
COMMENT ON COLUMN "EISTRONG"."LSHC_FILE"."HC_ID" IS '普查编号';
COMMENT ON COLUMN "EISTRONG"."LSHC_FILE"."RIGHT_NO" IS '产权证编号';
COMMENT ON COLUMN "EISTRONG"."LSHC_FILE"."FILE_NAME" IS '附件名称';
COMMENT ON COLUMN "EISTRONG"."LSHC_FILE"."FILE_SIZE" IS '附件大小';
COMMENT ON COLUMN "EISTRONG"."LSHC_FILE"."FILE_TYPE" IS '附件类型';
COMMENT ON COLUMN "EISTRONG"."LSHC_FILE"."URL" IS 'url';
COMMENT ON COLUMN "EISTRONG"."LSHC_FILE"."SUBMIT_DATE" IS '上传时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_FILE"."USER_NAME" IS '上传者账号';
COMMENT ON COLUMN "EISTRONG"."LSHC_FILE"."UNIT_NAME" IS '单位名称';
COMMENT ON COLUMN "EISTRONG"."LSHC_FILE"."VERSION" IS '版本号';
COMMENT ON COLUMN "EISTRONG"."LSHC_FILE"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_FILE"."CREATE_ID" IS '创建者id';
COMMENT ON COLUMN "EISTRONG"."LSHC_FILE"."UPDATE_TIME" IS '修改时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_FILE"."UPDATE_ID" IS '修改者id';
COMMENT ON COLUMN "EISTRONG"."LSHC_FILE"."DELETED" IS '逻辑删除';
COMMENT ON COLUMN "EISTRONG"."LSHC_FILE"."ISVALIDE" IS '数据是否有效';
COMMENT ON TABLE "EISTRONG"."LSHC_FILE" IS '附件信息';

-- ----------------------------
-- Table structure for LSHC_INDEX_NOTICE
-- ----------------------------
DROP TABLE "EISTRONG"."LSHC_INDEX_NOTICE";
CREATE TABLE "EISTRONG"."LSHC_INDEX_NOTICE" (
  "ID" VARCHAR2(200 BYTE) NOT NULL ,
  "NOTICE_TYPE" VARCHAR2(128 BYTE) ,
  "NOTICE_TITLE" VARCHAR2(255 BYTE) ,
  "CONTENT" CLOB ,
  "NOTICE_DATE" VARCHAR2(50 BYTE) ,
  "URL" VARCHAR2(255 BYTE) ,
  "COMMON" VARCHAR2(64 BYTE) ,
  "VERSION" NUMBER(5) ,
  "CREATE_TIME" TIMESTAMP(6) ,
  "CREATE_ID" VARCHAR2(50 BYTE) ,
  "UPDATE_TIME" TIMESTAMP(6) ,
  "UPDATE_ID" VARCHAR2(50 BYTE) ,
  "DELETED" NUMBER(1) ,
  "ISVALIDE" VARCHAR2(2 BYTE) 
)
TABLESPACE "TBS_EISTRONG"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "EISTRONG"."LSHC_INDEX_NOTICE"."ID" IS 'id';
COMMENT ON COLUMN "EISTRONG"."LSHC_INDEX_NOTICE"."NOTICE_TYPE" IS '公告类型';
COMMENT ON COLUMN "EISTRONG"."LSHC_INDEX_NOTICE"."NOTICE_TITLE" IS '公告标题';
COMMENT ON COLUMN "EISTRONG"."LSHC_INDEX_NOTICE"."CONTENT" IS '公告内容';
COMMENT ON COLUMN "EISTRONG"."LSHC_INDEX_NOTICE"."NOTICE_DATE" IS '公告日期';
COMMENT ON COLUMN "EISTRONG"."LSHC_INDEX_NOTICE"."URL" IS 'url';
COMMENT ON COLUMN "EISTRONG"."LSHC_INDEX_NOTICE"."COMMON" IS '备用';
COMMENT ON COLUMN "EISTRONG"."LSHC_INDEX_NOTICE"."VERSION" IS '版本号';
COMMENT ON COLUMN "EISTRONG"."LSHC_INDEX_NOTICE"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_INDEX_NOTICE"."CREATE_ID" IS '创建者id';
COMMENT ON COLUMN "EISTRONG"."LSHC_INDEX_NOTICE"."UPDATE_TIME" IS '修改时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_INDEX_NOTICE"."UPDATE_ID" IS '修改者id';
COMMENT ON COLUMN "EISTRONG"."LSHC_INDEX_NOTICE"."DELETED" IS '逻辑删除';
COMMENT ON COLUMN "EISTRONG"."LSHC_INDEX_NOTICE"."ISVALIDE" IS '数据是否有效';
COMMENT ON TABLE "EISTRONG"."LSHC_INDEX_NOTICE" IS '首页公告信息';

-- ----------------------------
-- Table structure for LSHC_JOB_ACCEPT
-- ----------------------------
DROP TABLE "EISTRONG"."LSHC_JOB_ACCEPT";
CREATE TABLE "EISTRONG"."LSHC_JOB_ACCEPT" (
  "ID" VARCHAR2(50 BYTE) NOT NULL ,
  "SERVICE_NUM" VARCHAR2(200 BYTE) ,
  "TYPE_NAME" VARCHAR2(50 BYTE) ,
  "STATUS" NUMBER ,
  "CUR_STATUS" VARCHAR2(50 BYTE) ,
  "ACCEPT_TIME" TIMESTAMP(6) ,
  "VERSION" NUMBER(5) ,
  "CREATE_TIME" DATE ,
  "CREATE_ID" VARCHAR2(50 BYTE) ,
  "UPDATE_TIME" DATE ,
  "UPDATE_ID" VARCHAR2(50 BYTE) ,
  "DELETED" NUMBER(1) ,
  "ISVALIDE" VARCHAR2(2 BYTE) 
)
TABLESPACE "TBS_EISTRONG"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_ACCEPT"."ID" IS '主键';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_ACCEPT"."SERVICE_NUM" IS '受理号';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_ACCEPT"."TYPE_NAME" IS '工作类型';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_ACCEPT"."STATUS" IS '状态';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_ACCEPT"."CUR_STATUS" IS '当前状态';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_ACCEPT"."ACCEPT_TIME" IS '受理时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_ACCEPT"."VERSION" IS '版本号';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_ACCEPT"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_ACCEPT"."CREATE_ID" IS '创建者id';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_ACCEPT"."UPDATE_TIME" IS '修改时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_ACCEPT"."UPDATE_ID" IS '修改者id';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_ACCEPT"."DELETED" IS '逻辑删除';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_ACCEPT"."ISVALIDE" IS '数据是否有效';
COMMENT ON TABLE "EISTRONG"."LSHC_JOB_ACCEPT" IS '工作日志';

-- ----------------------------
-- Table structure for LSHC_JOB_LOG
-- ----------------------------
DROP TABLE "EISTRONG"."LSHC_JOB_LOG";
CREATE TABLE "EISTRONG"."LSHC_JOB_LOG" (
  "ID" VARCHAR2(50 BYTE) NOT NULL ,
  "JOB_ACCEPT_ID" VARCHAR2(50 BYTE) ,
  "AUDIT_NAME" VARCHAR2(50 BYTE) ,
  "AUDIT_ID" VARCHAR2(50 BYTE) ,
  "AUDIT_RESULT" VARCHAR2(50 BYTE) ,
  "NODE_NAME" VARCHAR2(200 BYTE) ,
  "JOB_OPINION" VARCHAR2(2000 BYTE) ,
  "UNIT_NAME" VARCHAR2(200 BYTE) ,
  "COMMON" VARCHAR2(50 BYTE) ,
  "VERSION" NUMBER(5) ,
  "CREATE_TIME" DATE ,
  "CREATE_ID" VARCHAR2(50 BYTE) ,
  "UPDATE_TIME" DATE ,
  "UPDATE_ID" VARCHAR2(50 BYTE) ,
  "DELETED" NUMBER(1) ,
  "ISVALIDE" VARCHAR2(2 BYTE) 
)
TABLESPACE "TBS_EISTRONG"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_LOG"."ID" IS '主键';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_LOG"."JOB_ACCEPT_ID" IS '工作主表id';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_LOG"."AUDIT_NAME" IS '审核人';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_LOG"."AUDIT_ID" IS '审核人姓名';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_LOG"."AUDIT_RESULT" IS '结论';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_LOG"."NODE_NAME" IS '节点名称';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_LOG"."JOB_OPINION" IS '意见';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_LOG"."VERSION" IS '版本号';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_LOG"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_LOG"."CREATE_ID" IS '创建者id';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_LOG"."UPDATE_TIME" IS '修改时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_LOG"."UPDATE_ID" IS '修改者id';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_LOG"."DELETED" IS '逻辑删除';
COMMENT ON COLUMN "EISTRONG"."LSHC_JOB_LOG"."ISVALIDE" IS '数据是否有效';
COMMENT ON TABLE "EISTRONG"."LSHC_JOB_LOG" IS '工作日志明细';

-- ----------------------------
-- Table structure for LSHC_REGI_INFO
-- ----------------------------
DROP TABLE "EISTRONG"."LSHC_REGI_INFO";
CREATE TABLE "EISTRONG"."LSHC_REGI_INFO" (
  "ID" VARCHAR2(200 BYTE) NOT NULL ,
  "HOUSE_ID" VARCHAR2(200 BYTE) ,
  "SYS_CODE" VARCHAR2(20 BYTE) ,
  "HOUSE_STATUS" VARCHAR2(10 BYTE) ,
  "APPROVAL_STATUS" VARCHAR2(10 BYTE) ,
  "REGION" VARCHAR2(100 BYTE) ,
  "STREET" VARCHAR2(100 BYTE) ,
  "PROJECT_NAME" VARCHAR2(100 BYTE) ,
  "MAP_CODE" VARCHAR2(100 BYTE) ,
  "ESTATE_UNIT_NAME" VARCHAR2(50 BYTE) ,
  "ADDRESS_CITY" VARCHAR2(255 BYTE) ,
  "ADDRESS_REGION" VARCHAR2(255 BYTE) ,
  "ADDRESS_COUNTY" VARCHAR2(255 BYTE) ,
  "APT" VARCHAR2(64 BYTE) ,
  "BUILD_NO" VARCHAR2(64 BYTE) ,
  "UNIT_NO" VARCHAR2(64 BYTE) ,
  "LAYER" VARCHAR2(64 BYTE) ,
  "ROOM_NO" VARCHAR2(64 BYTE) ,
  "RIGHT_ADDR" VARCHAR2(255 BYTE) ,
  "HOUSE_AREA" NUMBER(15,2) ,
  "INNER_HOUSE_AREA" NUMBER(15,2) ,
  "HOUSE_ROOM" VARCHAR2(200 BYTE) ,
  "HOUSE_HALL" VARCHAR2(200 BYTE) ,
  "HOUSE_BATHROOM" VARCHAR2(200 BYTE) ,
  "IS_REGI" VARCHAR2(50 BYTE) ,
  "RIGHT_NO" VARCHAR2(64 BYTE) ,
  "BUILD_DATE" VARCHAR2(64 BYTE) ,
  "HOUSE_TYPE" VARCHAR2(50 BYTE) ,
  "HOUSE_STRUCTURE" VARCHAR2(50 BYTE) ,
  "HOUSE_USE" VARCHAR2(50 BYTE) ,
  "LAND_STATUS" VARCHAR2(50 BYTE) ,
  "PRE_SALE_NO" VARCHAR2(64 BYTE) ,
  "FIT_STATUS" VARCHAR2(50 BYTE) ,
  "IS_CHECK_IN" VARCHAR2(10 BYTE) ,
  "PERSON_NUM" NUMBER(8) ,
  "IS_RENT" VARCHAR2(10 BYTE) ,
  "RENT_START_DATE" VARCHAR2(64 BYTE) ,
  "RENT_END_DATE" VARCHAR2(64 BYTE) ,
  "ORG_CODE" VARCHAR2(64 BYTE) ,
  "ORG_NAME" VARCHAR2(255 BYTE) ,
  "UNIT_NAME" VARCHAR2(50 BYTE) ,
  "APPLY_USER" VARCHAR2(50 BYTE) ,
  "REPORT_DATE" VARCHAR2(50 BYTE) ,
  "COMMON" VARCHAR2(128 BYTE) ,
  "VERSION" NUMBER(5) ,
  "CREATE_TIME" TIMESTAMP(6) ,
  "CREATE_ID" VARCHAR2(50 BYTE) ,
  "UPDATE_TIME" TIMESTAMP(6) ,
  "UPDATE_ID" VARCHAR2(50 BYTE) ,
  "DELETED" NUMBER(1) ,
  "ISVALIDE" VARCHAR2(2 BYTE) ,
  "BUILD_NAME" VARCHAR2(255 BYTE) ,
  "COMMUNITY_NAME" VARCHAR2(255 BYTE) ,
  "TOTAL_LAYER" NUMBER(5) 
)
TABLESPACE "TBS_EISTRONG"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."ID" IS 'id';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."HOUSE_ID" IS '房屋编号';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."SYS_CODE" IS '普查系统编号';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."HOUSE_STATUS" IS '普查信息状态';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."APPROVAL_STATUS" IS '审批状态';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."REGION" IS '区县';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."STREET" IS '街道';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."PROJECT_NAME" IS '项目名称';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."MAP_CODE" IS '楼栋地图编号';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."ESTATE_UNIT_NAME" IS '物管单位';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."ADDRESS_CITY" IS '实际地址(市)';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."ADDRESS_REGION" IS '实际地址(区)';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."ADDRESS_COUNTY" IS '实际地址(县)';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."APT" IS '门牌号';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."BUILD_NO" IS '物理楼栋';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."UNIT_NO" IS '单元';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."LAYER" IS '楼层';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."ROOM_NO" IS '房屋号';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."RIGHT_ADDR" IS '产权证地址';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."HOUSE_AREA" IS '建筑面积';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."INNER_HOUSE_AREA" IS '套内面积';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."HOUSE_ROOM" IS '房屋户型(室)';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."HOUSE_HALL" IS '房屋户型(厅)';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."HOUSE_BATHROOM" IS '房屋户型(卫)';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."IS_REGI" IS '是否办理产权';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."RIGHT_NO" IS '产权证号';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."BUILD_DATE" IS '建成年份';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."HOUSE_TYPE" IS '房屋类别';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."HOUSE_STRUCTURE" IS '房屋结构';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."HOUSE_USE" IS '房屋用途';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."LAND_STATUS" IS '土地性质';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."PRE_SALE_NO" IS '预售许可证';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."FIT_STATUS" IS '装修状态';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."IS_CHECK_IN" IS '是否入住';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."PERSON_NUM" IS '居住人数';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."IS_RENT" IS '是否出租';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."RENT_START_DATE" IS '出租开始时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."RENT_END_DATE" IS '出租结束时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."ORG_CODE" IS '填报者机构编码';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."ORG_NAME" IS '机构名称';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."UNIT_NAME" IS '填报单位';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."APPLY_USER" IS '填报人';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."REPORT_DATE" IS '填报时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."COMMON" IS '备用';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."VERSION" IS '版本号';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."CREATE_ID" IS '创建者id';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."UPDATE_TIME" IS '修改时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."UPDATE_ID" IS '修改者id';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."DELETED" IS '逻辑删除';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."ISVALIDE" IS '数据是否有效';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."BUILD_NAME" IS '楼栋名称';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."COMMUNITY_NAME" IS '社区名称';
COMMENT ON COLUMN "EISTRONG"."LSHC_REGI_INFO"."TOTAL_LAYER" IS '总层数';
COMMENT ON TABLE "EISTRONG"."LSHC_REGI_INFO" IS '普查信息';

-- ----------------------------
-- Table structure for LSHC_SYS_CONFIG
-- ----------------------------
DROP TABLE "EISTRONG"."LSHC_SYS_CONFIG";
CREATE TABLE "EISTRONG"."LSHC_SYS_CONFIG" (
  "ID" VARCHAR2(200 BYTE) NOT NULL ,
  "CONFIG_NAME" VARCHAR2(200 BYTE) ,
  "CONFIG_CODE" VARCHAR2(50 BYTE) ,
  "CONFIG_VALUE" VARCHAR2(200 BYTE) ,
  "CONFIG_TYPE" VARCHAR2(64 BYTE) ,
  "SORT" NUMBER(5) ,
  "VERSION" NUMBER(5) ,
  "CREATE_TIME" TIMESTAMP(6) ,
  "CREATE_ID" VARCHAR2(50 BYTE) ,
  "UPDATE_TIME" TIMESTAMP(6) ,
  "UPDATE_ID" VARCHAR2(50 BYTE) ,
  "DELETED" NUMBER(1) ,
  "ISVALIDE" VARCHAR2(2 BYTE) 
)
TABLESPACE "TBS_EISTRONG"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;
COMMENT ON COLUMN "EISTRONG"."LSHC_SYS_CONFIG"."ID" IS 'id';
COMMENT ON COLUMN "EISTRONG"."LSHC_SYS_CONFIG"."CONFIG_NAME" IS '参数名称';
COMMENT ON COLUMN "EISTRONG"."LSHC_SYS_CONFIG"."CONFIG_CODE" IS '参数编码';
COMMENT ON COLUMN "EISTRONG"."LSHC_SYS_CONFIG"."CONFIG_VALUE" IS '参数值';
COMMENT ON COLUMN "EISTRONG"."LSHC_SYS_CONFIG"."CONFIG_TYPE" IS '参数分类';
COMMENT ON COLUMN "EISTRONG"."LSHC_SYS_CONFIG"."SORT" IS '排序';
COMMENT ON COLUMN "EISTRONG"."LSHC_SYS_CONFIG"."VERSION" IS '版本号';
COMMENT ON COLUMN "EISTRONG"."LSHC_SYS_CONFIG"."CREATE_TIME" IS '创建时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_SYS_CONFIG"."CREATE_ID" IS '创建者id';
COMMENT ON COLUMN "EISTRONG"."LSHC_SYS_CONFIG"."UPDATE_TIME" IS '修改时间';
COMMENT ON COLUMN "EISTRONG"."LSHC_SYS_CONFIG"."UPDATE_ID" IS '修改者id';
COMMENT ON COLUMN "EISTRONG"."LSHC_SYS_CONFIG"."DELETED" IS '逻辑删除';
COMMENT ON COLUMN "EISTRONG"."LSHC_SYS_CONFIG"."ISVALIDE" IS '数据是否有效';
COMMENT ON TABLE "EISTRONG"."LSHC_SYS_CONFIG" IS '参数配置信息';

-- ----------------------------
-- Primary Key structure for table LSHC_BUILD_INFO
-- ----------------------------
ALTER TABLE "EISTRONG"."LSHC_BUILD_INFO" ADD CONSTRAINT "PK_LSHC_BUILD_INFO" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table LSHC_BUILD_INFO
-- ----------------------------
ALTER TABLE "EISTRONG"."LSHC_BUILD_INFO" ADD CONSTRAINT "SYS_C00301951" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Indexes structure for table LSHC_BUILD_INFO
-- ----------------------------
CREATE INDEX "EISTRONG"."IDX_LSHC_BUILD_INFO"
  ON "EISTRONG"."LSHC_BUILD_INFO" ("MAP_CODE" ASC, "ID" ASC) LOCAL
  LOGGING
  TABLESPACE "TBS_EISTRONG"
  VISIBLE
PCTFREE 10
INITRANS 2
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
);

-- ----------------------------
-- Triggers structure for table LSHC_BUILD_INFO
-- ----------------------------
CREATE TRIGGER "EISTRONG"."BUILD_SERIAL_NO" BEFORE INSERT ON "EISTRONG"."LSHC_BUILD_INFO" REFERENCING OLD AS "OLD" NEW AS "NEW" FOR EACH ROW 
BEGIN
	SELECT
		SEQ_BUILD_SERIAL.nextval INTO :new.SERIAL_NO
FROM
	dual;
	END build_serial_no;
/

-- ----------------------------
-- Primary Key structure for table LSHC_DICTIONARY
-- ----------------------------
ALTER TABLE "EISTRONG"."LSHC_DICTIONARY" ADD CONSTRAINT "PK_LSHC_DICTIONARY" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table LSHC_DICTIONARY
-- ----------------------------
ALTER TABLE "EISTRONG"."LSHC_DICTIONARY" ADD CONSTRAINT "SYS_C00303159" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table LSHC_ENT_INFO
-- ----------------------------
ALTER TABLE "EISTRONG"."LSHC_ENT_INFO" ADD CONSTRAINT "PK_LSHC_ENT_INFO" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table LSHC_ENT_INFO
-- ----------------------------
ALTER TABLE "EISTRONG"."LSHC_ENT_INFO" ADD CONSTRAINT "SYS_C00302240" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Indexes structure for table LSHC_ENT_INFO
-- ----------------------------
CREATE INDEX "EISTRONG"."IDX_LSHC_ENT_INFO"
  ON "EISTRONG"."LSHC_ENT_INFO" ("HC_ID" ASC, "ID" ASC) LOCAL
  LOGGING
  ONLINE
  TABLESPACE "TBS_EISTRONG"
  NOSORT
  VISIBLE
PCTFREE 10
INITRANS 2
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
);

-- ----------------------------
-- Primary Key structure for table LSHC_FILE
-- ----------------------------
ALTER TABLE "EISTRONG"."LSHC_FILE" ADD CONSTRAINT "PK_LSHC_FILE" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table LSHC_FILE
-- ----------------------------
ALTER TABLE "EISTRONG"."LSHC_FILE" ADD CONSTRAINT "SYS_C00301955" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Indexes structure for table LSHC_FILE
-- ----------------------------
CREATE INDEX "EISTRONG"."IDX_LSHC_FILE"
  ON "EISTRONG"."LSHC_FILE" ("ID" ASC, "RIGHT_NO" ASC, "HC_ID" ASC) LOCAL
  LOGGING
  ONLINE
  TABLESPACE "TBS_EISTRONG"
  NOSORT
  VISIBLE
PCTFREE 10
INITRANS 2
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
);

-- ----------------------------
-- Primary Key structure for table LSHC_INDEX_NOTICE
-- ----------------------------
ALTER TABLE "EISTRONG"."LSHC_INDEX_NOTICE" ADD CONSTRAINT "PK_LSHC_INDEX_NOTICE" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table LSHC_INDEX_NOTICE
-- ----------------------------
ALTER TABLE "EISTRONG"."LSHC_INDEX_NOTICE" ADD CONSTRAINT "SYS_C00301953" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Indexes structure for table LSHC_INDEX_NOTICE
-- ----------------------------
CREATE INDEX "EISTRONG"."IDX_LSHC_INDEX_NOTICE"
  ON "EISTRONG"."LSHC_INDEX_NOTICE" ("NOTICE_TITLE" ASC, "ID" ASC) LOCAL
  LOGGING
  ONLINE
  TABLESPACE "TBS_EISTRONG"
  NOSORT
  VISIBLE
PCTFREE 10
INITRANS 2
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
);

-- ----------------------------
-- Primary Key structure for table LSHC_JOB_ACCEPT
-- ----------------------------
ALTER TABLE "EISTRONG"."LSHC_JOB_ACCEPT" ADD CONSTRAINT "PK_LSHC_JOB_ACCEPT" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table LSHC_JOB_ACCEPT
-- ----------------------------
ALTER TABLE "EISTRONG"."LSHC_JOB_ACCEPT" ADD CONSTRAINT "SYS_C00302844" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table LSHC_JOB_LOG
-- ----------------------------
ALTER TABLE "EISTRONG"."LSHC_JOB_LOG" ADD CONSTRAINT "PK_LSHC_JOB_LOG" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table LSHC_JOB_LOG
-- ----------------------------
ALTER TABLE "EISTRONG"."LSHC_JOB_LOG" ADD CONSTRAINT "SYS_C00302846" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Primary Key structure for table LSHC_REGI_INFO
-- ----------------------------
ALTER TABLE "EISTRONG"."LSHC_REGI_INFO" ADD CONSTRAINT "PK_LSHC_REGI_INFO" PRIMARY KEY ("ID");

-- ----------------------------
-- Checks structure for table LSHC_REGI_INFO
-- ----------------------------
ALTER TABLE "EISTRONG"."LSHC_REGI_INFO" ADD CONSTRAINT "SYS_C00301962" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;

-- ----------------------------
-- Indexes structure for table LSHC_REGI_INFO
-- ----------------------------
CREATE UNIQUE INDEX "EISTRONG"."IDX_LSHC_HOUSE_INFO"
  ON "EISTRONG"."LSHC_REGI_INFO" ("MAP_CODE" ASC, "HOUSE_ID" ASC, "RIGHT_NO" ASC)
  LOGGING
  TABLESPACE "TBS_EISTRONG"
  VISIBLE
PCTFREE 10
INITRANS 2
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  BUFFER_POOL DEFAULT
);

-- ----------------------------
-- Checks structure for table LSHC_SYS_CONFIG
-- ----------------------------
ALTER TABLE "EISTRONG"."LSHC_SYS_CONFIG" ADD CONSTRAINT "SYS_C00302246" CHECK ("ID" IS NOT NULL) NOT DEFERRABLE INITIALLY IMMEDIATE NORELY VALIDATE;
