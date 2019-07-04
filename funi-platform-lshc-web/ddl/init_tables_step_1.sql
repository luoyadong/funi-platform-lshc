----------------------------------------------
-- Export file for user EISTRONG            --
-- Created by yadong on 2019/6/29, 22:58:52 --
----------------------------------------------
create table LSHC_BUILD_INFO
(
  id             VARCHAR2(200) not null,
  serial_no      NUMBER(8),
  region         VARCHAR2(100),
  street         VARCHAR2(100),
  project_name   VARCHAR2(100),
  map_code       VARCHAR2(100),
  address        VARCHAR2(255),
  common         VARCHAR2(128),
  version        NUMBER(5),
  create_time    TIMESTAMP(6),
  create_id      VARCHAR2(50),
  update_time    TIMESTAMP(6),
  update_id      VARCHAR2(50),
  deleted        NUMBER(1),
  isvalide       VARCHAR2(2),
  build_name     VARCHAR2(255),
  community_name VARCHAR2(255)
)
;
comment on table LSHC_BUILD_INFO
  is '楼栋信息';
comment on column LSHC_BUILD_INFO.id
  is 'id';
comment on column LSHC_BUILD_INFO.serial_no
  is '序号';
comment on column LSHC_BUILD_INFO.region
  is '区县';
comment on column LSHC_BUILD_INFO.street
  is '街道';
comment on column LSHC_BUILD_INFO.project_name
  is '项目名称';
comment on column LSHC_BUILD_INFO.map_code
  is '楼栋地图编号';
comment on column LSHC_BUILD_INFO.address
  is '实际地址';
comment on column LSHC_BUILD_INFO.common
  is '备用';
comment on column LSHC_BUILD_INFO.version
  is '版本号';
comment on column LSHC_BUILD_INFO.create_time
  is '创建时间';
comment on column LSHC_BUILD_INFO.create_id
  is '创建者id';
comment on column LSHC_BUILD_INFO.update_time
  is '修改时间';
comment on column LSHC_BUILD_INFO.update_id
  is '修改者id';
comment on column LSHC_BUILD_INFO.deleted
  is '逻辑删除';
comment on column LSHC_BUILD_INFO.isvalide
  is '数据是否有效';
comment on column LSHC_BUILD_INFO.build_name
  is '楼栋名称';
comment on column LSHC_BUILD_INFO.community_name
  is '社区名称';
create index IDX_LSHC_BUILD_INFO on LSHC_BUILD_INFO (ID, MAP_CODE);
alter table LSHC_BUILD_INFO
  add constraint PK_LSHC_BUILD_INFO primary key (ID);

create table LSHC_DICTIONARY
(
  id          VARCHAR2(200) default SYS_GUID() not null,
  name        VARCHAR2(200),
  former_name VARCHAR2(200),
  config_type VARCHAR2(200),
  pid         NUMBER,
  isonline    NUMBER,
  note        VARCHAR2(2000)
)
;
alter table LSHC_DICTIONARY
  add constraint PK_LSHC_DICTIONARY primary key (ID);

create table LSHC_ENT_INFO
(
  id              VARCHAR2(200) not null,
  hc_id           VARCHAR2(200),
  ent_name        VARCHAR2(200),
  sex             VARCHAR2(10),
  ent_type        VARCHAR2(50),
  ent_nation      VARCHAR2(20),
  ent_native      VARCHAR2(64),
  tel             VARCHAR2(15),
  marriage_status VARCHAR2(10),
  id_type         VARCHAR2(50),
  id_no           VARCHAR2(50),
  career          VARCHAR2(64),
  version         NUMBER(5),
  create_time     TIMESTAMP(6),
  create_id       VARCHAR2(50),
  update_time     TIMESTAMP(6),
  update_id       VARCHAR2(50),
  deleted         NUMBER(1),
  isvalide        VARCHAR2(2)
)
;
comment on table LSHC_ENT_INFO
  is '人口信息';
comment on column LSHC_ENT_INFO.id
  is 'id';
comment on column LSHC_ENT_INFO.hc_id
  is '普查编号';
comment on column LSHC_ENT_INFO.ent_name
  is '姓名';
comment on column LSHC_ENT_INFO.sex
  is '性别';
comment on column LSHC_ENT_INFO.ent_type
  is '人员类别';
comment on column LSHC_ENT_INFO.ent_nation
  is '民族';
