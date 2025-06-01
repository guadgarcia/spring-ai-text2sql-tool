create table stock_trade (
	trade_id INT,
	stock_symbol VARCHAR(50),
	quantity INT,
	purchase_price DECIMAL(6,2),
	sale_price DECIMAL(6,2),
	purchase_date DATE,
	sale_date DATE,
	profit DECIMAL(10,2),
	transaction_type VARCHAR(4),
	broker_name VARCHAR(50)
);
