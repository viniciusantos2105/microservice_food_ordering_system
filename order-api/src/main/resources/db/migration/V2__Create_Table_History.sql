CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE order_history
(
    order_history_id  UUID DEFAULT uuid_generate_v4() NOT NULL,
    order_order_id    UUID                            NOT NULL,
    order_status      SMALLINT,
    order_status_date TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_order_history PRIMARY KEY (order_history_id)
);

ALTER TABLE order_history
    ADD CONSTRAINT FK_ORDER_HISTORY_ON_ORDER_ORDER FOREIGN KEY (order_order_id) REFERENCES "order" (order_id);

ALTER TABLE "order"
    ALTER COLUMN order_restaurant DROP NOT NULL;

ALTER TABLE "order"
    ALTER COLUMN order_user DROP NOT NULL;

ALTER TABLE order_food
    ALTER COLUMN restaurant_id DROP NOT NULL;