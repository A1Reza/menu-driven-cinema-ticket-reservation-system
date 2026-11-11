/*Question 1: Display the first three characters of each employee's first_name as
SHORT_NAME and show the employee’s hire year as HIRE_YEAR for employees whose
hire_date is after '1995-01-01' and whose last_name contains the letter 'o'.
Hint: Use SUBSTRING(first_name, 1, 3) */
SELECT SUBSTRING(first_name, 1, 3)  AS SHORT_NAME,
       EXTRACT(YEAR FROM hire_date) AS HIRE_YEAR
FROM employees
WHERE hire_date > '1995-01-01'
  AND last_name LIKE '%o%';

/*Question 2: Find the total number of different titles in the titles table and name the result
UNIQUE_TITLE_COUNT.*/
SELECT COUNT(DISTINCT title) AS UNIQUE_TITLE_COUNT
FROM titles;

/*Question 3: Display all department names in uppercase format as
DEPARTMENT_UPPERCASE for departments whose dept_no is not 'd001' and not 'd002'.*/
SELECT UPPER(dept_name) AS DEPARTMENT_UPPERCASE
FROM departments
WHERE dept_no NOT IN ('d001', 'd002');

/*Question 4: Display employee full name and the current system timestamp converted to
UTC timezone as CURRENT_UTC_TIME for employees whose hire_date is after '1995-01-
01'.*/
SELECT CONCAT(first_name, ' ', last_name)   AS FULL_NAME,
       CURRENT_TIMESTAMP AT TIME ZONE 'UTC' AS CURRENT_UTC_TIME
FROM employees
WHERE hire_date > '1995-01-01';

/*Question 5: Display emp_no, hire_date, and hire_date formatted as:
Day, DD Month YYYY
using TO_CHAR for employees whose gender is 'F'.*/
SELECT emp_no,
       hire_date,
       TO_CHAR(hire_date, 'Day, DD Month YYYY') AS FORMATTED_HIRE_DATE
FROM employees
WHERE gender = 'F';

/*Question 6: Display employee full name and birth_date converted to 'Asia/Tehran' timezone
for employees whose birth_date is before '1970-01-01'.*/
SELECT CONCAT(first_name, ' ', last_name)               AS FULL_NAME,
       birth_date::timestamp AT TIME ZONE 'Asia/Tehran' AS BIRTHDATE_TEHRAN
FROM employees
WHERE birth_date < '1970-01-01';

/*Question 7: Display employee full name and age in years for employees older than 45
years. Also display the current timestamp in 'Europe/London' timezone.
Hint:TIMESTAMP represents a specific date and time value, while TIME ZONE defines how
that time is adjusted or displayed in different regions.
Use CURRENT_TIMESTAMP AT TIME ZONE 'Europe/London' to convert the current
system time into London time. It shows the same moment in a different timezone format in
the query output*/
SELECT CONCAT(first_name, ' ', last_name)               AS FULL_NAME,
       EXTRACT(YEAR FROM AGE(CURRENT_DATE, birth_date)) AS AGE_YEARS,
       CURRENT_TIMESTAMP AT TIME ZONE 'Europe/London'   AS LONDON_TIME
FROM employees
WHERE EXTRACT(YEAR FROM AGE(CURRENT_DATE, birth_date)) > 45;

/*Question 8: Display employee full name and hire_date formatted as:
HH24:MI DD/MM/YYYY
for employees whose first_name starts with 'S'.*/
SELECT CONCAT(first_name, ' ', last_name)                  AS FULL_NAME,
       TO_CHAR(hire_date::timestamp, 'HH24:MI DD/MM/YYYY') AS FORMATTED_HIRE_DATE
FROM employees
WHERE first_name LIKE 'S%';

/*Question 9: From salaries table, display MIN_SALARY, MAX_SALARY, and
SALARY_RANGE (difference between max and min salary) for employees where emp_no is
between 10005 and 10150 and salary is not NULL and from_date is after '1995-01-01'. Also
display from_date converted to UTC time zone.*/
SELECT MIN(salary)                             AS MIN_SALARY,
       MAX(salary)                             AS MAX_SALARY,
       MAX(salary) - MIN(salary)               AS SALARY_RANGE,
       from_date::timestamp AT TIME ZONE 'UTC' AS FROM_DATE_UTC
FROM salaries
WHERE emp_no BETWEEN 10005 AND 10150
  AND salary IS NOT NULL
  AND from_date > '1995-01-01'
GROUP BY from_date;

/*Question 10: Display emp_no, title, and a concatenated column TITLE_INFO in format [title]
started from [from_date] from titles table where from_date is between '1985-01-01' and
'1994-12-31' and (title like '%Engineer%' or title like '%Staff%') and to_date is NULL or
to_date = '9999-01-01'. Also display current timestamp in 'Asia/Tehran' time zone format*/
SELECT emp_no,
       title,
       CONCAT(title, ' started from ', from_date)   AS TITLE_INFO,
       CURRENT_TIMESTAMP AT TIME ZONE 'Asia/Tehran' AS TEHRAN_CURRENT_TIME
FROM titles
WHERE from_date BETWEEN '1985-01-01' AND '1994-12-31'
  AND (title LIKE '%Engineer%' OR title LIKE '%Staff%')
  AND (to_date IS NULL OR to_date = '9999-01-01');
