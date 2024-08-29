insert into surveys (created_at, updated_at, description, name) values (Now(), Now(), "Preoperative Survey for Open Heart Surgery", "Open Heart Survey");

insert into chapter (created_at, survey_id, updated_at, chapter_number, chapter_title) values 
(NOW(), 1, NOW(), 1, "Patient Information"),
(NOW(), 1, NOW(), 2, "Personal Medical History"),
(NOW(), 1, NOW(), 3, "Cardiovascular Status Evaluation"),
(NOW(), 1, NOW(), 4, "Family Histor"),
(NOW(), 1, NOW(), 5, "Preoperative Assessment"),
(NOW(), 1, NOW(), 6, "Information about the Surgery");

select * from chapter;

insert into questions (chapter_id, created_at, updated_at, question_number, response_type, comment_question, question_text) values
(1, NOW(), NOW(), 1, "checkbox", null, "What is your age?"),
(1, NOW(), NOW(), 2, "checkbox", null, "What is your gender?"),
(1, NOW(), NOW(), 3, "checkbox", null, "Are you pregnant? (for women only)"),
(1, NOW(), NOW(), 4, "checkbox", null, "What is your weight and height?"),
(1, NOw(), NOW(), 5, "checkbox", null, "Do you have a history of tobacco use?"),
(2, NOW(), NOW(), 1, "checkbox", null, "Do you have a history of heart disease?"),
(2, NOW(), NOW(), 2, "checkbox", null, "Do you have any other chronic illnesses?"),
(2, NOW(), NOW(), 3, "checkbox", null, "Have you had any previous surgeries?"),
(2, NOW(), NOW(), 4, "checkbox", null, "Do you have any allergies?"),
(3, NOW(), NOW(), 1, "checkbox", null, "Do you experience chest pain?"),
(3, NOW(), NOW(), 2, "checkbox", null, "Do you experience shortness of breath?"),
(3, NOW(), NOW(), 3, "checkbox", null, "Have you noticed swelling in your legs or ankles?"),
(3, NOW(), NOW(), 4, "checkbox", null, "Have you had any dizziness or fainting recently?"),
(4, NOW(), NOW(), 1, "checkbox", null, "Do you have any direct family members with a history of heart disease?"),
(4, NOW(), NOW(), 2, "checkbox", null, "Are there any instances of sudden deaths in your family?"),
(5, NOW(), NOW(), 1, "checkbox", null, "Have you received preoperative instructions from your doctor?"),
(5, NOW(), NOW(), 2, "checkbox", null, "Have you followed any special diet before the surgery?"),
(5, NOW(), NOW(), 3, "checkbox", null, "Are you currently taking any medications?"),
(6, NOW(), NOW(), 1, "checkbox", null, "Are you aware of the risks and benefits of open heart surgery?");

INSERT INTO categories_catalog (created_at, updated_at, name)
VALUES 
(NOW(), NOW(), 'Age Group'),
(NOW(), NOW(), 'Risk Associated with Age'),
(NOW(), NOW(), 'Gender Identity'),
(NOW(), NOW(), 'Special Considerations'),
(NOW(), NOW(), 'Pregnancy Status'),
(NOW(), NOW(), 'Risk for Surgery'),
(NOW(), NOW(), 'Body Mass Index (BMI)'),
(NOW(), NOW(), 'Risk Associated with Weight'),
(NOW(), NOW(), 'Tobacco Use'),
(NOW(), NOW(), 'Daily Tobacco Consumption'),
(NOW(), NOW(), 'Duration of Tobacco Use'),
(NOW(), NOW(), 'Impact on Surgery'),
(NOW(), NOW(), 'Type of Heart Disease'),
(NOW(), NOW(), 'Severity of Disease'),
(NOW(), NOW(), 'Chronic Illnesses'),
(NOW(), NOW(), 'Impact on Surgery'),
(NOW(), NOW(), 'Type of Surgery'),
(NOW(), NOW(), 'Recovery from Previous Surgeries'),
(NOW(), NOW(), 'Type of Allergy'),
(NOW(), NOW(), 'Severity of Allergy'),
(NOW(), NOW(), 'Frequency of Pain'),
(NOW(), NOW(), 'Activities Triggering Pain'),
(NOW(), NOW(), 'Intensity of Pain'),
(NOW(), NOW(), 'Situations of Shortness of Breath'),
(NOW(), NOW(), 'Severity of Shortness of Breath'),
(NOW(), NOW(), 'Type of Swelling'),
(NOW(), NOW(), 'Location of Swelling'),
(NOW(), NOW(), 'Frequency of Dizziness or Fainting'),
(NOW(), NOW(), 'Activities Triggering Dizziness or Fainting'),
(NOW(), NOW(), 'Family Relationship'),
(NOW(), NOW(), 'Type of Family Heart Disease'),
(NOW(), NOW(), 'Age at Time of Death'),
(NOW(), NOW(), 'Possible Cause of Death'),
(NOW(), NOW(), 'Level of Preparation'),
(NOW(), NOW(), 'Understanding of Instructions'),
(NOW(), NOW(), 'Type of Preoperative Diet'),
(NOW(), NOW(), 'Adherence to Diet'),
(NOW(), NOW(), 'Current Medications'),
(NOW(), NOW(), 'Impact on Surgery'),
(NOW(), NOW(), 'Level of Knowledge'),
(NOW(), NOW(), 'Perceived Risks');

