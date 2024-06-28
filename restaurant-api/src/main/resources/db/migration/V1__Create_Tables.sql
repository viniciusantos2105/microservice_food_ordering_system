CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE food
(
    food_id          UUID DEFAULT uuid_generate_v4() NOT NULL,
    food_name        VARCHAR(255),
    food_description VARCHAR(255),
    food_price       DOUBLE PRECISION,
    restaurant_id    UUID,
    CONSTRAINT pk_food PRIMARY KEY (food_id)
);

CREATE TABLE restaurant
(
    restaurant_id      UUID DEFAULT uuid_generate_v4() NOT NULL,
    owner_id           UUID NOT NULL ,
    restaurant_name    VARCHAR(255),
    restaurant_address VARCHAR(255),
    CONSTRAINT pk_restaurant PRIMARY KEY (restaurant_id)
);

CREATE TABLE restaurant_foods
(
    food_id       UUID NOT NULL,
    restaurant_id UUID NOT NULL
);

ALTER TABLE restaurant_foods
    ADD CONSTRAINT uc_restaurant_foods_food UNIQUE (food_id);

ALTER TABLE restaurant
    ADD CONSTRAINT uc_restaurant_restaurant_name UNIQUE (restaurant_name);

ALTER TABLE food
    ADD CONSTRAINT FK_FOOD_ON_RESTAURANT FOREIGN KEY (restaurant_id) REFERENCES restaurant (restaurant_id);

ALTER TABLE restaurant_foods
    ADD CONSTRAINT fk_resfoo_on_food FOREIGN KEY (food_id) REFERENCES food (food_id);

ALTER TABLE restaurant_foods
    ADD CONSTRAINT fk_resfoo_on_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant (restaurant_id);