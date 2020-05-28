package action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import model.News;
import service.factorys.NewsFactory;
import service.interfaces.NewsInterface;
import utils.ActionUtils;

public class NewsAction {
	private String pushNews;
	private String newsId;
	Gson gson = new Gson();

	NewsInterface nif = NewsFactory.newsInterface();

	// 新闻添加
	public void insert() {
		News news = gson.fromJson(pushNews, News.class);
		if (nif.insert(news)) {
			try {
				ActionUtils.actionPrintWrite("success");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 查询总新闻
	public void select() {
		List<News> list = nif.select();
		if (list.size() > 0) {
			String listStr = gson.toJson(list);
			try {
				ActionUtils.actionPrintWrite(listStr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				ActionUtils.actionPrintWrite("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 根据新闻id查询新闻
	public void selectById() {
		List<News> list = nif.selectById(Integer.parseInt(newsId));
		if (list.size() > 0) {
			News news = list.get(0);
			String newsStr = gson.toJson(news);
			try {
				ActionUtils.actionPrintWrite(newsStr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				ActionUtils.actionPrintWrite("");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void upload() throws Exception {
		Map<String, String> map = ActionUtils.MultipartFormResponse();
		ActionUtils.actionPrintWrite("success");
	}

	public String getPushNews() {
		return pushNews;
	}

	public void setPushNews(String pushNews) {
		this.pushNews = pushNews;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

}
