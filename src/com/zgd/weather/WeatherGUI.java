
/**
 * @Copyright (C) 2015 Harbin Institute of Technology
 * @Progect: WeatherUtil
 * @Package com.zgd.weather
 * @Author zgd
 * @Date 2015年4月17日 下午10:31:29
 */
package com.zgd.weather;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JComboBox;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.TextField;
import javax.swing.JTextArea;

/**
 * @Class: WeatherGUI
 * @Date 2015年4月17日 下午10:31:29
 * @Author zgd
 * @Version
 */
public class WeatherGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WeatherGUI frame = new WeatherGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	JLabel weatherLabel2;
	JLabel weatherLabel;
	JComboBox cityBox;
	JComboBox ProvinceCombo;
	static DefaultComboBoxModel cityModel =null;
	private JLabel weatherImageLabel;
	private JLabel weatherImageLabel2;
	private JTextArea textArea;
	/**
	 * Create the frame.
	 */
	public WeatherGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 556, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("省份：");
		lblNewLabel.setFont(new Font("华文行楷", Font.PLAIN, 15));
		lblNewLabel.setBounds(47, 57, 72, 18);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("天气概况：");
		label.setFont(new Font("华文行楷", Font.PLAIN, 15));
		label.setBounds(31, 111, 88, 18);
		contentPane.add(label);
		
		weatherLabel = new JLabel("");
		weatherLabel.setFont(new Font("楷体", Font.PLAIN, 15));
		weatherLabel.setBounds(116, 111, 408, 18);
		contentPane.add(weatherLabel);
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String province,city;
				if(ProvinceCombo.getSelectedItem()==null ) {
					province = "上海";
					city = "上海";
				}
				else{
					province = (String) ProvinceCombo.getSelectedItem();
					city = (String) cityBox.getSelectedItem();
				}
				int provinceCode = new WeatherUtil().getProvinceCode(province);
				int cityCode = new WeatherUtil().getCityCode(provinceCode, city);
				List<String> weatherList = new WeatherUtil().getWeather(cityCode);
				StringBuffer bf = new StringBuffer("");
				StringBuffer bf1 = new StringBuffer("");
				StringBuffer bf2 = new StringBuffer("");
				StringBuffer bf3 = new StringBuffer("");
				int i=0;
				for(String n:weatherList){
					System.out.println(n);
					if(i>6&&i<10) bf1.append(n);
					else if(i==10) bf2.append(n);
					else if(i==11) bf3.append(n);
					else if(i==4||i==5)bf.append(n);
					i++;
				}
				weatherLabel.setText("");
				weatherLabel.setText(bf1.toString());
				weatherImageLabel.setText("");
				weatherImageLabel.setIcon(new ImageIcon("weather/weather/"+bf2.toString()));
				weatherImageLabel2.setText("");
				weatherImageLabel2.setIcon(new ImageIcon("weather/weather/"+bf3.toString()));
				textArea.setText("");
				textArea.setText(bf.toString());
			}
		});
		button.setBounds(357, 342, 113, 27);
		contentPane.add(button);
		
		weatherLabel2 = new JLabel("天气实况：");
		weatherLabel2.setFont(new Font("华文行楷", Font.PLAIN, 15));
		weatherLabel2.setBounds(31, 178, 88, 18);
		contentPane.add(weatherLabel2);
		
		weatherImageLabel = new JLabel("");
		weatherImageLabel.setBounds(143, 142, 72, 18);
		contentPane.add(weatherImageLabel);
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		cityModel = new DefaultComboBoxModel();
		model.addElement("");
		model.addElement("北京");
		model.addElement("上海");
		model.addElement("广东");
		model.addElement("福建");
		model.addElement("四川");
		model.addElement("陕西");
		model.addElement("黑龙江");
		model.addElement("湖南");
		model.addElement("湖北");
		model.addElement("浙江");
		 ProvinceCombo = new JComboBox(model);
		 ProvinceCombo.setFont(new Font("华文行楷", Font.PLAIN, 15));
		ProvinceCombo.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				cityModel.removeAllElements();
				if(ProvinceCombo.getSelectedIndex()>0){	
					switch (ProvinceCombo.getSelectedIndex()){
						case 1:
							cityModel.addElement("北京");
							break;
						case 2:
							cityModel.addElement("上海");
							break;
						case 3:
							cityModel.addElement("广州");
							cityModel.addElement("东莞");
							cityModel.addElement("佛山");
							break;
						case 4:
							cityModel.addElement("福州");
							cityModel.addElement("厦门");
							cityModel.addElement("泉州");
							cityModel.addElement("宁德");
							break;
						case 5:
							cityModel.addElement("成都");
//							cityModel.addElement("");
//							cityModel.addElement("泉州");
//							cityModel.addElement("宁德");
							break;
						case 6:
							cityModel.addElement("西安");
							break;
						case 7:
							cityModel.addElement("哈尔滨");
							break;
						case 8:
							cityModel.addElement("长沙");
							break;
						case 9:
							cityModel.addElement("武汉");
							break;
						case 10:
							cityModel.addElement("杭州");
							cityModel.addElement("宁波");
							cityModel.addElement("温州");
							break;
							default:
								System.out.println("");
								break;
					}
				}
			}
		});
		ProvinceCombo.setMaximumRowCount(40);
		ProvinceCombo.setToolTipText("");
		ProvinceCombo.setBounds(116, 54, 103, 24);
		contentPane.add(ProvinceCombo);
		
		JLabel label_3 = new JLabel("城市：");
		label_3.setFont(new Font("华文行楷", Font.PLAIN, 15));
		label_3.setBounds(253, 57, 72, 18);
		contentPane.add(label_3);
		
		cityBox = new JComboBox(cityModel);
		cityBox.setFont(new Font("华文行楷", Font.PLAIN, 15));
		cityBox.setBounds(334, 54, 136, 24);
		contentPane.add(cityBox);
		
		weatherImageLabel2 = new JLabel("");
		weatherImageLabel2.setBounds(208, 142, 72, 18);
		contentPane.add(weatherImageLabel2);
		
		textArea = new JTextArea();
		textArea.setBounds(116, 175, 408, 146);
		contentPane.add(textArea);
	}
}
