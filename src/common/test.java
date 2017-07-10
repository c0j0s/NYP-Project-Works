package common;

import java.util.HashMap;
import java.util.Map;

import database.DBAO;
import database.ForumDB;

public class test {

	public static void main(String[] args) {
		ForumDB fdb = new ForumDB();
		Map<String, String> input = new HashMap<String, String>();
		input.put("c", "v");
		input.put("b", "b");
		fdb.updatePost(input, "POS0000000");
	}

}
