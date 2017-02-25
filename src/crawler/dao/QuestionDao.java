package crawler.dao;

import java.util.List;

import crawler.entity.Question;

public interface QuestionDao {
	/**
	 * 
	 * @param questionId
	 * @return
	 */
	public Question getQuestionById(String questionId);

	/**
	 * 根据问题描述模糊查询问题
	 * 
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 */
	public List<Question> getQuestionByKey(String key, int start, int end);

	/**
	 * 根据话题查询，返回问题集合
	 * 
	 * @param key
	 * @param start
	 * @param count
	 * @return
	 */
	public List<Question> getQuetionByTopicName(String key, int start, int count);

	/**
	 * 搜索话题,计算总页数
	 * 
	 * @param content
	 * @return
	 */
	public int getTotalByTopicName(String content);

	/**
	 * 根据话题查出的问题,计算总页数
	 * @param content
	 * @return
	 */
	public int getTotalByKey(String content);

}
