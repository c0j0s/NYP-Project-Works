package tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class PostListPagination extends SimpleTagSupport {
	private int currentPage,pageCount,base = 0;
	private String category;

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setPageCount(int postCount) {
		double pageCount = Math.ceil(postCount/10.0);
		if(pageCount > (base+10)){
			base = base + 10;
		}else{
			this.pageCount = (int)pageCount;
		}
	}

	public void doTag() throws JspException, IOException {
		JspWriter out = getJspContext().getOut();
				
		//pagination
		out.println("<nav aria-label='Page navigation'><ul class='pagination pagination-lg'>");
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
		out.println("</ul></nav>");
				
	}

}
