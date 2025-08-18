CREATE TABLE `admin_user` (
  `id`           BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '管理員ID',
  `username`     VARCHAR(64)     NOT NULL COMMENT '登入帳號',
  `password_hash` VARCHAR(100)   NOT NULL COMMENT 'BCrypt加密後密碼',
  `nickname`     VARCHAR(64)     DEFAULT NULL COMMENT '管理員暱稱',
  `email`        VARCHAR(128)    DEFAULT NULL COMMENT '電子郵件',
  `role`         VARCHAR(32)     NOT NULL DEFAULT 'ADMIN' COMMENT '角色類型 例如ADMIN、SUPER_ADMIN',
  `status`       TINYINT         NOT NULL DEFAULT 1 COMMENT '帳號狀態 1=啟用,0=停用',
  `last_login_at` DATETIME       DEFAULT NULL COMMENT '最後登入時間',
  `created_at`   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立時間',
  `updated_at`   DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  KEY `idx_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='後台管理員帳號表';

INSERT INTO `admin_user` (
    `username`, `password_hash`, `nickname`, `email`, `role`, `status`, `last_login_at`
) VALUES (
    'admin',
    '$2a$10$Dow1GxC8cFZL7q2dS5hZduFZOfHj0cM1mWhzIuNlmIYwzVxlLPtku', -- admin123 的 BCrypt Hash
    '系統管理員',
    'admin@example.com',
    'SUPER_ADMIN',
    1,
    NULL
);