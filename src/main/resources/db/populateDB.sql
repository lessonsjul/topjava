DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (dateTime, description, calories, user_id)
VALUES ('2015-05-30T10:00','Завтрак', 145, 100000);

INSERT INTO meals (dateTime, description, calories, user_id)
VALUES ('2015-05-30T13:00','Обед', 500, 100000);

INSERT INTO meals (dateTime, description, calories, user_id)
VALUES ('2015-05-30T20:00','Ужин', 700, 100000);
