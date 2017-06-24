/**
 * 
 */
package bean;

import java.util.ArrayList;

/**
 * @author cjuns
 *
 */
public class Forum {
	private ArrayList<Post> postList;
	private String category;
	private int pageCount;
	
	public Forum() {
	
	}

	public ArrayList<Post> getPostList() {
		return postList;
	}

	public void setPostList(ArrayList<Post> postList) {
		this.postList = postList;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int postCount) {
		double pageCount = Math.ceil(postCount/10.0);
		if(pageCount > 10){
			this.pageCount = 10;
		}else{
			this.pageCount = (int)pageCount;
		}
	}
	
	
	
}
