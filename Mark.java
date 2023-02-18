package intranet;


import java.io.Serializable;

public class Mark implements Serializable {
	private static final long serialVersionUID = -221375337781799843L;
	private Course course;
	private double firstAttestation;
	private double secondAttestation;
	private double finalExam;/*
	Implements getter
	of First attestation for Mark class*/

	public Mark(Course course, double firstAttestation, double secondAttestation, double finalExam) {
		this.course = course;
		this.firstAttestation = firstAttestation;
		this.secondAttestation = secondAttestation;
		this.finalExam = finalExam;
	}/*
	Implements getter
	of Second attestation for Mark class*/

	public double getFirstAttestation() {
    return firstAttestation;
  }/*
	Implements getter
	of Second attestation for Mark class*/

	public double getSecondAttestation() {
    return secondAttestation;
  }/*
	Implements getter
	of Total attestation for Mark class*/

	public double getTotal() {
    return firstAttestation * 0.3 + secondAttestation * 0.3 + finalExam * 0.4;
  }/*Implements getter
	of Course for Mark class*/

	public Course getCourse() {
    return course;
  }/*
	Implements getter
	of Final Exam for Mark class*/

	public double getFinalExam() {
    return finalExam;
  }
}