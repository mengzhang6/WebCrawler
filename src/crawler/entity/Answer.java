package crawler.entity;

import java.io.Serializable;

public class Answer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1197403592951161761L;
	private String id;
	private String link_id;// 问题id
	private String author;// 作者
	private String answer;// 回答
	private String vote_amount;// 点赞数
	private int num;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLink_id() {
		return link_id;
	}

	public void setLink_id(String link_id) {
		this.link_id = link_id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getVote_amount() {
		return vote_amount;
	}

	public void setVote_amount(String vote_amount) {
		this.vote_amount = vote_amount;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}
