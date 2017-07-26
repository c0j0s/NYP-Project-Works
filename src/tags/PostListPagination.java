package tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PostListPagination extends SimpleTagSupport {
	private int currentPage,pageCount,base = 0,itemPerPage = 10,maxCount = 0;
	private String category,type,postId;
	
	public String getPostId() {
		return postId;
	}

	public void setPostId(String postId) {
		this.postId = postId;
	}

	public int getItemPerPage() {
		return itemPerPage;
	}

	public void setItemPerPage(int itemPerPage) {
		this.itemPerPage = itemPerPage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setPageCount(int postCount) {
		maxCount = postCount;
		double pageCount = Math.ceil(postCount/(double)getItemPerPage());
		if(pageCount > (base + getItemPerPage())){
			base = base + getItemPerPage();
		}else{
			this.pageCount = (int)pageCount;
		}
	}

	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();

		//pagination
		if(maxCount > getItemPerPage()) {
			out.println("<div class='col-sm-2'></div><nav aria-label='Page navigation'><ul class='pagination pagination-lg'>");
			if(type.equalsIgnoreCase("post")) {
			
				if(currentPage != 1){
					out.println("<li><a href='?category="+category+"&page="+ (currentPage - 1) +"' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>");
				}
				
				for(int i = 1; i<=pageCount; i++){
			    	String active = "";
			    	if(currentPage == i) active = "active";
					out.println("<li class='"+ active +"'><a href='?category="+category+"&page="+ (base + i) +"'>"+ (base + i) +"</a></li>");
			    	 
			    }
				
				if(currentPage != pageCount){
					out.println("<li><a href='?category="+category+"&page="+ (currentPage + 1) +"' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>");
				}
			
			}else if(type.equalsIgnoreCase("comment")){
				
				if(currentPage != 1){
					out.println("<li><a href='?postId="+getPostId()+"&page="+ (currentPage - 1) +"' aria-label='Previous'><span aria-hidden='true'>&laquo;</span></a></li>");
				}
				
				for(int i = 1; i<=pageCount; i++){
			    	String active = "";
			    	if(currentPage == i) active = "active";
					out.println("<li class='"+ active +"'><a href='?postId="+getPostId()+"&page="+ (base + i) +"'>"+ (base + i) +"</a></li>");
			    	 
			    }
				
				if(currentPage != pageCount){
					out.println("<li><a href='?postId="+getPostId()+"&page="+ (currentPage + 1) +"' aria-label='Next'><span aria-hidden='true'>&raquo;</span></a></li>");
				}
				
			}
			out.println("</ul></nav>");
		}
	}

}
