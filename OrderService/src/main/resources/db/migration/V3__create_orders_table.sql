
CREATE TABLE orders (
                        order_id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
                        cart_id INT(11),
                        order_date TIMESTAMP DEFAULT LOCALTIMESTAMP NOT NULL NULL_TO_DEFAULT,
                        order_desc VARCHAR(255),
                        order_fee DECIMAL(7, 2),
                        created_at TIMESTAMP DEFAULT LOCALTIMESTAMP NOT NULL NULL_TO_DEFAULT,
                        updated_at TIMESTAMP
);

