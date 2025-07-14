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

    public AccountCreateDialog(JFrame parent, Consumer<Object[]> onCreate) {
        super(parent, "계정 생성", true);
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());
        setResizable(false);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 아이디
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("아이디"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(15);
        formPanel.add(idField, gbc);

        // 비밀번호
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("비밀번호"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        formPanel.add(passwordField, gbc);

        // 이름
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("이름"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(15);
        formPanel.add(nameField, gbc);

        // 담당부서 (콤보박스)
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("담당부서"), gbc);
        gbc.gridx = 1;
        String[] departments = {"기획부서", "마케팅부서", "지원부서"};
        departmentCombo = new JComboBox<>(departments);
        formPanel.add(departmentCombo, gbc);

        // 직급 (콤보박스)
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("직급"), gbc);
        gbc.gridx = 1;
        String[] positions = {"주무관", "주임", "사무장"};
        positionCombo = new JComboBox<>(positions);
        formPanel.add(positionCombo, gbc);

        // 버튼 패널
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

            if(id.isEmpty() || pwd.isEmpty() || name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "모든 필드를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 간단히 테이블에 추가할 데이터 배열 생성 (등록번호 자동 생성)
            Object[] newRow = new Object[]{
                    String.valueOf(generateNewRegisterNumber()),
                    id,
                    name,
                    pos,
                    "",
                    ""
            };
            onCreate.accept(newRow);
            dispose();
        });

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private int generateNewRegisterNumber() {
        // 임시로 현재 시간을 밀리초 단위로 사용 (실제론 DB 시퀀스 등 사용)
        return (int) (System.currentTimeMillis() % 100000);
    }
}