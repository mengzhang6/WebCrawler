package crawler.dao;

import java.util.List;

import crawler.entity.Answer;

public interface AnswerDao {

	public List<Answer> getAnswersByQuestionId(String questionid);

}