comment on column LSHC_ENT_INFO.ent_native
  is '籍贯';
comment on column LSHC_ENT_INFO.tel
  is '联系电话';
comment on column LSHC_ENT_INFO.marriage_status
  is '婚姻状态';
comment on column LSHC_ENT_INFO.id_type
  is '证件类型';
comment on column LSHC_ENT_INFO.id_no
  is '证件号码';
comment on column LSHC_ENT_INFO.career
  is '职业';
comment on column LSHC_ENT_INFO.version
  is '版本号';
comment on column LSHC_ENT_INFO.create_time
  is '创建时间';
comment on column LSHC_ENT_INFO.create_id
  is '创建者id';
comment on column LSHC_ENT_INFO.update_time
  is '修改时间';
comment on column LSHC_ENT_INFO.update_id
  is '修改者id';
comment on column LSHC_ENT_INFO.deleted
  is '逻辑删除';
comment on column LSHC_ENT_INFO.isvalide
  is '数据是否有效';
create index IDX_LSHC_ENT_INFO on LSHC_ENT_INFO (ID, HC_ID);
alter table LSHC_ENT_INFO
  add constraint PK_LSHC_ENT_INFO primary key (ID);

create table LSHC_FILE
(
  id          VARCHAR2(200) not null,
  hc_id       VARCHAR2(200),
  right_no    VARCHAR2(64),
  file_name   VARCHAR2(255),
  file_size   NUMBER(10,2),
  file_type   VARCHAR2(10),
  url         VARCHAR2(255),
  submit_date TIMESTAMP(6),
  user_name   VARCHAR2(128),
  unit_name   VARCHAR2(128),
  version     NUMBER(5),
  create_time TIMESTAMP(6),
  create_id   VARCHAR2(50),
  update_time TIMESTAMP(6),
  update_id   VARCHAR2(50),
  deleted     NUMBER(1),
  isvalide    VARCHAR2(2)
)
;
comment on table LSHC_FILE
  is '附件信息';
comment on column LSHC_FILE.id
  is 'id';
comment on column LSHC_FILE.hc_id
  is '普查编号';
comment on column LSHC_FILE.right_no
  is '产权证编号';
comment on column LSHC_FILE.file_name
  is '附件名称';
comment on column LSHC_FILE.file_size
  is '附件大小';
comment on column LSHC_FILE.file_type
  is '附件类型';
comment on column LSHC_FILE.url
  is 'url';
comment on column LSHC_FILE.submit_date
  is '上传时间';
comment on column LSHC_FILE.user_name
  is '上传者账号';
comment on column LSHC_FILE.unit_name
  is '单位名称';
comment on column LSHC_FILE.version
  is '版本号';
comment on column LSHC_FILE.create_time
  is '创建时间';
comment on column LSHC_FILE.create_id
  is '创建者id';
comment on column LSHC_FILE.update_time
  is '修改时间';
comment on column LSHC_FILE.update_id
  is '修改者id';
comment on column LSHC_FILE.deleted
  is '逻辑删除';
comment on column LSHC_FILE.isvalide
  is '数据是否有效';
create index IDX_LSHC_FILE on LSHC_FILE (ID, HC_ID, RIGHT_NO);
alter table LSHC_FILE
  add constraint PK_LSHC_FILE primary key (ID);

create table LSHC_INDEX_NOTICE
(
  id           VARCHAR2(200) not null,
  notice_type  VARCHAR2(128),
  notice_title VARCHAR2(255),
  content      CLOB,
  notice_date  VARCHAR2(50),
  url          VARCHAR2(255),
  common       VARCHAR2(64),
  version      NUMBER(5),
  create_time  TIMESTAMP(6),
  create_id    VARCHAR2(50),
  update_time  TIMESTAMP(6),
  update_id    VARCHAR2(50),
  deleted      NUMBER(1),
  isvalide     VARCHAR2(2)
)
;
comment on table LSHC_INDEX_NOTICE
  is '首页公告信息';
comment on column LSHC_INDEX_NOTICE.id
  is 'id';
comment on column LSHC_INDEX_NOTICE.notice_type
  is '公告类型';
comment on column LSHC_INDEX_NOTICE.notice_title
  is '公告标题';
comment on column LSHC_INDEX_NOTICE.content
  is '公告内容';
comment on column LSHC_INDEX_NOTICE.notice_date
  is '公告日期';
comment on column LSHC_INDEX_NOTICE.url
  is 'url';
