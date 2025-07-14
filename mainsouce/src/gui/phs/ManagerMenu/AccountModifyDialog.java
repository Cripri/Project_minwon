package gui.phs.ManagerMenu;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class AccountModifyDialog extends JDialog {
    private JTextField idField, nameField, positionField, deptField;
    private JComboBox<String> complaintDeptChange;
    private JButton saveBtn, cancelBtn;
    private Consumer<Object[]> onSave;

    public AccountModifyDialog(Window owner, String userId, Consumer<Object[]> onSave) {
        super(owner, "계정 수정", ModalityType.APPLICATION_MODAL);
        this.onSave = onSave;

        setLayout(new GridLayout(7, 2, 10, 10));
        setSize(350, 300);
        setLocationRelativeTo(owner);

        add(new JLabel("등록번호"));
        JTextField regNumField = new JTextField();
        regNumField.setEditable(false);
        add(regNumField);

        add(new JLabel("아이디"));
        idField = new JTextField(userId);
        idField.setEditable(false);
        add(idField);

        add(new JLabel("이름"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("직급"));
        positionField = new JTextField();
        add(positionField);

        add(new JLabel("담당부서"));
        deptField = new JTextField();
        add(deptField);

        add(new JLabel("민원 부서 변경"));
        complaintDeptChange = new JComboBox<>(new String[]{"가능", "불가"});
        add(complaintDeptChange);

        saveBtn = new JButton("저장");
        cancelBtn = new JButton("취소");

        saveBtn.addActionListener(e -> {
            Object[] updatedData = new Object[]{
                    regNumField.getText(),  // 등록번호 (비어있음, 필요시 셋팅 추가)
                    idField.getText(),
                    nameField.getText(),
                    positionField.getText(),
                    deptField.getText(),
                    complaintDeptChange.getSelectedItem(),
                    "", // 편집 버튼 자리
                    ""  // 삭제 버튼 자리
            };
            onSave.accept(updatedData);
            dispose();
        });

        cancelBtn.addActionListener(e -> dispose());

        add(saveBtn);
        add(cancelBtn);
    }
}
