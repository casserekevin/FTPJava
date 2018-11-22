package client.views.connection.plainsDocuments;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class ServerIPPlainDocument extends PlainDocument{

	@Override
	public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
		super.insertString(offs, str.replaceAll("[^\\d|^.]", ""), a);
	}
	
}
