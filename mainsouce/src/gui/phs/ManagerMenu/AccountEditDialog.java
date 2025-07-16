package gui.phs.ManagerMenu;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class AccountEditDialog extends JDialog {
    private JTextField idField;
    private JPasswordField passwordField;
    private JComboBox<String> departmentCombo;
    private JComboBox<String> positionCombo;
    private JCheckBox allowChangeCheckBox;

    public AccountEditDialog(JFrame parent, Object[] userData, Consumer<Object[]> onUpdate) {
        super(parent, "계정 수정", true);
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new GridBagLayout());
        setResizable(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 아이디 (수정 불가)
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("아이디"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(15);
        idField.setText(userData[1].toString());
        idField.setEditable(false);
        add(idField, gbc);

        // 비밀번호
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("비밀번호"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(15);
        add(passwordField, gbc);

        // 담당부서
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("담당부서"), gbc);
        gbc.gridx = 1;
        String[] departments = {"기획부서", "마케팅부서", "지원부서"};
        departmentCombo = new JComboBox<>(departments);
        add(departmentCombo, gbc);

        // 직급
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("직급"), gbc);
        gbc.gridx = 1;
        String[] positions = {"주무관", "주임", "사무장"};
        positionCombo = new JComboBox<>(positions);
        add(positionCombo, gbc);

        // 민원 부서 변경 허가 체크박스
        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("민원 부서 변경"), gbc);
        gbc.gridx = 1;
        allowChangeCheckBox = new JCheckBox("가능");
        add(allowChangeCheckBox, gbc);

        // 기존 데이터로 초기화
        String dept = userData.length > 4 && userData[4] != null ? userData[4].toString() : "";
        String pos = userData.length > 3 && userData[3] != null ? userData[3].toString() : "";
        departmentCombo.setSelectedItem(dept.isEmpty() ? departments[0] : dept);
        positionCombo.setSelectedItem(pos.isEmpty() ? positions[0] : pos);

        boolean allowChange = userData.length > 5 && userData[5] != null && userData[5].toString().equals("가능");
        allowChangeCheckBox.setSelected(allowChange);

        // 버튼 패널
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton updateBtn = new JButton("수정");
        JButton cancelBtn = new JButton("취소");
        buttonPanel.add(updateBtn);
        buttonPanel.add(cancelBtn);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        cancelBtn.addActionListener(e -> dispose());

        updateBtn.addActionListener(e -> {
            String pwd = new String(passwordField.getPassword()).trim();
            if (pwd.isEmpty()) {
                JOptionPane.showMessageDialog(this, "비밀번호를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // 수정된 데이터 배열 생성
            Object[] updatedData = new Object[]{
                userData[0], // 등록번호
                userData[1], // 아이디 (수정 불가)
                userData[2], // 이름 (필요 시 수정 UI 추가 가능)
                positionCombo.getSelectedItem(),
                departmentCombo.getSelectedItem(),
                allowChangeCheckBox.isSelected() ? "가능" : "불가"
            };

            onUpdate.accept(updatedData);
            dispose();
        });
    }
}