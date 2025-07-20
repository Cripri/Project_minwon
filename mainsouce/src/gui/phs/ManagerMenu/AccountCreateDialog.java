package gui.phs.ManagerMenu;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class AccountCreateDialog extends JDialog {
    private JTextField idField;
    private JPasswordField passwordField;
    private JTextField nameField;
    private JComboBox<String> departmentCombo;
    private JComboBox<String> positionCombo;

    public AccountCreateDialog(Window parent, Consumer<Object[]> onCreate) {
        super(parent, "계정 생성", ModalityType.APPLICATION_MODAL);
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("아이디"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(15);
        formPanel.add(idField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("비밀번호"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("이름"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(15);
        formPanel.add(nameField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("담당부서"), gbc);
        gbc.gridx = 1;
        String[] departments = {"기획부서", "마케팅부서", "지원부서"};
        departmentCombo = new JComboBox<>(departments);
        formPanel.add(departmentCombo, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("직급"), gbc);
        gbc.gridx = 1;
        String[] positions = {"주무관", "주임", "사무장"};
        positionCombo = new JComboBox<>(positions);
        formPanel.add(positionCombo, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton createBtn = new JButton("생성");
        JButton cancelBtn = new JButton("취소");
        buttonPanel.add(createBtn);
        buttonPanel.add(cancelBtn);

        cancelBtn.addActionListener(e -> dispose());

        createBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            String pwd = new String(passwordField.getPassword()).trim();
            String name = nameField.getText().trim();
            String dept = (String) departmentCombo.getSelectedItem();
            String pos = (String) positionCombo.getSelectedItem();

            if (id.isEmpty() || pwd.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "모든 필드를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Object[] newRow = new Object[]{
                    String.valueOf(generateNewRegisterNumber()),
                    id,
                    name,
                    pos,
                    dept,
                    "가능", "", ""
            };

            onCreate.accept(newRow);
            dispose();
        });

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private int generateNewRegisterNumber() {
        return (int) (System.currentTimeMillis() % 100000);
    }
}
