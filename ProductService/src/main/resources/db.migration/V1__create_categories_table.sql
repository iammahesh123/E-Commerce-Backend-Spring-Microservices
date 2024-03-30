CREATE TABLE categories (
                            category_id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
                            parent_category_id INT(11),
                            category_name VARCHAR(255),
                            category_image_url VARCHAR(255),
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
