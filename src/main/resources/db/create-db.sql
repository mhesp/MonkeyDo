CREATE TABLE users (
  id INTEGER PRIMARY KEY,
  name VARCHAR(30)
);

CREATE TABLE lists (
  owner VARCHAR(30),
  owner_id INTEGER,
  list_name VARCHAR(30)
);

CREATE TABLE tasks (
  owner VARCHAR(30),
  list VARCHAR(30),
  task_name VARCHAR(30)
);