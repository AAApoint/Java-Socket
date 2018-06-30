import java.awt.Button;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Server extends JFrame implements ActionListener{
	
	//����������������
    private JButton btn,btn1;
    private JLabel label;
    private ServerSocket sereverSocket;
    public static ClientMap<String, DataOutputStream> clients=new ClientMap<>();
    
	public static void main(String[] args) {
		Server server =new Server();
	
	}

	public Server() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		initialize();
	}

	//��������������
	private void initialize() {
		btn=new JButton("����������");
		btn1=new JButton("�رշ�����");
		btn1.setEnabled(false);
		btn.addActionListener(this);
		btn1.addActionListener(this);
		label = new JLabel("������δ����");
        getContentPane().add(label);
        getContentPane().add(btn);
        getContentPane().add(btn1);
        setTitle("��������");
        getContentPane().setLayout(new GridLayout(3, 0, 0, 0));
        setSize(500, 350);
        setLocation(200, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
    
	//�԰�ť����������
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource()==btn) {
			label.setText("�������ѿ�����");
			btn.setEnabled(false);
			btn1.setEnabled(true);

			try {
				sereverSocket=new ServerSocket(Constant.port);
				
				while(true) {
					
					Socket socket=sereverSocket.accept();
					Thread thread =new Thread(new ServerThread(socket));
					thread.start();
					label.setText("�������ѿ���");
					
				}
			} catch (IOException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
			
		}else if(e.getSource()==btn1) {
			label.setText("�������ѹرգ�");
			btn.setEnabled(true);
			btn1.setEnabled(false);
		    System.exit(0);
		}
		
	}

		
	}

