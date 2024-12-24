CREATE DATABASE library_management;

CREATE TABLE Author (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        birth_date DATE,
                        country VARCHAR(50)
);

-- Create Book table
CREATE TABLE Book (
                      id SERIAL PRIMARY KEY,
                      title VARCHAR(150) NOT NULL,
                      publication_date DATE,
                      genre VARCHAR(50),
                      author_id INT,
                      FOREIGN KEY (author_id) REFERENCES Author(id)
);

-- Optional: Insert sample data
INSERT INTO Author (name, birth_date, country) VALUES
                                                   ('J.K. Rowling', '1965-07-31', 'United Kingdom'),
                                                   ('George Orwell', '1903-06-25', 'United Kingdom');

INSERT INTO Book (title, publication_date, genre, author_id) VALUES
    ('Harry Potter and the Philosophers Stone', '1997-06-26', 'Fantasy', 1),
('1984', '1949-06-08', 'Dystopian', 2);