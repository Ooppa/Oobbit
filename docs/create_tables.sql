CREATE SCHEMA IF NOT EXISTS `oobbit` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `oobbit` ;

CREATE TABLE IF NOT EXISTS `oobbit`.`access_levels` (
  `level` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL DEFAULT '',
  `description` VARCHAR(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`level`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `oobbit`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `access_level` INT NOT NULL DEFAULT 1,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX `access_level_idx` (`access_level` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `user_access_level`
    FOREIGN KEY (`access_level`)
    REFERENCES `oobbit`.`access_levels` (`level`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `oobbit`.`categories` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `oobbit`.`links` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `content` MEDIUMTEXT NULL,
  `link` VARCHAR(255) NOT NULL,
  `category` INT NOT NULL,
  `creator` INT NOT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `edit_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `create_time`),
  INDEX `creator_idx` (`creator` ASC),
  INDEX `catgory_idx` (`category` ASC),
  CONSTRAINT `link_creator_id`
    FOREIGN KEY (`creator`)
    REFERENCES `oobbit`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `link_catgory_id`
    FOREIGN KEY (`category`)
    REFERENCES `oobbit`.`categories` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `oobbit`.`connections` (
  `source_link_id` INT NOT NULL AUTO_INCREMENT,
  `destination_link_id` INT NOT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `creator` INT NOT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `edit_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`source_link_id`, `destination_link_id`),
  INDEX `destination_link_id_idx` (`destination_link_id` ASC),
  CONSTRAINT `connection_source_link_id`
    FOREIGN KEY (`source_link_id`)
    REFERENCES `oobbit`.`links` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `connection_destination_link_id`
    FOREIGN KEY (`destination_link_id`)
    REFERENCES `oobbit`.`links` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `oobbit`.`comments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `link_id` INT NOT NULL,
  `creator` INT NOT NULL,
  `content` MEDIUMTEXT NULL,
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  INDEX `link_id_idx` (`link_id` ASC),
  INDEX `creator_idx` (`creator` ASC),
  CONSTRAINT `comment_link_id`
    FOREIGN KEY (`link_id`)
    REFERENCES `oobbit`.`links` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `comment_creator_id`
    FOREIGN KEY (`creator`)
    REFERENCES `oobbit`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;