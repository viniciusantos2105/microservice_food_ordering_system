ALTER TABLE food
    ADD restaurant_restaurant_id BIGINT;

ALTER TABLE food
    ADD CONSTRAINT FK_FOOD_ON_RESTAURANT_RESTAURANT FOREIGN KEY (restaurant_restaurant_id) REFERENCES restaurant (restaurant_id);