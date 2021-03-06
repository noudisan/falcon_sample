##单元派发数据
CREATE TABLE TB_DELIVERY_DATA_COMMUNITY_UNIT(
	ID INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
	COMMUNITY_UNIT_ID INT not null COMMENT '单元id',
	BUILDING_ID INT not null COMMENT '楼栋id',
	DELIVERY_NUM INT DEFAULT NULL COMMENT '派发数量',
	DELIVERY_EMPLOYEE_ID INT not null COMMENT '员工id',
	DELIVERY_EMPLOYEE_NAME VARCHAR(32)  COMMENT '员工姓名',
	DELIVERY_TASK_ID INT  not null COMMENT '任务id',
	DELIVERY_TYPE VARCHAR(32) DEFAULT null COMMENT '派发方式',
	DELIVERY_RESULT VARCHAR(32) DEFAULT null COMMENT '派发结果',
	REMARK VARCHAR(512) COMMENT '备注',
        DELIVERY_DT DATETIME DEFAULT NULL COMMENT '派发时间',
	COMMUNITY_ID INT not null comment '小区id',
	COMMUNITY_NAME VARCHAR(255) DEFAULT null comment '小区名称',
	BUILDING_NAME VARCHAR(128) DEFAULT null comment '楼栋名称',
	COMMUNITY_UNIT_NAME VARCHAR(128) DEFAULT null comment '单元名称',
	SETTLESTATUS VARCHAR(32) not null comment '结算状态',
	TASK_SAMPLING  VARCHAR(32) DEFAULT null comment '抽查状态',
	PRIMARY KEY (ID)
) comment '单元派发数据';

## 楼栋派发数据
CREATE TABLE TB_DELIVERY_DATA_COMMUNITY_BUILDING(
	ID INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
	BUILDING_ID INT NOT NULL COMMENT '楼栋id',
	DELIVERY_NUM INT DEFAULT NULL COMMENT '派发数量',
	COMMUNITY_ID INT NOT NULL COMMENT '小区id',
	DELIVERY_EMPLOYEE_ID INT not null COMMENT '员工id',
	DELIVERY_TASK_ID INT  not null COMMENT '任务id',
	DELIVERY_STATUS VARCHAR(32) not null COMMENT '派发状态',
	DELIVERY_DT DATETIME NULL COMMENT '派发时间',
	BEGIN_DT DATETIME NULL COMMENT '开始时间',
	END_DT DATETIME NULL COMMENT '结束时间',
	REMARK VARCHAR(512) COMMENT '备注',
	LATITUDE  DOUBLE NOT NULL COMMENT '中心点经度' ,
	LONGITUDE  DOUBLE NOT NULL COMMENT '中心点纬度' ,
	COMMUNITY_NAME VARCHAR(255) default null comment '小区名称',
	BUILDING_NAME VARCHAR(128) default null comment '楼栋名称',
	PRIMARY KEY (ID)
)comment '楼栋派发数据';

##小区派发数据
CREATE TABLE TB_DELIVERY_DATA_COMMUNITY(
	ID INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
	COMMUNITY_ID INT NOT NULL COMMENT '小区id',
	DELIVERY_NUM INT DEFAULT NULL COMMENT '派发数量',
	SECTION_ID INT NOT NULL COMMENT '板块id',
	DELIVERY_TASK_ID INT NOT NULL COMMENT '任务id',
	DELIVERY_STATUS VARCHAR(32) not null COMMENT '派发状态',
	DELIVERY_DT DATETIME NULL COMMENT '派发时间',
	BEGIN_DT DATETIME NULL COMMENT '开始时间',
	REMARK VARCHAR(512) COMMENT '备注',
	COMMUNITY_NAME VARCHAR(255) not null comment '小区名称',
	PRIMARY KEY (ID)
)comment '小区派发数据';


/*
 *   城市表
 */
CREATE TABLE TB_BASE_CITY(
	ID INT UNSIGNED not null AUTO_INCREMENT COMMENT 'ID',
	PROVINCE varchar(16) COMMENT '所属省份',
	CITY_NAME varchar(16) not null COMMENT '城市名称',
	CITY_ABBR VARCHAR (32) not NULL COMMENT '城市简称',
	MODIFY_USER VARCHAR(16) NULL COMMENT '最后修改人',
	MODIFY_DT DATETIME NULL COMMENT '最后修改时间',
        CREATE_USER VARCHAR(16) NULL COMMENT '创建人',
	CREATE_DT DATETIME NULL COMMENT '创建时间',
	IS_LOCKED varchar(16) COMMENT '是否解锁',
	PRIMARY KEY(ID),
	UNIQUE INDEX UQ_CITY_NAME (CITY_NAME)
);

