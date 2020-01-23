/**
Leetcode <Problem 1179> Reformat Department Table

Table: Department:
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| revenue       | int     |
| month         | varchar |
+---------------+---------+
(id, month) is the primary key of this table.
The table has information about the revenue of each department per month.
The month has values in ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"].


Write an SQL query to reformat the table such that there is a department id column and a revenue column for each month.
The query result format is in the following example:
Department table:
+------+---------+-------+
| id   | revenue | month |
+------+---------+-------+
| 1    | 8000    | Jan   |
| 2    | 9000    | Jan   |
| 3    | 10000   | Feb   |
| 1    | 7000    | Feb   |
| 1    | 6000    | Mar   |
+------+---------+-------+

Result table:
+------+-------------+-------------+-------------+-----+-------------+
| id   | Jan_Revenue | Feb_Revenue | Mar_Revenue | ... | Dec_Revenue |
+------+-------------+-------------+-------------+-----+-------------+
| 1    | 8000        | 7000        | 6000        | ... | null        |
| 2    | 9000        | null        | null        | ... | null        |
| 3    | null        | 10000       | null        | ... | null        |
+------+-------------+-------------+-------------+-----+-------------+

Note that the result table has 13 columns (1 for the department id + 12 for the months).
*/


-- create table department
CREATE TABLE department (
    id INT,
    revenue INT,
    month VARCHAR(3) NOT NULL,
    PRIMARY KEY (id , month)
);

-- insert rows
INSERT INTO department(id, revenue, month)
VALUES (1, 8000,  'Jan'),
       (2, 9000,  'Jan'),
       (3, 10000, 'Feb'),
       (1, 7000,  'Feb'),
       (1, 6000,  'Mar');

SELECT 
    id,
    SUM(IF(month = 'Jan', revenue, Null)) Jan_Revenue,
    SUM(IF(month = 'Feb', revenue, Null)) Feb_Revenue,
    SUM(IF(month = 'Mar', revenue, Null)) Mar_Revenue,
    SUM(IF(month = 'Apr', revenue, Null)) Apr_Revenue,
    SUM(IF(month = 'May', revenue, Null)) May_Revenue,
    SUM(IF(month = 'Jun', revenue, Null)) Jun_Revenue,
    SUM(IF(month = 'Jul', revenue, Null)) Jul_Revenue,
    SUM(IF(month = 'Aug', revenue, Null)) Aug_Revenue,
    SUM(IF(month = 'Sep', revenue, Null)) Sep_Revenue,
    SUM(IF(month = 'Oct', revenue, Null)) Oct_Revenue,
    SUM(IF(month = 'Nov', revenue, Null)) Nov_Revenue,
    SUM(IF(month = 'Dec', revenue, Null)) Dec_Revenue
FROM
    department
GROUP BY
    id;