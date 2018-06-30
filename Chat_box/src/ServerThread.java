import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerThread implements Runnable{
	
	//服务器端线程类
	private Socket socket;
	private DataInputStream inputStream=null;
	private  DataOutputStream outputStream=null;
	
	
	public ServerThread(Socket s) {
		this.socket=s;
	}

	@Override
	public void run() {
		// TODO 自动生成的方法存根
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
		
			inputStream =new DataInputStream(socket.getInputStream());
			outputStream =new DataOutputStream(socket.getOutputStream());
			
			String content=null;
			while((content=inputStream.readUTF())!=null) {
				if(content.startsWith(Constant.USER)) {
					String name=getName(content);
					if(Server.clients.containsKey(name)) {
						
						System.out.print("该用户名已存在！！！请重新设置昵称！");
					}else{
						Server.clients.put(name, outputStream);
	
						for(DataOutputStream outputStream:Server.clients.ValueSet()) {
							outputStream.writeUTF(df.format(new Date())+"\n"+name+"进入了聊天室！");
						}
					}
				}else if(content.startsWith(Constant.U)) {
					String name_1=getChatName(content);
					String primsg=getRealMsg(content);
					Server.clients.get(name_1).writeUTF(df.format(new Date())+"\n"+"私聊<----+++&&&+++---->"+Server.clients.getKeyByvalue(outputStream)+"对你说:"+primsg);
				}else {
					String whoSend=Server.clients.getKeyByvalue(outputStream);
					for(DataOutputStream outputStream:Server.clients.ValueSet()) {
						outputStream.writeUTF(df.format(new Date())+"\n"+whoSend+":"+content);
					}
				}
			}
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	//提取登录名
	private String getName(String s) {	
		return s.substring(2);
	}
	
	//提取私聊对象昵称
	private String getChatName(String s) {
		return s.substring(1, s.indexOf(Constant.colon));
	}
	
	//提取私聊信息内容
	private String getRealMsg(String s) {
		return s.substring(s.indexOf("："));
	}

}
