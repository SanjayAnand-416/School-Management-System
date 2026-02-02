package com.school.web.models;

import jakarta.persistence.*;

@Entity
@Table(name="grade")
public class grade_table {

    @EmbeddedId
    private GradeId id;

    @Column(nullable = false)
    private String name;

    private int maths;
    private int science;
    private int english;
    private int history;
    private int biology;


    public int getHistory() {
		return history;
	}

	public void setHistory(int history) {
		this.history = history;
	}

	public int getBiology() {
		return biology;
	}

	public void setBiology(int biology) {
		this.biology = biology;
	}

	public GradeId getId() {
        return id;
    }

    public void setId(GradeId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaths() {
        return maths;
    }

    public void setMaths(int maths) {
        this.maths = maths;
    }

    public int getScience() {
        return science;
    }

    public void setScience(int science) {
        this.science = science;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }

}
