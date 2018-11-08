package messages;
import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageContent implements Serializable{
	private static final long serialVersionUID = -9114067742875695406L;
	
	private byte[] contentBytes;
	private String contentString;
}
