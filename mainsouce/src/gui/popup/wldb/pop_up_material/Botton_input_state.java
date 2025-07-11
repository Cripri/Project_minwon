package gui.popup.wldb.pop_up_material;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

	public class Botton_input_state {
		// 임시 저장소
		// 팝업 창마다 하나씩 두면 되겠지?
		// 이름 버튼말고 팝업으로 바꿀까
		
		private JFrame pop_up;
		
		private ArrayList<JComponent> nexts = new ArrayList<>();
		private HashMap<String, String> input_texts = new HashMap<>();
		
		private JTextField pass_jt = null;
		private static String id = "아이디";
		private static String name = "이름";
		private static String password = "비밀번호";
		
		private String dpt = "";
		private String posin = "";
		private boolean change_dep_authority = false;
		private boolean delet = false;
		private String memo = null;
		
		 {
			input_texts.put(id, "");
			input_texts.put(name, "");
			input_texts.put(password, "");
			
		}
		
		public String getMemo() {
			return memo;
		}
		public void setMemo(String memo) {
			this.memo = memo;
		}
		
		
		public String getId() {
			return id;
		}
		public String getName() {
			return name;
		}
		public String getPassword() {
			return password;
		}
		public String getPosin() {
			return posin;
		}
		public void setPosin(String posin) {
			this.posin = posin;
		}
		public String getDpt() {
			return dpt;
		}
		public void setDpt(String dpt) {
			this.dpt = dpt;
		}
		public JFrame getPop_up() {
			return pop_up;
		}
		public void setPop_up(JFrame pop_up) {
			this.pop_up = pop_up;
		}
		public boolean isDelet() {
			return delet;
		}
		public void setDelet(boolean delet) {
			this.delet = delet;
		}
		public boolean isChange_dep_authority() {
			return change_dep_authority;
		}
		public void setChange_dep_authority(boolean change_dep_authority) {
			this.change_dep_authority = change_dep_authority;
		}
		public boolean getChange_dep_authority() {
			return this.change_dep_authority;
		}
		public boolean isDeleted() {
			return delet;
		}
		public void setDeleted(boolean delet) {
			this.delet = delet;
		}
		public ArrayList<JComponent> getNexts() {
			return nexts;
		}
		public void Nexts_add(JComponent tf) {
			this.nexts.add(tf);
		}
		public JTextField getPass_jt() {
			return pass_jt;
		}
		public void setPass_jt(JTextField pass_jt) {
			this.pass_jt = pass_jt;
		}
		public HashMap<String, String> getInput_texts() {
			return input_texts;
		}
		public void input_textsAdd(String str1, String str2) {
			this.input_texts.put(str1, str2);
		}
		public Set<String> getInput_texts_key() {
			return input_texts.keySet();
		}
		
		

}
