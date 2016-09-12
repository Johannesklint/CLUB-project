use club_db;

DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS `club_db`.`user` (`id` INT NOT NULL AUTO_INCREMENT, `first_name` VARCHAR(45) NOT NULL, `last_name` VARCHAR(45) NOT NULL,`email` VARCHAR(60) NOT NULL, `password` VARCHAR(60) NOT NULL, `admin` TINYINT(1) NOT NULL, `approved` TINYINT(1) NOT NULL, PRIMARY KEY (`id`));

DROP TABLE IF EXISTS comment;
CREATE TABLE `club_db`.`comment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created` DATETIME NOT NULL,
  `text` VARCHAR(500) NOT NULL,
  `author_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `author_id_idx` (`author_id` ASC),
  CONSTRAINT `author_id`
    FOREIGN KEY (`author_id`)
    REFERENCES `club_db`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

# Create Admins
INSERT INTO `club_db`.`user` (`id`, `first_name`, `last_name`, `email`,`password`,`admin`, `approved`) VALUES (null, 'John', 'Doe', 'john-doe@anon.net', 'password', true, true);
INSERT INTO `club_db`.`user` (`id`, `first_name`, `last_name`, `email`,`password`,`admin`, `approved`) VALUES (null, 'Jane', 'Doe', 'jane-doe@anon.net', 'password', true, true);

# Create Users
INSERT INTO `club_db`.`user` (`id`, `first_name`, `last_name`, `email`,`password`,`admin`, `approved`) VALUES (null, 'Kalle', 'Larsson', 'kalle-larsson@telia.net', 'password', false, false);
INSERT INTO `club_db`.`user` (`id`, `first_name`, `last_name`, `email`,`password`,`admin`, `approved`) VALUES (null, 'Emma', 'Karlsson', 'emma_karlssont@tele2.se', '12345', false, false);


CREATE TABLE IF NOT EXISTS `club_db`.`comment` (`id` INT NOT NULL AUTO_INCREMENT, `created`VARCHAR(45) NOT NULL, `text` TEXT NOT NULL, `user_id `INT NOT NULL PRIMARY KEY (`ID`), FOREIGN KEY(user_id));
