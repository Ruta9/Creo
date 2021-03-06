CREATE ALIAS TIMESTAMP_TO_STRING FOR "com.example.demo.utils.TimestampConverter.getStringFromTimestamp";

INSERT INTO USER (id, firstname, lastname, password, email) VALUES
    (1, 'John', 'Administrator', '$2a$10$H0T4iqxjR1WuG2q2EKOE/u7DbZ1vHeT6sibJojYgkr2YI4aOwKrYS', 'admin@gmail.com'),
    (2, 'John', 'Smith', '$2a$10$pDcrYbWJn86MI6HN7KnmuOPYOAz0Ynp3TeV7GPlhmCRy0RPBYulrS', 'smith@gmail.com'),
    (3, 'Tom', 'Peterson', '$2a$10$YEb.WbrMwDg/D80tmAROJuXn7kkAP.mNYIE6y7X6zXNZHM7cX8nv.', 'peterson@gmail.com'),
    (4, 'Clara', 'Berns', '$2a$10$r0R6QBUrL6Nc7dcxsmMB2.7LlY2W8cOoRD6OBOshhEba4aOzotniS', 'berns@gmail.com'),
    (5, 'Joane', 'Guber', '$2a$10$VCjfmfgzmY7O7oiK9xJjJ.0LqUp3KiaJp4bRlh4.7o6Dxzv1saLGi', 'guber@gmail.com'),
    (6, 'Abraham', 'Piers', '$2a$10$M25g5b/Zu86WvENsk4JO4.kV.HJr7qH4oqVHfNr3qJfcsWPOFTwhC', 'piers.abraham@gmail.com'),
    (7, 'Katherine', 'Jones', '$2a$10$/hUYb/vOq.PYG7mM.EHNres0z3sN02y7Emc8ItrOvfbwrXaVawy.K', 'katherine.jones@gmail.com'),
    (8, 'Dylan', 'Sanderson', '$2a$10$yPtapHWLnN02fwNBuNfJ/.kS0bHfL6hV8Uumy7CPb3WjUG74O2aMe', 'dylan.sanderson@gmail.com'),
    (9, 'Stewart', 'Miller', '$2a$10$k0qr.2jwsINObc86tyWhTeGVKc0uCPUfB98itRsZVSmfrHq2MH1H.', 'stewart.miller@gmail.com'),
    (10, 'Madeleine', 'Metcalfe', '$2a$10$LU7y3/9YOA3itOZ0MPm.GuPG9L8ITbmss91//pK7n/m5pom2Gj.Me', 'madeleine.metcalfe@gmail.com'),
    (11, 'Leonard', 'Ince', '$2a$10$JxVR4WkxB99cAnoIHvGveOYFkt3cwZq7Gx3/pq31/VYTOwPRD.P9O', 'leonard.ince@gmail.com'),
    (12, 'Pippa', 'MacDonald', '$2a$10$lRtsVMfYjhElfnJ2pcjYgeu/qZz.p5qZLHScZj7223dXXa37zZKgS', 'pippa.macdonald@gmail.com'),
    (13, 'Steven', 'Hardacre', '$2a$10$UV26d.9weyPjvat.iLWTr.ucH1fdhfnIwjmSLSkTz9cMN6/6wRwQ.', 'steven.hardacre@gmail.com'),
    (14, 'Christian', 'Greene', '$2a$10$eKSkyCWb06eKGVdTKe3Mh.Eah0EYvd3i7HN9sM9YIGcLus4mb2Mai', 'christian.greene@gmail.com'),
    (15, 'Adrian', 'Powell', '$2a$10$ynB/tVSHdFW1a8qZbbNmY.v8gdGCrpFQLGkhnXA7R3LBONpeJ5ike', 'adrian.powell@gmail.com'),
    (16, 'Leonard', 'Peake', '$2a$10$vM6EDcOYwRpXK/n6233xle0bnOw8d9Zs75IiyuOzjJWy9YRzEYLF6', 'leonard.peake@gmail.com'),
    (17, 'John', 'Davies', '$2a$10$gmlk2SOMMWcXSE5D2ZklB.J4IGMXakua9Sa8wkOmnp1/QSBUg1Uiy', 'john.davies@gmail.com'),
    (18, 'Victoria', 'Nash', '$2a$10$zKHSY5MKvZO61qEujAxcEuOpI2vTx3YUSh8/q2yrn6Jq34kuMhWky', 'victoria.nash@gmail.com'),
    (19, 'Sarah', 'Marshall', '$2a$10$XssUZ0WS7zXRYAZsiF3McO2X7vvhJVmDghHtJl8HG.lSC9Mv5qBEq', 'sarah.marshall@gmail.com'),
    (20, 'Theresa', 'Ross', '$2a$10$KFqFtSfHTzWnIaIi5cRPRerrpufLQzvhkei2onU8cNbbUvG3mN39q', 'theresa.ross@gmail.com');


