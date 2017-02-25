package crawler.service;

import java.util.List;

import crawler.dao.AnswerDao;
import crawler.dao.impl.AnswerDaoImpl;
import crawler.entity.Answer;

public class AnswerService {
	AnswerDao dao = new AnswerDaoImpl();

	/**
	 * 根据问题id查找出答案列表
	 * 
	 * @param questionid
	 * @return
	 */
	public List<Answer> getAnswersByQuestionId(String questionid) {
		return dao.getAnswersByQuestionId(questionid);
	}

}
