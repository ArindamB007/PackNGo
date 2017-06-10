CREATE TABLE user(
  id_user LONG AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(48) NOT NULL,
  password VARCHAR(48) NOT NULL,
  first_name VARCHAR(48),
  last_name VARCHAR(48)
);

CREATE TABLE user_roles(
  id_role LONG AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(48) NOT NULL,
  description VARCHAR(48)
)