CREATE TABLE if NOT EXISTS users (
    username VARCHAR(20) NOT NULL PRIMARY KEY,
    password  VARCHAR(100) NOT NULL,
    enabled boolean NOT NULL
);

CREATE TABLE  if NOT EXISTS authorities (
    username VARCHAR(20) NOT NULL,
    authority VARCHAR(20) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY(username) REFERENCES users(username)
);

CREATE TABLE if NOT EXISTS exercise (
    id IDENTITY,
    muscle_group VARCHAR(20) NOT NULL,
    exercise_name VARCHAR(40) NOT NULL,
    number_of_reps int,
    weight float,
    description VARCHAR(120) NOT NULL
);