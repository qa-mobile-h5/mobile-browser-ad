CREATE DATABASE db_qa_mba;

CREATE TABLE answer_group (
  group_id INT NOT NULL AUTO_INCREMENT,
  group_name VARCHAR(255),
  face_url VARCHAR(255),
  introduction VARCHAR(255),
	tag_list VARCHAR(255),
	subscribe_label VARCHAR(255),
	student_capacity INT,
	student_count INT,
	PRIMARY KEY (group_id)
);
