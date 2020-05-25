CREATE TABLE `angajati` (
    `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`nume` VARCHAR(255) NOT NULL,
	`prenume` VARCHAR(255) NOT NULL,
	`experienta` INT NOT NULL,
	`salariu` FLOAT NOT NULL,
	`specializare` VARCHAR(255) NOT NULL,
	`comision` int,
	`caracteristica` VARCHAR(255)
);
CREATE TABLE `lideri` (
    `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
	`nume` VARCHAR(255) NOT NULL,
	`prenume` VARCHAR(255) NOT NULL,
	`experienta` INT NOT NULL,
	`salariu` FLOAT NOT NULL
);
CREATE TABLE `proiecte` (
    `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `index` INT NOT NULL,
    `i` INT NOT NULL
);
CREATE TABLE `echipe` (
    `i` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `id` INT NOT NULL,
    `index` INT NOT NULL
);