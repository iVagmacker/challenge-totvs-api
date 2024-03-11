CREATE TABLE customer (
  id SERIAL PRIMARY KEY UNIQUE NOT NULL,
  name TEXT NOT NULL,
  district TEXT,
  address TEXT
);