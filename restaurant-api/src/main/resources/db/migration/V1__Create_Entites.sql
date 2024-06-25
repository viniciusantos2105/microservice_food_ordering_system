CREATE TABLE food
(
    food_id          BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    food_name        VARCHAR(255),
    food_description VARCHAR(255),
    food_price       DOUBLE PRECISION,
    CONSTRAINT pk_food PRIMARY KEY (food_id)
);

CREATE TABLE restaurant
(
    restaurant_id      BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    restaurant_owner   BIGINT,
    restaurant_name    VARCHAR(255),
    restaurant_address VARCHAR(255),
    CONSTRAINT pk_restaurant PRIMARY KEY (restaurant_id)
);

CREATE TABLE restaurant_foods
(
    food_id       BIGINT NOT NULL,
    restaurant_id BIGINT NOT NULL
);

CREATE TABLE "user"
(
    user_id       BIGINT NOT NULL,
    user_name     VARCHAR(255),
    user_email    VARCHAR(255),
    user_password VARCHAR(255),
    user_type     SMALLINT,
    CONSTRAINT pk_user PRIMARY KEY (user_id)
);

ALTER TABLE restaurant_foods
    ADD CONSTRAINT uc_restaurant_foods_food UNIQUE (food_id);

ALTER TABLE restaurant
    ADD CONSTRAINT uc_restaurant_restaurant_name UNIQUE (restaurant_name);

ALTER TABLE "user"
    ADD CONSTRAINT uc_user_user_email UNIQUE (user_email);

ALTER TABLE restaurant
    ADD CONSTRAINT FK_RESTAURANT_ON_RESTAURANT_OWNER FOREIGN KEY (restaurant_owner) REFERENCES "user" (user_id);

ALTER TABLE restaurant_foods
    ADD CONSTRAINT fk_resfoo_on_food FOREIGN KEY (food_id) REFERENCES food (food_id);

ALTER TABLE restaurant_foods
    ADD CONSTRAINT fk_resfoo_on_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant (restaurant_id);