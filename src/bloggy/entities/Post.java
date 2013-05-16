package bloggy.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table( name="posts")
public class Post {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	private String post;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	public Post(){
		
	}
	
	public Post(int id,String name, String post, Date date) {
		setId(id);
		setName(name);
		setPost(post);
		setDate(date);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
