ALTER TABLE TB_SYS_RESOURCES  ADD COLUMN  AUTHORIZE  varchar(32) NULL AFTER  ORDERS,
ALTER TABLE TB_SYS_RESOURCES  ADD UNIQUE(AUTHORIZE);