comment on column LSHC_INDEX_NOTICE.common
  is '备用';
comment on column LSHC_INDEX_NOTICE.version
  is '版本号';
comment on column LSHC_INDEX_NOTICE.create_time
  is '创建时间';
comment on column LSHC_INDEX_NOTICE.create_id
  is '创建者id';
comment on column LSHC_INDEX_NOTICE.update_time
  is '修改时间';
comment on column LSHC_INDEX_NOTICE.update_id
  is '修改者id';
comment on column LSHC_INDEX_NOTICE.deleted
  is '逻辑删除';
comment on column LSHC_INDEX_NOTICE.isvalide
  is '数据是否有效';
create index IDX_LSHC_INDEX_NOTICE on LSHC_INDEX_NOTICE (ID, NOTICE_TITLE);
alter table LSHC_INDEX_NOTICE
  add constraint PK_LSHC_INDEX_NOTICE primary key (ID);

create table LSHC_JOB_ACCEPT
(
  id          VARCHAR2(50) not null,
  service_num VARCHAR2(200),
  type_name   VARCHAR2(50),
  status      NUMBER,
  cur_status  VARCHAR2(50),
  accept_time TIMESTAMP(6),
  version     NUMBER(5),
  create_time DATE,
  create_id   VARCHAR2(50),
  update_time DATE,
  update_id   VARCHAR2(50),
  deleted     NUMBER(1),
  isvalide    VARCHAR2(2)
)
;
comment on table LSHC_JOB_ACCEPT
  is '工作日志';
comment on column LSHC_JOB_ACCEPT.id
  is '主键';
comment on column LSHC_JOB_ACCEPT.service_num
  is '受理号';
comment on column LSHC_JOB_ACCEPT.type_name
  is '工作类型';
comment on column LSHC_JOB_ACCEPT.status
  is '状态';
comment on column LSHC_JOB_ACCEPT.cur_status
  is '当前状态';
comment on column LSHC_JOB_ACCEPT.accept_time
  is '受理时间';
comment on column LSHC_JOB_ACCEPT.version
  is '版本号';
comment on column LSHC_JOB_ACCEPT.create_time
  is '创建时间';
comment on column LSHC_JOB_ACCEPT.create_id
  is '创建者id';
comment on column LSHC_JOB_ACCEPT.update_time
  is '修改时间';
comment on column LSHC_JOB_ACCEPT.update_id
  is '修改者id';
comment on column LSHC_JOB_ACCEPT.deleted
  is '逻辑删除';
comment on column LSHC_JOB_ACCEPT.isvalide
  is '数据是否有效';
alter table LSHC_JOB_ACCEPT
  add constraint PK_LSHC_JOB_ACCEPT primary key (ID);

create table LSHC_JOB_LOG
(
  id            VARCHAR2(50) not null,
  job_accept_id VARCHAR2(50),
  audit_name    VARCHAR2(50),
  audit_id      VARCHAR2(50),
  audit_result  VARCHAR2(50),
  node_name     VARCHAR2(200),
  job_opinion   VARCHAR2(2000),
  unit_name     VARCHAR2(200),
  common        VARCHAR2(50),
  version       NUMBER(5),
  create_time   DATE,
  create_id     VARCHAR2(50),
  update_time   DATE,
  update_id     VARCHAR2(50),
  deleted       NUMBER(1),
  isvalide      VARCHAR2(2)
)
;
comment on table LSHC_JOB_LOG
  is '工作日志明细';
comment on column LSHC_JOB_LOG.id
  is '主键';
comment on column LSHC_JOB_LOG.job_accept_id
  is '工作主表id';
comment on column LSHC_JOB_LOG.audit_name
  is '审核人';
comment on column LSHC_JOB_LOG.audit_id
  is '审核人姓名';
comment on column LSHC_JOB_LOG.audit_result
  is '结论';
comment on column LSHC_JOB_LOG.node_name
  is '节点名称';
comment on column LSHC_JOB_LOG.job_opinion
  is '意见';
comment on column LSHC_JOB_LOG.version
  is '版本号';
comment on column LSHC_JOB_LOG.create_time
  is '创建时间';
comment on column LSHC_JOB_LOG.create_id
  is '创建者id';
comment on column LSHC_JOB_LOG.update_time
  is '修改时间';
comment on column LSHC_JOB_LOG.update_id
  is '修改者id';
