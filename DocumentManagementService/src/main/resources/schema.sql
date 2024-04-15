CREATE TABLE IF NOT EXISTS `document` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `title` VARCHAR(255) NOT NULL,
    `url` VARCHAR(255) NOT NULL,
    `user_id` INT NOT NULL,
    `content` TEXT NOT NULL ,
    `created_at` TIMESTAMP NOT NULL ,
    `created_by` VARCHAR(255) NOT NULL,
    `updated_at` TIMESTAMP DEFAULT NULL,
    `updated_by` VARCHAR(255) DEFAULT NULL
);