/*
 *    板块表
 */
CREATE TABLE TB_BASE_DELIVERY_SECTION (
	ID  INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
	ADDRESS  VARCHAR(255) COMMENT '地址' ,
	CITY varchar(50) NOT NULL COMMENT '城市编号',
	LATITUDE  DOUBLE NOT NULL COMMENT '中心点经度' ,
	LONGITUDE  DOUBLE NOT NULL COMMENT '中心点纬度' ,
	SECTION_NAME  VARCHAR(128) NOT NULL COMMENT '板块名称' ,
	STATUS_FLAG  varchar(32) NOT NULL COMMENT '状态标记',
	MODIFY_USER VARCHAR(16) NULL COMMENT '最后修改人',
	MODIFY_DT DATETIME NULL COMMENT '最后修改时间',
	CREATE_USER VARCHAR(16) NULL COMMENT '创建人',
	CREATE_DT DATETIME NULL COMMENT '创建时间',
	PRIMARY KEY (ID)
);
/*
 * 板块点表
 */
CREATE TABLE TB_BASE_SECTION_POINT (
	ID  INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID' ,
	LATITUDE  DOUBLE NOT NULL COMMENT '板块经度' ,
	LONGITUDE  DOUBLE NOT NULL COMMENT '板块纬度' ,
	POINT_INDEX  TINYINT NOT NULL COMMENT '位置索引号' ,
	SECTION_ID  INT UNSIGNED NOT NULL COMMENT '板块编号',
	PRIMARY KEY (ID)
);

/*
 * 小区表
 */
CREATE TABLE TB_BASE_COMMUNITY(
	ID INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
	COMMUNITY_CODE VARCHAR(32) NOT NULL COMMENT '小区编号' ,
	ADDRESS  VARCHAR(255) NOT NULL COMMENT '小区地址' ,
	CITY  VARCHAR(16) NOT NULL COMMENT '所属城市' ,
	AREA  VARCHAR(16) NOT NULL COMMENT '所属区域',
	SECTION VARCHAR(50) NOT NULL COMMENT '所属板块',
	COMMUNITY_NAME  VARCHAR(255) NOT NULL COMMENT '小区名称' ,
	LATITUDE  DOUBLE NOT NULL COMMENT '小区经度' ,
	LONGITUDE  DOUBLE NOT NULL COMMENT '小区纬度' ,
	PINYIN_CODE  VARCHAR(100) NOT NULL COMMENT '简写名称',
	POSTCODE  VARCHAR(20) COMMENT '小区邮编',
	REMARKS  VARCHAR(512) COMMENT '小区说明',
	DELIVERY_SECTION_ID  INT DEFAULT 0 COMMENT '手册派发板块编号' ,
	STATUS_FLAG  varchar(64) NOT NULL COMMENT '状态标记',
	ELEVATOR_FLAG varchar(16) COMMENT '是否有电梯（0:未知；1:否；2:是；3:混合）',
	HOUSEHOLDS INT COMMENT '户数',
	PRICES INT COMMENT '房价',
	MODIFY_YEARS varchar(32) COMMENT '年限',
	COMMUNITY_TYPE varchar(64) COMMENT '小区类型（0：未知；1：低密度豪宅；2：高密度成熟小区；3：公寓/老公房）',
	BUILD_TYPE varchar(32) COMMENT '建造类型',
	FUN_TYPE varchar(320) COMMENT '类型',
	DELIVERY_FLAG varchar(20) COMMENT '',
	MODIFY_USER VARCHAR(16) NULL COMMENT '最后修改人',
	MODIFY_DT DATETIME NULL COMMENT '最后修改时间',
	CREATE_USER VARCHAR(16) NULL COMMENT '创建人',
	CREATE_DT DATETIME NULL COMMENT '创建时间',
	PRIMARY KEY (ID)
);

/*
 * 楼栋表
 */
