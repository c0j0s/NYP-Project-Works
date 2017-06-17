package Controllers;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

public class UID{
	public static String genId(){
		int r = new Random().nextInt(10000000);
		DecimalFormat df = new  DecimalFormat("0000000");
		String id = df.format(r);
		return id;
	}
	public static String genAccountId(){
		String id = "ACC" + genId();
		return id;
	}
	public static String genPostId(){
		String id = "POS" + genId();
		return id;
	}
	public static String genCommentId(){
		String id = "COM" + genId();
		return id;
	}
	public static String genActivityId(){
		String id = "ACT" + genId();
		return id;
	}
	public static String genRewardId(){
		String id = "REW" + genId();
		return id;
	}
	public static UUID genSessionId(){
		UUID uid = UUID.randomUUID();
		return uid;
	}
}
