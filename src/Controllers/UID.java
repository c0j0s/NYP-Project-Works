package Controllers;

import java.util.Random;
import java.util.UUID;

public class UID{
	public String genId(){
		int r = new Random().nextInt(100000000);
		String id = Integer.toString(r);
		return id;
	}
	public String genAccountId(){
		String id = "ACC" + genId();
		return id;
	}
	public String genPostId(){
		String id = "POS" + genId();
		return id;
	}
	public String genCommentId(){
		String id = "COM" + genId();
		return id;
	}
	public String genActivityId(){
		String id = "ACT" + genId();
		return id;
	}
	public String genRewardId(){
		String id = "REW" + genId();
		return id;
	}
	public UUID genSessionId(){
		UUID uid = UUID.randomUUID();
		return uid;
	}
}
