package com.cst438.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int question_id;

    @Column(name = "question_text")
    private String question_text;
    @Column(name = "option_a")
    private String option_a;
    @Column(name = "option_b")
    private String option_b;
    @Column(name = "option_c")
    private String option_c;
    @Column(name = "option_d")
    private String option_d;
    @Column(name = "correct_answer")
    private String correct_answer;

    public Question() {
    }

    public Question(String question_text, String option_a, String option_b, String option_c, String option_d, String correct_answer) {
        this.question_text = question_text;
        this.option_a = option_a;
        this.option_b = option_b;
        this.option_c = option_c;
        this.option_d = option_d;
        this.correct_answer = correct_answer;
    }

	public int getQuestion_id() {
		return question_id;
	}

	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}

	public String getQuestion_text() {
		return question_text;
	}

	public void setQuestion_text(String question_text) {
		this.question_text = question_text;
	}

	public String getOption_a() {
		return option_a;
	}

	public void setOption_a(String option_a) {
		this.option_a = option_a;
	}

	public String getOption_b() {
		return option_b;
	}

	public void setOption_b(String option_b) {
		this.option_b = option_b;
	}

	public String getOption_c() {
		return option_c;
	}

	public void setOption_c(String option_c) {
		this.option_c = option_c;
	}

	public String getOption_d() {
		return option_d;
	}

	public void setOption_d(String option_d) {
		this.option_d = option_d;
	}

	public String getCorrect_answer() {
		return correct_answer;
	}

	public void setCorrect_answer(String correct_answer) {
		this.correct_answer = correct_answer;
	}

	@Override
	public String toString() {
		return "Question [question_id=" + question_id + ", question_text=" + question_text + ", option_a=" + option_a
				+ ", option_b=" + option_b + ", option_c=" + option_c + ", option_d=" + option_d + ", correct_answer="
				+ correct_answer + "]";
	}

    
}
