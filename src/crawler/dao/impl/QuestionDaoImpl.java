package crawler.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import crawler.dao.QuestionDao;
import crawler.db.MySqlUtils;
import crawler.entity.Question;

public class QuestionDaoImpl implements QuestionDao {
	private MySqlUtils utils = MySqlUtils.getInstance();
	private int i = 0;

	/**
	 * 根据问题id查询问题
	 */
	@Override
	public Question getQuestionById(String questionId) {
		Question question = new Question();

		Map map = utils
				.finddatabase("SELECT id,link_id,question_name FROM get_question WHERE link_id = '"
						+ questionId + "'");
		if (map == null || String.valueOf(map).equals("[]")) {
			return null;
		} else {
			i++;
			question.setId(String.valueOf(map.get("id")));
			question.setLink_id(String.valueOf(map.get("link_id")));
			question.setQuestion_name(String.valueOf(map.get("question_name")));
			// int num = Integer.parseInt(String.valueOf(map.get("id"))) % 3;
			int num = i % 3;
			question.setNum(num);
			return question;
		}

	}

	/**
	 * 根据问题描述模糊查询出问题列表
	 */
	@Override
	public List<Question> getQuestionByKey(String key, int start, int count) {
		List<Question> questions = new ArrayList<Question>();

		List<Map> list = utils
				.finddatabase_list("SELECT link_id,question_name FROM get_question WHERE question_name LIKE '%"
						+ key
						+ "%' ORDER BY link_id LIMIT "
						+ start
						+ ","
						+ count + "");

		for (Map map : list) {
			String id = String.valueOf(map.get("link_id"));
			Question question = getQuestionById(id);
			questions.add(question);
		}
		return questions;
	}

	/**
	 * 根据模糊查询话题得到所有相匹配的问题
	 */
	@Override
	public List<Question> getQuetionByTopicName(String key, int start, int count) {
		List<Question> questions = new ArrayList<Question>();

		List<Map> list = utils
				.finddatabase_list("SELECT link_id FROM get_question WHERE topic_id in (SELECT LINK_ID FROM topic WHERE `NAME` LIKE '%"
						+ key + "%') LIMIT " + start + "," + count);

		for (Map map : list) {
			String id = String.valueOf(map.get("link_id"));
			Question question = getQuestionById(id);
			questions.add(question);
		}
		return questions;
	}

	/**
	 * 搜索话题,计算总页数
	 */
	@Override
	public int getTotalByTopicName(String content) {
		List<Map> list = utils
				.finddatabase_list("SELECT link_id FROM get_question WHERE topic_id in (SELECT LINK_ID FROM topic WHERE `NAME` LIKE '%"
						+ content + "%')");
		return list.size();
	}

	/**
	 * 根据话题查出的问题,计算总页数
	 */
	@Override
	public int getTotalByKey(String content) {
		List<Map> list = utils
				.finddatabase_list("SELECT link_id FROM get_question WHERE question_name LIKE '%"
						+ content + "%' ORDER BY link_id ");
		return list.size();
	}

}
