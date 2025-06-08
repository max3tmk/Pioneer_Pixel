DROP TABLE IF EXISTS "user" CASCADE;
DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS email_data CASCADE;
DROP TABLE IF EXISTS phone_data CASCADE;

CREATE TABLE "user" (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        date_of_birth DATE NOT NULL,
                        email VARCHAR(255) NOT NULL UNIQUE,
                        password VARCHAR(255) NOT NULL
);

CREATE TABLE account (
                         id SERIAL PRIMARY KEY,
                         user_id INTEGER NOT NULL REFERENCES "user"(id) ON DELETE CASCADE,
                         balance NUMERIC(19, 2) NOT NULL,
                         account_name VARCHAR(255)
);

CREATE TABLE email_data (
                            id SERIAL PRIMARY KEY,
                            user_id INTEGER NOT NULL REFERENCES "user"(id) ON DELETE CASCADE,
                            email VARCHAR(255) NOT NULL
);

CREATE TABLE phone_data (
                            id SERIAL PRIMARY KEY,
                            user_id INTEGER NOT NULL REFERENCES "user"(id) ON DELETE CASCADE,
                            phone_number VARCHAR(20) NOT NULL
);
