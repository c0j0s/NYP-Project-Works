package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import bean.Account;
import bean.Post;

/**
 * @author cjuns
 *
 */
public class ForumDB extends DBAO implements ForumMetaById{
	
	public ForumDB(){
		super();
	}
	
	/**
	 * create new post into database
	 * @param post
	 * @return postId
	 */
	public String createPost(Post post){
		
		String stmt = "INSERT INTO "+ schema +".post (`postId`, `postTitle`, `postDate`, `postContent`, `postCategory`, `points`, `valid`, `hideId`, `tagList`, `UseraccountId`,`ActivityactivityId`) "
				+ "VALUES (?,?,?,?,?,?,'Y',?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			post.setPostId(common.UID.genPostId());
			ps.setString(1, post.getPostId());
			ps.setString(2, post.getPostTitle());
			ps.setString(3, post.getDate());
			ps.setString(4, post.getPostContent());
			ps.setString(5, post.getPostCategory());
			ps.setInt(6, post.getPoints());
			ps.setString(7, String.valueOf(post.getHideId()));
			ps.setString(8, post.getTagList());
			ps.setString(9, post.getAccountId());
			if(post.getActivityId() != null){
				ps.setString(10, post.getActivityId());
			}else{
				ps.setString(10, null);
			}

			int status = ps.executeUpdate();
			if(status != 0){
				ps.close();
				
				return post.getPostId();
			}else{
				ps.close();
				
				return "fail";
			}
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "fail";
	}

	/**
	 * To retrieve all post from database
	 * @param statement
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> getPost(String statement){
		
		ArrayList<Post> postList = new ArrayList<Post>();
		try {
			PreparedStatement ps;
			if(statement == null){
				statement = "SELECT * FROM "+ schema +".postlist ORDER BY postStatus DESC,postDate DESC";
			}
			ps = con.prepareStatement(statement);
			ps.setFetchSize(1000);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Post post = new Post();
				
				post.setPostId(rs.getString("postId"));
				post.setPostTitle(rs.getString("postTitle"));
				post.setPostContent(rs.getString("postContent"));
				post.setPostCategory(rs.getString("postCategory"));
				post.setTagList(rs.getString("tagList"));
				post.setAccountName(rs.getString("givenName"));
				post.setAccountImgUrl(rs.getString("imgUrl"));
				post.setAccountId(rs.getString("UseraccountId"));
				post.setActivityId(rs.getString("ActivityactivityId"));
				post.setDate(rs.getString("postDate"));
				post.setPostStatus(rs.getString("postStatus"));
				post.setPoints(rs.getInt("points"));
				
				post.setLikeCount(rs.getInt("likeCount"));
				post.setDislikeCount(rs.getInt("dislikeCount"));
				post.setFollowerCount(rs.getInt("followCount"));
				
				post.setCommentCount(rs.getInt("commentCount"));
				post.setValid(rs.getString("valid").charAt(0));
				post.setHideId(rs.getString("hideId").charAt(0));
	
				MetaValueDB mdb = new MetaValueDB();
				post.setFollowerAccounts(mdb.getMetaAccounts("post", "postId", post.getPostId(), "follow"));
				post.setBestAnswer(rs.getString("bestAnswer"));
				
				ResultSetMetaData rsmd = rs.getMetaData();		
				if(rsmd.getColumnName(rsmd.getColumnCount()).equals("hitlevel")) {
					post.setHitLevel(Integer.parseInt(rs.getString("hitlevel")));
				}
				postList.add(post);
			}
			rs.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return postList;
	}	
	
	/**
	 * To retrieve all post from database for simple list | performance testing
	 * @param statement
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> getPostSimpleList(int start,String category){
		ArrayList<Post> postList = new ArrayList<Post>();
		try {
			String statement = "SELECT postId,postTitle,givenName,imgUrl,UseraccountId,postDate,points,likeCount,dislikeCount,commentCount,valid,hideId,postStatus"
					+ " FROM "+ schema +".postlist WHERE valid = 'Y' AND postCategory = '"+ category +"' ORDER BY postStatus DESC,postDate DESC limit " + start + ", 10";
			PreparedStatement ps = con.prepareStatement(statement);
			ps.setFetchSize(1000);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Post post = new Post();
				post.setPostId(rs.getString("postId"));
				post.setPostTitle(rs.getString("postTitle"));
				post.setAccountName(rs.getString("givenName"));
				post.setAccountImgUrl(rs.getString("imgUrl"));
				post.setAccountId(rs.getString("UseraccountId"));
				post.setDate(rs.getString("postDate"));
				post.setPoints(rs.getInt("points"));
				post.setLikeCount(rs.getInt("likeCount"));
				post.setDislikeCount(rs.getInt("dislikeCount"));
				post.setCommentCount(rs.getInt("commentCount"));
				post.setValid(rs.getString("valid").charAt(0));
				post.setHideId(rs.getString("hideId").charAt(0));
				post.setPostStatus(rs.getString("postStatus"));
				postList.add(post);
			}
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return postList;
	}
	
	/**
	 * retrieve post by category
	 * @param start 
	 * @param limit 
	 * @param category 
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> getPost(int start,String category){
		String stmt = "SELECT * FROM "+ schema +".postlist WHERE valid = 'Y' AND postCategory = '"+ category +"' ORDER BY postStatus DESC,postDate DESC limit " + start + ", 10";
		return getPost(stmt);
	}
		
	/**
	 * retrieve post for post page by post id
	 * @param postId
	 * @return ArrayList<Post>
	 */
	public Post getPostById(String postId){
		String stmt = "SELECT * FROM "+ schema +".postlist WHERE postId = '"+ postId +"'";
		ArrayList<bean.Post> pl = getPost(stmt);
		if(pl.size() > 0) {
			Post p = pl.get(0);
			return p;
		}else {
			return null;
		}
	}
	
