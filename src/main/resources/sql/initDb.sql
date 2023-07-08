CREATE TABLE book_category(
    id SERIAL PRIMARY KEY,
    name varchar(64) NOT NULL
);

CREATE TABLE book(
    id SERIAL PRIMARY KEY,
    name varchar(128) NOT NULL,
    author varchar(64) NOT NULL,
    description varchar(255) NOT NULL,
    bookCategory_id int NOT NULL,
    FOREIGN KEY (bookCategory_id) REFERENCES book_category(id)
);


