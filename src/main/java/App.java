

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import org.quartz.SchedulerException;

import com.fajarlabs.cronjobs.CronJob;

public class App {

	private JFrame frame;
	private JTextField path1;
	private JTextField path2;
	private JTextField path3;
	private JTextField cron1;
	private JTextField cron2;
	private JTextField cron3;
	private JTextField group1;
	private JTextField group2;
	private JTextField group3;
	private JTextField trigger1;
	private JTextField trigger2;
	private JTextField trigger3;
	private CronJob cronJob = new CronJob();
	private JLabel lblJobName;
	private JTextField job1;
	private JTextField job2;
	private JTextField job3;
	
	private final JButton btnStart1 = new JButton("Start");
	private final JButton btnStart2 = new JButton("Start");
	private final JButton btnStart3 = new JButton("Start");
	private final JButton btnStop1 = new JButton("Stop");
	private final JButton btnStop2 = new JButton("Stop");
	private final JButton btnStop3 = new JButton("Stop");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 820, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Scheduler V 2.1.7");
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblE = new JLabel("Path Execute");
		lblE.setBounds(10, 11, 114, 14);
		panel.add(lblE);
		
		JLabel lblCronFormula = new JLabel("Cron Formula");
		lblCronFormula.setBounds(535, 11, 105, 14);
		panel.add(lblCronFormula);
		
		path1 = new JTextField();
		path1.setText("DATA 1");
		path1.setBounds(10, 26, 264, 20);
		panel.add(path1);
		path1.setColumns(10);
		
		path2 = new JTextField();
		path2.setText("DATA 2");
		path2.setBounds(10, 46, 264, 20);
		panel.add(path2);
		path2.setColumns(10);
		
		path3 = new JTextField();
		path3.setText("DATA 3");
		path3.setBounds(10, 66, 264, 20);
		panel.add(path3);
		path3.setColumns(10);
		
		cron1 = new JTextField();
		cron1.setText("0 0/1 * 1/1 * ? *");
		cron1.setBounds(535, 26, 105, 20);
		panel.add(cron1);
		cron1.setColumns(10);
		
		cron2 = new JTextField();
		cron2.setText("0 0/1 * 1/1 * ? *");
		cron2.setBounds(535, 46, 105, 20);
		panel.add(cron2);
		cron2.setColumns(10);
		
		cron3 = new JTextField();
		cron3.setText("0 0/1 * 1/1 * ? *");
		cron3.setBounds(535, 66, 105, 20);
		panel.add(cron3);
		cron3.setColumns(10);
		
		btnStart1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cronJob.buildCronJob(job1.getText(),group1.getText(), trigger1.getText(),cron1.getText(), path1.getText());
					btnStart1.setEnabled(false);
					btnStop1.setEnabled(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SchedulerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStart1.setBounds(647, 26, 71, 21);
		panel.add(btnStart1);
		
		btnStart2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cronJob.buildCronJob(job2.getText(),group2.getText(), trigger2.getText(),cron2.getText(), path2.getText());
					btnStart2.setEnabled(false);
					btnStop2.setEnabled(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SchedulerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStart2.setBounds(647, 46, 71, 21);
		panel.add(btnStart2);
		
		btnStart3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					cronJob.buildCronJob(job3.getText(),group3.getText(), trigger3.getText(),cron1.getText(), path3.getText());
					btnStart3.setEnabled(false);
					btnStop3.setEnabled(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SchedulerException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnStart3.setBounds(647, 66, 71, 21);
		panel.add(btnStart3);
		
		btnStop1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cronJob.deleteCronJob(job1.getText(), group1.getText());
				btnStart1.setEnabled(true);
				btnStop1.setEnabled(false);
			}
		});
		btnStop1.setEnabled(false);
		btnStop1.setBounds(720, 26, 71, 21);
		panel.add(btnStop1);
		
		btnStop2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cronJob.deleteCronJob(job2.getText(), group2.getText());
				btnStart2.setEnabled(true);
				btnStop2.setEnabled(false);
			}
		});
		btnStop2.setEnabled(false);
		btnStop2.setBounds(720, 47, 71, 20);
		panel.add(btnStop2);
		
		btnStop3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cronJob.deleteCronJob(job3.getText(), group3.getText());
				btnStart3.setEnabled(true);
				btnStop3.setEnabled(false);
			}
		});
		btnStop3.setEnabled(false);
		btnStop3.setBounds(720, 67, 71, 20);
		panel.add(btnStop3);
		
		JTextArea textArea = new JTextArea();
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		System.setOut(printStream);
		System.setErr(printStream);
		textArea.setBounds(10, 91, 792, 171);
		panel.add(textArea);
		
		group1 = new JTextField();
		group1.setText("GROUP-1");
		group1.setBounds(365, 26, 89, 20);
		panel.add(group1);
		group1.setColumns(10);
		
		group2 = new JTextField();
		group2.setText("GROUP-1");
		group2.setColumns(10);
		group2.setBounds(365, 46, 89, 20);
		panel.add(group2);
		
		group3 = new JTextField();
		group3.setText("GROUP-1");
		group3.setColumns(10);
		group3.setBounds(365, 66, 89, 20);
		panel.add(group3);
		
		JLabel lblGroup = new JLabel("Group Name");
		lblGroup.setBounds(365, 11, 89, 14);
		panel.add(lblGroup);
		
		JLabel lblTrigger = new JLabel("Trigger Name");
		lblTrigger.setBounds(456, 11, 83, 14);
		panel.add(lblTrigger);
		
		trigger1 = new JTextField();
		trigger1.setText("TRIGGER-1");
		trigger1.setColumns(10);
		trigger1.setBounds(456, 26, 78, 20);
		panel.add(trigger1);
		
		trigger2 = new JTextField();
		trigger2.setText("TRIGGER-2");
		trigger2.setColumns(10);
		trigger2.setBounds(456, 46, 78, 20);
		panel.add(trigger2);
		
		trigger3 = new JTextField();
		trigger3.setText("TRIGGER-3");
		trigger3.setColumns(10);
		trigger3.setBounds(456, 66, 78, 20);
		panel.add(trigger3);
		
		lblJobName = new JLabel("Job Name");
		lblJobName.setBounds(275, 11, 89, 14);
		panel.add(lblJobName);
		
		job1 = new JTextField();
		job1.setText("JOB-1");
		job1.setColumns(10);
		job1.setBounds(275, 26, 89, 20);
		panel.add(job1);
		
		job2 = new JTextField();
		job2.setText("JOB-2");
		job2.setColumns(10);
		job2.setBounds(275, 46, 89, 20);
		panel.add(job2);
		
		job3 = new JTextField();
		job3.setText("JOB-3");
		job3.setColumns(10);
		job3.setBounds(275, 66, 89, 20);
		panel.add(job3);
	}
	
	class CustomOutputStream extends OutputStream {
	    private JTextArea textArea;

	    public CustomOutputStream(JTextArea textArea) {
	        this.textArea = textArea;
	    }

	    @Override
	    public void write(int b) throws IOException {
	        textArea.append(String.valueOf((char)b));
			DefaultCaret caret = (DefaultCaret)textArea.getCaret();
			caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	    }
	}
}
