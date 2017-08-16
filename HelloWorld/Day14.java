/*
创建新项目，按以下要求编写代码：
1.使用InetAddress类获取本地计算机的IP地址和主机名
2.使用InetAddress类获取传智播客(www.itcast.cn)主机的IP地址：
提示 getLocalHost()获取本机地址
   getByName(“”)获取指定网络
 */
   public class One4 {
   	public static void main(String[] args) {
   		InetAddress itcast = InetAddress.getByName("www.itcast.cn");
   		InetAddress local = InetAddress.getLocalHost();
   		System.out.println(itcast);
   		System.out.println(local);
   	}
   }

/*
需求说明：创建新项目,通过UDP协议，完成UDP发送端，使用UDP给本机的接收端(8888端口)发送信息“Hello”
 */
public class One10 {}
//UDP发送端
public class UDPsend{
	public static void main(String[] args) throws IOException{
		DatagramSocket ds = new DatagramSocket();
		//要发送的数据
		byte[] bys = "hello".getBytes();
		//InetAddress对象
		InetAddress ip = InetAddress.getByName("127.0.0.1");
		DatagramPacket dp = new DatagramPacket(bys,bys.length,ip,8888);
		ds.send(dp);
		ds.close();
	}
}
//UDP接收端
public class UDPreceive{
	public static void main(String[] args) throws IOException{
		DatagramSocket ds = new DatagramSocket(8888);
		byte[] bys = new byte[1024];
		DatagramPacket dp = new DatagramPacket(bys, 1024);
		ds.receive(dp);
		System.out.println(new String(bys,0,bys.length));
		ds.close();
	}
}


/*
需求说明：创建新项目，按以下要求编写代码：
在项目下创建TCP 服务器端 端口号为8888
1:等待客户端连接   如果有客户端连接  获取到客户端对象
2:获取到客户端对象之后 当前在服务器读取数据客户端传送数据
 */
public class One12 {}
//创建TCP服务器
public class Server{
	public static void main(String[] args) throws IOException{
		ServerSocket ss = new ServerSocket(8080);
		Socket s = ss.accept();
		InputStream in = s.getInputStream();
		byte[] bys = new byte[1024];
		int len = in.read(bys);
		System.out.println(new String(bys,0,len));
		ss.close();
	}
}
//创建TCP客户端
public class Client{
	public static void main(String[] args) throws IOException{
		Socket s = new Socket("127.0.0.1",8080);
		OutputStream os = s.getOutputStream();
		byte[] bys = "hello".getBytes();
		os.write(bys);
		s.close();
	}
}

/*
项目需求：请用户完成一个UDP接收端作为模拟网络聊天室的操作。
1:创建UDP传输下的聊天室发送端UDPSend类
2: 创建UDP传输下的聊天室接收端 Room类
 */
public class Two1 {}
//UDP发送端
public class UDPSend{
	public static void main(String[] args) throws IOException{
		DatagramSocket ds = new DatagramSocket();
		byte[] bys = "hello".getBytes();
		InetAddress ip = InetAddress.getByName("127.0.0.1");
		int port = 8080;
		DatagramPacket dp = new DatagramPacket(bys,bys.length,ip,port);
		ds.send(dp);
		ds.close();
	}
}
//UDP接收端
public class Room{
	public static void main(String[] args) throws IOException{
		while(true){
			DatagramSocket ds = new DatagramSocket(8080);
			byte[] bys = new byte[1024];
			DatagramPacket dp = new DatagramPacket(bys,1024);
			ds.receive(dp);
			System.out.println(new String(bys,0,bys.length));
			ds.close();
		}
	}
}

/*
需求说明：使用TCP编写一个网络程序,设置服务器程序监听端口为8002,
当于客户端建立后,向客户端发送”hello world”,客户端将信息输出
 */
public class Two2 {}
//TCP服务器端
public class Server{
	public static void main(String[] args) throws IOException{
		ServerSocket ss = new ServerSocket(8002);
		Socket s = ss.accept();
		OutputStream os = s.getOutputStream();
		os.write("hello world".getBytes());
		s.close();
		ss.close();
	}
}
//TCP客户端
public class Client{
	public static void main(String[] args) thorws IOException{
		Socket s = new Socket("127.0.0.1",8002);
		InputStream in = s.getInputStream();
		byte[] bys = new byte[1024];
		int len = in.read(bys);
		System.out.println(new String(bys,0,len));
		s.close();
	}
}

/*
需求说明：我们来做一个“文件上传案例”，有以下要求：
将项目中的一个图片,通过客户端上传至服务器

1,创建服务器，等待客户端连接
2,创建客户端Socket，连接服务器
3,获取Socket流中的输出流，功能：用来把数据写到服务器
4,创建字节输入流，功能：用来读取数据源(图片)的字节
5,把图片数据写到Socket的输出流中(把数据传给服务器)
6,客户端发送数据完毕，结束Socket输出流的写入操作，告知服务器端
7,获取Socket的输入流
8,创建目的地的字节输出流
9,把Socket输入流中的数据，写入目的地的字节输出流中
10,获取Socket的输出流, 作用：写反馈信息给客户端
11,写反馈信息给客户端
12,获取Socket的输入流  作用： 读反馈信息
13,读反馈信息
 */
public class Three1 {}
//TCP客户端
public class Client{
	public static void main(String[] args) throws IOException{
		Socket s = new Socket("127.0.0.1",8080);
		OutputStream os = s.getOutputStream();
		FileInputStream fis = new FileInputStream("a.jpg");

		byte[] bys = new byte[1024];
		int len;
		while((len = fis.read(bys)) != -1){
			os.write(bys,0,len);
		}
		s.shutdownOutput();
		//接收服务器发送过来的反馈信息
		InputStream in = s.getInputStream();
		len = in.read(bys);
		System.out.println("客户端接收到服务器的反馈信息:"+new String(bys,0,len));
		fis.close();
		s.close();
	}
}
//TCP服务器
public class Server{
	public static void main(String[] args) throws IOException {
		final ServerSocket ss = new ServerSocket(8080);
		while (true) {
			final Socket s = ss.accept();
			new Thread() {
				final InputStream in = s.getInputStream();
				InetAddress ip = s.getInetAddress();
				final FileOutputStream fos = new FileOutputStream("E:\\" + ip + ".jpg");
				final byte[] bys = new byte[1024];
				@Override
				public void run() {
					try {
						int len;
						while ((len = in.read(bys)) != -1) {
							fos.write(bys, 0, len);
						}
						//发送反馈信息
						OutputStream os = s.getOutputStream();
						os.write("上传成功".getBytes());

					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						try {
							fos.close();
							in.close();
							s.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}.start();
		}
	}
}