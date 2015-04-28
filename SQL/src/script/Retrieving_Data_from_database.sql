/*1*/
select * from departments;
select * from employees;
select * from jobs;
/*2*/
SELECT * from employees
where DEPARTMENT_ID=50;
/*3*/
UPDATE employees
SET SALARY=SALARY*130/100
WHERE DEPARTMENT_ID=50;
/*4*/
DELETE FROM employees
WHERE EMPLOYEE_ID=101;
/*5*/
SELECT *
FROM  employees e, jobs j
WHERE e.job_id=j.job_id and j.JOB_ID='IT_PROG'
ORDER BY e.first_name;
/*6*/
SELECT e.FIRST_NAME, e.LAST_NAME, d.DEPARTMENT_NAME from employees e, departments d
where e.DEPARTMENT_ID=50 and e.DEPARTMENT_ID=d.DEPARTMENT_ID;
/*7*/
select count(employee_id) from employees emp where emp.JOB_ID = 'IT_PROG';
/*8*/
SELECT e.LAST_NAME, e.FIRST_NAME, l.CITY FROM employees e,locations l, departments d
WHERE e.DEPARTMENT_ID=d.DEPARTMENT_ID and d.LOCATION_ID=l.LOCATION_ID and l.CITY='Seattle';
/*9*/
CREATE VIEW view_employee_dep AS
  SELECT e.EMPLOYEE_ID, e.FIRST_NAME,d.DEPARTMENT_NAME
  FROM employees e, departments d
  WHERE e.DEPARTMENT_ID=d.DEPARTMENT_ID;

SELECT * from view_employee_dep;
/*10*/
