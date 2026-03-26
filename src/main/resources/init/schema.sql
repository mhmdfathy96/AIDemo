-- Enable extensions
CREATE EXTENSION IF NOT EXISTS vector;
CREATE EXTENSION IF NOT EXISTS hstore;

-- Create table using halfvec (16-bit floats) 
-- This allows 3072 dims in an HNSW index
CREATE TABLE IF NOT EXISTS vector_store (
    id text PRIMARY KEY,
    content text,
    metadata json,
    embedding halfvec(3072) 
);

-- Create the HNSW index using halfvec_cosine_ops
CREATE INDEX IF NOT EXISTS vector_store_embedding_idx 
ON vector_store USING HNSW (embedding halfvec_cosine_ops);