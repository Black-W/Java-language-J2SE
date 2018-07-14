package lab12_1;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JSliderPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	JSlider slider;
	JTextField textField=new JTextField();
	JLabel label;
	JButton approve;
	
	public JSliderPanel() {
		slider=new JSlider(SwingConstants.HORIZONTAL, 0	, 1000, 0);
		slider.setMajorTickSpacing(100);
		slider.setPaintTicks(true);
		
		label=new JLabel("当前刻度值");
		textField=new JTextField(Integer.toString(slider.getValue()), 16);
		approve=new JButton("确定");
		setLayout(new FlowLayout());
		add(label);
		add(textField);
		add(approve);
		add(slider);
		
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				textField.setText(Integer.toString(slider.getValue()));
				repaint();
			}
		});
		
		approve.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				slider.setValue(Integer.parseInt(textField.getText()));
				repaint();
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics arg0) {
		super.paintComponent(arg0);
	}
}
