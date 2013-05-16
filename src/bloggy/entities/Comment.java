package bloggy.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name="commnts")
public class Comment {
	
	@Id
	@GeneratedValue
	private int postId;
	
	private String name;
	private String comment;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	public Comment(){
	}
	
	public Comment (int postId, String name, String comment, Date date){
		setPostId(postId);
		setName(name);
		setComment(comment);
		setDate(date);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	
}
