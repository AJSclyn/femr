# --- !Ups

ALTER TABLE `patients`
ADD COLUMN `isDeleted` DATETIME NULL DEFAULT NULL AFTER `photo_id`;

ALTER TABLE `patients`
ADD COLUMN `patientId` VARCHAR(256) NULL DEFAULT NULL AFTER `isDeleted`;

ALTER TABLE `mission_teams`
ADD COLUMN `team_acronym` VARCHAR(256) NULL DEFAULT NULL AFTER `location`;

ALTER TABLE `mission_trips`
ADD COLUMN `trip_acronym` VARCHAR(256) NULL DEFAULT NULL AFTER `end_date`;

# --- !Downs

ALTER TABLE `patients`
DROP COLUMN `isDeleted;

ALTER TABLE `patients`
DROP COLUMN `patientId`;

ALTER TABLE `mission_teams`
DROP COLUMN `team_acronym`;

ALTER TABLE `mission_trips`
DROP COLUMN `trip_acronym`;
