CREATE DATABASE surveys_hell;
USE surveys_hell;

CREATE TABLE roles(
    id INT(8) AUTO_INCREMENT,
    name VARCHAR(255),
    CONSTRAINT pk_id_roles PRIMARY KEY (id)
);

CREATE TABLE users(
    id INT(8) AUTO_INCREMENT,
    enabled BOOLEAN,
    username VARCHAR(12),
    password VARCHAR(255),
    CONSTRAINT pk_id_users PRIMARY KEY (id)
);

CREATE TABLE user_roles(
    role_id INT(8),
    user_id INT(8),
    CONSTRAINT pk_id_user_roles PRIMARY KEY (role_id, user_id),
    CONSTRAINT fk_id_user_roles FOREIGN KEY (role_id) REFERENCES roles(id),
    CONSTRAINT fk_id_roles_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE categories_catalog(
    id INT(8) AUTO_INCREMENT,
    created_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    name VARCHAR(255),
    CONSTRAINT pk_id_categories_cat PRIMARY KEY (id)
);

CREATE TABLE surveys(
    id INT(8) AUTO_INCREMENT,
    created_at TIMESTAMP(6),
    updated_at TIMESTAMP(6),
    description VARCHAR(255),
    name VARCHAR(255),
    CONSTRAINT pk_id_surveys PRIMARY KEY (id)
);

CREATE TABLE chapter(
    id INT(8) AUTO_INCREMENT,
    created_at TIMESTAMP(6),
    survey_id INT(8),
    updated_at TIMESTAMP(6),
    chapter_number VARCHAR(50),
    chapter_title VARCHAR(50),
    CONSTRAINT pk_id_chapter PRIMARY KEY (id),
    CONSTRAINT fk_id_survey_c FOREIGN KEY (survey_id) REFERENCES surveys(id)
);

CREATE TABLE questions(
    id INT(8) AUTO_INCREMENT,
    chapter_id INT(8),
    created_at timestamp(6),
    updated_at timestamp(6),
    question_number VARCHAR(10),
    response_type VARCHAR(20),
    comment_question TEXT,
    question_text TEXT,
    CONSTRAINT pk_id_questions PRIMARY KEY (id),
    CONSTRAINT fk_id_chapter_q FOREIGN KEY (id) REFERENCES chapter(id)
);

CREATE TABLE response_options(
    id INT(8) AUTO_INCREMENT,
    option_value INT(4),
    category_catalog_id INT(8),
    created_at timestamp(6),
    parent_response_id INT(8),
    question_id INT(8),
    updated_at TIMESTAMP(6),
    type_component_html VARCHAR(30),
    comment_reponse TEXT,
    option_text TEXT,
    CONSTRAINT pk_id_response_options PRIMARY KEY (id),
    CONSTRAINT fk_id_question_ro FOREIGN KEY (question_id) REFERENCES questions(id),
    CONSTRAINT fk_id_category_ro FOREIGN KEY (category_catalog_id) REFERENCES categories_catalog(id)
);

CREATE TABLE subresponse_options(
    id INT(8) AUTO_INCREMENT,
    subresponse_number INT(4),
    created_at TIMESTAMP(6),
    response_options_id INT(8),
    updated_at TIMESTAMP(6),
    component_html VARCHAR(225),
    subresponse_text VARCHAR(225),
    CONSTRAINT pk_id_subr_op PRIMARY KEY (id),
    CONSTRAINT fk_id_response_options FOREIGN KEY (response_options_id) REFERENCES response_options(id)
);

CREATE TABLE response_question(
    id INT(8) AUTO_INCREMENT,
    response_id INT(8),
    subresponse_id INT(8),
    response_text VARCHAR(80),
    CONSTRAINT pk_id_response_q PRIMARY KEY (id),
    CONSTRAINT fk_id_response FOREIGN KEY (response_id) REFERENCES response_options(id),
    CONSTRAINT fk_id_subresponse FOREIGN KEY (subresponse_id) REFERENCES subresponse_options(id)
);