CREATE TABLE TB_BASE_COMMUNITY_BUILDING(
	ID INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
	NAME VARCHAR(128) COMMENT '楼栋名称',
	LATITUDE  DOUBLE NOT NULL COMMENT '小区经度',
	LONGITUDE  DOUBLE NOT NULL COMMENT '小区纬度',
	REMARK VARCHAR(512) COMMENT '楼栋说明',
	COMMUNITY_ID INT UNSIGNED NOT NULL COMMENT '小区编号',
	COMMUNITY_NAME varchar(255) NOT NULL COMMENT '小区名称',
	MODIFY_USER VARCHAR(16) DEFAULT NULL COMMENT '最后修改人',
        MODIFY_DT DATETIME DEFAULT NULL COMMENT '最后修改时间',
        CREATE_USER VARCHAR(16) NOT NULL COMMENT '创建人',
        CREATE_DT DATETIME NOT NULL COMMENT '创建时间',
        PRIMARY KEY(ID)
);

/*
 * 单元表
 */
CREATE TABLE TB_BASE_COMMUNITY_UNIT(
	ID  BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
	BUILDING_ID INT NOT NULL COMMENT '楼栋编号',
	FLOOR_NUM  INT DEFAULT NULL COMMENT '楼层数' ,
	HOUSEHOLDS  INT DEFAULT NULL COMMENT '每层户数' ,
	ALL_NUM INT DEFAULT NULL COMMENT '总户数',
	NAME  VARCHAR(128) DEFAULT NULL  COMMENT '单元名称' ,
	MODIFY_USER VARCHAR(16) DEFAULT NULL COMMENT '最后修改人',
	MODIFY_DT DATETIME DEFAULT NULL COMMENT '最后修改时间',
	CREATE_USER VARCHAR(16) DEFAULT NULL COMMENT '创建人',
	CREATE_DT DATETIME DEFAULT NULL COMMENT '创建时间',
	COMMUNITY_NAME varchar(255) DEFAULT NULL COMMENT '小区名称',
	BUILDING_NAME VARCHAR(128) COMMENT '楼栋名称',
	PRIMARY KEY (ID)
);

/*
 * 员工管理表
 */
CREATE TABLE TB_BASE_DELIVERY_EMPLOYEE (
	ID INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
	USER_CODE INT(20) NOT NULL COMMENT '用户编号',
	USER_NAME VARCHAR(16) NOT NULL COMMENT '用户名',
	PASSWORD VARCHAR(255) NOT NULL COMMENT '密码',
	SEX VARCHAR(10) NOT NULL COMMENT '性别',
	CAREER VARCHAR(255) NOT NULL COMMENT '职业',
	PHONE VARCHAR(11) NOT NULL COMMENT '手机号',
	ID_NO VARCHAR(18) DEFAULT NULL COMMENT '身份证号',
	CARD_HOLDER VARCHAR(16) DEFAULT NULL COMMENT '持卡人',
	BANK_NAME VARCHAR(255) DEFAULT NULL COMMENT '银行名称',
	BANK_NO VARCHAR(25) DEFAULT NULL COMMENT '银行卡号',
	HEAD_FILE VARCHAR(255) DEFAULT NULL COMMENT '头像',
	ROLE VARCHAR(255) NOT NULL COMMENT '角色',
	CITY VARCHAR(255) NOT NULL COMMENT '城市',
	IS_LOCKED VARCHAR(30) DEFAULT NULL COMMENT '是否锁定',
	DEVICE_ID VARCHAR(255) DEFAULT NULL COMMENT '设备ID',
	MODIFY_USER VARCHAR(16) DEFAULT NULL COMMENT '最后修改人',
	MODIFY_DT DATETIME DEFAULT NULL COMMENT '最后修改时间',
	CREATE_USER VARCHAR(16) DEFAULT NULL COMMENT '创建人',
	CREATE_DT DATETIME DEFAULT NULL COMMENT '创建时间',
        PRIMARY KEY  (ID),
        UNIQUE KEY USER_PHONE USING BTREE (PHONE)
);

/*
 * 步数表
 */
CREATE TABLE TB_DELIVERY_STEPS (
  ID INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  USER_ID INT NOT NULL COMMENT '用户ID',
  TASK_ID INT NOT NULL COMMENT '任务ID',
  STEPS VARCHAR(16) DEFAULT NULL COMMENT '步数',
  START_TIME DATETIME DEFAULT NULL COMMENT '开始时间',
  END_TIME DATETIME DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY  (ID)
);

/*
 * 版本维护表
 */
