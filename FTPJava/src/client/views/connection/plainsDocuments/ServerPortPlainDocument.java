package client.views.connection.plainsDocuments;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class ServerPortPlainDocument extends PlainDocument {

	@Override
	public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException{
		super.insertString(offset, str.replaceAll("[^\\d]", ""), attr);
	}
	
}
