package messages;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reply implements Serializable{
	private static final long serialVersionUID = -852600584896679439L;

	public static final int FILE_EXISTS_REPLY = 1;
	public static final int FILE_NOT_FOUND_REPLY = 2;
	public static final int CONTENT_AVAILABLE_REPLY = 3;
	public static final int EOF_REPLY = 4;
	
	private int replyCode;
	private MessageContent replyContent;
}
