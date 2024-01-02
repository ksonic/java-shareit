CREATE TABLE IF NOT EXISTS users (
  id long auto_increment PRIMARY KEY,
  name varchar,
  email varchar
);
CREATE TABLE IF NOT EXISTS items (
  id long auto_increment PRIMARY KEY,
  name varchar,
  description varchar,
  available BOOLEAN DEFAULT TRUE,
  owner long
);

commit;