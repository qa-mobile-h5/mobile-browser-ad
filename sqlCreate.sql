CREATE DATABASE db_qa_mba;

CREATE TABLE answer_group (
  groupId INT NOT NULL AUTO_INCREMENT,
  groupName VARCHAR(255),
  faceUrl VARCHAR(255),
  introduction VARCHAR(255),
	tagList VARCHAR(255),
	subscriberLable VARCHAR(255),
	studentCapacity INT,
	studentCount INT,
	PRIMARY KEY (groupId)
);
