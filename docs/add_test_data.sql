START TRANSACTION;
USE `oobbit`;
INSERT INTO `oobbit`.`access_levels` (`level`, `name`, `description`) VALUES (1, 'User', 'Normal user');
INSERT INTO `oobbit`.`access_levels` (`level`, `name`, `description`) VALUES (10, 'Moderator', 'User with extended rights');
INSERT INTO `oobbit`.`access_levels` (`level`, `name`, `description`) VALUES (100, 'Administrator', 'Highest authority');
INSERT INTO `oobbit`.`access_levels` (`level`, `name`, `description`) VALUES (0, 'Banned', 'Restricted user');
COMMIT;

START TRANSACTION;
USE `oobbit`;
INSERT INTO `oobbit`.`users` (`user_id`, `username`, `email`, `password`, `access_level`, `create_time`) VALUES (22, 'admin', 'admin@ooppa.fi', 'admin', 100, '2015-01-01 00:00:00');
INSERT INTO `oobbit`.`users` (`user_id`, `username`, `email`, `password`, `access_level`, `create_time`) VALUES (23, 'moderator', 'moderator@ooppa.fi', 'moderator', 10, '2015-01-01 00:00:00');
INSERT INTO `oobbit`.`users` (`user_id`, `username`, `email`, `password`, `access_level`, `create_time`) VALUES (24, 'user', 'user@ooppa.fi', 'user', 1, '2015-01-01 00:00:00');
INSERT INTO `oobbit`.`users` (`user_id`, `username`, `email`, `password`, `access_level`, `create_time`) VALUES (25, 'banned', 'troll@ooppa.fi', 'troll', 0, '2015-01-01 00:00:00');
INSERT INTO `oobbit`.`users` (`user_id`, `username`, `email`, `password`, `access_level`, `create_time`) VALUES (26, 'user2', 'user2@ooppa.fi', 'user2', 1, '2015-01-01 00:00:00');
COMMIT;

START TRANSACTION;
USE `oobbit`;
INSERT INTO `oobbit`.`categories` (`category_id`, `title`, `description`) VALUES ('v', 'Videos', 'Category - Videos');
INSERT INTO `oobbit`.`categories` (`category_id`, `title`, `description`) VALUES ('p', 'Pictures', 'Category - Pictures');
INSERT INTO `oobbit`.`categories` (`category_id`, `title`, `description`) VALUES ('m', 'Music', 'Category - Music');
INSERT INTO `oobbit`.`categories` (`category_id`, `title`, `description`) VALUES ('d', 'Discussion', 'Category - Discussion');
COMMIT;

START TRANSACTION;
USE `oobbit`;
INSERT INTO `oobbit`.`links` (`link_id`, `title`, `content`, `link`, `category`, `creator`, `create_time`, `edit_time`) VALUES (1, 'Pssst... Hey, You Guys Wanna Buy a Cat?', 'Cat in a box', 'https://i.imgur.com/6LCNnDm.jpg', 'p', 24, '2015-01-01 00:00:00', NULL);
INSERT INTO `oobbit`.`links` (`link_id`, `title`, `content`, `link`, `category`, `creator`, `create_time`, `edit_time`) VALUES (2, 'Heart nose', 'A cat has a LOVELY nose, haha. Get it?', 'https://i.imgur.com/I4vJzFY.jpg', 'p', 26, '2015-01-01 00:00:00', NULL);
COMMIT;

START TRANSACTION;
USE `oobbit`;
INSERT INTO `oobbit`.`comments` (`comment_id`, `link_id`, `creator`, `content`, `create_time`) VALUES (1, 1, 22, 'This link is so good I need to ban you!', '2015-01-01 00:00:00');
INSERT INTO `oobbit`.`comments` (`comment_id`, `link_id`, `creator`, `content`, `create_time`) VALUES (2, 2, 23, 'Nice link, moderator approves!', '2015-01-01 00:00:00');
COMMIT;