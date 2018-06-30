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
	
	//创建服务器界面类
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

	//创建服务器界面
	private void initialize() {
		btn=new JButton("开启服务器");
		btn1=new JButton("关闭服务器");
		btn1.setEnabled(false);
		btn.addActionListener(this);
		btn1.addActionListener(this);
		label = new JLabel("服务器未开启");
        getContentPane().add(label);
        getContentPane().add(btn);
        getContentPane().add(btn1);
        setTitle("服务器端");
        getContentPane().setLayout(new GridLayout(3, 0, 0, 0));
        setSize(500, 350);
        setLocation(200, 200);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
    
	//对按钮创建监听器
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==btn) {
			label.setText("服务器已开启！");
			btn.setEnabled(false);
			btn1.setEnabled(true);

			try {
				sereverSocket=new ServerSocket(Constant.port);
				
				while(true) {
					
					Socket socket=sereverSocket.accept();
					Thread thread =new Thread(new ServerThread(socket));
					thread.start();
					label.setText("服务器已开启");
					
				}
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
		}else if(e.getSource()==btn1) {
			label.setText("服务器已关闭！");
			btn.setEnabled(true);
			btn1.setEnabled(false);
		    System.exit(0);
		}
		
	}

		
	}

