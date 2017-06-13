package Controllers;

public class test {

	public static void main(String[] args) {
		UID id = new UID();
		System.out.println(id.genId());
		System.out.println(id.genAccountId());
		System.out.println(id.genActivityId());
		System.out.println(id.genCommentId());
		System.out.println(id.genActivityId());
		System.out.println(id.genRewardId());
		System.out.println(id.genSessionId());
	}

}