INSERT INTO PROJECT (id, name, description, image_name, created_date, owner_id) VALUES
    (1, 'Personal', 'Personal project for everyday needs', 'papaja.png',TO_DATE('2020-05-20', 'YYYY-MM-DD'), 1),
    (2, 'Twitter', '-', 'plumps.png', TO_DATE('2011-04-01', 'YYYY-MM-DD'), 1),
    (3, 'Trafi', '-', 'PROJECT3.png', TO_DATE('2018-11-15', 'YYYY-MM-DD'), 2),
    (4, 'Harvard', 'Dream big', 'banana.png', TO_DATE('2019-09-01', 'YYYY-MM-DD'), 3),
    (5, 'Uni Project', 'For Object oriented programming course', 'pears.png', TO_DATE('2017-09-21', 'YYYY-MM-DD'), 4),
    (6, 'NASA', '', 'PROJECT6.png', TO_DATE('1997-10-15', 'YYYY-MM-DD'), 5),
    (7, 'Vacation', 'TODO for our upcoming vacation', 'oranges.png', TO_DATE('2020-01-01', 'YYYY-MM-DD'), 2),
    (8, 'English language lessons', '', 'pineapple.png', TO_DATE('2016-05-04', 'YYYY-MM-DD'), 4),
    (9, 'GameJam', 'Team A', 'grapes.png', TO_DATE('2018-01-20', 'YYYY-MM-DD'), 1),
    (10, 'Creo', 'Creo product', 'PROJECT10.png', TO_DATE('2020-08-20', 'YYYY-MM-DD'), 1);

INSERT INTO TEAM (project_id, user_id) values
(1, 1),

(2, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 5),
(2, 6),
(2, 7),
(2, 8),
(2, 9),
(2, 10),
(2, 11),

(3, 1),
(3, 2),
(3, 3),
(3, 4),
(3, 5),
(3, 6),
(3, 7),
(3, 8),
(3, 9),
(3, 10),
(3, 11),

(4, 3),

(5, 4),
(5, 15),
(5, 16),
(5, 17),

(6, 5),
(6, 1),
(6, 9),

(7, 2),
(7, 4),
(7, 8),
(7, 19),

(8, 4),

(9, 1),
(9, 14),
(9, 13),
(9, 12),

(10, 1),
(10, 11),
(10, 12),
(10, 13),
(10, 14),
(10, 15),
(10, 16),
(10, 17);


INSERT INTO STATUS (id, name, status_order, ticket_type, project_id) values
(1, 'Created', 1, 'STORY', 1),
(2, 'In Progress', 2, 'STORY', 1),
(3, 'On Hold', 3, 'STORY', 1),
(4, 'Testing', 4, 'STORY', 1),
(5, 'Done', 5, 'STORY', 1),
(6, 'Archived', 6, 'STORY', 1),
(7, 'Created', 1, 'TASK', 1),
(8, 'In Progress', 2, 'TASK', 1),
(9, 'On Hold', 3, 'TASK', 1),
(10, 'Testing', 4, 'TASK', 1),
(11, 'Done', 5, 'TASK', 1),
(12, 'Archived', 6, 'TASK', 1),

