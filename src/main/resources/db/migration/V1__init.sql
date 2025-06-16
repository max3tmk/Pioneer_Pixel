-- V1__init.sql

-- Drop tables if they exist
DROP TABLE IF EXISTS phone_data CASCADE;
DROP TABLE IF EXISTS email_data CASCADE;
DROP TABLE IF EXISTS account CASCADE;
DROP TABLE IF EXISTS "user" CASCADE;

-- User table
CREATE TABLE "user" (
                        id BIGSERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        password VARCHAR(255) NOT NULL,
                        email VARCHAR(255) UNIQUE NOT NULL,
                        date_of_birth DATE NOT NULL
);

-- Account table
CREATE TABLE account (
                         id BIGSERIAL PRIMARY KEY,
                         user_id BIGINT UNIQUE NOT NULL,
                         balance NUMERIC(19,2) NOT NULL DEFAULT 0,
                         initial_balance NUMERIC(19,2) NOT NULL DEFAULT 0,
                         CONSTRAINT fk_account_user FOREIGN KEY (user_id) REFERENCES "user"(id) ON DELETE CASCADE
);

-- Email data table
CREATE TABLE email_data (
                            id BIGSERIAL PRIMARY KEY,
                            user_id BIGINT NOT NULL,
                            email VARCHAR(255) NOT NULL,
                            CONSTRAINT fk_email_user FOREIGN KEY (user_id) REFERENCES "user"(id) ON DELETE CASCADE
);

-- Phone data table
CREATE TABLE phone_data (
                            id BIGSERIAL PRIMARY KEY,
                            user_id BIGINT NOT NULL,
                            phone VARCHAR(13) NOT NULL,
                            CONSTRAINT fk_phone_user FOREIGN KEY (user_id) REFERENCES "user"(id) ON DELETE CASCADE
);
