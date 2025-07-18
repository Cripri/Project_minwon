package gui.popup.wldb;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

import gui.popup.wldb.pop_up_material.Botton_input_state;
import gui.popup.wldb.pop_up_material.Color_list;
import gui.popup.wldb.pop_up_material.Get_base;
import gui.popup.wldb.pop_up_material.Get_pop_up_buttons;

public class Button_test {
	
	static Botton_input_state creation_state = new Botton_input_state();
	static Botton_input_state fix_state = new Botton_input_state();
	static Botton_input_state delet_state = new Botton_input_state();
	static Botton_input_state sinmungo_state = new Botton_input_state();
	
	static Get_pop_up_buttons get_pop = new Get_pop_up_buttons();
	
	
	public static void main(String[] args) {
		
		Get_base set = new Get_base();
		
		
		
		JButton employees_creation = get_pop.get_account_makes_button();
		
		JButton employees_fix = get_pop.get_account_fix_button();
		
		JButton employees_delet = get_pop.get_account_delet_button();
		
		JButton sinmungo_reply = get_pop.get_sinmungo_reply_button();
		
		JButton pop_ups = get_pop.get_alarm_button();
		
		
		JFrame test = set.get_base("시험용");
		// 내가 테스트를 먼저 만들고 버튼을 넣으면 버튼이 안생기는데 
		// 버튼을 만들고 테스트를 넣으면 잘 작동됨
		// 이게 뭔 버그여...
		// 확인 결과 버퍼링 문제인듯...
		
		
		test.add(employees_creation);
		test.add(employees_fix);
		test.add(employees_delet);
		test.add(sinmungo_reply);
		test.add(pop_ups);
		
	}

}