(13, 'Created', 1, 'STORY', 2),
(14, 'In Progress', 2, 'STORY', 2),
(15, 'On Hold', 3, 'STORY', 2),
(16, 'Testing', 4, 'STORY', 2),
(17, 'Done', 5, 'STORY', 2),
(18, 'Archived', 6, 'STORY', 2),
(19, 'Created', 1, 'TASK', 2),
(20, 'In Progress', 2, 'TASK', 2),
(21, 'On Hold', 3, 'TASK', 2),
(22, 'Testing', 4, 'TASK', 2),
(23, 'Done', 5, 'TASK', 2),
(24, 'Archived', 6, 'TASK', 2),

(25, 'Created', 1, 'STORY', 3),
(26, 'In Progress', 2, 'STORY', 3),
(27, 'On Hold', 3, 'STORY', 3),
(28, 'Testing', 4, 'STORY', 3),
(29, 'Done', 5, 'STORY', 3),
(30, 'Archived', 6, 'STORY', 3),
(31, 'Created', 1, 'TASK', 3),
(32, 'In Progress', 2, 'TASK', 3),
(33, 'On Hold', 3, 'TASK', 3),
(34, 'Testing', 4, 'TASK', 3),
(35, 'Done', 5, 'TASK', 3),
(36, 'Archived', 6, 'TASK', 3),

(37, 'Created', 1, 'STORY', 4),
(38, 'In Progress', 2, 'STORY', 4),
(39, 'On Hold', 3, 'STORY', 4),
(40, 'Testing', 4, 'STORY', 4),
(41, 'Done', 5, 'STORY', 4),
(42, 'Archived', 6, 'STORY', 4),
(43, 'Created', 1, 'TASK', 4),
(44, 'In Progress', 2, 'TASK', 4),
(45, 'On Hold', 3, 'TASK', 4),
(46, 'Testing', 4, 'TASK', 4),
(47, 'Done', 5, 'TASK', 4),
(48, 'Archived', 6, 'TASK', 4),

(49, 'Created', 1, 'STORY', 5),
(50, 'In Progress', 2, 'STORY', 5),
(51, 'On Hold', 3, 'STORY', 5),
(52, 'Testing', 4, 'STORY', 5),
(53, 'Done', 5, 'STORY', 5),
(54, 'Archived', 6, 'STORY', 5),
(55, 'Created', 1, 'TASK', 5),
(56, 'In Progress', 2, 'TASK', 5),
(57, 'On Hold', 3, 'TASK', 5),
(58, 'Testing', 4, 'TASK', 5),
(59, 'Done', 5, 'TASK', 5),
(60, 'Archived', 6, 'TASK', 5),

(61, 'Created', 1, 'STORY', 6),
(62, 'In Progress', 2, 'STORY', 6),
(63, 'On Hold', 3, 'STORY', 6),
(64, 'Testing', 4, 'STORY', 6),
(65, 'Done', 5, 'STORY', 6),
(66, 'Archived', 6, 'STORY', 6),
(67, 'Created', 1, 'TASK', 6),
(68, 'In Progress', 2, 'TASK', 6),
(69, 'On Hold', 3, 'TASK', 6),
(70, 'Testing', 4, 'TASK', 6),
(71, 'Done', 5, 'TASK', 6),
(72, 'Archived', 6, 'TASK', 6),

(73, 'Created', 1, 'STORY', 7),
(74, 'In Progress', 2, 'STORY', 7),
(75, 'On Hold', 3, 'STORY', 7),
(76, 'Testing', 4, 'STORY', 7),
(77, 'Done', 5, 'STORY', 7),
(78, 'Archived', 6, 'STORY', 7),
(79, 'Created', 1, 'TASK', 7),
(80, 'In Progress', 2, 'TASK', 7),
(81, 'On Hold', 3, 'TASK', 7),
(82, 'Testing', 4, 'TASK', 7),
(83, 'Done', 5, 'TASK', 7),
(84, 'Archived', 6, 'TASK', 7),

