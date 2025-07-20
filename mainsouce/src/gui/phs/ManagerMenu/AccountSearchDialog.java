package gui.phs.ManagerMenu;

import javax.swing.*;
import java.util.function.Consumer;

public class AccountSearchDialog extends JDialog {
    public static class FilterCriteria {
        public String id = "";
        public String name = "";
        public String position = "전체";
        // 생성자, getter/setter 등
    }

    public AccountSearchDialog(JFrame parent, Consumer<FilterCriteria> onSearch) {
        super(parent, "검색필터", true);
        // 다이얼로그 UI 구성
        // 검색 버튼 누르면 onSearch.accept(new FilterCriteria(...)) 호출
    }
}
