package model;

import java.util.List;

public class News {
    private int newsId;
    private String title;//标题
    private String content;//内容
    private List<String > imageName;//图片
    private String imageNameStr;//图片
    private User user;//作者
    private String type;//类型
    private String time;//时间
    private int heat;//热度
    private int comment;//评论数

    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImageName() {
        return imageName;
    }

    public void setImageName(List<String> imageName) {
        this.imageName = imageName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getHeat() {
        return heat;
    }

    public void setHeat(int heat) {
        this.heat = heat;
    }

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}

	public String getImageNameStr() {
		return imageNameStr;
	}

	public void setImageNameStr(String imageNameStr) {
		this.imageNameStr = imageNameStr;
	}


	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

  
}