(85, 'Created', 1, 'STORY', 8),
(86, 'In Progress', 2, 'STORY', 8),
(87, 'On Hold', 3, 'STORY', 8),
(88, 'Testing', 4, 'STORY', 8),
(89, 'Done', 5, 'STORY', 8),
(90, 'Archived', 6, 'STORY', 8),
(91, 'Created', 1, 'TASK', 8),
(92, 'In Progress', 2, 'TASK', 8),
(93, 'On Hold', 3, 'TASK', 8),
(94, 'Testing', 4, 'TASK', 8),
(95, 'Done', 5, 'TASK', 8),
(96, 'Archived', 6, 'TASK', 8),

(97, 'Created', 1, 'STORY', 9),
(98, 'In Progress', 2, 'STORY', 9),
(99, 'On Hold', 3, 'STORY', 9),
(100, 'Testing', 4, 'STORY', 9),
(101, 'Done', 5, 'STORY', 9),
(102, 'Archived', 6, 'STORY', 9),
(103, 'Created', 1, 'TASK', 9),
(104, 'In Progress', 2, 'TASK', 9),
(105, 'On Hold', 3, 'TASK', 9),
(106, 'Testing', 4, 'TASK', 9),
(107, 'Done', 5, 'TASK', 9),
(108, 'Archived', 6, 'TASK', 9),

(109, 'TO DO', 1, 'STORY', 10),
(110, 'IN PROGRESS', 2, 'STORY', 10),
(111, 'READY', 3, 'STORY', 10),
(112, 'TESTING', 4, 'STORY', 10),
(113, 'REJECTED', 5, 'STORY', 10),
(114, 'DONE', 6, 'STORY', 10),
(115, 'ARCHIVED', 7, 'STORY', 10),
(116, 'TO DO', 2, 'TASK', 10),
(117, 'IN PROGRESS', 3, 'TASK', 10),
(118, 'DONE', 4, 'TASK', 10),
(119, 'ARCHIVED', 5, 'TASK', 10);

INSERT INTO PROJECT_ROLE (id, role, is_global, project_id) VALUES
(1, 'PROJECTADMIN', 0, 1),
(2, 'PROJECTADMIN', 0, 2),
(3, 'PROJECTADMIN', 0, 3),
(4, 'PROJECTADMIN', 0, 4),
(5, 'PROJECTADMIN', 0, 5),
(6, 'PROJECTADMIN', 0, 6),
(7, 'PROJECTADMIN', 0, 7),
(8, 'PROJECTADMIN', 0, 8),
(9, 'PROJECTADMIN', 0, 9),
(10, 'PROJECTADMIN', 0, 10),

(11, 'TASKCREATOR', 0, 1),
(12, 'TASKCREATOR', 0, 2),
(13, 'TASKCREATOR', 0, 3),
(14, 'TASKCREATOR', 0, 4),
(15, 'TASKCREATOR', 0, 5),
(16, 'TASKCREATOR', 0, 6),
(17, 'TASKCREATOR', 0, 7),
(18, 'TASKCREATOR', 0, 8),
(19, 'TASKCREATOR', 0, 9),
(20, 'TASKCREATOR', 0, 10),

(21, 'STORYCREATOR', 0, 1),
(22, 'STORYCREATOR', 0, 2),
(23, 'STORYCREATOR', 0, 3),
(24, 'STORYCREATOR', 0, 4),
(25, 'STORYCREATOR', 0, 5),
(26, 'STORYCREATOR', 0, 6),
(27, 'STORYCREATOR', 0, 7),
(28, 'STORYCREATOR', 0, 8),
(29, 'STORYCREATOR', 0, 9),
(30, 'STORYCREATOR', 0, 10),

