DROP TABLE IF EXISTS phones;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(255),
    user_email VARCHAR(255),
    user_password VARCHAR(255),
    user_created VARCHAR(255),
    user_modified VARCHAR(255),
    user_last_login VARCHAR(255),
    user_token VARCHAR(255),
    user_is_active BOOLEAN
);

CREATE TABLE phones (
    phone_id INT PRIMARY KEY AUTO_INCREMENT,
    phone_number VARCHAR(255),
    phone_city_code VARCHAR(255),
    phone_country_code VARCHAR(255),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);