package common;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import bean.Post;
import database.Forum;

public class test {

	public static void main(String[] args) {
		Mail m = new Mail();
		m.sendEmail("c.junsheng@hotmail.com", "test", "body","none");

	}

}
