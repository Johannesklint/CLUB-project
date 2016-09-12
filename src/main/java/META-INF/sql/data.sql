use club_db;

DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS `club_db`.`user` (`id` INT NOT NULL AUTO_INCREMENT, `first_name` VARCHAR(45) NOT NULL, `last_name` VARCHAR(45) NOT NULL,`email` VARCHAR(60) NOT NULL, `password` VARCHAR(60) NOT NULL, `admin` TINYINT(1) NOT NULL, `approved` TINYINT(1) NOT NULL, PRIMARY KEY (`id`));



DROP TABLE IF EXISTS `theme`;
CREATE TABLE `theme` (`id` int(11) NOT NULL AUTO_INCREMENT, `primary_color_hex` varchar(7) NOT NULL, `secondary_color_hex` varchar(7) NOT NULL, PRIMARY KEY (`id`));

DROP TABLE IF EXISTS `platform`;
CREATE TABLE `platform` (`id` int(11) NOT NULL AUTO_INCREMENT, `title` varchar(45) DEFAULT NULL,  `description` text, `terms_and_condition` text, `theme_id` int(11) DEFAULT NULL, PRIMARY KEY (`id`), FOREIGN KEY (theme_id) REFERENCES theme(id));



# Create Admins
INSERT INTO `club_db`.`user` (`id`, `first_name`, `last_name`, `email`,`password`,`admin`, `approved`) VALUES (null, 'John', 'Doe', 'john-doe@anon.net', 'password', true, true);
INSERT INTO `club_db`.`user` (`id`, `first_name`, `last_name`, `email`,`password`,`admin`, `approved`) VALUES (null, 'Jane', 'Doe', 'jane-doe@anon.net', 'password', true, true);

# Create Users
INSERT INTO `club_db`.`user` (`id`, `first_name`, `last_name`, `email`,`password`,`admin`, `approved`) VALUES (null, 'Kalle', 'Larsson', 'kalle-larsson@telia.net', 'password', false, false);
INSERT INTO `club_db`.`user` (`id`, `first_name`, `last_name`, `email`,`password`,`admin`, `approved`) VALUES (null, 'Emma', 'Karlsson', 'emma_karlssont@tele2.se', '12345', false, false);