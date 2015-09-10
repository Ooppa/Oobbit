START TRANSACTION;
USE `oobbit`;
INSERT INTO `oobbit`.`access_levels` (`level`, `name`, `description`) VALUES (1, 'User', 'Normal user');
INSERT INTO `oobbit`.`access_levels` (`level`, `name`, `description`) VALUES (10, 'Moderator', 'User with extended rights');
INSERT INTO `oobbit`.`access_levels` (`level`, `name`, `description`) VALUES (100, 'Administrator', 'Highest authority');
INSERT INTO `oobbit`.`access_levels` (`level`, `name`, `description`) VALUES (0, 'Banned', 'Restricted user');

COMMIT;

START TRANSACTION;
USE `oobbit`;
INSERT INTO `oobbit`.`users` (`id`, `username`, `email`, `password`, `access_level`, `create_time`) VALUES (22, 'admin', 'admin@ooppa.fi', 'admin', 100, 'NOW()');
INSERT INTO `oobbit`.`users` (`id`, `username`, `email`, `password`, `access_level`, `create_time`) VALUES (23, 'moderator', 'moderator@ooppa.fi', 'moderator', 10, 'NOW()');
INSERT INTO `oobbit`.`users` (`id`, `username`, `email`, `password`, `access_level`, `create_time`) VALUES (24, 'user', 'user@ooppa.fi', 'user', 1, 'NOW()');
INSERT INTO `oobbit`.`users` (`id`, `username`, `email`, `password`, `access_level`, `create_time`) VALUES (25, 'banned', 'troll@ooppa.fi', 'troll', 0, 'NOW()');
INSERT INTO `oobbit`.`users` (`id`, `username`, `email`, `password`, `access_level`, `create_time`) VALUES (26, 'user2', 'user2@ooppa.fi', 'user2', 1, 'NOW()');

COMMIT;

START TRANSACTION;
USE `oobbit`;
INSERT INTO `oobbit`.`links` (`id`, `title`, `content`, `link`, `creator`, `create_time`, `edit_time`) VALUES (1, 'Pssst... Hey, You Guys Wanna Buy a Cat?', 'Cat in a box', 'https://i.imgur.com/6LCNnDm.jpg', 24, 'NOW()', NULL);
INSERT INTO `oobbit`.`links` (`id`, `title`, `content`, `link`, `creator`, `create_time`, `edit_time`) VALUES (2, 'Heart nose', 'A cat has a LOVELY nose, haha. Get it?', 'https://i.imgur.com/I4vJzFY.jpg', 26, 'NOW()', NULL);

COMMIT;

START TRANSACTION;
USE `oobbit`;
INSERT INTO `oobbit`.`comments` (`id`, `link_id`, `creator`, `content`, `create_time`) VALUES (1, 1, 22, 'This link is so good I need to ban you!', 'NOW()');
INSERT INTO `oobbit`.`comments` (`id`, `link_id`, `creator`, `content`, `create_time`) VALUES (2, 2, 23, 'Nice link, moderator approves!', 'NOW()');

COMMIT;

