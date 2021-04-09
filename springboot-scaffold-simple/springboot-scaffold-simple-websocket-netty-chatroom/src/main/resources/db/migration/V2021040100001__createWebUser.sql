SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for basic_bucket_part
-- ----------------------------
CREATE TABLE IF NOT EXISTS `web_user`  (
    `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
    `warehouse_id` bigint(20) NULL DEFAULT NULL COMMENT '仓库Id',
    `bucket_part_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '货架部件编码',
    `bucket_type_code` varchar(255) NOT NULL COMMENT '货架类型编码',
    `bucket_part_name` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '货架部件名称',
    `bucket_part_layer` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '部件层',
    `length` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '长',
    `width` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '宽',
    `height` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '高',
    `safe_length` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '长安全距离',
    `safe_width` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '宽安全距离',
    `safe_height` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '高安全距离',
    `rotation_radius` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '旋转直径',
    `offset_off_center_x` int(11) NULL DEFAULT NULL COMMENT '距中心偏移X',
    `offset_off_center_y` int(11) NULL DEFAULT NULL COMMENT '距中心偏移Y',
    `state` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'effective' COMMENT '状态',
    `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
    `created_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    `created_user` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'EVO_BASIC' COMMENT '创建者',
    `created_app` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建应用',
    `last_updated_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '最后更新时间',
    `last_updated_user` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'EVO_BASIC' COMMENT '最后更新者',
    `last_updated_app` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后更新应用',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_code`(`bucket_part_code`, `bucket_type_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;