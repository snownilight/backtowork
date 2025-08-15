-- 產品基本資訊表
CREATE TABLE `products` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '產品ID',
    `name` VARCHAR(100) NOT NULL COMMENT '產品名稱',
    `description` TEXT COMMENT '產品描述',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立時間',
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='產品基本資訊表';

-- 商品項目表(價格/數量/上架狀態)
CREATE TABLE `product_items` (
    `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品項目ID',
    `product_id` BIGINT UNSIGNED NOT NULL COMMENT '對應產品ID',
    `price` DECIMAL(10,2) NOT NULL COMMENT '價格',
    `stock` INT UNSIGNED NOT NULL DEFAULT 0 COMMENT '庫存數量',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '狀態 (0=下架, 1=上架)',
    `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '建立時間',
    `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新時間',
    PRIMARY KEY (`id`),
    KEY `idx_product_id` (`product_id`) COMMENT '產品ID索引',
    KEY `idx_status` (`status`) COMMENT '狀態索引',
    CONSTRAINT `fk_product_item_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品項目表(價格/數量/上架狀態)';


-- 插入產品基本資訊
INSERT INTO products (name, description, created_at, updated_at) VALUES
('Java 入門書籍', '適合初學者的 Java 教學書籍', NOW(), NOW()),
('高效能筆記型電腦', '適合程式開發與設計的高規筆電', NOW(), NOW()),
('藍牙無線滑鼠', '人體工學設計，適合長時間使用', NOW(), NOW());

-- 插入產品項目資訊 (假設產品 ID 分別為 1, 2, 3)
INSERT INTO product_items (product_id, price, stock, status, created_at, updated_at) VALUES
(1, 450.00, 100, 1, NOW(), NOW()),   -- Java 入門書籍
(2, 39999.00, 25, 1, NOW(), NOW()), -- 高效能筆記型電腦
(3, 899.00, 200, 0, NOW(), NOW());  -- 藍牙無線滑鼠 (下架)

-- 插入一筆沒有 product_items 的產品
INSERT INTO products (name, description, created_at, updated_at) VALUES
('USB-C 集線器', '支援多種介面，方便筆電擴充', NOW(), NOW());