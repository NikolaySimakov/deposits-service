-- Создание таблицы bank_accounts
CREATE TABLE IF NOT EXISTS bank_accounts (
    id_bank_accounts SERIAL PRIMARY KEY,
    num_bank_accounts NUMERIC(20, 0),
    amount NUMERIC(19,2)
);

-- Создание таблицы customers
CREATE TABLE IF NOT EXISTS customers (
    id_customers SERIAL PRIMARY KEY,
    bank_account_id INTEGER REFERENCES bank_accounts(id_bank_accounts),
    phone_number VARCHAR(11)
);

-- Создание таблицы request_statuses
CREATE TABLE IF NOT EXISTS request_statuses (
    id_request_status SERIAL PRIMARY KEY,
    request_status_name VARCHAR(21)
);

-- Создание таблицы deposits_types
CREATE TABLE IF NOT EXISTS deposits_types (
    id_deposits_types SERIAL PRIMARY KEY,
    deposits_types_name VARCHAR(28)
);

-- Создание таблицы types_percent_payment
CREATE TABLE IF NOT EXISTS types_percent_payment (
                                                     id_type_percent_payment SERIAL PRIMARY KEY,
                                                     type_percent_payment_period VARCHAR(13)
);

-- Создание таблицы deposits
CREATE TABLE IF NOT EXISTS deposits (
                                        id_deposit SERIAL PRIMARY KEY,
                                        deposit_account_id INTEGER REFERENCES bank_accounts(id_bank_accounts),
                                        deposits_type_id INTEGER REFERENCES deposits_types(id_deposits_types),
                                        deposit_refill BOOLEAN,
                                        deposits_amount NUMERIC(19,2),
                                        start_date DATE,
                                        end_date DATE,
                                        deposit_rate DECIMAL(4, 2),
                                        type_percent_payment_id INTEGER REFERENCES types_percent_payment(id_type_percent_payment),
                                        percent_payment_account_id INTEGER,
                                        percent_payment_date DATE,
                                        capitalization BOOLEAN,
                                        deposit_refund_account_id INTEGER
);

-- Создание таблицы customer_deposits
CREATE TABLE IF NOT EXISTS customer_deposits (
                                                 customer_id INTEGER REFERENCES customers(id_customers),
                                                 deposit_id INTEGER REFERENCES deposits(id_deposit),
                                                 PRIMARY KEY (customer_id, deposit_id)
);

-- Создание таблицы requests
CREATE TABLE IF NOT EXISTS requests (
                                        id_request SERIAL PRIMARY KEY,
                                        customer_id INTEGER REFERENCES customers(id_customers),
                                        request_date DATE,
                                        request_status_id INTEGER REFERENCES request_statuses(id_request_status)
);

-- Создание таблицы current_request_status
CREATE TABLE IF NOT EXISTS current_request_status (
                                                      request_id INTEGER REFERENCES requests(id_request),
                                                      request_status_id INTEGER REFERENCES request_statuses(id_request_status),
                                                      change_datetime TIMESTAMPTZ,
                                                      PRIMARY KEY (request_id, request_status_id)
);