CREATE TABLE IF NOT EXISTS `document` (
    `id`  INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NOT NULL,
    `oss_url` VARCHAR(255) NOT NULL,
    `content` TEXT NOT NULL ,
    `communication_sw` BOOLEAN,
    `created_at` TIMESTAMP NOT NULL ,
    `created_by` VARCHAR(255) NOT NULL,
    `updated_at` TIMESTAMP DEFAULT NULL,
    `updated_by` VARCHAR(255) DEFAULT NULL,
     FOREIGN KEY (response_id) REFERENCES response(id)
);