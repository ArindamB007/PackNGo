CREATE DATABASE IF NOT EXISTS `pngdb` /*!40100 DEFAULT CHARACTER SET latin1 */;

CREATE TABLE user(
  id_user LONG AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(48) NOT NULL UNIQUE,
  email_validated BOOLEAN NOT NULL DEFAULT FALSE,
  password VARCHAR(60) NOT NULL,
  name VARCHAR(48),
  last_login_timestamp TIMESTAMP,
  created_timestamp TIMESTAMP,
  updated_timestamp TIMESTAMP,
  deleted_flag  BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE role(
  id_role LONG AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(48) NOT NULL,
  description VARCHAR(48)
);

CREATE TABLE user_roles(
  id_user LONG,
  id_role LONG,
  FOREIGN KEY (id_user) REFERENCES user(id_user),
  FOREIGN KEY (id_role) REFERENCES role(id_role)
);
