CREATE TABLE IF NOT EXISTS organization
(
    id                bigserial,
    organization_id   varchar(100) NOT NULL,
    organization_name varchar(100) NOT NULL,
    contact_name      varchar(100) NOT NULL,
    contact_email     varchar(100) NOT NULL,
    contact_phone     varchar(100) NOT NULL
);
