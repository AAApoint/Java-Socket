import java.awt.EventQueue;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class Client implements Runnable{
    
	//创建用户界面类
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;
	private JButton button_1 ;
	private JScrollPane scrollPane;
	private Socket socket=null;
	private DataInputStream inputstream=null;
	private DataOutputStream outputstream=null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {	
		
		    Client client = new Client();
			client.frame.setVisible(true);
			Thread thread = new Thread(client);
	        thread.start();
			
			
	}

	/**
	 * Create the application.
	 */
	public Client() {
		initialize();

			try {
				socket=new Socket("127.0.0.1",Constant.port);
				inputstream=new DataInputStream(socket.getInputStream());
				outputstream=new DataOutputStream(socket.getOutputStream());
			} catch (UnknownHostException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		
	}
    
  
	/**
	 * Initialize the contents of the frame.
	 * 创建用户端界面
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Client.class.getResource("/images/qq.png")));
		frame.setTitle("\u5317\u533A3012\u5C0F\u7EC4-\u591A\u7A97\u53E3\u7248");
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
                    if (socket != null)
                        socket.close();
                    if (inputstream!= null)
                        inputstream.close();
                    if (outputstream != null)
                        outputstream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
			}
		});
		frame.setBounds(100, 100, 744, 409);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u6635\u79F0 \uFF1A");
		label.setBounds(49, 33, 58, 15);
		frame.getContentPane().add(label);
		
		textField = new JTextField();
		textField.setBounds(100, 31, 265, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1=textField.getText();
				
				if(textField.getText().length()==0) {
					textArea.append("昵称不能为空！\n");
				}else {
					
					textField.setEditable(false);
					button.setEnabled(false);
					
					String s2=Constant.USER+s1;
					try {
						outputstream.writeUTF(s2);
						
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				
                   if(Server.clients.containsKey(s1)) {
                	   textArea.append("用户名重复！");
                   }else {
                	  
					   button_1.setEnabled(true);
                   }				
					
				
				}
				
				
			}
		});
		button.setBounds(389, 30, 97, 23);
		frame.getContentPane().add(button);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(38, 69, 473, 223);
		frame.getContentPane().add(scrollPane_1);
		
		textArea = new JTextArea();
		scrollPane_1.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		
		textField_1 = new JTextField();
		textField_1.setBounds(31, 322, 372, 21);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		button_1 = new JButton("\u53D1\u9001");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s=textField_1.getText();
				if(!s.equals("")) {
				try {
					outputstream.writeUTF(s);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			} textField_1.setText("");
	       }
		});
		button_1.setBounds(418, 324, 97, 23);
		frame.getContentPane().add(button_1);
		button_1.setEnabled(false);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setText("\u4F7F\u7528\u8BF4\u660E\uFF1A1.\u767B\u9646\u540E\u76F4\u63A5\r\n\r\n\u53D1\u6D88\u606F\u4E3A\u7FA4\u804A\uFF1B2.@+\u5BF9\r\n\r\n\u65B9\u6635\u79F0+\uFF1A(\u4E2D\u6587\u5192\u53F7)+\r\n\r\n\u5185\u5BB9\u5373\u53EF\u8FDB\u884C\u79C1\u804A\u3002");
		textArea_1.setBounds(541, 43, 143, 318);
		frame.getContentPane().add(textArea_1);
		
		
		
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		try {
            inputstream = new DataInputStream(socket.getInputStream());

            while (true) {
                String s = inputstream.readUTF();
                textArea.append(s+"\n");
            }
        } catch (IOException e) {
        }
		
	}
}
