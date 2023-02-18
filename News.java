package intranet;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable{
	private static final long serialVersionUID = -794633865571151941L;
	private String title;
	private String text;
	private Date postDate;

	public News(String title, String text, Date postDate) {
		super();
		this.title = title;
		this.text = text;
		this.postDate = postDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
}