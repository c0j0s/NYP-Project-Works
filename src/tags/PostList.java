package tags;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import bean.Forum;
import bean.Post;
import database.ForumDB;

public class PostList extends SimpleTagSupport {
	private int start,currentPage;
	private String category;
	StringWriter sw = new StringWriter();

	public void setStart(int start) {
		this.start = start;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void doTag() throws JspException, IOException {
		getJspBody().invoke(sw);
		JspWriter out = getJspContext().getOut();
		ForumDB fdb = new ForumDB();
		Forum f = fdb.getPostAdvance(start, 10);

		for(Post p: f.getPostList()){
			Map<String, String> input = new HashMap<String, String>();
			input.put("FFL:postId", p.getPostId());
			input.put("FFL:postTitle", p.getPostTitle());
			input.put("FFL:accountId", p.getAccountId());
			input.put("FFL:date", p.getDate());
			input.put("FFL:likeCount", Integer.toString(p.getLikeCount()));
			input.put("FFL:dislikeCount", Integer.toString(p.getDislikeCount()));
			input.put("FFL:commentCount", Integer.toString(p.getCommentCount()));
			out.print(insertValue(sw.toString(),input));
		}
		
		
		//pagination
		out.println("<nav aria-label='Page navigation'><ul class='pagination pagination-lg'>");
		if(currentPage != 1){
			out.println("<li><a href='?category=1&page="+ (currentPage - 1) +"}' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>");
		}
		
		for(int i = 1; i<=f.getPageCount(); i++){
	    	
			out.println("<li><a href='?category=1&page="+ i +"'>"+ i +"</a></li>");
	    	 
	    }
		
		if(currentPage != f.getPageCount()){
			out.println("<li><a href='?category=1&page="+ (currentPage + 1) +"}' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>");
		}
		out.println("</ul></nav>");
				
	}

	private static String insertValue(String file, Map<String, String> input)
	{

		try
		{
			Set<Entry<String, String>> entries = input.entrySet();
			for(Map.Entry<String, String> entry : entries) {
				file = file.replace(entry.getKey().trim(), entry.getValue().trim());
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
		return file;
	}
}