comment on column LSHC_JOB_LOG.deleted
  is '逻辑删除';
comment on column LSHC_JOB_LOG.isvalide
  is '数据是否有效';
alter table LSHC_JOB_LOG
  add constraint PK_LSHC_JOB_LOG primary key (ID);

create table LSHC_REGI_INFO
(
  id               VARCHAR2(200) not null,
  house_id         VARCHAR2(200),
  sys_code         VARCHAR2(20),
  house_status     VARCHAR2(10),
  approval_status  VARCHAR2(10),
  region           VARCHAR2(100),
  street           VARCHAR2(100),
  project_name     VARCHAR2(100),
  map_code         VARCHAR2(100),
  estate_unit_name VARCHAR2(50),
  address_city     VARCHAR2(255),
  address_region   VARCHAR2(255),
  address_county   VARCHAR2(255),
  apt              VARCHAR2(64),
  build_no         VARCHAR2(64),
  unit_no          VARCHAR2(64),
  layer            VARCHAR2(64),
  room_no          VARCHAR2(64),
  right_addr       VARCHAR2(255),
  house_area       NUMBER(15,2),
  inner_house_area NUMBER(15,2),
  house_room       VARCHAR2(200),
  house_hall       VARCHAR2(200),
  house_bathroom   VARCHAR2(200),
  is_regi          VARCHAR2(50),
  right_no         VARCHAR2(64),
  build_date       VARCHAR2(64),
  house_type       VARCHAR2(50),
  house_structure  VARCHAR2(50),
  house_use        VARCHAR2(50),
  land_status      VARCHAR2(50),
  pre_sale_no      VARCHAR2(64),
  fit_status       VARCHAR2(50),
  is_check_in      VARCHAR2(10),
  person_num       NUMBER(8),
  is_rent          VARCHAR2(10),
  rent_start_date  VARCHAR2(64),
  rent_end_date    VARCHAR2(64),
  org_code         VARCHAR2(64),
  org_name         VARCHAR2(255),
  unit_name        VARCHAR2(50),
  apply_user       VARCHAR2(50),
  report_date      VARCHAR2(50),
  common           VARCHAR2(128),
  version          NUMBER(5),
  create_time      TIMESTAMP(6),
  create_id        VARCHAR2(50),
  update_time      TIMESTAMP(6),
  update_id        VARCHAR2(50),
  deleted          NUMBER(1),
  isvalide         VARCHAR2(2),
  build_name       VARCHAR2(255),
  community_name   VARCHAR2(255),
  total_layer      NUMBER(5)
)
;
comment on table LSHC_REGI_INFO
  is '普查信息';
comment on column LSHC_REGI_INFO.id
  is 'id';
comment on column LSHC_REGI_INFO.house_id
  is '房屋编号';
comment on column LSHC_REGI_INFO.sys_code
  is '普查系统编号';
comment on column LSHC_REGI_INFO.house_status
  is '普查信息状态';
comment on column LSHC_REGI_INFO.approval_status
  is '审批状态';
comment on column LSHC_REGI_INFO.region
  is '区县';
comment on column LSHC_REGI_INFO.street
  is '街道';
comment on column LSHC_REGI_INFO.project_name
  is '项目名称';
comment on column LSHC_REGI_INFO.map_code
  is '楼栋地图编号';
comment on column LSHC_REGI_INFO.estate_unit_name
  is '物管单位';
comment on column LSHC_REGI_INFO.address_city
  is '实际地址(市)';
comment on column LSHC_REGI_INFO.address_region
  is '实际地址(区)';
comment on column LSHC_REGI_INFO.address_county
  is '实际地址(县)';
comment on column LSHC_REGI_INFO.apt
  is '门牌号';
comment on column LSHC_REGI_INFO.build_no
  is '物理楼栋';
comment on column LSHC_REGI_INFO.unit_no
  is '单元';
comment on column LSHC_REGI_INFO.layer
  is '楼层';
comment on column LSHC_REGI_INFO.room_no
  is '房屋号';
comment on column LSHC_REGI_INFO.right_addr
  is '产权证地址';
comment on column LSHC_REGI_INFO.house_area
  is '建筑面积';
comment on column LSHC_REGI_INFO.inner_house_area
  is '套内面积';
comment on column LSHC_REGI_INFO.house_room
  is '房屋户型(室)';
comment on column LSHC_REGI_INFO.house_hall
  is '房屋户型(厅)';
