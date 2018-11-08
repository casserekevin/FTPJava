package messages;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Request implements Serializable{
	private static final long serialVersionUID = -1638483608386781863L;
	
	public static final int CLOSE_INSTANCE_CONNECTION_REQUEST = 0;
	
	public static final int DOWNLOAD_FILE_REQUEST = 1;
	public static final int FILECONTENT_REQUEST = 2;
	
	private int requestCode;
	private MessageContent requestContent;  

}