	/**
	 * get top three trending post
	 * @param limit
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> getTrendingPost() {
		String stmt = "Select * from ffl.trendinglist";
		return getPost(stmt);
	}

	/**
	 * get all post created by the user
	 * @param accountId
	 * @return ArrayList<Post>
	 */
	public ArrayList<Post> getUserPost(String accountId){
		String stmt = "Select * from ffl.postlist where UseraccountId = '"+ accountId +"'";
		return getPost(stmt);
	}
	
	/**
	 * get max post count for pagination
	 * @param category
	 * @return
	 */
	public int getPostCount(String category) {
		
		String stmt = "Select count(*) from ffl.postlist where postCategory = ? and valid = 'Y'";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, category);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) return rs.getInt(1);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	/**
	 * get top 5 answerer
	 * @return ArrayList<Account>
	 */
	public ArrayList<Account> getTopAnswerer(){
		ArrayList<Account> list = new ArrayList<Account>();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT imgUrl,givenName,(postCounts + commentCounts +(bestAnswerCount * 2)) hitLevel FROM ffl.userinfo limit 0,5;");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Account ac = new Account();
				ac.setImgUrl(rs.getString("imgUrl"));
				ac.setGivenName(rs.getString("givenName"));
				ac.setPostsCounts(rs.getInt("hitLevel"));
				list.add(ac);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * add category [Not tested]
	 * @param newCat
	 */
	public void addCategory(String newCat){
		
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO "+ schema +".category(`categoryName`) VALUES (?)");
			ps.setString(1, newCat);
			int status = ps.executeUpdate();
			if(status != 0){
				System.out.println("Log addCategory(): " + ps);
			}
			
		} catch (SQLException e) {
			System.out.println("Log addCategory(): " + e.getMessage());
		}
	}
	
	/**
	 * retrieve category list
	 * @return
	 */
	public Map<String, String> getCategoryList(){
		
		Map<String, String> categoryList = new HashMap<String, String>();
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM "+ schema +".category");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				categoryList.put(rs.getString("categoryId"), rs.getString("categoryName"));
			}
			con.createStatement();
		} catch (SQLException e) {
			System.out.println("Log getCategory(): " + e.getMessage());
		}
		return categoryList;
	}

	/**
	 * update post value
	 * @param input
	 * @param postId
	 * @return
	 */
	public String updatePost(Map<String, String> input, String postId) {
		
		String startStmt = "Update ffl.post set ";
		String bodyStmt = "";
		for(Entry<String, String> entry : input.entrySet()) {
			 bodyStmt = bodyStmt + entry.getKey() + " = '" + entry.getValue() + "',";
		}
		bodyStmt = bodyStmt.substring(0, (bodyStmt.length()-1));
		String endStmt = " Where postId = ?";

		try {
			String statement = startStmt + bodyStmt + endStmt;
			PreparedStatement ps = con.prepareStatement(statement);
			ps.setString(1, postId);
			
			int status = ps.executeUpdate();
			
			if(status != 0) {
				ps.close();
				
				return postId;
			}else {
				
				ps.close();
				return "fail";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return "fail";
	}

	/**
	 * for deleting post -- invalidate post 
	 * @param postId
	 */
	public void invalidPost(String postId) {
		
		String statement = "update ffl.post set valid = 'N' where postId = ?";
		try {
			PreparedStatement ps = con.prepareStatement(statement);
			ps.setString(1, postId);

			int status = ps.executeUpdate();
			
			if(status != 0) {
				System.out.println("log invalidPost("+ postId +"): (success)" + ps);
			}
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * valid post
	 * @param postId
	 */
	public void validPost(String postId) {
		String statement = "update ffl.post set valid = 'Y' where postId = ?";
		try {
			PreparedStatement ps = con.prepareStatement(statement);
			ps.setString(1, postId);

			int status = ps.executeUpdate();
			
			if(status != 0) {
				System.out.println("log invalidPost("+ postId +"): (success)" + ps);
			}
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * select comment as best answer for post
	 * @param postId
	 * @param commentId
	 */
	public void pickBestAnswer(String postId, String commentId) {
		
		String stmt = "update ffl.post set bestAnswer = ?, postStatus = 'closed' where postId = ?";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, commentId);
			ps.setString(2, postId);
			
			int status = ps.executeUpdate();
			if (status != 0) {
				
				System.out.println("log pickBestAnswer(): (success)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getItemAccountIdByItemId(String itemId) {
		String stmt = "select UseraccountId from ffl.post where postId = ?";
		try {
			PreparedStatement ps = con.prepareStatement(stmt);
			ps.setString(1, itemId);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
