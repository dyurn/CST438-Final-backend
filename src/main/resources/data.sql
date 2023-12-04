INSERT INTO Questions (question_text, option_a, option_b, option_c, option_d, correct_answer) VALUES
('What is the capital of France?', 'Berlin', 'London', 'Paris', 'Rome', 'C'),
('Who wrote Romeo and Juliet?', 'Mark Twain', 'William Shakespeare', 'Jane Austen', 'Charles Dickens', 'B'),
('What is the chemical symbol for water?', 'CO2', 'H2O', 'O2', 'NaCl', 'B');

INSERT INTO Users (username, password, role) VALUES
('adminUser', '$2a$10$hyU3REVNjVGrqcCGFLxiq.aBTMkLhADcOIv9JmIITWG72vS5e4yPu', 'ADMIN'),
('Dyse', '$2a$10$WvpwLoqJbahKULRji1OvT.ooPbW5pn005UjIPkXiOmxI7026Ss4V2', 'PLAYER'),
('HeyMan', '$2a$10$WvpwLoqJbahKULRji1OvT.ooPbW5pn005UjIPkXiOmxI7026Ss4V2', 'PLAYER');
