package com.school.web.models;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GradeId implements Serializable 
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rollno;
    private String exam;


    public GradeId() {}

    public GradeId(String rollno, String exam) {
        this.rollno = rollno;
        this.exam = exam;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getExam() {
        return exam;
    }

    public void setExam(String exam) {
        this.exam = exam;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeId gradeId = (GradeId) o;
        return rollno.equals(gradeId.rollno) && exam.equals(gradeId.exam);
    }

    @Override
    public int hashCode() {
        return 31 * rollno.hashCode() + exam.hashCode();
    }
}
