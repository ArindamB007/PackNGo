INSERT INTO user (password,email, name,email_validated,created_timestamp,updated_timestamp,deleted_flag)
VALUES ('password','arindam.bandyopadhyay@gmail.com', 'Arindam Bandyopadhyay',FALSE , now(),now(),FALSE );

INSERT into role(name,description) VALUES ("ROLE_USER","An applicaiton user");
