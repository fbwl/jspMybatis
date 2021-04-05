package survey.model.dto;

public class SurveyAnswerDTO {
	private int answer_no;
	private int no;
	private int answer;
	private int answer_count;
	
	public SurveyAnswerDTO() {
		super();
	}
	public int getAnswer_no() {
		return answer_no;
	}
	public void setAnswer_no(int answer_no) {
		this.answer_no = answer_no;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public int getAnswer_count() {
		return answer_count;
	}
	public void setAnswer_count(int answer_count) {
		this.answer_count = answer_count;
	}
	@Override
	public String toString() {
		return "SurveyAnswerDTO [answer_no=" + answer_no + ", no=" + no + ", answer=" + answer + ", answer_count="
				+ answer_count + "]";
	}
	
	
}
