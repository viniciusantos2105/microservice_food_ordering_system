CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "order"
(
    order_id          UUID DEFAULT uuid_generate_v4() NOT NULL,
    order_user        UUID                            NOT NULL,
    order_restaurant  UUID                            NOT NULL,
    order_total_price DOUBLE PRECISION,
    order_status      SMALLINT,
    CONSTRAINT pk_order PRIMARY KEY (order_id)
);

CREATE TABLE order_food
(
    order_food_id    UUID DEFAULT uuid_generate_v4() NOT NULL,
    order_id         UUID,
    food_name        VARCHAR(255),
    food_description VARCHAR(255),
    food_price       DOUBLE PRECISION,
    restaurant_id    UUID                            NOT NULL,
    CONSTRAINT pk_order_food PRIMARY KEY (order_food_id)
);

ALTER TABLE order_food
    ADD CONSTRAINT FK_ORDER_FOOD_ON_ORDER FOREIGN KEY (order_id) REFERENCES "order" (order_id);