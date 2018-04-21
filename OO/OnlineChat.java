import java.util.*;

public class OnlineChat {
	public static void main(String[] args) {
		User user1, user2, user3;
		user1 = new CommonUser("1", "zhang");
		user2 = new CommonUser("2", "li");
		user3 = new CommonUser("3", "chen");
		user1.addFriend(user2);
		user1.addFriend(user3);
		Set<User> charUsers = new HashSet<User>();
		charUsers.add(user2);
		charUsers.add(user3);
		Chat ch1 = user1.createChat(charUsers, "ch1");
		Msg msg = new TextMsg("1", "12", "hello");
		ch1.sendMsg(msg, user1);
		msg.appendMsg(" too");
		ch1.sendMsg(msg, user2);
	}

}

interface User extends Comparable<User> {

	String getUserId();

	String getUserName();

	boolean addFriend(User user);

	boolean handleAddRequest(User user);

	Chat createChat(Set<User> users, String chatId);

	boolean handleChatRequest(Chat chat);

	void sendMsg(Chat chat, Msg msg);

	void rcvMsg(Msg msg, User user);

}

class CommonUser implements User {
	private String userId;
	private String userName;
	private Set<User> friends;
	private Map<String, Chat> chats;

	public CommonUser(String userId, String userName) {
		this.userId = userId;
		this.userName = userName;
		this.friends = new HashSet<User>();
		this.chats = new HashMap<String, Chat>();
	}

	public int compareTo(User user) {
		if (this.userId == user.getUserId()) {
			return 0;
		}
		return this.userId.compareTo(user.getUserId());
	}

	public String getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public boolean addFriend(User user) {
		if (user.handleAddRequest(this)) {
			System.out.println("user " + this.getUserId() + " " + this.getUserName() + " add user " + user.getUserId());
			friends.add(user);
			return true;
		}
		return false;
	}

	public boolean handleAddRequest(User user) {
		System.out.println("user " + this.getUserId() + " " + this.getUserName() + " accept user " + user.getUserId());
		friends.add(user);
		return true;
	}

	public Chat createChat(Set<User> users, String chatId) {
		System.out.println("user " + this.getUserId() + " " + this.getUserName() + " create chat with " + users);
		Chat chat = new MultiChat(chatId);
		for (User user : users) {
			user.handleChatRequest(chat);
		}
		chat.addUser(this);
		chats.put(chatId, chat);
		return chat;
	}

	public boolean handleChatRequest(Chat chat) {
		System.out.println("user " + this.getUserId() + " accept chat " + chat.getChatId() + " request");
		chat.addUser(this);
		return true;
	}

	public void sendMsg(Chat chat, Msg msg) {
		System.out.println("user " + this.getUserId() + " send msg " + msg.getMsg() + " to chat " + chat.getChatId());
		chat.sendMsg(msg, this);
	}

	public void rcvMsg(Msg msg, User user) {
		System.out.println("user: " + this.getUserId() + " " + this.getUserName() + " recv msg: " + msg.getMsg()
				+ " from user " + user.getUserId());
	}

}

interface Chat {
	void addUser(User user);

	void delUser(User user);

	void sendMsg(Msg msg, User user);

	void notifyUserMsg(Msg msg, User user);

	String getChatId();
}

class MultiChat implements Chat {
	private String chatId;
	private Set<User> users;
	private List<Msg> msgs;

	public MultiChat(String chatId) {
		this.chatId = chatId;
		users = new HashSet<User>();
		msgs = new LinkedList<Msg>();
	}

	public void addUser(User user) {
		System.out.println("chat " + this.getChatId() + " add user " + user.getUserId());
		users.add(user);
	}

	public void delUser(User user) {
		System.out.println("chat " + this.getChatId() + " delete user " + user.getUserId());
		users.remove(user);
	}

	public void sendMsg(Msg msg, User user) {
		System.out.println("chat " + this.getChatId() + " user " + user.getUserId() + " send msg " + msg.getMsg());
		msgs.add(msg);
		notifyUserMsg(msg, user);
	}

	public void notifyUserMsg(Msg msg, User user) {
		for (User recvUser : users) {
			if (recvUser.compareTo(user) != 0) {
				System.out.println("notify user " + recvUser.getUserId() + " msg");
				recvUser.rcvMsg(msg, user);
			}
		}
	}

	public String getChatId() {
		return chatId;
	}
}

abstract class Msg {
	private String msgId;
	private String msgTime;
	private StringBuilder msg;

	public Msg(String id, String time) {
		msgId = id;
		msgTime = time;
		msg = new StringBuilder();
	}

	public void appendMsg(String s) {
		msg.append(s);
	}

	public String getMsg() {
		return msg.toString();
	}

	public String getMsgId() {
		return msgId;
	}

	public String getMsgTime() {
		return msgTime;
	}
}

class TextMsg extends Msg {
	public TextMsg(String id, String time, String content) {
		super(id, time);
		appendMsg(content);
	}
}