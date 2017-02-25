package crawler.service;

import java.util.ArrayList;
import java.util.List;

import crawler.dao.QuestionDao;
import crawler.dao.impl.QuestionDaoImpl;
import crawler.entity.Question;

public class QuestionService {
	QuestionDao questionDao = new QuestionDaoImpl();

	/**
	 * 根据话题查询，返回问题集合
	 * 
	 * @param key
	 * @return
	 */
	public List<Question> getQuestionByTopicName(String key, int start, int count) {

		List<Question> questions = new ArrayList<Question>();
		questions = questionDao.getQuetionByTopicName(key, start, count);

		return questions;
	}

	/**
	 * 根据问题id查询问题
	 * 
	 * @param questionId
	 * @return
	 */
	public Question getQuestionById(String questionId) {
		return questionDao.getQuestionById(questionId);
	}

	/**
	 * 根据问题描述模糊查询问题
	 * 
	 * @param key
	 * @return
	 */
	public List<Question> getQuestionByKey(String key, int start, int count) {
		return questionDao.getQuestionByKey(key, start, count);
	}

	/**
	 * 搜索话题,计算总页数
	 * 
	 * @param content
	 * @return
	 */
	public int getTotalByTopicName(String content) {
		return questionDao.getTotalByTopicName(content);
	}

	/**
	 * 根据话题查出的问题,计算总页数
	 * 
	 * @param content
	 * @return
	 */
	public int getTotalByKey(String content) {
		return questionDao.getTotalByKey(content);
	}

}
