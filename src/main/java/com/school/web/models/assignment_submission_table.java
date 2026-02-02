package com.school.web.models;

import jakarta.persistence.*;

@Entity
@Table (name="submission_table")
public class assignment_submission_table 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String assignmentName;
	
	private String studentName;
	
	private String className;
	
	private String email;
	
	@Lob
	@Column(name="file_data",columnDefinition="LONGBLOB")
	private byte[] filedata;
	
	private String fileName;
	
	private String fileType;
	
	public byte[] getFiledata() {
		return filedata;
	}

	public void setFiledata(byte[] filedata) {
		this.filedata = filedata;
	}
	public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAssignmentName() {
		return assignmentName;
	}

	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	public String getStudentName() {
		return studentName;
	}

	
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	private String status="Not Submitted";
}
