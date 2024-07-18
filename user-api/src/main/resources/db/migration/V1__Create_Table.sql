CREATE
EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "user"
(
    user_id       UUID DEFAULT uuid_generate_v4() NOT NULL,
    user_name     VARCHAR(255),
    user_email    VARCHAR(255),
    user_password VARCHAR(255),
    user_type     SMALLINT,
    CONSTRAINT pk_user PRIMARY KEY (user_id)
);

ALTER TABLE "user"
    ADD CONSTRAINT uc_user_user_email UNIQUE (user_email);