INSERT INTO surveys (created_at, updated_at, description, name)
VALUES (NOW(), NOW(), 'Preoperative Survey for Open Heart Surgery', 'Heart Surgery Survey');

INSERT INTO chapter (created_at, updated_at, survey_id, chapter_number, chapter_title)
VALUES
(NOW(), NOW(), LAST_INSERT_ID(), '1', 'Patient Information'),
(NOW(), NOW(), LAST_INSERT_ID(), '2', 'Personal Medical History'),
(NOW(), NOW(), LAST_INSERT_ID(), '3', 'Cardiovascular Status Evaluation'),
(NOW(), NOW(), LAST_INSERT_ID(), '4', 'Family History'),
(NOW(), NOW(), LAST_INSERT_ID(), '5', 'Preoperative Assessment'),
(NOW(), NOW(), LAST_INSERT_ID(), '6', 'Information about the Surgery');

INSERT INTO questions (created_at, updated_at, chapter_id, question_number, response_type, comment_question, question_text)
VALUES 
(NOW(), NOW(), 1, '1', 'dropdown', 'Age-related risk factor', 'What is your age?'),
(NOW(), NOW(), 1, '2', 'dropdown', 'Gender considerations', 'What is your gender?'),
(NOW(), NOW(), 1, '3', 'yes_no', 'Applicable only to women', 'Are you pregnant? (for women only)'),
(NOW(), NOW(), 1, '4', 'text', 'BMI calculation', 'What is your weight and height?'),
(NOW(), NOW(), 1, '5', 'yes_no', 'Smoking history impact on surgery', 'Do you have a history of tobacco use?');
-- Y así sucesivamente para el resto de las preguntas.

INSERT INTO response_options (option_value, category_catalog_id, created_at, parent_response_id, question_id, updated_at, type_component_html, comment_reponse, option_text)
VALUES
(1, 1, NOW(), NULL, 1, NOW(), 'radio', 'Age group option', 'Under 18 years'),
(2, 1, NOW(), NULL, 1, NOW(), 'radio', 'Age group option', '18-35 years'),
(3, 1, NOW(), NULL, 1, NOW(), 'radio', 'Age group option', '36-60 years'),
(4, 1, NOW(), NULL, 1, NOW(), 'radio', 'Age group option', 'Over 60 years'),
(1, 2, NOW(), NULL, 1, NOW(), 'radio', 'Risk associated with age', 'Low risk'),
(2, 2, NOW(), NULL, 1, NOW(), 'radio', 'Risk associated with age', 'Moderate risk'),
(3, 2, NOW(), NULL, 1, NOW(), 'radio', 'Risk associated with age', 'High risk');
-- Y así sucesivamente para el resto de las opciones de respuesta.

INSERT INTO subresponse_options (subresponse_number, created_at, response_options_id, updated_at, component_html, subresponse_text)
VALUES
(1, NOW(), 3, NOW(), 'text', 'Specify the type of surgery'),
(2, NOW(), 5, NOW(), 'text', 'Specify the name and dosage');
-- Y así sucesivamente para el resto de las subopciones de respuesta.

INSERT INTO response_question (response_id, subresponse_id, response_text)
VALUES
(1, NULL, 'Selected Age Group: 18-35 years'),
(2, NULL, 'Selected Risk Level: Moderate risk');
-- Y así sucesivamente para las demás respuestas.
