package common;

import bean.Comment;
import database.CommentDB;

public class test {

	public static void main(String[] args) {
		CommentDB c = new CommentDB();
		Comment com = new Comment();
		com.setCommentContent("this is the comment content");
		com.setDate();
		com.setCommentGroup("Parent");
		com.setPostId("POS0000000");
		com.setAccountId("ACC0000000");
		String result = c.createComment(com);
		System.out.println(result);
	}

}
