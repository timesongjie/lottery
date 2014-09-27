

CREATE TABLE `t_lottery_users` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '优质用户名单',
  `name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `phonenum` VARCHAR(45) NULL,
  `userid` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
ALTER TABLE `t_lottery_users` 
ADD UNIQUE INDEX `userid_index` (`userid` ASC);



CREATE TABLE `t_lottery_download` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(45) NULL,
  `app_id` INT NULL COMMENT '下载记录表',
  `download_time`DATETIME, 
  PRIMARY KEY (`id`))ENGINE = InnoDB;
ALTER TABLE `t_lottery_download` 
ADD INDEX `userid_index` (`userId` ASC);


CREATE TABLE `t_lottery_click_0930` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(45) NULL,
  `grade` TINYINT(1) NULL,
  `click_date` DATETIME,
  PRIMARY KEY (`id`))ENGINE = InnoDB;
ALTER TABLE `t_lottery_click_0930` 
ADD INDEX `userid_index` (`userId` ASC);

CREATE TABLE `t_lottery_click_1001` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(45) NULL,
  `grade`  TINYINT(2) NULL,
  `click_date` DATETIME ,
  PRIMARY KEY (`id`))ENGINE = InnoDB;
ALTER TABLE `t_lottery_click_1001` 
ADD INDEX `userid_index` (`userId` ASC);

CREATE TABLE `t_lottery_click_1002` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(45) NULL,
  `grade`  TINYINT(2) NULL,
  `click_date` DATETIME ,
  PRIMARY KEY (`id`))ENGINE = InnoDB;
ALTER TABLE `t_lottery_click_1002` 
ADD INDEX `userid_index` (`userId` ASC);


CREATE TABLE `t_lottery_click_1003` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(45) NULL,
  `grade` TINYINT(2) NULL,
  `click_date` DATETIME ,
  PRIMARY KEY (`id`))ENGINE = InnoDB;
ALTER TABLE `t_lottery_click_1003` 
ADD INDEX `userid_index` (`userId` ASC);

CREATE TABLE `t_lottery_click_1004` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(45) NULL,
  `grade` TINYINT(2) NULL,
  `click_date` DATETIME ,
  PRIMARY KEY (`id`))ENGINE = InnoDB;
ALTER TABLE `t_lottery_click_1004` 
ADD INDEX `userid_index` (`userId` ASC);

CREATE TABLE `t_lottery_click_1005` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(45) NULL,
  `grade` TINYINT(2) NULL,
  `click_date` DATETIME ,
  PRIMARY KEY (`id`))ENGINE = InnoDB;
ALTER TABLE `t_lottery_click_1005` 
ADD INDEX `userid_index` (`userId` ASC);

CREATE TABLE `t_lottery_click_1006` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(45) NULL,
  `grade` TINYINT(2) NULL,
  `click_date` DATETIME ,
  PRIMARY KEY (`id`))ENGINE = InnoDB;
ALTER TABLE `t_lottery_click_1006` 
ADD INDEX `userid_index` (`userId` ASC);

CREATE TABLE `t_lottery_click_1007` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(45) NULL,
  `grade` TINYINT(2) NULL,
  `click_date` DATETIME ,
  PRIMARY KEY (`id`))ENGINE = InnoDB;
ALTER TABLE `t_lottery_click_1007` 
ADD INDEX `userid_index` (`userId` ASC);


CREATE TABLE `t_lottery_award` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `grade` TINYINT(2) NULL,
  `code` VARCHAR(50) NULL COMMENT '兑奖码',
  `status` CHAR(1) NULL DEFAULT 0 COMMENT '0未被抽取1已抽取',
  `userid` VARCHAR(45) NULL,
  `lottery_time` DATETIME ,
  `start_time` DATETIME NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
COMMENT = '奖池表';
ALTER TABLE `t_lottery_award` 
ADD INDEX `userid_index` (`userId` ASC);
ALTER TABLE `t_lottery_award` 
ADD INDEX `status_index` (`status` ASC);
ALTER TABLE `t_lottery_award` 
ADD INDEX `grade_index` (`grade` ASC);
ALTER TABLE `t_lottery_award` 
ADD INDEX `start_time_index` (`start_time` ASC);