CREATE TABLE TB_DELIVERY_VERSION (
  ID INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
  VERSION_CODE INT NOT NULL COMMENT '版本号',
  VERSION_NAME VARCHAR(16) NOT NULL COMMENT '版本名称',
  SYSTEM_TYPE VARCHAR(16) NOT NULL COMMENT '系统类别',
  UPDATE_CONTENT VARCHAR(512) NOT NULL COMMENT '更新内容',
  IS_FORCE_UPDATE VARCHAR(16) NOT NULL COMMENT '是否强制更新',
  MODIFY_USER VARCHAR(16) DEFAULT NULL COMMENT '最后修改人',
  MODIFY_DT DATETIME DEFAULT NULL COMMENT '最后修改时间',
  CREATE_USER VARCHAR(16) DEFAULT NULL COMMENT '创建人',
  CREATE_DT DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY  (ID)
);


##任务
CREATE TABLE TB_DELIVERY_TASK (
	ID INT UNSIGNED NOT NULL  AUTO_INCREMENT,
	CODE VARCHAR(32) NOT NULL COMMENT '任务编号',
	SEND_COUNT INT COMMENT '派发数量',
	MASS_TIME DATETIME NOT NULL COMMENT '集结时间',
	MASS_ADDRESS VARCHAR(128) NOT NULL COMMENT '集结地址',
	LEADER VARCHAR(16) DEFAULT NULL COMMENT '领队姓名',
	LEADER_PHONE_NUM VARCHAR(11) DEFAULT NULL COMMENT '领队手机号',
	DRIVER VARCHAR(16) DEFAULT NULL COMMENT '司机姓名',
	DRIVER_PHONE_NUM VARCHAR(11) DEFAULT NULL COMMENT '司机手机号',
	IS_SAMPLING VARCHAR(16) DEFAULT NULL COMMENT '是否抽样',
	TASK_DESC VARCHAR(255) DEFAULT NULL COMMENT '任务描述',
	STATUS  VARCHAR(16) DEFAULT NULL COMMENT '状态',
	START_TIME DATETIME NOT NULL COMMENT '任务开始时间',
	MODIFY_USER VARCHAR(16) NULL COMMENT '最后修改人',
	MODIFY_DT DATETIME NULL COMMENT '最后修改时间',
	CREATE_USER VARCHAR(16) NULL COMMENT '创建人',
	CREATE_DT DATETIME NULL COMMENT '创建时间',
	REGION VARCHAR(32) not null comment '任务区域',
	SECTION_NAMES VARCHAR(512) not null comment '任务板块',
	PRIMARY KEY  (ID)
);

##任务关联板块
CREATE TABLE TB_DELIVERY_TASK_SECTION (
	ID INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
	SEND_TASK_ID INT COMMENT '任务id',
	SECTION_ID INT COMMENT '板块id'
);

##任务关联人员
CREATE TABLE TB_DELIVERY_TASK_EMPLOYEE(
	ID INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
	EMPLOYEE_ID INT COMMENT '兼职id',
	SEND_TASK_ID INT COMMENT '任务id',
	TASK_STATUS VARCHAR(16) COMMENT '任务状态'
);

CREATE TABLE TB_ACCOUNT_INFO
(
   ID                   INT                            UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',
   USER_ID              INT                            NULL    COMMENT '用户编号',
   USER_NAME            VARCHAR(16)                    NULL    COMMENT '用户名称',
   PHONE                VARCHAR(11)                    NULL    COMMENT '手机号',
   ACCOUNT_AMOUNT       DOUBLE                         NULL    COMMENT '账户余额',
   CREATE_DT            DATETIME                       NULL    COMMENT '创建时间',
   MIDIFY_DT            DATETIME                       NULL    COMMENT '修改时间',
   CREATE_USER          VARCHAR(16)                    NULL    COMMENT '创建人',
   MODIFY_USER          VARCHAR(16)                    NULL    COMMENT '修改人',
   PRIMARY KEY  (ID)
);

