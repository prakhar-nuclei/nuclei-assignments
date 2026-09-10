INSERT INTO items (name, price, quantity, type)
SELECT 'Laptop', 100.00, 2, 'RAW'
    WHERE NOT EXISTS (
    SELECT 1
    FROM items
    WHERE name = 'Laptop'
);

INSERT INTO items (name, price, quantity, type)
SELECT 'Phone', 500.00, 3, 'MANUFACTURED'
    WHERE NOT EXISTS (
    SELECT 1
    FROM items
    WHERE name = 'Phone'
);

INSERT INTO items (name, price, quantity, type)
SELECT 'Watch', 1000.00, 1, 'IMPORTED'
    WHERE NOT EXISTS (
    SELECT 1
    FROM items
    WHERE name = 'Watch'
);