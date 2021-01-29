DELETE FROM exercise;
DELETE FROM authorities;
DELETE FROM users;


INSERT INTO users (username, password, enabled) VALUES
    ('admin', '$2y$12$uI2U6ltxcdF2hzJ6jhjAte67fvlOeF6qa9YF6K6COuvLWG7QJfQSK', 1),
    ('user', '$2y$12$4DJ5xP9a0V4s2mWY7D.YqespjOh1QMhTa.fzRbXv2msW9lAURU8zq', 1);

INSERT INTO authorities (username, authority) VALUES
    ('admin', 'ROLE_ADMIN'),
    ('admin', 'ROLE_USER'),
    ('user', 'ROLE_USER');

INSERT INTO exercise (id, muscle_group, exercise_name, number_of_reps, weight, description) VALUES
    (1, 'Chest', 'Bench-press', '10', '80', 'https://www.youtube.com/embed/2fcZdoRyEiQ?autoplay=1&rel=0'),
    (2, 'Shoulders', 'Dumbbell Lateral Rise', '10', '15', 'https://www.youtube.com/embed/MnPj-4CRdgU?autoplay=1&rel=0'),
    (3, 'Biceps', 'Standing Alternate Biceps Dumbbell Curl', '24', '15', 'https://www.youtube.com/embed/Of_dSFTM9vA?autoplay=1&rel=0'),
    (4, 'Triceps', 'Dips', Null, '0', 'https://www.youtube.com/embed/51f492JJHqg?autoplay=1&rel=0'),
    (5, 'Forearms', 'Dumbbell Wrist Rotation', '15', '0', 'https://www.youtube.com/embed/IKXGwKvbIP4?autoplay=1&rel=0');