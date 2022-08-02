CREATE TABLE IF NOT EXISTS license
(
    id              bigserial,
    license_id      varchar(100) NOT NULL,
    description     varchar(100),
    organization_id varchar(100) NOT NULL,
    product_name    varchar(100) NOT NULL,
    license_type    varchar(100) NOT NULL
);
