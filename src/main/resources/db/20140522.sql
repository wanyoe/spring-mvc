-- �����û����ͣ���¼�û�������¼ --
ALTER TABLE GLP_USER_OPERATE_LOG ADD COLUMN USER_TYPE VARCHAR(1) NOT NULL DEFAULT '1' COMMENT '1,normal user;2,admin user';

-- ������ͨ����֤���û�״̬Ϊ���ͨ�� --
UPDATE TABLE GLP_USER SET `STATUS`='2' WHERE `STATUS`='1';
-- ����ɾ�����û�״̬Ϊδ��֤ --
UPDATE TABLE GLP_USER SET `STATUS`='4' WHERE `STATUS`='2';
