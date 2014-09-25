

CREATE TABLE `t_lottery_users` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '优质用户名单',
  `name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `phonenum` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE `t_lottery_apps` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `app_id` VARCHAR(45) NULL,
  `order` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE `t_lottery_download` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(45) NULL,
  `app_id` INT NULL COMMENT '下载记录表',
  PRIMARY KEY (`id`))ENGINE = InnoDB;

CREATE TABLE `t_lottery_click` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(45) NULL,
  `click_date` DATETIME NULL,
  PRIMARY KEY (`id`))ENGINE = InnoDB;

CREATE TABLE `t_lottery_record` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` VARCHAR(45) NULL,
  `lottery_date` DATETIME NULL COMMENT '中奖时间',
  `sn` VARCHAR(45) NULL COMMENT '兑奖序列号',
  `lottery_grade` INT NULL COMMENT '中奖等级',
  PRIMARY KEY (`id`))
ENGINE = InnoDB;




