INSERT INTO play (play_name) 
VALUES ('Ancient Greece');
INSERT INTO play (play_name)
VALUES ('Nights Dream');
INSERT INTO play (play_name)
VALUES ('Nights Live');
INSERT INTO play (play_name)
VALUES ('Is He Dead');
INSERT INTO play (play_name)
VALUES ('Comedy of Errors');
INSERT INTO play (play_name)
VALUES ('Inspector');
INSERT INTO play (play_name)
VALUES ('Duchess of Malfi');
INSERT INTO play (play_name)
VALUES ('Hamlet');
INSERT INTO play (play_name)
VALUES ('King John');
INSERT INTO play (play_name)
VALUES ('Wicked');

INSERT INTO genre (genre_name)
VALUES ('Comedy');
INSERT INTO genre (genre_name)
VALUES ('Farce');
INSERT INTO genre (genre_name)
VALUES ('Satirical');
INSERT INTO genre (genre_name)
VALUES ('Tragedy');
INSERT INTO genre (genre_name)
VALUES ('Historical');

INSERT INTO seats (play_id, seats_list)
VALUES ((SELECT id FROM play WHERE play_name='Ancient Greece'),
    ARRAY[false, false, false, false, false, false, false, false, false, false]);
INSERT INTO seats (play_id, seats_list)
VALUES ((SELECT id FROM play WHERE play_name='Nights Dream'),
    ARRAY[false, false, false, false, false, false, false, false, false, false]);
INSERT INTO seats (play_id, seats_list)
VALUES ((SELECT id FROM play WHERE play_name='Nights Live'),
    ARRAY[false, false, false, false, false, false, false, false, false, false]);
INSERT INTO seats (play_id, seats_list)
VALUES ((SELECT id FROM play WHERE play_name='Is He Dead'),
    ARRAY[false, false, false, false, false, false, false, false, false, false]);
INSERT INTO seats (play_id, seats_list)
VALUES ((SELECT id FROM play WHERE play_name='Comedy of Errors'),
    ARRAY[false, false, false, false, false, false, false, false, false, false]);
INSERT INTO seats (play_id, seats_list)
VALUES ((SELECT id FROM play WHERE play_name='Inspector'),
    ARRAY[false, false, false, false, false, false, false, false, false, false]);
INSERT INTO seats (play_id, seats_list)
VALUES ((SELECT id FROM play WHERE play_name='Duchess of Malfi'),
    ARRAY[false, false, false, false, false, false, false, false, false, false]);
INSERT INTO seats (play_id, seats_list)
VALUES ((SELECT id FROM play WHERE play_name='Hamlet'),
    ARRAY[false, false, false, false, false, false, false, false, false, false]);
INSERT INTO seats (play_id, seats_list)
VALUES ((SELECT id FROM play WHERE play_name='King John'),
    ARRAY[false, false, false, false, false, false, false, false, false, false]);
INSERT INTO seats (play_id, seats_list)
VALUES ((SELECT id FROM play WHERE play_name='Wicked'),
    ARRAY[false, false, false, false, false, false, false, false, false, false]);

INSERT INTO play_genre (play_id, genre_id)
VALUES ((SELECT id FROM play WHERE play_name='Ancient Greece'),
        (SELECT id FROM genre WHERE genre_name='Comedy'));
INSERT INTO play_genre (play_id, genre_id)
VALUES ((SELECT id FROM play WHERE play_name='Nights Dream'),
        (SELECT id FROM genre WHERE genre_name='Comedy'));
INSERT INTO play_genre (play_id, genre_id)
VALUES ((SELECT id FROM play WHERE play_name='Nights Live'),
        (SELECT id FROM genre WHERE genre_name='Comedy'));
INSERT INTO play_genre (play_id, genre_id)
VALUES ((SELECT id FROM play WHERE play_name='Is He Dead'),
        (SELECT id FROM genre WHERE genre_name='Farce'));
INSERT INTO play_genre (play_id, genre_id)
VALUES ((SELECT id FROM play WHERE play_name='Comedy of Errors'),
        (SELECT id FROM genre WHERE genre_name='Farce'));
INSERT INTO play_genre (play_id, genre_id)
VALUES ((SELECT id FROM play WHERE play_name='Inspector'),
        (SELECT id FROM genre WHERE genre_name='Satirical'));
INSERT INTO play_genre (play_id, genre_id)
VALUES ((SELECT id FROM play WHERE play_name='Duchess of Malfi'),
        (SELECT id FROM genre WHERE genre_name='Tragedy'));
INSERT INTO play_genre (play_id, genre_id)
VALUES ((SELECT id FROM play WHERE play_name='Hamlet'),
        (SELECT id FROM genre WHERE genre_name='Tragedy'));
INSERT INTO play_genre (play_id, genre_id)
VALUES ((SELECT id FROM play WHERE play_name='King John'),
        (SELECT id FROM genre WHERE genre_name='Historical'));
INSERT INTO play_genre (play_id, genre_id)
VALUES ((SELECT id FROM play WHERE play_name='Wicked'),
        (SELECT id FROM genre WHERE genre_name='Historical'));