(31, 'TEAMADMIN', 0, 1),
(32, 'TEAMADMIN', 0, 2),
(33, 'TEAMADMIN', 0, 3),
(34, 'TEAMADMIN', 0, 4),
(35, 'TEAMADMIN', 0, 5),
(36, 'TEAMADMIN', 0, 6),
(37, 'TEAMADMIN', 0, 7),
(38, 'TEAMADMIN', 0, 8),
(39, 'TEAMADMIN', 0, 9),
(40, 'TEAMADMIN', 0, 10);

INSERT INTO PROJECT_PARTICIPANT_ROLE (project_role_id, user_id) values
(1, 1),
(11, 1),
(21, 1),
(31, 1),

(2, 1),
(12, 1),
(22, 1),
(32, 1),

(3, 2),
(13, 2),
(23, 2),
(33, 2),

(4, 3),
(14, 3),
(24, 3),
(34, 3),

(5, 4),
(15, 4),
(25, 4),
(35, 4),

(6, 5),
(16, 5),
(26, 5),
(36, 5),

(7, 2),
(17, 2),
(27, 2),
(37, 2),

(8, 4),
(18, 4),
(28, 4),
(38, 4),

(9, 1),
(19, 1),
(29, 1),
(39, 1),

(10, 1),
(20, 1),
(30, 1),
(40, 1);

