-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `parent_id`   bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
    `name`        varchar(64) NOT NULL COMMENT '菜单名称',
    `path`        varchar(255) DEFAULT NULL COMMENT '菜单URL',
    `perms`       varchar(255) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
    `component`   varchar(255) DEFAULT NULL COMMENT '菜单组件',
    `type`        int(5) NOT NULL COMMENT '类型(0：目录,1：菜单,2：按钮)',
    `icon`        varchar(32)  DEFAULT NULL COMMENT '菜单图标',
    `order_num`   int(11) DEFAULT NULL COMMENT '排序',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `create_by`   varchar(64) NOT NULL COMMENT '创建人',
    `update_by`   varchar(64)  DEFAULT NULL COMMENT '修改人',
    `status`      int(5) NOT NULL COMMENT '状态',
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `name`        varchar(64) NOT NULL COMMENT '角色名称',
    `code`        varchar(64) NOT NULL COMMENT '角色编码',
    `remark`      varchar(64) DEFAULT NULL COMMENT '备注',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    `update_time` datetime    DEFAULT NULL COMMENT '更新时间',
    `create_by`   varchar(64) NOT NULL COMMENT '创建人',
    `update_by`   varchar(64) DEFAULT NULL COMMENT '修改人',
    `status`      int(5) NOT NULL COMMENT '状态',
    PRIMARY KEY (`id`),
    UNIQUE KEY `name` (`name`) USING BTREE,
    UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `role_id` bigint(20) NOT NULL COMMENT '角色id',
    `menu_id` bigint(20) NOT NULL COMMENT '菜单id',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`          bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `username`    varchar(64)  DEFAULT NULL COMMENT '用户名',
    `password`    varchar(64)  DEFAULT NULL COMMENT '密码',
    `avatar`      varchar(255) DEFAULT NULL COMMENT '头像',
    `email`       varchar(64)  DEFAULT NULL COMMENT '邮箱',
    `city`        varchar(64)  DEFAULT NULL COMMENT '城市',
    `last_login`  datetime     DEFAULT NULL COMMENT '最后登录时间',
    `create_time` datetime    NOT NULL COMMENT '创建时间',
    `update_time` datetime     DEFAULT NULL COMMENT '更新时间',
    `create_by`   varchar(64) NOT NULL COMMENT '创建人',
    `update_by`   varchar(64)  DEFAULT NULL COMMENT '修改人',
    `status`      int(5) NOT NULL COMMENT '状态',
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_USERNAME` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`      bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `user_id` bigint(20) NOT NULL COMMENT '用户id',
    `role_id` bigint(20) NOT NULL COMMENT '角色id',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;