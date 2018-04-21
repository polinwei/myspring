Demo: SalaryAccount
==========================

-- Create table
create table SALARY_ACCOUNT
(
  ID        NUMBER(19) not null,
  FULL_NAME VARCHAR2(128) not null,
  DEPT_NAME VARCHAR2(128) not null,
  Salary   NUMBER not null
) ;
--  
alter table SALARY_ACCOUNT
  add constraint SALARY_ACCOUNT_PK primary key (ID);
 
 
Insert into SALARY_ACCOUNT(ID, Full_Name, Dept_Name, Salary) values (1, 'Tom', 'HR' , 45000);
Insert into SALARY_ACCOUNT(ID, Full_Name, Dept_Name, Salary) values (2, 'Jack', 'RD' , 55000);
Insert into SALARY_ACCOUNT(ID, Full_Name, Dept_Name, Salary) values (3, 'Jill', 'SALES' , 65000);
commit;