package crawler.action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.opensymphony.xwork2.ModelDriven;

import crawler.entity.Answer;
import crawler.entity.Question;
import crawler.service.AnswerService;
import crawler.service.QuestionService;

public class QuestionAction extends SuperAction implements ModelDriven<Question> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8892340943628850008L;
	private Question question;
	private String content;// 用户输入的需要搜索的内容
	private QuestionService questionService = new QuestionService();
	private AnswerService answerService = new AnswerService();
	private static final int PAGENUM = 10;
	private int start;// 开始记录
	private int count;// 显示条数
	private int total=1;//总页数

	/**
	 * 根据话题查找问题
	 * 
	 * @return
	 */
	public String getQuestionsByKey() {
		String flag = "1";
		String content = request.getParameter("content");

		// 获取分页索引
		String index = request.getParameter("index");
		if (index == null) {
			return "index";
		}
		int num = Integer.parseInt(index);
		if (num < 1)
			num = 1;
		start = (num - 1) * PAGENUM;
		count = PAGENUM;

		// 如果搜索内容为null，将不进行搜索
		if (String.valueOf(content).equals("null") || String.valueOf(content).equals("")) {
			return "index";
		}

		// 第一种情况：搜索话题
		List<Question> questions = questionService.getQuestionByTopicName(content, start, count);
		if(questions.size() != 0){
			//计算总页数
			total = questionService.getTotalByTopicName(content);
			total = total / PAGENUM + 1;
		}

		// 第二种情况：如果根据话题查出的问题为0，则根据问题描述进行模糊查询
		if (questions.size() == 0) {
			questions = questionService.getQuestionByKey(content, start, count);
			flag = "0";
			// 如果依然为空，不进行搜索
			if (questions.size() == 0) {
				return "index";
			}
			//计算总页数
			total = questionService.getTotalByKey(content);
			total = total / PAGENUM + 1;
		}

		

		session.setAttribute("total", total);
		session.setAttribute("index", index);
		session.setAttribute("questions", questions);
		session.setAttribute("content", content);
		session.setAttribute("flag", flag);
		return "questions";
	}

	/**
	 * 根据问题查找答案
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getAnswers() throws UnsupportedEncodingException {
		String questionid = request.getParameter("questionid");
		String content = request.getParameter("content");
		String index = request.getParameter("index");
		

		// 根据问题id查找出答案列表
		List<Answer> answers = answerService.getAnswersByQuestionId(questionid);
		// 根据问题id查询问题
		Question question = questionService.getQuestionById(questionid);

		session.setAttribute("index", index);
		session.setAttribute("question", question);
		session.setAttribute("answers", answers);
		session.setAttribute("content", content);
		return "answers";
	}

	/**
	 * 返回到搜索页面
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String index() {
		return "index";
	}

	@Override
	public Question getModel() {
		return this.question;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
