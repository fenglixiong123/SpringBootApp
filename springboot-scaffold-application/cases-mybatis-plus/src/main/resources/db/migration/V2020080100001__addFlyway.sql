-- ----------------------------
-- Table structure for flyway_schema_history
-- ----------------------------
# DROP TABLE IF EXISTS `flyway_schema_history`;
# CREATE TABLE `flyway_schema_history`  (
#   `installed_rank` int(11) NOT NULL,
#   `version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
#   `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
#   `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
#   `script` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
#   `checksum` int(11) NULL DEFAULT NULL,
#   `installed_by` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
#   `installed_on` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
#   `execution_time` int(11) NOT NULL,
#   `success` tinyint(1) NOT NULL,
#   PRIMARY KEY (`installed_rank`) USING BTREE,
#   INDEX `flyway_schema_history_s_idx`(`success`) USING BTREE
# ) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;