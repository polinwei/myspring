package com.polinwei.myspring.db.oracle.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SALARY_ACCOUNT", schema = "JAPPS")
public class SalaryAccount {
    private long id;
    private String fullName;
    private String deptName;
    private long salary;

    public SalaryAccount() {
    }

    public SalaryAccount(long id, String fullName, String deptName, long salary) {
        this.id = id;
        this.fullName = fullName;
        this.deptName = deptName;
        this.salary = salary;
    }

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FULL_NAME", nullable = false, length = 128)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "DEPT_NAME", nullable = false, length = 128)
    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Basic
    @Column(name = "SALARY", nullable = false, precision = 0)
    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryAccount that = (SalaryAccount) o;
        return id == that.id &&
                salary == that.salary &&
                Objects.equals(fullName, that.fullName) &&
                Objects.equals(deptName, that.deptName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, fullName, deptName, salary);
    }

    @Override
    public String toString() {
        return "SalaryAccount{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", deptName='" + deptName + '\'' +
                ", salary=" + salary +
                '}';
    }
}
