use club_db;

# Create Admins
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`password`,`admin`, `approved`) VALUES (0,'Erik', 'Wiberg', 'erik@wiberg.se', 'password', true, true);
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`password`,`admin`, `approved`) VALUES (1,'Marcin', 'Retek', 'marcin@retek.se', 'password', true, true);
                             
# Create Users                
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`password`,`admin`, `approved`) VALUES (2, 'Kalle', 'Larsson', 'kalle-larsson@telia.net', 'password', false, false);
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`password`,`admin`, `approved`) VALUES (3, 'Emma', 'Karlsson', 'emma_karlssont@tele2.se', '12345', false, false);

# Create Theme

INSERT INTO `club_db`.`theme`(`id`,`primary_color_hex`,`secondary_color_hex`) VALUES (1,"#ffffff","#dddddd");


# Create Platform
INSERT INTO `club_db`.`platform`(`id`,`title`,`description`,`terms_and_condition`,`theme_id`) VALUES (1,'Default Title','Default Description','Terms and condition',1);



