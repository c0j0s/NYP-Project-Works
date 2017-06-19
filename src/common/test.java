package common;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import bean.Comment;
import bean.Post;
import database.CommentDB;
import database.Forum;

public class test {

	public static void main(String[] args) {
		CommentDB c = new CommentDB();
		Comment com = new Comment();
		com.setCommentContent("this is the comment content");
	}

}
