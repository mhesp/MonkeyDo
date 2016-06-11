CREATE TABLE users (
  user_id INTEGER IDENTITY,
  user_name VARCHAR(30),
  PRIMARY KEY (user_id)
);

CREATE TABLE lists (
  list_user_name VARCHAR(30),
  list_user_id INTEGER,
  list_name VARCHAR(30),
  FOREIGN KEY (list_user_id) REFERENCES users(user_id)
);

CREATE TABLE tasks (
  task_list_name VARCHAR(30),
  task_list_id INTEGER,
  task_name VARCHAR(30),
  task_due_date TIMESTAMP,
  task_created_date TIMESTAMP,
  done BOOLEAN,
  FOREIGN KEY (task_list_id) REFERENCES lists(list_user_id)
);