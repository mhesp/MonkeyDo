INSERT INTO users (user_name) VALUES ('Maria');
INSERT INTO users (user_name) VALUES ('Kim');

INSERT INTO lists (list_user_id, list_name) VALUES (0, 'TestListMaria');
INSERT INTO lists (list_user_id, list_name) VALUES (1, 'TestListKim');

INSERT INTO tasks (task_list_id, task_name, task_due_date, task_created_date, done)
VALUES (0, 'Kim er et B-menneske :D', null, CURRENT_TIMESTAMP, 'false');
INSERT INTO tasks (task_list_id, task_name, task_due_date, task_created_date, done)
VALUES (0, 'Maria rules!', null, CURRENT_TIMESTAMP, 'false');