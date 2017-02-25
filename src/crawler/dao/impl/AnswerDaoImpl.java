package crawler.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import crawler.dao.AnswerDao;
import crawler.db.MySqlUtils;
import crawler.entity.Answer;

public class AnswerDaoImpl implements AnswerDao {
	private MySqlUtils utils = MySqlUtils.getInstance();

	/**
	 * 根据问题id查询出答案列表
	 */

	@Override
	public List<Answer> getAnswersByQuestionId(String questionid) {
		List<Answer> answers = new ArrayList<Answer>();

		List<Map> list = utils
				.finddatabase_list("SELECT id,author,answer,vote_amount,link_id FROM answer WHERE link_id = '"
						+ questionid + "'");

		for (Map map : list) {
			Answer answer = new Answer();
			answer.setId(String.valueOf(map.get("id")));
			answer.setAuthor(String.valueOf(map.get("author")));
			answer.setAnswer(String.valueOf(map.get("answer")));
			answer.setVote_amount(String.valueOf(map.get("vote_amount")));
			answer.setLink_id(String.valueOf(map.get("link_id")));

			int num = Integer.parseInt(String.valueOf(map.get("id"))) % 3;
			answer.setNum(num);
			answers.add(answer);
		}

		return answers;
	}

}
