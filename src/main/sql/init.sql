create table sys_user (
  user_id bigint auto_increment not null,
	created_date datetime,
	created_by varchar(100),
	modified_date datetime,
	modified_by varchar(100),
	deleted bool default false,
  username varchar(100),
  password varchar(100),
  organization_id bigint,
  salt varchar(100),
  locked bool default false,
  constraint pk_sys_user primary key(user_id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_sys_user_username on sys_user(username);
create index idx_sys_user_organization_id on sys_user(organization_id);


create table sys_role (
  role_id bigint auto_increment,
	created_date datetime,
	created_by varchar(100),
	modified_date datetime,
	modified_by varchar(100),
	deleted bool default false,
  role_name varchar(100),
  description varchar(100),
  available bool default false,
  constraint pk_sys_role primary key(role_id)
) charset=utf8 ENGINE=InnoDB;


create table sys_resource (
  resource_id bigint auto_increment,
	created_date datetime,
	created_by varchar(100),
	modified_date datetime,
	modified_by varchar(100),
	deleted bool default false,
  resource_name varchar(100),
  type varchar(50),
  url varchar(200),
  parent_id bigint,
  parent_ids varchar(100),
  permission varchar(100),
  available bool default false,
  constraint pk_sys_resource primary key(resource_id)
) charset=utf8 ENGINE=InnoDB;
create index idx_sys_resource_parent_id on sys_resource(parent_id);
create index idx_sys_resource_parent_ids on sys_resource(parent_ids);

-- user_role中间表
create table sys_user_role (
  user_id bigint,
	role_id bigint
) charset=utf8 ENGINE=InnoDB;

-- role_resource中间表
create table sys_role_resource (
  role_id bigint,
	resource_id bigint
) charset=utf8 ENGINE=InnoDB;


delete from sys_user;
delete from sys_role;
delete from sys_resource;


insert into sys_user(user_id,created_date,created_by,modified_date,modified_by,deleted,username,password,salt,locked)
values(1,NOW(),'系统初始化',NOW(),'系统初始化',FALSE,'admin','d3c59d25033dbf980d29554025c23a75','8d78869f470951332959580424d4bf4f',FALSE);

insert into sys_resource values(1,NOW(),'',NOW(),'',FALSE,'资源', 'menu', '', 0, '0/', '', true);

insert into sys_resource values(11,NOW(),'',NOW(),'',FALSE,'组织机构管理', 'menu', '/organization', 1, '0/1/', 'organization:*', true);
insert into sys_resource values(12,NOW(),'',NOW(),'',FALSE,'组织机构新增', 'button', '', 11, '0/1/11/', 'organization:create', true);
insert into sys_resource values(13,NOW(),'',NOW(),'',FALSE,'组织机构修改', 'button', '', 11, '0/1/11/', 'organization:update', true);
insert into sys_resource values(14,NOW(),'',NOW(),'',FALSE,'组织机构删除', 'button', '', 11, '0/1/11/', 'organization:delete', true);
insert into sys_resource values(15,NOW(),'',NOW(),'',FALSE,'组织机构查看', 'button', '', 11, '0/1/11/', 'organization:view', true);

insert into sys_resource values(21,NOW(),'',NOW(),'',FALSE,'用户管理', 'menu', '/user', 1, '0/1/', 'user:*', true);
insert into sys_resource values(22,NOW(),'',NOW(),'',FALSE,'用户新增', 'button', '', 21, '0/1/21/', 'user:create', true);
insert into sys_resource values(23,NOW(),'',NOW(),'',FALSE,'用户修改', 'button', '', 21, '0/1/21/', 'user:update', true);
insert into sys_resource values(24,NOW(),'',NOW(),'',FALSE,'用户删除', 'button', '', 21, '0/1/21/', 'user:delete', true);
insert into sys_resource values(25,NOW(),'',NOW(),'',FALSE,'用户查看', 'button', '', 21, '0/1/21/', 'user:view', true);

insert into sys_resource values(31,NOW(),'',NOW(),'',FALSE,'资源管理', 'menu', '/resource', 1, '0/1/', 'resource:*', true);
insert into sys_resource values(32,NOW(),'',NOW(),'',FALSE,'资源新增', 'button', '', 31, '0/1/31/', 'resource:create', true);
insert into sys_resource values(33,NOW(),'',NOW(),'',FALSE,'资源修改', 'button', '', 31, '0/1/31/', 'resource:update', true);
insert into sys_resource values(34,NOW(),'',NOW(),'',FALSE,'资源删除', 'button', '', 31, '0/1/31/', 'resource:delete', true);
insert into sys_resource values(35,NOW(),'',NOW(),'',FALSE,'资源查看', 'button', '', 31, '0/1/31/', 'resource:view', true);

insert into sys_resource values(41,NOW(),'',NOW(),'',FALSE,'角色管理', 'menu', '/role', 1, '0/1/', 'role:*', true);
insert into sys_resource values(42,NOW(),'',NOW(),'',FALSE,'角色新增', 'button', '', 41, '0/1/41/', 'role:create', true);
insert into sys_resource values(43,NOW(),'',NOW(),'',FALSE,'角色修改', 'button', '', 41, '0/1/41/', 'role:update', true);
insert into sys_resource values(44,NOW(),'',NOW(),'',FALSE,'角色删除', 'button', '', 41, '0/1/41/', 'role:delete', true);
insert into sys_resource values(45,NOW(),'',NOW(),'',FALSE,'角色查看', 'button', '', 41, '0/1/41/', 'role:view', true);

insert into sys_role values(1,NOW(),'',NOW(),'',FALSE,'admin','超级管理员',true);


-- 中间表数据插入
insert into sys_user_role values (1,1);

insert into sys_role_resource values (1,11);
insert into sys_role_resource values (1,12);
insert into sys_role_resource values (1,13);
insert into sys_role_resource values (1,14);
insert into sys_role_resource values (1,15);
insert into sys_role_resource values (1,21);
insert into sys_role_resource values (1,31);
insert into sys_role_resource values (1,41);