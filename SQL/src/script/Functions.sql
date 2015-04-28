/*1*/
SELECT sysdate() FROM dual;
/*2*/
SELECT DATE_FORMAT(SYSDATE(), '%Y-%m-%d') from dual;
/*3*/
select str_to_date ('25-11-2014', '%d-%m-%Y') from dual;
/*4*/
SELECT upper(FIRST_NAME), lower(EMAIL)
FROM employees;
/*5*/
SELECT concat('First Name ',FIRST_NAME)
FROM employees;
/* !!! 5.2*/
SELECT 'First Name ' || FIRST_NAME
FROM employees;
/* 6*/
SELECT count(EMPLOYEE_ID)
FROM employees;
/*7*/
SELECT count(e.EMPLOYEE_ID)
FROM employees e,jobs j
WHERE e.JOB_ID=j.JOB_ID and j.JOB_ID='IT_prog';
/*7.1*/
SELECT count(EMPLOYEE_ID)
FROM employees
WHERE JOB_ID='it_prog';
/*8*/
SELECT count(e.EMPLOYEE_ID), d.DEPARTMENT_NAME
FROM employees e, departments d
WHERE e.DEPARTMENT_ID=d.DEPARTMENT_ID
GROUP BY d.DEPARTMENT_ID;
/*9*/
SELECT sum(SALARY)
FROM employees
WHERE DEPARTMENT_ID=50;
/*10*/
SELECT max(SALARY),min(SALARY)
FROM employees
WHERE DEPARTMENT_ID=50;
