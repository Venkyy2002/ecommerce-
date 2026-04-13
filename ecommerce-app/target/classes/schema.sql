CREATE DATABASE IF NOT EXISTS ecommerce;
USE ecommerce;

CREATE TABLE IF NOT EXISTS users (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100),
    email    VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    role     VARCHAR(20) DEFAULT 'User'
);

CREATE TABLE IF NOT EXISTS products (
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100),
    category VARCHAR(50),
    price    DOUBLE,
    emoji    VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS cart (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    user_email   VARCHAR(255) NOT NULL,
    product_id   INT          NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    emoji        VARCHAR(10),
    price        DOUBLE       NOT NULL,
    quantity     INT          NOT NULL DEFAULT 1,
    UNIQUE KEY uq_user_product (user_email, product_id)
);

CREATE TABLE IF NOT EXISTS orders (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(255),
    total      DOUBLE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS order_items (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    order_id     INT,
    product_id   INT,
    product_name VARCHAR(255),
    price        DOUBLE,
    quantity     INT,
    FOREIGN KEY (order_id) REFERENCES orders(id)
);

INSERT INTO products (name, category, price, emoji) VALUES
('iPhone 15',          'Electronics', 79999, ''),
('Samsung 55" TV',     'Electronics', 54999, ''),
('Laptop Stand',       'Electronics',  2499, ''),
('Bluetooth Speaker',  'Electronics',  3999, ''),
('Cotton T-Shirt',     'Clothing',      799, ''),
('Running Shoes',      'Clothing',     3499, ''),
('Denim Jeans',        'Clothing',     2199, ''),
('The Alchemist',      'Books',         399, ''),
('Atomic Habits',      'Books',         599, ''),
('Coffee Mug',         'Home',          449, ''),
('Desk Lamp',          'Home',         1299, ''),
('Yoga Mat',           'Sports',        999, ''),
('Dumbbells 5kg',      'Sports',       1499, '');