CREATE TABLE TB_CASH_OUT_INFO
(
   ID                   INT                            UNSIGNED NOT NULL  AUTO_INCREMENT    COMMENT 'ID',
   USER_ID		INT			       NULL    COMMENT '用户编号',
   NAME                 VARCHAR(16)                    NULL    COMMENT '姓名',
   CASH_AMOUNT          DOUBLE                         NULL    COMMENT '提现金额',
   CASH_DATE            DATETIME                       NULL    COMMENT '提现日期',
   OPEN_BANK            VARCHAR(64)                    NULL    COMMENT '开户行',
   CASH_CARD            VARCHAR(64)                    NULL    COMMENT '提现卡号',
   DEAL_STATUS          INT                            NULL    COMMENT '处理状态',
   RESULT_COMENT        VARCHAR(128)                   NULL    COMMENT '结果说明',
   CREATE_DT            DATETIME                       NULL    COMMENT '创建时间',
   MIDIFY_DT            DATETIME                       NULL    COMMENT '修改时间',
   CREATE_USER          VARCHAR(16)                    NULL    COMMENT '创建人',
   MODIFY_USER          VARCHAR(16)                    NULL    COMMENT '修改人',
   PRIMARY KEY  (ID)
);

CREATE TABLE TB_SETTLE
(
   ID                   INT                            UNSIGNED NOT NULL  AUTO_INCREMENT    COMMENT 'ID',
   USER_ID              INT                            NULL    COMMENT '用户编号',
   TASK_ID              INT                            NULL    COMMENT '任务编号',
   NAME                 VARCHAR(16)                    NULL    COMMENT '姓名',
   SEND_NUM             INT                            NULL    COMMENT '派送数量',
   PRICE                INT                            NULL    COMMENT '单册价格',
   SETTLE_AMOUNT        DOUBLE                         NULL    COMMENT '结算金额',
   SETTLE_DATE          DATETIME                       NULL    COMMENT '结算日期',
   CAUSH_STATUS         INT                            NULL    COMMENT '状态',
   START_TIME           DATETIME                       NULL    COMMENT '开始时间',
   END_TIME             DATETIME                       NULL    COMMENT '结束时间',
   TOTAL_TIME           DOUBLE                         NULL    COMMENT '总时间',
   NEIGHBORHOODS_NUM    INT                            NULL    COMMENT '小区数',
   FLOOR_NUM            INT                            NULL    COMMENT '楼栋数',
   UNIT_NUM             INT                            NULL    COMMENT '单元数',
   CREATE_USER          VARCHAR(16)                    NULL    COMMENT '创建人',
   CREATE_DT            DATETIME                       NULL    COMMENT '创建时间',
   MODIFY_USER          VARCHAR(16)                    NULL    COMMENT '修改人',
   MODIFY_DT            DATETIME                       NULL    COMMENT '修改时间',
   PRIMARY KEY  (ID)
);

CREATE TABLE TB_SETTLE_DETAIL
(
   ID                   INT                            UNSIGNED NOT NULL  AUTO_INCREMENT    COMMENT 'ID',
   SETTLE_ID            INT                            NULL    COMMENT '清算外键',
   SEND_STYLE           VARCHAR(16)                    NULL    COMMENT '派送册子方式',
   BOOK_NUM             INT                            NULL    COMMENT '派送册子数量',
   PRICE                DOUBLE                         NULL    COMMENT '单册价格',
   SETTLE_AMOUNT        DOUBLE                         NULL    COMMENT '清算金额',
   STATUS               INT                            NULL    COMMENT '状态',
   NEIGHBORHOOD_NAME    VARCHAR(255)                   NULL    COMMENT '小区名称',
   FLOOR_NUM            INT                            NULL    COMMENT '楼栋数',
   UNIT_NUM             INT                            NULL    COMMENT '单元数',
   PRIMARY KEY  (ID)
);


CREATE TABLE TB_BASIC_SETTLE_PRICE
(
   ID                   INTEGER                        UNSIGNED NOT NULL  AUTO_INCREMENT    COMMENT 'ID',
   PROVINCE             VARCHAR(20)                    NULL    COMMENT '省份',
   CITY                 VARCHAR(20)                    NULL    COMMENT '城市',
   SEND_STYLE           VARCHAR(50)                    NULL    COMMENT '投递方式',
   PRICE                DOUBLE                         NULL    COMMENT '单册价格',
   PRICE_STATUS         INT                            NULL    COMMENT '价格状态',
   COMENT               VARCHAR(100)                   NULL    COMMENT '备注',
   CREATE_USER          VARCHAR(20)                    NULL    COMMENT '创建人',
   CREATE_DT            DATETIME                       NULL    COMMENT '创建时间',
   MODIFY_USER          VARCHAR(50)                    NULL    COMMENT '修改人',
   MODIFY_DT            DATETIME                       NULL    COMMENT '修改时间',
   PRIMARY KEY  (ID)
);
