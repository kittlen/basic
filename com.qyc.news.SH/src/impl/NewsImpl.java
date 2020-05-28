package impl;

import java.util.ArrayList;
import java.util.List;

import basedao.Hibernatedao;
import basedao.iHibernatedao;
import model.News;
import model.User;
import service.interfaces.NewsInterface;

public class NewsImpl implements NewsInterface{
	iHibernatedao hb=new Hibernatedao();

	@Override
	public List<News> select() {
		// TODO Auto-generated method stub
		List<News> list=new ArrayList<News>();
		String HQL="from News n ORDER BY n.time desc";
		list=hb.select(HQL);
		return list;
	}


	@Override
	public boolean insert(News news) {
		// TODO Auto-generated method stub
		if("0".equals(hb.save(news).toString())) {
			return false;
		}
		return true;
	}


	@Override
	public List<News> selectById(Integer newsId) {
		// TODO Auto-generated method stub
		List<News> list=new ArrayList<>();
		String HQL="from News n where n.newsId = ?";
		Object parmas[]= {newsId};
		list=hb.select(HQL, parmas);
		return list;
	}

}
