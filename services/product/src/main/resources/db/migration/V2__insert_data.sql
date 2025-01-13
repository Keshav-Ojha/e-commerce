-- Insert dummy data into the category table
INSERT INTO category (id, description, name)
VALUES
    (nextval('category_seq'), 'Electronics category', 'Electronics'),
    (nextval('category_seq'), 'Books category', 'Books'),
    (nextval('category_seq'), 'Clothing category', 'Clothing'),
    (nextval('category_seq'), 'Groceries category', 'Groceries');

-- Insert dummy data into the product table
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES
    (nextval('product_seq'), 'Smartphone with 128GB storage', 'Smartphone', 100, 699.99,
     (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), 'Laptop with 16GB RAM', 'Laptop', 50, 999.99,
     (SELECT id FROM category WHERE name = 'Electronics')),
    (nextval('product_seq'), 'Science fiction novel', 'Sci-fi Book', 200, 19.99,
     (SELECT id FROM category WHERE name = 'Books')),
    (nextval('product_seq'), 'T-shirt with custom design', 'Custom T-shirt', 150, 14.99,
     (SELECT id FROM category WHERE name = 'Clothing')),
    (nextval('product_seq'), 'Pack of organic vegetables', 'Organic Veggies', 300, 9.99,
     (SELECT id FROM category WHERE name = 'Groceries'));
