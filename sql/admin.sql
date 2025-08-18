CREATE TABLE `admin_role` (
    `id`          TINYINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色ID (主鍵)',
    `role_name`   VARCHAR(50) NOT NULL UNIQUE COMMENT '角色名稱',
    `description` VARCHAR(255) DEFAULT NULL COMMENT '角色說明',
    `created_at`  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立時間',
    `updated_at`  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='後台角色表';

INSERT INTO `admin_role` (`id`, `role_name`, `description`) VALUES
(1, 'PENDING', '待審核'),
(2, 'MANAGER', '可管理商品等功能，但無法管理帳號'),
(3, 'ADMIN', '可管理商品與帳號，但不可操作同級別帳號'),
(4, 'SUPER_ADMIN', '最高權限，僅限DBA建立');


CREATE TABLE `admin_user` (
    `id`            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '管理員ID (主鍵)',
    `username`      VARCHAR(100) NOT NULL UNIQUE COMMENT '帳號名稱',
    `password_hash` VARCHAR(255) NOT NULL COMMENT '密碼Hash',
    `role_id`       TINYINT UNSIGNED NOT NULL COMMENT '角色ID，對應 admin_role.id',
    `status`        TINYINT UNSIGNED NOT NULL DEFAULT 1 COMMENT '帳號狀態 (0停用, 1啟用)',
    `created_at`    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立時間',
    `updated_at`    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uq_username` (`username`),
    CONSTRAINT `fk_admin_user_role` FOREIGN KEY (`role_id`) REFERENCES `admin_role`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='後台管理員表';

INSERT INTO `admin_user` (`username`, `password_hash`, `role_id`, `status`)
VALUES ('superadmin', '$2b$12$8rwIJFauYJUH3haKDvutQuiq355mSo4kflR.7y6djh07v4n1et3D.', 4, 1);