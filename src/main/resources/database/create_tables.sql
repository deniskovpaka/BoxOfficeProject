CREATE TABLE IF NOT EXISTS play(
  id SERIAL PRIMARY KEY,
  play_name VARCHAR (20) NOT NULL
);

CREATE TABLE IF NOT EXISTS genre(
  id SERIAL PRIMARY KEY,
  genre_name VARCHAR (20) NOT NULL
);

CREATE TABLE IF NOT EXISTS seats(
  id SERIAL PRIMARY KEY,
  play_id INT NOT NULL,
  seats_list boolean ARRAY[10],
  FOREIGN KEY (play_id) REFERENCES play(id),
  UNIQUE (play_id)
);

CREATE TABLE IF NOT EXISTS play_genre (
  id SERIAL PRIMARY KEY,
  play_id INT NOT NULL,
  genre_id INT NOT NULL,
  FOREIGN KEY (play_id) REFERENCES play(id),
  FOREIGN KEY (genre_id) REFERENCES genre(id),
  UNIQUE (play_id, genre_id)
);

CREATE TABLE IF NOT EXISTS ticket(
  id SERIAL PRIMARY KEY,
  ticket_number INT ARRAY[10] NOT NULL,
  play_id INT NOT NULL,
  visitor_name VARCHAR (20),
  FOREIGN KEY (play_id) REFERENCES play(id)
);