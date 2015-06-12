package th.go.excise.edbarcode.report.jaxb;

import javax.xml.bind.annotation.XmlElement;

public class Answer {
	private int id;
	private String answername;
	private String postedby;

	public Answer() {
	}

	public Answer(int id, String answername, String postedby) {
		super();
		this.id = id;
		this.answername = answername;
		this.postedby = postedby;
	}
	
	@XmlElement(name = "Id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement(name = "Answername")
	public String getAnswername() {
		return answername;
	}

	public void setAnswername(String answername) {
		this.answername = answername;
	}
	
	@XmlElement(name = "Postedby")
	public String getPostedby() {
		return postedby;
	}

	public void setPostedby(String postedby) {
		this.postedby = postedby;
	}

}