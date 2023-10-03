CREATE TABLE IF NOT EXISTS student (
    id_student SERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    school_id INT NOT NULL
);