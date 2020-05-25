DROP TABLE IF EXISTS TBL_TRANSACTIONS;

CREATE TABLE TBL_TRANSACTIONS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    time TIME NOT NULL,
    value DECIMAL NOT NULL,
    card_application ENUM('DEBITO', 'CREDITO', 'VOUCHER') NOT NULL,
    payment_method ENUM('SUCCESS', 'PENDING', 'CANCELED', 'FAILED') NOT NULL
);