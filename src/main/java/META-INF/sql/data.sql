use club_db;

# Create Admins
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`password`,`admin`, `approved_state`) VALUES (null,'Erik', 'Wiberg', 'erik@wiberg.se', 'password', true, 0);
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`password`,`admin`, `approved_state`) VALUES (null,'Marcin', 'Retek', 'marcin@retek.se', 'password', true, 2);
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`password`,`admin`, `approved_state`) VALUES (null,'Emil', 'Rånge', 'emil@range.se', 'password', false, 2);
                             
# Create Users                
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`password`,`admin`, `approved_state`) VALUES (null, 'Kalle', 'Larsson', 'kalle-larsson@telia.net', 'password', false, 1);
                             
# Create Theme

INSERT INTO `club_db`.`theme`(`id`,`primary_color_hex`,`secondary_color_hex`) VALUES (null,"#ffffff","#dddddd");


# Create Platform
INSERT INTO `club_db`.`platform`(`id`,`title`,`description`,`terms_and_condition`,`theme_id`) VALUES (null,'Default Title','Default Description','Terms and condition',1);

# Create Post > News
INSERT INTO `club_db`.`POST` (`ID`, `post_type`, `TEXT`, `TITLE`, `AUTHOR_ID`) VALUES ('1', 'NEWS', 'text for news', 'News -1', 1);
INSERT INTO `club_db`.`POST` (`ID`, `post_type`, `TEXT`, `TITLE`, `AUTHOR_ID`) VALUES ('2', 'NEWS', 'text for news', 'News -2', 1);




