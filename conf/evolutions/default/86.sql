# --- !Ups

ALTER TABLE `patients`
ADD COLUMN `isDeleted` DATETIME NULL DEFAULT NULL AFTER `photo_id`;

ALTER TABLE `patients`
ADD COLUMN `patientId` VARCHAR(256) NULL DEFAULT NULL AFTER `isDeleted`;


# --- !Downs

ALTER TABLE `patients`
DROP COLUMN `isDeleted;

ALTER TABLE `patients`
DROP COLUMN `patientId`;