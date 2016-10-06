use club_db;

# Create custom function to generate timestamp inte the future (for future events)
DROP FUNCTION IF EXISTS timestamp_plus_x_days_with_y_time;
CREATE FUNCTION timestamp_plus_x_days_with_y_time(daysIntoFuture INTEGER, new_time VARCHAR(5)) RETURNS DATETIME RETURN DATE_ADD(DATE_ADD( CURRENT_DATE(), INTERVAL daysIntoFuture DAY), INTERVAL new_time HOUR_MINUTE);


# Create Admins
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`hmac_password`,`admin`, `approved_state`) VALUES (null,'Admin', 'Adminsson', 'admin@admin.se', '73617059CED40DB461984E72F15EA0F2F30C2385565CDF59124B8C56911ECDC29E90328EF190E5D0EA339E7B1221A6F9BDC4BB5D25F14F560E7FA8B451931A0A:7B8509E35DD2EBBC00BAECA928F9CD57', true, 0);
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`hmac_password`,`admin`, `approved_state`) VALUES (null,'Marcin', 'Retek', 'marcin@retek.se', '73617059CED40DB461984E72F15EA0F2F30C2385565CDF59124B8C56911ECDC29E90328EF190E5D0EA339E7B1221A6F9BDC4BB5D25F14F560E7FA8B451931A0A:7B8509E35DD2EBBC00BAECA928F9CD57', true, 2);
INSERT INTO `club_db`.`user` (`id`,`first_name`, `last_name`, `email`,`hmac_password`,`admin`, `approved_state`) VALUES (null,'Emil', 'Rånge', 'emil@range.se', '73617059CED40DB461984E72F15EA0F2F30C2385565CDF59124B8C56911ECDC29E90328EF190E5D0EA339E7B1221A6F9BDC4BB5D25F14F560E7FA8B451931A0A:7B8509E35DD2EBBC00BAECA928F9CD57', false, 2);
                             

# Create Theme

INSERT INTO `club_db`.`theme`(`id`,`primary_color_hex`,`secondary_color_hex`) VALUES (null,"#6cffb8","#fcb0b3");

# Create Platform
INSERT INTO `club_db`.`platform`(`id`,`title`,`description`,`terms_and_condition`,`theme_id`) VALUES (null,'Default title','Default platform description','Terms and conditions',1);

# Create Post > News
INSERT INTO `club_db`.`post` (`ID`, `created`, `post_type`, `TEXT`, `TITLE`, `AUTHOR_ID`, `hidden`) VALUES (null, NOW(),'NEWS', 'text for news 1', 'News - 1', 1, false);
INSERT INTO `club_db`.`post` (`ID`, `created`, `post_type`, `TEXT`, `TITLE`, `AUTHOR_ID`, `hidden`) VALUES (null, NOW(),'NEWS', 'text for news 2', 'News - 2', 1, true);
INSERT INTO `club_db`.`post` (`ID`, `created`, `post_type`, `TEXT`, `TITLE`, `AUTHOR_ID`, `hidden`) VALUES (null, NOW(), 'NEWS', 'text for news 3', 'News - 3', 1, true);
INSERT INTO `club_db`.`post` (`ID`, `created`, `post_type`, `TEXT`, `TITLE`, `AUTHOR_ID`, `hidden`) VALUES (null, NOW(), 'NEWS', 'text for news 4', 'News - 4', 1, true);

# Create Comment to post
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), false, 'Comment to post 1, by user 1', 1, 1);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), false, 'Comment to post 1, by user 2', 1, 2);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), false, 'Comment to post 2, by user 1', 2, 1);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), false, 'Comment to post 2, by user 2', 2, 2);
INSERT INTO `club_db`.`comment` VALUES (null, NOW(), false, 'Comment to post 2, by user 1', 2, 1);

# Create Post > Event
INSERT INTO `club_db`.`post` (`post_type`, `CREATED`, `hidden`, `TITLE`, `TEXT`, `AUTHOR_ID`, `STARTTIME`,`DURATIONINMINUTES`) VALUES ("EVENT", NOW(), 0, "Event nr 1", "There will be a 2 hour event today.", 1, timestamp_plus_x_days_with_y_time(0, '20:00'), 120);
INSERT INTO `club_db`.`post` (`post_type`, `CREATED`, `hidden`, `TITLE`, `TEXT`, `AUTHOR_ID`, `STARTTIME`,`DURATIONINMINUTES`) VALUES ("EVENT", NOW(), 0, "Event nr 2", "There will be a 1 hour event tomorrow.", 1, timestamp_plus_x_days_with_y_time(1, '15:00'), 60);
