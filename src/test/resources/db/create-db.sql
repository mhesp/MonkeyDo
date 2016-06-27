CREATE TABLE users (
  user_id INTEGER IDENTITY PRIMARY KEY,
  user_name VARCHAR(30)
);

CREATE TABLE lists (
  list_user_id INTEGER,
  list_name VARCHAR(30),
  list_id INTEGER IDENTITY PRIMARY KEY,
  FOREIGN KEY (list_user_id) REFERENCES users(user_id)
);

CREATE TABLE tasks (
  task_id INTEGER IDENTITY PRIMARY KEY,
  task_list_id INTEGER,
  task_name VARCHAR(30),
  task_due_date TIMESTAMP,
  task_created_date TIMESTAMP,
  done BOOLEAN,
  FOREIGN KEY (task_list_id) REFERENCES lists(list_id)
);