SELECT 1;

DELETE FROM schedule;
CREATE UNIQUE INDEX IF NOT EXISTS ux_schedule
    ON schedule (school_class, week_day, lesson_time);
SELECT school_class, week_day, lesson_time, COUNT(*)
FROM schedule
GROUP BY school_class, week_day, lesson_time
HAVING COUNT(*) > 1;

-- ================= CLASS 10 =================
INSERT INTO schedule (subject, room, week_day, lesson_time, school_class) VALUES
-- MONDAY
('Math', '101', 'MONDAY', '09:00-09:50', '10'),
('Physics', '102', 'MONDAY', '10:20-11:10', '10'),
('English', '103', 'MONDAY', '11:40-12:30', '10'),
('History', '201', 'MONDAY', '13:30-14:20', '10'),
('Biology', '202', 'MONDAY', '14:50-15:40', '10'),
-- TUESDAY
('Math', '101', 'TUESDAY', '09:00-09:50', '10'),
('English', '103', 'TUESDAY', '10:20-11:10', '10'),
('Chemistry', '203', 'TUESDAY', '11:40-12:30', '10'),
('Geography', '204', 'TUESDAY', '13:30-14:20', '10'),
('PE', 'Gym', 'TUESDAY', '14:50-15:40', '10'),
-- WEDNESDAY
('Math', '101', 'WEDNESDAY', '09:00-09:50', '10'),
('Physics', '102', 'WEDNESDAY', '10:20-11:10', '10'),
('English', '103', 'WEDNESDAY', '11:40-12:30', '10'),
('IT', 'Lab', 'WEDNESDAY', '13:30-14:20', '10'),
('Art', '301', 'WEDNESDAY', '14:50-15:40', '10'),
-- THURSDAY
('Biology', '202', 'THURSDAY', '09:00-09:50', '10'),
('Chemistry', '203', 'THURSDAY', '10:20-11:10', '10'),
('Math', '101', 'THURSDAY', '11:40-12:30', '10'),
('History', '201', 'THURSDAY', '13:30-14:20', '10'),
('English', '103', 'THURSDAY', '14:50-15:40', '10'),
-- FRIDAY
('Math', '101', 'FRIDAY', '09:00-09:50', '10'),
('Physics', '102', 'FRIDAY', '10:20-11:10', '10'),
('English', '103', 'FRIDAY', '11:40-12:30', '10'),
('PE', 'Gym', 'FRIDAY', '13:30-14:20', '10'),
('Class Hour', '101', 'FRIDAY', '14:50-15:40', '10');

-- ================= CLASS 11 =================
INSERT INTO schedule (subject, room, week_day, lesson_time, school_class) VALUES
('Math', '201', 'MONDAY', '09:00-09:50', '11'),
('Physics', '202', 'MONDAY', '10:20-11:10', '11'),
('English', '203', 'MONDAY', '11:40-12:30', '11'),
('History', '204', 'MONDAY', '13:30-14:20', '11'),
('Biology', '205', 'MONDAY', '14:50-15:40', '11'),

('Math', '201', 'TUESDAY', '09:00-09:50', '11'),
('Chemistry', '206', 'TUESDAY', '10:20-11:10', '11'),
('English', '203', 'TUESDAY', '11:40-12:30', '11'),
('IT', 'Lab', 'TUESDAY', '13:30-14:20', '11'),
('PE', 'Gym', 'TUESDAY', '14:50-15:40', '11'),

('Math', '201', 'WEDNESDAY', '09:00-09:50', '11'),
('Physics', '202', 'WEDNESDAY', '10:20-11:10', '11'),
('English', '203', 'WEDNESDAY', '11:40-12:30', '11'),
('History', '204', 'WEDNESDAY', '13:30-14:20', '11'),
('Biology', '205', 'WEDNESDAY', '14:50-15:40', '11'),

('Math', '201', 'THURSDAY', '09:00-09:50', '11'),
('Chemistry', '206', 'THURSDAY', '10:20-11:10', '11'),
('English', '203', 'THURSDAY', '11:40-12:30', '11'),
('IT', 'Lab', 'THURSDAY', '13:30-14:20', '11'),
('PE', 'Gym', 'THURSDAY', '14:50-15:40', '11'),

('Math', '201', 'FRIDAY', '09:00-09:50', '11'),
('Physics', '202', 'FRIDAY', '10:20-11:10', '11'),
('English', '203', 'FRIDAY', '11:40-12:30', '11'),
('Class Hour', '201', 'FRIDAY', '13:30-14:20', '11'),
('Elective', '301', 'FRIDAY', '14:50-15:40', '11');

-- ================= CLASS 12 =================
INSERT INTO schedule (subject, room, week_day, lesson_time, school_class) VALUES
('Math', '301', 'MONDAY', '09:00-09:50', '12'),
('Physics', '302', 'MONDAY', '10:20-11:10', '12'),
('English', '303', 'MONDAY', '11:40-12:30', '12'),
('IT', '305', 'MONDAY', '13:30-14:20', '12'),
('Biology', '306', 'MONDAY', '14:50-15:40', '12'),

('Math', '301', 'TUESDAY', '09:00-09:50', '12'),
('Chemistry', '307', 'TUESDAY', '10:20-11:10', '12'),
('English', '303', 'TUESDAY', '11:40-12:30', '12'),
('History', '308', 'TUESDAY', '13:30-14:20', '12'),
('PE', 'Gym', 'TUESDAY', '14:50-15:40', '12'),

('Math', '301', 'WEDNESDAY', '09:00-09:50', '12'),
('Physics', '302', 'WEDNESDAY', '10:20-11:10', '12'),
('English', '303', 'WEDNESDAY', '11:40-12:30', '12'),
('IT', '305', 'WEDNESDAY', '13:30-14:20', '12'),
('Elective', '401', 'WEDNESDAY', '14:50-15:40', '12'),

('Math', '301', 'THURSDAY', '09:00-09:50', '12'),
('Chemistry', '307', 'THURSDAY', '10:20-11:10', '12'),
('English', '303', 'THURSDAY', '11:40-12:30', '12'),
('History', '308', 'THURSDAY', '13:30-14:20', '12'),
('PE', 'Gym', 'THURSDAY', '14:50-15:40', '12'),

('Math', '301', 'FRIDAY', '09:00-09:50', '12'),
('Physics', '302', 'FRIDAY', '10:20-11:10', '12'),
('English', '303', 'FRIDAY', '11:40-12:30', '12'),
('Class Hour', '301', 'FRIDAY', '13:30-14:20', '12'),
('Consultation', '401', 'FRIDAY', '14:50-15:40', '12');
