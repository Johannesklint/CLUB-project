use club_db;


DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `platform`;
DROP TABLE IF EXISTS `theme`;

CREATE TABLE IF NOT EXISTS `club_db`.`user` (`id` INT NOT NULL AUTO_INCREMENT, `first_name` VARCHAR(45) NOT NULL, `last_name` VARCHAR(45) NOT NULL,`email` VARCHAR(60) NOT NULL, `password` VARCHAR(60) NOT NULL, `admin` TINYINT(1) NOT NULL, `approved` TINYINT(1) NOT NULL, PRIMARY KEY (`id`));

CREATE TABLE IF NOT EXISTS `club_db`.`comment` (`id` INT NOT NULL AUTO_INCREMENT,`created` DATETIME NOT NULL, `text` VARCHAR(500) NOT NULL, `author_id` INT NOT NULL, PRIMARY KEY (`id`), INDEX `author_id_idx` (`author_id` ASC), CONSTRAINT `author_id` FOREIGN KEY (`author_id`) REFERENCES `club_db`.`user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION);

CREATE TABLE `theme` (`id` int(11) NOT NULL AUTO_INCREMENT, `primary_color_hex` varchar(7) NOT NULL, `secondary_color_hex` varchar(7) NOT NULL, PRIMARY KEY (`id`));

CREATE TABLE `platform` (`id` int(11) NOT NULL AUTO_INCREMENT, `title` varchar(45) DEFAULT NULL,  `description` text, `terms_and_condition` text, `theme_id` int(11) DEFAULT NULL, PRIMARY KEY (`id`), FOREIGN KEY (theme_id) REFERENCES theme(id));

# Create Admins
INSERT INTO `club_db`.`user` (`id`, `first_name`, `last_name`, `email`,`password`,`admin`, `approved`) VALUES (null, 'Erik', 'Wiberg', 'erik@wiberg.se', 'password', true, true);
INSERT INTO `club_db`.`user` (`id`, `first_name`, `last_name`, `email`,`password`,`admin`, `approved`) VALUES (null, 'Marcin', 'Retek', 'marcin@retek.se', 'password', true, true);

# Create Users
INSERT INTO `club_db`.`user` (`id`, `first_name`, `last_name`, `email`,`password`,`admin`, `approved`) VALUES (null, 'Kalle', 'Larsson', 'kalle-larsson@telia.net', 'password', false, false);
INSERT INTO `club_db`.`user` (`id`, `first_name`, `last_name`, `email`,`password`,`admin`, `approved`) VALUES (null, 'Emma', 'Karlsson', 'emma_karlssont@tele2.se', '12345', false, false);

# Create Theme

INSERT INTO `club_db`.`theme`(`id`,`primary_color_hex`,`secondary_color_hex`) VALUES (1,"#ffffff","#dddddd");


# Create Platform
INSERT INTO `club_db`.`platform`(`id`,`title`,`description`,`terms_and_condition`,`theme_id`) VALUES (1,'hej','j','a',1);



