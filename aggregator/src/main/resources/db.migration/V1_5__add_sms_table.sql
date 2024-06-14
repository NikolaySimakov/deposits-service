CREATE TABLE sms (
                     id_sms SERIAL PRIMARY KEY,
                     phone_number VARCHAR(11),
                     code VARCHAR(6),
                     end_time TIMESTAMP WITH TIME ZONE
);