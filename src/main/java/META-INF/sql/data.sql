use club_db;

# Create Admins
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`password`,`admin`, `approved_state`) VALUES (null,'Erik', 'Wiberg', 'erik@wiberg.se', 'password', true, 0);
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`password`,`admin`, `approved_state`) VALUES (null,'Marcin', 'Retek', 'marcin@retek.se', 'password', true, 2);
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`password`,`admin`, `approved_state`) VALUES (null,'Emil', 'Rånge', 'emil@range.se', 'password', false, 2);
                             
# Create Users                
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`password`,`admin`, `approved_state`) VALUES (null, 'Kalle', 'Larsson', 'kalle-larsson@telia.net', 'password', false, 1);
                             
# Create Theme

INSERT INTO `club_db`.`theme`(`id`,`primary_color_hex`,`secondary_color_hex`) VALUES (null,"#6cffb8","#fcb0b3");


# Create Platform
INSERT INTO `club_db`.`platform`(`id`,`title`,`description`,`terms_and_condition`,`theme_id`) VALUES (null,'Liseberg','Bunnys and stuff','Terms and condition',1);

# Create Post > News
INSERT INTO `club_db`.`post` (`ID`, `created`, `post_type`, `TEXT`, `TITLE`, `AUTHOR_ID`) VALUES (null, NOW(),'NEWS', 'text for news 1', 'News - 1', 1);
INSERT INTO `club_db`.`post` (`ID`, `created`, `post_type`, `TEXT`, `TITLE`, `AUTHOR_ID`) VALUES (null, NOW(),'NEWS', 'text for news 2', 'News - 2', 1);
INSERT INTO `club_db`.`POST` (`ID`, `created`, `post_type`, `TEXT`, `TITLE`, `AUTHOR_ID`) VALUES (null, NOW(), 'NEWS', 'text for news 3', 'News - 3', 1);
INSERT INTO `club_db`.`POST` (`ID`, `created`, `post_type`, `TEXT`, `TITLE`, `AUTHOR_ID`) VALUES (null, NOW(), 'NEWS', 'text for news 4', 'News - 4', 1);

# Create Comment to post
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), 'Comment to post 1, by user 1', 1, 1);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), 'Comment to post 1, by user 2', 1, 2);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), 'Comment to post 2, by user 1', 2, 1);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), 'Comment to post 2, by user 2', 2, 2);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), 'Comment to post 2, by user 1', 2, 1);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), 'Comment to post 2, by user 1', 2, 1);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), 'Comment to post 2, by user 1', 2, 1);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), 'Comment to post 2, by user 1', 2, 1);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), 'Comment to post 2, by user 1', 2, 1);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), 'Comment to post 2, by user 1', 2, 1);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), 'Comment to post 2, by user 1', 2, 1);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), 'Comment to post 2, by user 1', 2, 1);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), 'Comment to post 2, by user 1', 2, 1);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), 'Comment to post 2, by user 1', 2, 1);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), 'Comment to post 2, by user 1', 2, 1);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), 'Comment to post 2, by user 1', 2, 1);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), 'Comment to post 2, by user 1', 2, 1);

