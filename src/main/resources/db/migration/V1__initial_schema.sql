-- Initial schema. Mirrors the JPA entities; Hibernate only validates it (ddl-auto=validate).

CREATE TABLE businesses (
    id              UUID                     PRIMARY KEY,
    business_type   VARCHAR(255)             NOT NULL
        CONSTRAINT ck_businesses_type CHECK (business_type IN ('RESTAURANT', 'HOTEL', 'FAST_FOOD')),
    name            VARCHAR(100)             NOT NULL,
    description     VARCHAR(500),
    phone           VARCHAR(16)              NOT NULL,
    street          VARCHAR(100)             NOT NULL,
    city            VARCHAR(100)             NOT NULL,
    lat             DOUBLE PRECISION
        CONSTRAINT ck_businesses_lat CHECK (lat BETWEEN -90 AND 90),
    lon             DOUBLE PRECISION
        CONSTRAINT ck_businesses_lon CHECK (lon BETWEEN -180 AND 180),
    open            BOOLEAN                  NOT NULL,
    deregistered_at TIMESTAMP(6) WITH TIME ZONE
);

-- A phone number is unique among active businesses; a deregistered business frees it up.
CREATE UNIQUE INDEX uq_businesses_phone_active ON businesses (phone) WHERE deregistered_at IS NULL;

CREATE TABLE customers (
    id         UUID                     PRIMARY KEY,
    name       VARCHAR(100)             NOT NULL,
    email      VARCHAR(100)             NOT NULL,
    phone      VARCHAR(16)              NOT NULL,
    created_at TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    closed_at  TIMESTAMP(6) WITH TIME ZONE
);

-- Email and phone are unique among open accounts; closing an account frees them up.
CREATE UNIQUE INDEX uq_customers_email_open ON customers (email) WHERE closed_at IS NULL;
CREATE UNIQUE INDEX uq_customers_phone_open ON customers (phone) WHERE closed_at IS NULL;

CREATE TABLE addresses (
    id          UUID             PRIMARY KEY,
    name        VARCHAR(100),
    customer_id UUID             NOT NULL REFERENCES customers (id),
    street      VARCHAR(100)     NOT NULL,
    city        VARCHAR(100)     NOT NULL,
    postal_code VARCHAR(10),
    latitude    DOUBLE PRECISION NOT NULL CHECK (latitude BETWEEN -90 AND 90),
    longitude   DOUBLE PRECISION NOT NULL CHECK (longitude BETWEEN -180 AND 180)
);

CREATE INDEX ix_addresses_customer ON addresses (customer_id);

CREATE TABLE categories (
    id          UUID         PRIMARY KEY,
    business_id UUID         NOT NULL REFERENCES businesses (id),
    name        VARCHAR(100) NOT NULL
);

CREATE INDEX ix_categories_business ON categories (business_id);

CREATE TABLE items (
    id          UUID          PRIMARY KEY,
    business_id UUID          NOT NULL REFERENCES businesses (id),
    name        VARCHAR(100)  NOT NULL,
    description VARCHAR(500),
    category_id UUID          NOT NULL REFERENCES categories (id),
    price       NUMERIC(12,2) NOT NULL CHECK (price >= 0),
    available   BOOLEAN       NOT NULL
);

CREATE INDEX ix_items_business ON items (business_id);
CREATE INDEX ix_items_category ON items (category_id);

CREATE TABLE orders (
    id           UUID                     PRIMARY KEY,
    customer_id  UUID                     NOT NULL REFERENCES customers (id),
    status       VARCHAR(255)             NOT NULL
        CONSTRAINT ck_orders_status CHECK (status IN
            ('PENDING', 'ACCEPTED', 'PREPARING', 'READY', 'DELIVERED', 'CANCELLED')),
    address_id   UUID                     NOT NULL REFERENCES addresses (id),
    total_amount NUMERIC(12,2)            NOT NULL CHECK (total_amount >= 0),
    business_id  UUID                     NOT NULL REFERENCES businesses (id),
    created_at   TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    updated_at   TIMESTAMP(6) WITH TIME ZONE NOT NULL
);

CREATE INDEX ix_orders_customer ON orders (customer_id);
CREATE INDEX ix_orders_business ON orders (business_id);
CREATE INDEX ix_orders_address  ON orders (address_id);

CREATE TABLE order_items (
    id         UUID          PRIMARY KEY,
    order_id   UUID          NOT NULL REFERENCES orders (id),
    item_id    UUID          NOT NULL REFERENCES items (id),
    quantity   INTEGER       NOT NULL CHECK (quantity > 0),
    unit_price NUMERIC(12,2) NOT NULL CHECK (unit_price >= 0)
);

CREATE INDEX ix_order_items_order ON order_items (order_id);
CREATE INDEX ix_order_items_item  ON order_items (item_id);

CREATE TABLE payments (
    id         UUID                     PRIMARY KEY,
    order_id   UUID                     NOT NULL REFERENCES orders (id),
    method     VARCHAR(255)             NOT NULL
        CONSTRAINT ck_payments_method CHECK (method IN ('CARD', 'CASH', 'WALLET')),
    amount     NUMERIC(12,2)            NOT NULL CHECK (amount >= 0),
    status     VARCHAR(255)             NOT NULL
        CONSTRAINT ck_payments_status CHECK (status IN ('PENDING', 'PAID', 'FAILED', 'REFUNDED')),
    created_at TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    paid_at    TIMESTAMP(6) WITH TIME ZONE
);

CREATE INDEX ix_payments_order ON payments (order_id);
