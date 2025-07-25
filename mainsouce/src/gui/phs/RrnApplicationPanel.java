package gui.phs;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

import function.connector.QueryRequest;
import function.connector.Simple_doc;
import function.drawingsign.DrawingSign;
import function.pdfwriter.PDFViewerFromPath;
import function.pdfwriter.PDFWriter;
import gui.mainframe.MainFrameState;
import gui.mainframe.MyPage;
import gui.mainframe.components.addressComboBoxPanel;

import static gui.mainframe.MainFrameState.civil;
import static gui.mainframe.MainFrameState.member;

public class RrnApplicationPanel extends JPanel {

    Map<String,JCheckBox> simcheck = new HashMap<>();
    JTextField yearField1 = new JTextField(3);
    JTextField yearField2 = new JTextField(3);
    boolean check = true;

    public RrnApplicationPanel() {
        setLayout(new BorderLayout());
        Color backgroundColor = new Color(217, 217, 217);
        Font boldFont = new Font("맑은 고딕", Font.BOLD, 14);
        Font smallBoldFont = new Font("맑은 고딕", Font.BOLD, 12);


        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(backgroundColor);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));
        add(centerPanel, BorderLayout.CENTER);

        JLabel title = new JLabel("주민등록표 등본(초본) 발급");
        title.setFont(new Font("맑은 고딕", Font.BOLD, 22));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(title);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel guide1 = new JLabel("* 정보를 정확히 확인하세요. 주민등록상 주소와 다를 경우 신청이 불가합니다.");
        guide1.setFont(smallBoldFont);
        guide1.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(guide1);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        ButtonGroup docTypeGroup = new ButtonGroup();
        JRadioButton option1 = new JRadioButton("주민등록표 등본 발급");
        JRadioButton option2 = new JRadioButton("주민등록표 초본 발급");
        option1.setSelected(true);
        option1.setFont(boldFont);
        option2.setFont(boldFont);
        option1.setOpaque(false);
        option2.setOpaque(false);
        docTypeGroup.add(option1);
        docTypeGroup.add(option2);

        JPanel radioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        radioPanel.setOpaque(false);
        radioPanel.add(option1);
        radioPanel.add(option2);
        radioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(radioPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel subTitle = new JLabel("신청내용");
        subTitle.setFont(new Font("맑은 고딕", Font.BOLD, 19));
        subTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(subTitle);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        
        JLabel addressLabel = new JLabel("주민등록상 주소 확인 (필수)");
        addressLabel.setFont(boldFont);
        addressLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(addressLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        addressComboBoxPanel addressCombo = new addressComboBoxPanel();
        JPanel addressPanel = addressCombo.addressComboBoxPanel();
        addressPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(addressPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel issueTypeLabel = new JLabel("발급형태 선택 (필수)");
        issueTypeLabel.setFont(boldFont);
        issueTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(issueTypeLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JRadioButton allIssue = new JRadioButton("전체 발급");
        JRadioButton partialIssue = new JRadioButton("선택 발급");
        allIssue.setSelected(true);
        allIssue.setFont(boldFont);
        partialIssue.setFont(boldFont);
        allIssue.setOpaque(false);
        partialIssue.setOpaque(false);

        ButtonGroup issueGroup = new ButtonGroup();
        issueGroup.add(allIssue);
        issueGroup.add(partialIssue);

        JPanel issueRadioPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        issueRadioPanel.setOpaque(false);
        issueRadioPanel.add(allIssue);
        issueRadioPanel.add(partialIssue);
        issueRadioPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(issueRadioPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JPanel detailPanel = new JPanel(new CardLayout());
        detailPanel.setOpaque(false);
        detailPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        detailPanel.setVisible(false);

        JPanel copyPanel = new JPanel();
        copyPanel.setLayout(new BoxLayout(copyPanel, BoxLayout.Y_AXIS));
        copyPanel.setOpaque(false);
        copyPanel.add(createCheckboxWithSubOptions("1. 과거의 주소 변동 사항", boldFont, "addrHistory",yearField1));

        copyPanel.add(createCheckbox("2. 세대 구성 사유", boldFont));
        copyPanel.add(createCheckbox("3. 세대 구성 일자", boldFont));
        copyPanel.add(createCheckbox("4. 발생일 / 신고일", boldFont));
        copyPanel.add(createCheckboxWithSubOptions("5. 변동 사유", boldFont, "changeReason",yearField1));
        copyPanel.add(createCheckbox("6. 교부 대상자 외 세대주·세대원·외국인등록 이름", boldFont));
        copyPanel.add(createCheckboxWithSubOptions("7. 주민등록번호 뒷자리", boldFont, "rrnBack",yearField1));
        copyPanel.add(createCheckbox("8. 세대원의 세대주와의 관계", boldFont));
        copyPanel.add(createCheckbox("9. 동거인", boldFont));
        copyPanel.add(createCheckbox("10. 개인 인적 사항 변경 내용", boldFont));

        check = false;
        JPanel extractPanel = new JPanel();
        extractPanel.setLayout(new BoxLayout(extractPanel, BoxLayout.Y_AXIS));
        extractPanel.setOpaque(false);
        extractPanel.add(createCheckbox("1. 개인 인적 사항 변경 내용", boldFont));
        extractPanel.add(createCheckboxWithSubOptions("2. 과거의 주소 변동 사항", boldFont, "addrHistory",yearField2));
        extractPanel.add(createCheckbox("3. 과거의 주소 변동 중 세대주/세대주와의 관계", boldFont));
        extractPanel.add(createCheckboxWithSubOptions("4. 주민등록번호 뒷자리", boldFont, "rrnBack",yearField2));
        extractPanel.add(createCheckbox("5. 세대주의 성명과 세대주와의 관계", boldFont));
        extractPanel.add(createCheckbox("6. 발생일 / 신고일", boldFont));
        extractPanel.add(createCheckbox("7. 변동 사유", boldFont));
        extractPanel.add(createCheckboxWithSubOptions("8. 병역 사항", boldFont, "military",yearField2));
        extractPanel.add(createCheckbox("9. 국내거소신고번호 / 외국인등록번호", boldFont));

        detailPanel.add(copyPanel, "COPY");
        detailPanel.add(extractPanel, "EXTRACT");
        centerPanel.add(detailPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel guide2 = new JLabel("* 재외국민 등록사항을 제외한 모든 정보가 표시되지 않습니다.");
        guide2.setFont(smallBoldFont);
        guide2.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(guide2);
        centerPanel.add(Box.createVerticalGlue());

        // ✅ 전송 버튼
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);
        JButton submitBtn = new JButton("신청하기");
        submitBtn.setFont(boldFont);
        submitBtn.setBackground(new Color(0, 120, 255));
        submitBtn.setForeground(Color.WHITE);
        submitBtn.setFocusPainted(false);
        submitBtn.setPreferredSize(new Dimension(100, 40));
        btnPanel.add(submitBtn);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        centerPanel.add(btnPanel);
        submitBtn.addActionListener(e ->{
//            System.out.println("등본" + option1.isSelected());
//            System.out.println("초본" + option2.isSelected());
//            System.out.println("그뭐냐 시발 선택" + partialIssue.isSelected());
            Simple_doc ndoc = new Simple_doc();
            ndoc.setDistrict_code(addressCombo.findDistrictCode(addressCombo.getSido(),addressCombo.getSigungu()));
            LocalDate localDate = LocalDate.now();
            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            ndoc.setCreate_date(date);
            ndoc.setMember_code(member.getMember_code());
            ndoc.setDoc_count(1);
            if(option1.isSelected()){
                ndoc.setComplaint_category_code("AA001");
//                for(String str : simcheck.keySet()){
//                    System.out.println(str);
//                }

                check = true;
                String suffix = check ? "1" : "2";

                if (allIssue.isSelected()) {
                    ndoc.setAll_Included("Y");
                }
                if(partialIssue.isSelected()) {
                    ndoc.setAll_Included("N");
                    for (Map.Entry<String, JCheckBox> entry : simcheck.entrySet()) {
                        String label = entry.getKey();
                        JCheckBox cb = entry.getValue();
                        if (cb.isSelected()) {
                            switch (label) {
                                case "1. 과거의 주소 변동 사항":
                                    ndoc.setaddress_history("Y");
                                    if(simcheck.get("직접 입력: 최근" + suffix).isSelected()){
                                        if (yearField1.getText().equals("")) {
                                            ndoc.setAddress_history_years(1);
                                        } else {
                                            ndoc.setAddress_history_years(Integer.valueOf(yearField1.getText()));
                                            //System.out.println(Integer.valueOf(yearField1.getText()));
                                        }
                                    }
                                    break;
                                case "2. 세대 구성 사유":
                                    ndoc.sethousehold_reason("Y");
                                    break;
                                case "3. 세대 구성 일자":
                                    ndoc.sethousehold_date("Y");
                                    break;
                                case "4. 발생일 / 신고일":
                                    ndoc.setoccurrence_date("Y");
                                    break;
                                case "5. 변동 사유":
                                    ndoc.setprevious_address("Y");
                                    if(simcheck.get("세대" + suffix).isSelected()){
                                        ndoc.setprevious_address_self("Y");
                                    }
                                    if(simcheck.get("세대포함" + suffix).isSelected()){
                                        ndoc.setprevious_address_member("Y");
                                    }
                                    break;
                                case "6. 교부 대상자 외 세대주·세대원·외국인등록 이름":
                                    ndoc.sethead_name("Y");
                                    break;
                                case "7. 주민등록번호 뒷자리":
                                    ndoc.setrrn_last7("Y");
                                    if(simcheck.get("본인" + suffix).isSelected()){
                                        ndoc.setrrn_last7_self("Y");
                                    }
                                    if(simcheck.get("세대원" + suffix).isSelected()){
                                        ndoc.setrrn_last7_member("Y");
                                    }
                                    break;
                                case "8. 세대원의 세대주와의 관계":
                                    ndoc.sethead_relationship("Y");
                                    break;
                                case "9. 동거인":
                                    ndoc.setRoommate("Y");
                                    break;
                            }
                        }
                    }
                }
            }

            if(option2.isSelected()){
                ndoc.setComplaint_category_code("AA002");

                check = false;
                String suffix = check ? "1" : "2";

                if(allIssue.isSelected()){
                    ndoc.setAll_Included("Y");
                }

                if(partialIssue.isSelected()){
                    ndoc.setAll_Included("N");
                    for (Map.Entry<String, JCheckBox> entry : simcheck.entrySet()) {
                        String label = entry.getKey();
                        JCheckBox cb = entry.getValue();
                        if (cb.isSelected()) {
                            switch (label) {
                                case "1. 개인 인적 사항 변경 내용":
                                    ndoc.setpersonal_change_details("Y");
                                    break;
                                case "2. 과거의 주소 변동 사항":
                                    ndoc.setaddress_history("Y");
                                    if(simcheck.get("직접 입력: 최근" + suffix).isSelected()){
                                        if (yearField2.getText().equals("")) {
                                            ndoc.setAddress_history_years(1);
                                        } else {
                                            ndoc.setAddress_history_years(Integer.valueOf(yearField2.getText()));
                                            //System.out.println(Integer.valueOf(yearField2.getText()));
                                        }
                                    }
                                    break;
                                case "3. 과거의 주소 변동 중 세대주/세대주와의 관계":
                                    ndoc.sethead_relationship("Y");
                                    break;
                                case "4. 주민등록번호 뒷자리":
                                    ndoc.setrrn_last7("Y");
                                    if(simcheck.get("본인" + suffix).isSelected()){
                                        ndoc.setrrn_last7_self("Y");
                                    }
                                    if(simcheck.get("세대원" + suffix).isSelected()){
                                        ndoc.setrrn_last7_member("Y");
                                    }
                                    break;
                                case "5. 세대주의 성명과 세대주와의 관계":
                                    ndoc.sethead_relationship("Y");
                                    break;
                                case "6. 발생일 / 신고일":
                                    ndoc.setoccurrence_date("Y");
                                    break;
                                case "7. 변동 사유":
                                    ndoc.setprevious_address("Y");
                                    break;
                                case "8. 병역 사항":
                                    ndoc.setmilitary_service("Y");
                                    if(simcheck.get("전체" + suffix).isSelected()){
                                        ndoc.setMilitary_service_full("Y");
                                    }else{
                                        ndoc.setMilitary_service_basic_only("Y");
                                    }
                                    break;
                                case "9. 국내거소신고번호 / 외국인등록번호":
                                    ndoc.setid_number("Y");
                                    break;
                            }
                        }
                    }
                }
            }

            //System.out.println(ndoc);
            int pk = civil.getNextSeqValue(Simple_doc.class);
            ndoc.setSimple_doc_code(pk);
            //System.out.println(pk);
            civil.insert(ndoc);

            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this); // this는 패널일 경우
            DrawingSign signDialog = new DrawingSign(parent);
            signDialog.setVisible(true);

            String rrn = JOptionPane.showInputDialog("주민등록번호를 입력하세요:");
            if (rrn == null || rrn.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "주민등록번호가 입력되지 않았습니다.");
                return;
            }

            new PDFWriter(pk, rrn, MainFrameState.civil);



            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("파일 저장 위치 선택");

            // 타임스탬프 생성
//                        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String timestamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            // 기본 파일 이름 설정
            String defaultFileName = "발급_서류_" + timestamp + ".pdf";
            fileChooser.setSelectedFile(new File(defaultFileName));

            int result = fileChooser.showSaveDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                // .pdf 확장자 자동 추가
                if (!file.getName().toLowerCase().endsWith(".pdf")) {
                    file = new File(file.getAbsolutePath() + ".pdf");
                }

                // 덮어쓰기 확인
                if (file.exists()) {
                    int overwrite = JOptionPane.showConfirmDialog(
                            null,
                            "이미 같은 이름의 파일이 존재합니다.\n덮어쓰시겠습니까?",
                            "파일 덮어쓰기 확인",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );
                    if (overwrite != JOptionPane.YES_OPTION) {
                        return;
                    }
                }

                try {
                    new PDFWriter(
                            pk,
                            rrn,
                            MainFrameState.civil,
                            file.getAbsolutePath()  // 추가된 저장 경로
                    );
                    JOptionPane.showMessageDialog(null, "파일이 성공적으로 저장되었습니다.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "파일 저장 중 오류가 발생했습니다.");
                }
            }

            new PDFViewerFromPath("resources/pdf/temp.pdf");

            MyPage myPage = new MyPage();
            MainFrameState.card.add("myPage", myPage);
            MainFrameState.card.show("myPage");
        });

        allIssue.addActionListener(e -> detailPanel.setVisible(false));
        partialIssue.addActionListener(e -> {
            detailPanel.setVisible(true);
            ((CardLayout) detailPanel.getLayout()).show(detailPanel, option1.isSelected() ? "COPY" : "EXTRACT");
        });

        option1.addActionListener(e -> {
            if (partialIssue.isSelected()) detailPanel.setVisible(true);
            ((CardLayout) detailPanel.getLayout()).show(detailPanel, "COPY");
        });

        option2.addActionListener(e -> {
            if (partialIssue.isSelected()) detailPanel.setVisible(true);
            ((CardLayout) detailPanel.getLayout()).show(detailPanel, "EXTRACT");
        });
    }

    private JPanel createCheckbox(String text, Font font) {
        JCheckBox cb = new JCheckBox(text);
        cb.setFont(font);
        cb.setOpaque(false);
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        p.setOpaque(false);
        p.add(cb);
        simcheck.put(text,cb);
        return p;
    }

    private JPanel createCheckboxWithSubOptions(String labelText, Font font, String key, JTextField yearField) {
        JCheckBox mainCheckBox = new JCheckBox(labelText);
        mainCheckBox.setFont(font);
        mainCheckBox.setOpaque(false);
        simcheck.put(labelText, mainCheckBox);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setOpaque(false);

        JPanel topLine = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        topLine.setOpaque(false);
        topLine.add(mainCheckBox);
        mainPanel.add(topLine);

        JPanel subOptionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        subOptionPanel.setOpaque(false);
        subOptionPanel.setVisible(false);

        String suffix = check ? "1" : "2";

        switch (key) {
            case "addrHistory":
                JCheckBox totalInclude = new JCheckBox("전체 포함");
                JCheckBox directInput = new JCheckBox("직접 입력: 최근");
                JLabel yearLabel = new JLabel("년 포함");
                totalInclude.setOpaque(false);
                directInput.setOpaque(false);
                yearField.setMaximumSize(new Dimension(40, 20));

                subOptionPanel.add(totalInclude);
                subOptionPanel.add(directInput);
                subOptionPanel.add(yearField);
                subOptionPanel.add(yearLabel);

                ButtonGroup ahgroup = new ButtonGroup();
                ahgroup.add(totalInclude);
                ahgroup.add(directInput);

                simcheck.put("전체 포함" + suffix, totalInclude);
                simcheck.put("직접 입력: 최근" + suffix, directInput);

                totalInclude.setSelected(true);
                break;

            case "changeReason":
                JCheckBox cr1 = new JCheckBox("세대");
                JCheckBox cr2 = new JCheckBox("세대포함");
                cr1.setOpaque(false);
                cr2.setOpaque(false);
                subOptionPanel.add(cr1);
                subOptionPanel.add(cr2);
                cr1.setSelected(true);

                simcheck.put("세대" + suffix, cr1);
                simcheck.put("세대포함" + suffix, cr2);
                break;
            case "rrnBack":
                JCheckBox cb1 = new JCheckBox("본인");
                JCheckBox cb2 = new JCheckBox("세대원");
                cb1.setOpaque(false);
                cb2.setOpaque(false);
                subOptionPanel.add(cb1);
                subOptionPanel.add(cb2);
                cb1.setSelected(true);

                simcheck.put("본인" + suffix, cb1);
                simcheck.put("세대원" + suffix, cb2);
                break;

            case "military":
                JCheckBox basic = new JCheckBox("기본(입영/전역일자)");
                JCheckBox all = new JCheckBox("전체");
                basic.setOpaque(false);
                all.setOpaque(false);
                subOptionPanel.add(basic);
                subOptionPanel.add(all);
                basic.setSelected(true);
                ButtonGroup miligroup = new ButtonGroup();
                miligroup.add(basic);
                miligroup.add(all);

                simcheck.put("기본(입영/전역일자)" + suffix, basic);
                simcheck.put("전체" + suffix, all);
                break;
        }

        mainCheckBox.addActionListener(e -> subOptionPanel.setVisible(mainCheckBox.isSelected()));
        mainPanel.add(subOptionPanel);
        return mainPanel;
    }
} 
