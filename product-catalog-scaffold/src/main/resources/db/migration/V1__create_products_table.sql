CREATE TABLE products (
  id uuid PRIMARY KEY,
  tenant_id varchar NOT NULL,
  sku varchar NOT NULL,
  name text NOT NULL,
  description text,
  price numeric(12,2) NOT NULL,
  currency varchar(3) NOT NULL DEFAULT 'ZAR',
  available boolean NOT NULL DEFAULT true,
  metadata jsonb,
  created_at timestamptz DEFAULT now(),
  updated_at timestamptz DEFAULT now()
);
CREATE UNIQUE INDEX ux_products_tenant_sku ON products (tenant_id, sku);
CREATE INDEX idx_products_tenant ON products (tenant_id);