comment on column LSHC_REGI_INFO.house_bathroom
  is '房屋户型(卫)';
comment on column LSHC_REGI_INFO.is_regi
  is '是否办理产权';
comment on column LSHC_REGI_INFO.right_no
  is '产权证号';
comment on column LSHC_REGI_INFO.build_date
  is '建成年份';
comment on column LSHC_REGI_INFO.house_type
  is '房屋类别';
comment on column LSHC_REGI_INFO.house_structure
  is '房屋结构';
comment on column LSHC_REGI_INFO.house_use
  is '房屋用途';
comment on column LSHC_REGI_INFO.land_status
  is '土地性质';
comment on column LSHC_REGI_INFO.pre_sale_no
  is '预售许可证';
comment on column LSHC_REGI_INFO.fit_status
  is '装修状态';
comment on column LSHC_REGI_INFO.is_check_in
  is '是否入住';
comment on column LSHC_REGI_INFO.person_num
  is '居住人数';
comment on column LSHC_REGI_INFO.is_rent
  is '是否出租';
comment on column LSHC_REGI_INFO.rent_start_date
  is '出租开始时间';
comment on column LSHC_REGI_INFO.rent_end_date
  is '出租结束时间';
comment on column LSHC_REGI_INFO.org_code
  is '填报者机构编码';
comment on column LSHC_REGI_INFO.org_name
  is '机构名称';
comment on column LSHC_REGI_INFO.unit_name
  is '填报单位';
comment on column LSHC_REGI_INFO.apply_user
  is '填报人';
comment on column LSHC_REGI_INFO.report_date
  is '填报时间';
comment on column LSHC_REGI_INFO.common
  is '备用';
comment on column LSHC_REGI_INFO.version
  is '版本号';
comment on column LSHC_REGI_INFO.create_time
  is '创建时间';
comment on column LSHC_REGI_INFO.create_id
  is '创建者id';
comment on column LSHC_REGI_INFO.update_time
  is '修改时间';
comment on column LSHC_REGI_INFO.update_id
  is '修改者id';
comment on column LSHC_REGI_INFO.deleted
  is '逻辑删除';
comment on column LSHC_REGI_INFO.isvalide
  is '数据是否有效';
comment on column LSHC_REGI_INFO.build_name
  is '楼栋名称';
comment on column LSHC_REGI_INFO.community_name
  is '社区名称';
comment on column LSHC_REGI_INFO.total_layer
  is '总层数';
create unique index IDX_LSHC_HOUSE_INFO on LSHC_REGI_INFO (MAP_CODE, RIGHT_NO, HOUSE_ID);
alter table LSHC_REGI_INFO
  add constraint PK_LSHC_REGI_INFO primary key (ID);

create table LSHC_SYS_CONFIG
(
  id           VARCHAR2(200) not null,
  config_name  VARCHAR2(200),
  config_code  VARCHAR2(50),
  config_value VARCHAR2(200),
  config_type  VARCHAR2(64),
  sort         NUMBER(5),
  version      NUMBER(5),
  create_time  TIMESTAMP(6),
  create_id    VARCHAR2(50),
  update_time  TIMESTAMP(6),
  update_id    VARCHAR2(50),
  deleted      NUMBER(1),
  isvalide     VARCHAR2(2)
)
;
comment on table LSHC_SYS_CONFIG
  is '参数配置信息';
comment on column LSHC_SYS_CONFIG.id
  is 'id';
comment on column LSHC_SYS_CONFIG.config_name
  is '参数名称';
comment on column LSHC_SYS_CONFIG.config_code
  is '参数编码';
comment on column LSHC_SYS_CONFIG.config_value
  is '参数值';
comment on column LSHC_SYS_CONFIG.config_type
  is '参数分类';
comment on column LSHC_SYS_CONFIG.sort
  is '排序';
comment on column LSHC_SYS_CONFIG.version
  is '版本号';
comment on column LSHC_SYS_CONFIG.create_time
  is '创建时间';
comment on column LSHC_SYS_CONFIG.create_id
  is '创建者id';
comment on column LSHC_SYS_CONFIG.update_time
  is '修改时间';
comment on column LSHC_SYS_CONFIG.update_id
  is '修改者id';
comment on column LSHC_SYS_CONFIG.deleted
  is '逻辑删除';
comment on column LSHC_SYS_CONFIG.isvalide
  is '数据是否有效';