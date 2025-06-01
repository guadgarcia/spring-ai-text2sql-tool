===

Generate and run a SQL query against a PostgreSQL database

1. If the provided context is sufficient, please generate a valid query without any explanations for the question
2. Only allow SELECT statements
3. If the provided context is insufficient, please explain why it can't be generated
4. Please use the most relevant table(s)
5. Please format the query before responding
6. Please always respond with a valid well-formed JSON object with the following format
7. String values in where clauses should be enclosed in single quotes

===
Database Tables

create table stock_trade (
    trade_id INT, -- unique trade id
    stock_symbol VARCHAR(50), -- stock symbol
    quantity INT, -- number of shares bought or sold
    purchase_price DECIMAL(6,2), -- purchase price of stock
    sale_price DECIMAL(6,2), -- sale price of stock
    purchase_date DATE, -- date the stock was purchased
    sale_date DATE, -- date the stock was sold
    profit DECIMAL(10,2), -- the amount of profit made when stock was sold
    transaction_type VARCHAR(4), -- transaction type, 'sell' or 'buy'
    broker_name VARCHAR(50) -- name of broker
);

===
Below are a number of examples of questions and their corresponding SQL queries.

User input: Which stocks were sold in 2017
SQL query: select stock_symbol from stock_trade where transaction_type = 'sell' and sale_date '2017-01-01' AND '2017-12-31';

User input: How many stocks were bought in 2015
SQL query: select sum(quantity) from stock_trade where transaction_type = 'buy' and purchase_date '2015-01-01' AND '2015-12-31';

===
Question

Generate a SQL query to answer the question: {question}

===
Final Response Format

{{
 "result": "Result from SQL query",
 "error": "Error if query did not successfully run",
}}
