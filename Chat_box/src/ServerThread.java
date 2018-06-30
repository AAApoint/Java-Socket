import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerThread implements Runnable{
	
	//���������߳���
	private Socket socket;
	private DataInputStream inputStream=null;
	private  DataOutputStream outputStream=null;
	
	
	public ServerThread(Socket s) {
		this.socket=s;
	}

	@Override
	public void run() {
		// TODO �Զ����ɵķ������
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
		
			inputStream =new DataInputStream(socket.getInputStream());
			outputStream =new DataOutputStream(socket.getOutputStream());
			
			String content=null;
			while((content=inputStream.readUTF())!=null) {
				if(content.startsWith(Constant.USER)) {
					String name=getName(content);
					if(Server.clients.containsKey(name)) {
						
						System.out.print("���û����Ѵ��ڣ����������������ǳƣ�");
					}else{
						Server.clients.put(name, outputStream);
	
						for(DataOutputStream outputStream:Server.clients.ValueSet()) {
							outputStream.writeUTF(df.format(new Date())+"\n"+name+"�����������ң�");
						}
					}
				}else if(content.startsWith(Constant.U)) {
					String name_1=getChatName(content);
					String primsg=getRealMsg(content);
					Server.clients.get(name_1).writeUTF(df.format(new Date())+"\n"+"˽��<----+++&&&+++---->"+Server.clients.getKeyByvalue(outputStream)+"����˵:"+primsg);
				}else {
					String whoSend=Server.clients.getKeyByvalue(outputStream);
					for(DataOutputStream outputStream:Server.clients.ValueSet()) {
						outputStream.writeUTF(df.format(new Date())+"\n"+whoSend+":"+content);
					}
				}
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	//��ȡ��¼��
	private String getName(String s) {	
		return s.substring(2);
	}
	
	//��ȡ˽�Ķ����ǳ�
	private String getChatName(String s) {
		return s.substring(1, s.indexOf(Constant.colon));
	}
	
	//��ȡ˽����Ϣ����
	private String getRealMsg(String s) {
		return s.substring(s.indexOf("��"));
	}

}
