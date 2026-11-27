ALTER TABLE addresses ADD COLUMN deleted_at TIMESTAMP(6) WITH TIME ZONE;
ALTER TABLE items ADD COLUMN deleted_at TIMESTAMP(6) WITH TIME ZONE;

CREATE UNIQUE INDEX uq_categories_business_name ON categories (business_id, lower(name));
