CREATE TABLE products (
                          product_id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
                          category_id INT(11),
                          product_name VARCHAR(255),
                          product_image_url VARCHAR(255),
                          sku VARCHAR(255),
                          product_price DECIMAL(7, 2),
                          product_quantity INT(11),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
