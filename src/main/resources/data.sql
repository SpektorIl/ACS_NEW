-- Insert authors
INSERT INTO public.author (name, birth_date, country) VALUES
                                                       ('William Shakespeare', '1564-04-26', 'England'),
                                                       ('Jane Austen', '1775-12-16', 'England'),
                                                       ('Mark Twain', '1835-11-30', 'USA'),
                                                       ('Fyodor Dostoevsky', '1821-11-11', 'Russia'),
                                                       ( 'Gabriel Garcia Marquez', '1927-03-06', 'Colombia');

-- Insert books
INSERT INTO public.book (title, publication_date, genre, author_id) VALUES
                                                                     ('Hamlet', '1600-01-01', 'Tragedy', 1),
                                                                     ('Pride and Prejudice', '1813-01-28', 'Romance', 2),
                                                                     ('Adventures of Huckleberry Finn', '1884-12-10', 'Adventure', 3),
                                                                     ('Crime and Punishment', '1866-01-01', 'Psychological Fiction', 4),
                                                                     ('One Hundred Years of Solitude', '1967-06-05', 'Magical Realism', 5),
                                                                     ('Macbeth', '1606-01-01', 'Tragedy', 1),
                                                                     ('Emma', '1815-01-01', 'Romance', 2),
                                                                     ('The Brothers Karamazov', '1880-01-01', 'Philosophical Fiction', 4),
                                                                     ('The Old Man and The Sea', '1952-09-01', 'Literary Fiction', 3),
                                                                     ('Love in the Time of Cholera', '1985-01-01', 'Romance', 5);
