CREATE TABLE IF NOT EXISTS items (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(19, 2) NOT NULL,
    quantity INT NOT NULL,
    type VARCHAR(20) NOT NULL,

    CONSTRAINT chk_item_price CHECK (price > 0),
    CONSTRAINT chk_item_quantity CHECK (quantity > 0),
    CONSTRAINT chk_item_type CHECK (
    type IN ('RAW', 'MANUFACTURED', 'IMPORTED')
    )
    );