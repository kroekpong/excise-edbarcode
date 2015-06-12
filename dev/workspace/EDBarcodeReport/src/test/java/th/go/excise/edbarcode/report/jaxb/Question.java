package th.go.excise.edbarcode.report.jaxb;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Question")
public class Question {
	private int id;
	private String questionname;
	private List<Answer> answers;

	public Question() {
	}

	public Question(int id, String questionname, List<Answer> answers) {
		super();
		this.id = id;
		this.questionname = questionname;
		this.answers = answers;
	}

	@XmlAttribute(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement(name = "Questionname")
	public String getQuestionname() {
		return questionname;
	}

	public void setQuestionname(String questionname) {
		this.questionname = questionname;
	}
	
	@XmlElement(name = "Answers")
	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

}