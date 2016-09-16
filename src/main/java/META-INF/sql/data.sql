use club_db;

# Create Admins
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`password`,`admin`, `approved_state`) VALUES (null,'Erik', 'Wiberg', 'erik@wiberg.se', 'password', true, 0);
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`password`,`admin`, `approved_state`) VALUES (null,'Marcin', 'Retek', 'marcin@retek.se', 'password', true, 0);
                             
# Create Users                
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`password`,`admin`, `approved_state`) VALUES (null, 'Kalle', 'Larsson', 'kalle-larsson@telia.net', 'password', false, 1);
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`password`,`admin`, `approved_state`) VALUES (null, 'Emma', 'Karlsson', 'emma_karlssont@tele2.se', '12345', false, 2);

# Create Theme

INSERT INTO `club_db`.`theme`(`id`,`primary_color_hex`,`secondary_color_hex`) VALUES (null,"#ffffff","#dddddd");


# Create Platform
INSERT INTO `club_db`.`platform`(`id`,`title`,`description`,`terms_and_condition`,`theme_id`) VALUES (null,'Default Title','Default Description','Terms and condition',1);



