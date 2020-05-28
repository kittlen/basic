package service.factorys;

import impl.NewsImpl;
import service.interfaces.NewsInterface;

public class NewsFactory {
	public static NewsInterface newsInterface() {
		return new NewsImpl();
	}

}
