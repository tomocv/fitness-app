INSERT INTO users (username, password, enabled) VALUES
    ('admin', '$2y$12$uI2U6ltxcdF2hzJ6jhjAte67fvlOeF6qa9YF6K6COuvLWG7QJfQSK', 1),
    ('user', '$2y$12$4DJ5xP9a0V4s2mWY7D.YqespjOh1QMhTa.fzRbXv2msW9lAURU8zq', 1);

INSERT INTO authorities (username, authority) VALUES
    ('admin', 'ROLE_ADMIN'),
    ('admin', 'ROLE_USER'),
    ('user', 'ROLE_USER');

INSERT INTO exercise (id, muscle_group, exercise_name, number_of_reps, weight, description) VALUES
    (1, 'Chest', 'Bench-press', '10', '80', 'https://www.youtube.com/embed/2fcZdoRyEiQ?autoplay=1&rel=0');