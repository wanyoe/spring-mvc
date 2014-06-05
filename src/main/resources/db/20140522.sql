-- 增加用户类型：记录用户操作记录 --
ALTER TABLE GLP_USER_OPERATE_LOG ADD COLUMN USER_TYPE VARCHAR(1) NOT NULL DEFAULT '1' COMMENT '1,normal user;2,admin user';

-- 更新已通过验证的用户状态为审核通过 --
UPDATE TABLE GLP_USER SET `STATUS`='2' WHERE `STATUS`='1';
-- 更新删除的用户状态为未验证 --
UPDATE TABLE GLP_USER SET `STATUS`='4' WHERE `STATUS`='2';
