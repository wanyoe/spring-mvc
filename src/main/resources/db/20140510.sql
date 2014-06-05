drop table if exists GLP_MAIL_DOMAIN;

/*==============================================================*/
/* Table: GLP_MAIL_DOMAIN                                       */
/*==============================================================*/
create table GLP_EMAIL_DOMAIN
(
   ID                   int not null auto_increment,
   DOMAIN               varchar(255) not null comment 'mail域名',
   MAIL_DOMAIN          varchar(255) not null comment '要转到的域名',
   primary key (ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

alter table GLP_EMAIL_DOMAIN comment 'mail域名转换';

/*==============================================================*/
/* Index: UNI_EMAIL                                             */
/*==============================================================*/
create unique index UNI_EMAIL on GLP_EMAIL_DOMAIN
(
   DOMAIN
);

insert into `GLP_EMAIL_DOMAIN` (`ID`, `DOMAIN`, `MAIL_DOMAIN`) values('1','gmail.com','http://mail.google.com');

insert into `GLP_EMAIL_DOMAIN` (`ID`, `DOMAIN`, `MAIL_DOMAIN`) values('2','hotmail.com','http://mail.msn.com');

insert into `GLP_EMAIL_DOMAIN` (`ID`, `DOMAIN`, `MAIL_DOMAIN`) values('3','mail.com','http://www.mail.com');


