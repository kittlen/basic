package service.interfaces;

import java.util.List;

import model.News;
import model.User;

public interface NewsInterface {
	List<News> select();
	boolean insert(News news);
	List<News> selectById(Integer newsId);

}
