package gui.mainframe.components;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

public class PlaceholderTextField extends JTextField {
	private final String placeholder;
    private boolean showingPlaceholder;

    public PlaceholderTextField(String placeholder, int columns) {
        super(placeholder, columns);
        this.placeholder = placeholder;
        this.showingPlaceholder = true;
        setForeground(Color.GRAY);

        // 포커스 리스너 등록
        this.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showingPlaceholder) {
                    setText("");
                    setForeground(Color.BLACK);
                    showingPlaceholder = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(Color.GRAY);
                    showingPlaceholder = true;
                }
            }
        });
    }

    @Override
    public String getText() {
        return showingPlaceholder ? "" : super.getText();
    }

}
