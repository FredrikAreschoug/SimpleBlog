package bloggy;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.apple.jobjc.PrimitiveCoder.SIntCoder;

import bloggy.entities.Comment;
import bloggy.entities.Post;

/**
 * Servlet implementation class start
 */
@WebServlet(value={"/Home", "/login", "/logout", "/newPost","/post/*", "/createNewPost", "/comment"})
public class controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static SessionFactory sessionFactory;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	 
	List<Post> posts;
	List<Comment> comments;
	
    public controller() {
        super();
        posts = new ArrayList<Post>();
        comments = new ArrayList<Comment>();
    }
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		
		String action = request.getRequestURI().replace("/Blog/", "");
		
		//checks whitch action to take
		if("createNewPost".equals(action)){
			httpSession.setAttribute("newPost", true);
			httpSession.setAttribute("inPost", false);
		}else if(action.startsWith("post")){
			int id = Integer.parseInt(action.split("/")[1]);
			
			httpSession.setAttribute("postId", id);
			httpSession.setAttribute("inPost", true);
		}else if("comment".equals(action)){
			httpSession.setAttribute("inPost", true);
		}
		
		if (request.getRequestURI().startsWith("/Blog/post/")){
			int id = Integer.parseInt(request.getRequestURI().replace("/Blog/post/", ""));
			httpSession.setAttribute("postId", id);
		}
		showView(request, response);
	}

/*
	Loads current site
*/
	private void showView(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		
		request.setAttribute("comments", comments);
		request.setAttribute("posts", posts);
		boolean inPost;
		if(null != httpSession.getAttribute("inPost")){
			inPost = (Boolean)httpSession.getAttribute("inPost");
		}else{
			inPost = false;
		}
		
		if(!inPost){
			request.getServletContext().getRequestDispatcher("/WEB-INF/views/allPosts.jsp").forward(request, response);
		}else{
			request.getServletContext().getRequestDispatcher("/WEB-INF/views/post.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession httpSession = request.getSession();
		String action = request.getRequestURI().replace("/Blog/", "");
		httpSession.setAttribute("newPost", null);
		
		System.out.println(action);
		
		//checks whitch action to take
		if("comment".equals(action)){
			String name = request.getParameter("name");
			String commentText = request.getParameter("comment");
			int postId = (int)httpSession.getAttribute("postId");
			System.out.println(postId);
			Date date = new Date();
			request.setAttribute("name", name);
			Comment comment = new Comment(postId,name, commentText, date);
			
			comments.add(comment);
			httpSession.setAttribute("inPost", true);
		}else if("logout".equals(action)){
			
			httpSession.invalidate();
		}else if("login".equals(action)){
			String username = request.getParameter("username");
			
			httpSession.setAttribute("username", username);
		}else if("newPost".equals(action)){
			String name = (String)httpSession.getAttribute("username");
			String postText = request.getParameter("post");
			Date date = new Date();
			Post post = new Post(posts.size(),name, postText, date);
			posts.add(0,post);
			httpSession.setAttribute("inPost", false);
		}
		
		String contextPath = request.getContextPath();
		
		response.sendRedirect(contextPath +"/"+ action);
	}

}
