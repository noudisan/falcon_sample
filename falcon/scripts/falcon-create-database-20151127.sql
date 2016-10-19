--单元派送数据的上传图片 表
CREATE TABLE `TB_DELIVERY_DATA_COMMUNITY_UNIT_PICTURE` (
  `DELIVERY_DATA_COMMUNITY_UNIT_ID` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '单元派送数据表ID，对应TB_DELIVERY_DATA_COMMUNITY_UNIT',
  `SEQUENCE` tinyint(1) unsigned NOT NULL COMMENT '序号，目前为止的业务只允许0~3',
  `PATH` varchar(255) NOT NULL COMMENT '图片文件的部分位置：/aaa/bbb/xxxx.png',
  `SAVE_DATE` datetime NOT NULL COMMENT '保存时间',
  UNIQUE KEY `UNIQUE` (`DELIVERY_DATA_COMMUNITY_UNIT_ID`,`SEQUENCE`)
) COMMENT='单元派送数据的上传图片';
