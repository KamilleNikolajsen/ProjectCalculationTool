use projectcalculationtool;

INSERT INTO employees(employee_id, employee_email, employee_password) VALUES (2, "test@yes.com", "123");

INSERT INTO projects(project_id, project_name, fk_employee_id) VALUES (2, "Hus", 2);

INSERT INTO subprojects(subproject_id, subproject_name, fk_project_id) VALUES (6,"Design", 2);
INSERT INTO tasks(task_name, task_hours, fk_subproject_id) VALUES ("Tilladelser", 65, 6);
INSERT INTO tasks(task_name, task_hours, fk_subproject_id) VALUES ("Tegninger", 45, 6);

INSERT INTO subprojects(subproject_id, subproject_name, fk_project_id) VALUES (7, "Indkøb", 2);
INSERT INTO tasks(task_name, task_hours, fk_subproject_id) VALUES ("Elektricitet", 205, 7);
INSERT INTO tasks(task_name, task_hours, fk_subproject_id) VALUES ("VVS", 85, 7);
INSERT INTO tasks(task_name, task_hours, fk_subproject_id) VALUES ("HVAC", 55, 7);

INSERT INTO subprojects(subproject_id, subproject_name, fk_project_id) VALUES (8, "Konstruktion", 2);
INSERT INTO tasks(task_name, task_hours, fk_subproject_id) VALUES ("Interiør", 525, 8);
INSERT INTO tasks(task_name, task_hours, fk_subproject_id) VALUES ("Eksteriør", 198, 8);

INSERT INTO subprojects(subproject_id, subproject_name, fk_project_id) VALUES (9, "Inspektion", 2);
INSERT INTO tasks(task_name, task_hours, fk_subproject_id) VALUES ("Murerarbejde", 68, 9);
INSERT INTO tasks(task_name, task_hours, fk_subproject_id) VALUES ("Bygningsfærdiggørelse", 45, 9);

INSERT INTO subprojects(subproject_id, subproject_name, fk_project_id) VALUES (10, "Omsætning", 2);
INSERT INTO tasks(task_name, task_hours, fk_subproject_id) VALUES ("Gennemgang", 10, 10);
INSERT INTO tasks(task_name, task_hours, fk_subproject_id) VALUES ("Afslutning", 12, 10);