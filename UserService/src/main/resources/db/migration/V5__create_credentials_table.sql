CREATE TABLE credentials (
                             credential_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
                             user_id INT,
                             username VARCHAR(255),
                             password VARCHAR(255),
                             role VARCHAR(255),
                             is_enabled BOOLEAN DEFAULT false,
                             is_account_non_expired BOOLEAN DEFAULT true,
                             is_account_non_locked BOOLEAN DEFAULT true,
                             is_credentials_non_expired BOOLEAN DEFAULT true,
                             created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                             updated_at TIMESTAMP NULL DEFAULT NULL

);