CREATE TABLE IF NOT EXISTS `conversation` (
    `id`  CHAR(36) PRIMARY KEY NOT NULL,
    `title` VARCHAR(255) NOT NULL,
    `user_id` INT NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `updated_at` TIMESTAMP NOT NULL,
    `created_by` VARCHAR(255) NOT NULL,
    `updated_by` VARCHAR(255) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `message` (
    `id`CHAR(36) PRIMARY KEY NOT NULL,
    `content` TEXT NOT NULL,
    `created_at` TIMESTAMP NOT NULL,
    `updated_at` TIMESTAMP DEFAULT NULL,
    `created_by` VARCHAR(255) NOT NULL,
    `updated_by` VARCHAR(255) DEFAULT NULL,
    `conversation_id` CHAR(36)  NOT NULL,
    FOREIGN KEY (`conversation_id`) REFERENCES `conversation`(`id`)
);