INSERT INTO STORY (id, name, description, project_id, assignee_id, creator_id, status_id, created_date, updated_date) VALUES
(1, 'Login functionality',
'Frontend and Backend parts of login functionality for Creo application. More detailed info is attached',
10, 1, 1, 109, parsedatetime('2020-09-25 15:08:33', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-09-25 15:15:28', 'yyyy-MM-dd hh:mm:ss')),
(2, 'Register functionality',
'Frontend and Backend parts of register functionality for Creo application. More detailed info is attached. Due date: 2020-12-05.',
10, 15, 1, 111, parsedatetime('2020-09-08 22:08:33', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-09-25 20:09:28', 'yyyy-MM-dd hh:mm:ss')),
(3, 'Wiring up Google oauth authentification',
'',
10, 17, 1, 115, parsedatetime('2020-09-15 15:08:33', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-09-25 14:15:28', 'yyyy-MM-dd hh:mm:ss')),
(4, 'Front page design',
'A good example is Trello.',
10, 1, 17, 112, parsedatetime('2020-09-23 15:08:33', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-09-24 15:15:28', 'yyyy-MM-dd hh:mm:ss')),
(5, 'User Project list "My Projects"',
'',
10, 13, 16, 114, parsedatetime('2020-09-23 15:08:33', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-09-23 15:15:28', 'yyyy-MM-dd hh:mm:ss')),
(6, 'Project image upload',
'',
10, 14, 14, 115, parsedatetime('2020-09-23 15:08:33', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-09-23 15:15:28', 'yyyy-MM-dd hh:mm:ss')),
(7, 'Project general settings',
'',
10, 1, 1, 115, parsedatetime('2020-09-23 15:08:33', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-09-23 15:15:28', 'yyyy-MM-dd hh:mm:ss')),
(8, 'Project status settings',
'',
10, 11, 12, 115, parsedatetime('2020-09-23 15:08:33', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-09-23 15:15:28', 'yyyy-MM-dd hh:mm:ss')),
(9, 'Project roles settings',
'',
10, 1, 12, 115, parsedatetime('2020-09-23 15:08:33', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-09-23 15:15:28', 'yyyy-MM-dd hh:mm:ss')),
(10, 'Team page',
'',
10, 12, 1, 115, parsedatetime('2020-10-05 08:08:33', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-10-05 10:15:28', 'yyyy-MM-dd hh:mm:ss')),
(11, 'Kanban',
'',
10, 1, 13, 115, parsedatetime('2020-09-23 15:08:33', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-09-23 15:15:28', 'yyyy-MM-dd hh:mm:ss')),
(12, 'Tickets',
'',
10, 1, 16, 115, parsedatetime('2020-10-03 16:08:33', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-10-4 14:15:28', 'yyyy-MM-dd hh:mm:ss')),
(13, 'Notifications',
'',
10, 16, 1, 115, parsedatetime('2020-09-23 15:08:33', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-09-23 15:15:28', 'yyyy-MM-dd hh:mm:ss')),
(14, 'Test',
'Test',
10, 14, 13, 115, parsedatetime('2020-09-23 15:08:33', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-09-23 15:15:28', 'yyyy-MM-dd hh:mm:ss')),
(15, 'Test',
'Test',
10, 14, 15, 115, parsedatetime('2020-09-23 15:08:33', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-09-23 15:15:28', 'yyyy-MM-dd hh:mm:ss')),
(16, 'Test',
'Test',
10, 13, 13, 115, parsedatetime('2020-09-23 15:08:33', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-09-23 15:15:28', 'yyyy-MM-dd hh:mm:ss')),
(17, 'Test',
'Test',
10, 16, 14, 115, parsedatetime('2020-09-23 15:08:33', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-09-23 15:15:28', 'yyyy-MM-dd hh:mm:ss')),
(18, 'Test',
'Test',
10, 14, 16, 115, parsedatetime('2020-09-23 15:08:33', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-09-23 15:15:28', 'yyyy-MM-dd hh:mm:ss'));



INSERT INTO TASK (id, name, description, story_id, assignee_id, creator_id, status_id, created_date, updated_date) VALUES
(1, 'Spring Security configuration, filters', 'Configure Spring Security', 1, 1, 1, 117, parsedatetime('2020-10-01 15:15:28', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-10-02 01:04:28', 'yyyy-MM-dd hh:mm:ss')),
(2, 'Front-end login form HTML+CSS', 'Create login form view', 1, 1, 1, 117, parsedatetime('2020-10-01 15:15:28', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-10-02 01:04:28', 'yyyy-MM-dd hh:mm:ss')),
(3, 'Front-end login form React', 'Front-end validation and submit functionality', 1, 1, 1, 117, parsedatetime('2020-10-01 15:15:28', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-10-02 01:04:28', 'yyyy-MM-dd hh:mm:ss')),
(4, 'Wire it all together', 'Make sure everything works', 1, 1, 1, 117, parsedatetime('2020-10-01 15:15:28', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-10-02 01:04:28', 'yyyy-MM-dd hh:mm:ss')),
(5, 'Testing', 'test everything', 1, 1, 1, 117, parsedatetime('2020-10-01 15:15:28', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-10-02 01:04:28', 'yyyy-MM-dd hh:mm:ss')),

(6, 'Spring Security configuration', 'Test', 2, 15, 1, 117, parsedatetime('2020-10-01 15:15:28', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-10-02 01:04:28', 'yyyy-MM-dd hh:mm:ss')),
(7, 'Front-end register form HTML+CSS', '', 2, 17, 1, 117, parsedatetime('2020-10-02 15:15:28', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-10-03 01:04:28', 'yyyy-MM-dd hh:mm:ss')),
(8, 'Front-end register form React', '', 2, 17, 1, 117, parsedatetime('2020-10-03 15:15:28', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-10-04 15:04:28', 'yyyy-MM-dd hh:mm:ss')),
(9, 'Wire it all together', 'Make sure everything works', 2, 15, 1, 117, parsedatetime('2020-10-02 15:15:28', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-10-02 18:04:28', 'yyyy-MM-dd hh:mm:ss')),
(10, 'Testing', 'test everything', 2, 15, 1, 117, parsedatetime('2020-10-01 15:15:28', 'yyyy-MM-dd hh:mm:ss'), parsedatetime('2020-10-02 01:04:28', 'yyyy-MM-dd hh:mm:ss'));










