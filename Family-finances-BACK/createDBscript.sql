USE FamilyFinances;

CREATE TABLE `Rooms` (
	`id` int NOT NULL,
	`name` TEXT NOT NULL,
	`date` DATE NOT NULL,
	`password` TEXT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Finances` (
	`id` int NOT NULL,
	`id_type` int NOT NULL,
	`id_room_user` int NOT NULL,
	`amount` FLOAT NOT NULL,
	`comment` TEXT NOT NULL,
	`date` DATE NOT NULL,
	`flag` bool NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Users` (
	`id` int NOT NULL,
	`id_role` int NOT NULL ,
	`name` TEXT NOT NULL ,
	`date` DATE NOT NULL ,
	`password` TEXT NOT NULL ,
	`access_token` TEXT NOT NULL ,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Roles` (
	`id` int NOT NULL,
	`role` TEXT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `Types` (
	`id` int NOT NULL,
	`name` TEXT NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `rooms_users` (
	`id_room_user` int NOT NULL,
	`id_room` int NOT NULL ,
	`id_user` int NOT NULL ,
	PRIMARY KEY (`id_room_user`)
);

ALTER TABLE `Finances` ADD CONSTRAINT `Finances_fk0` FOREIGN KEY (`id_type`) REFERENCES `Types`(`id`);

ALTER TABLE `Finances` ADD CONSTRAINT `Finances_fk1` FOREIGN KEY (`id_room_user`) REFERENCES `rooms_users`(`id_room_user`);

ALTER TABLE `Users` ADD CONSTRAINT `Users_fk0` FOREIGN KEY (`id_role`) REFERENCES `Roles`(`id`);

ALTER TABLE `rooms_users` ADD CONSTRAINT `rooms_users_fk0` FOREIGN KEY (`id_room`) REFERENCES `Rooms`(`id`);

ALTER TABLE `rooms_users` ADD CONSTRAINT `rooms_users_fk1` FOREIGN KEY (`id_user`) REFERENCES `Users`(`id`);
