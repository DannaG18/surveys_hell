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