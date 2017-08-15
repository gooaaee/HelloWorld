/*
 * 使用3个线程模拟电影院3个窗口同时卖票的过程，总票数量为100张。
 * 在卖票的过程要考虑到线程同步的问题，不能出现多卖、少卖、重复卖票的情况。
 */
public class One11 {
	public static void main(String[] args) {
		O11RunnableImpl impl = new O11RunnableImpl();
		Thread t1 = new Thread(impl, "窗口1");
		Thread t2 = new Thread(impl, "窗口2");
		Thread t3 = new Thread(impl, "窗口3");
		t1.start();
		t2.start();
		t3.start();
	}
}
class O11RunnableImpl implements Runnable {
	int ticket = 100;

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (this) {
				if (ticket > 0) {
					System.out.println(Thread.currentThread().getName() + "正在卖票,现在余票" + ticket--);
				} else {
					break;
				}
			}
		}
	}
}
/*
请按如下要求编写多线程程序：
	1.得到一个随机的整数n，创建n个子线程对象；
	2.要求在子线程中把当前线程的名称作为元素添加一个集合中；
	3.当n个线程的名称都添加到集合中，遍历集合打印每个线程的名称；

1.定义一个子任务类，实现Runnable接口：
	1.1 定义一个ArrayList集合对象，用于存放子线程的名称；
	1.2 定义一个变量，用来记录正在运行的线程个数；
	1.3定义一个构造方法，把进程总数作为参数进来；
	1.4 在构造方法中，给正在运行的线程个数赋值为线程总数；
		1.5 重写Runnable接口的run方法 ；
		1.6 使用同步代码块，保证同一时间只能向集合中添加一个线程名称；
		1.7 正在运行的线程个数减1；
		1.8 所有的线程运行结束后遍历集合，打印所有线程的名称；
	2.定义一个测试类：
		2.1 在main方法中使用Random得到一个1到10的随机数；
		2.2 调用子任务类的构造方法创建子任务对象，把随机数传递给构造方法；
		2.3 循环创建n的子线程对象，并开启子线程；
 */
public class Two1 {
	public static void main(String[] args) {
		Random r = new Random();
		int n = r.nextInt(10)+1;
		RunnableImpl impl = new RunnableImpl(n);
		impl.createThread();
		impl.print();

	}
}
class RunnableImpl implements Runnable {
	ArrayList<String> list = new ArrayList<>();
	int n;

	public RunnableImpl(int n) {
		this.n = n;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//如果不加synchronized,会出现问题,可能是在调用currentThread() getName() add()方法时出问题了
		synchronized (this) {
			String s = Thread.currentThread().getName();
			list.add(s);
		}
	}

	public void createThread() {
		for (int i = 0; i < n; i++) {
			new Thread(this, "线程" + i).start();
		}
	}

	public boolean isEnd() {
		return list.size() == n;
	}

	public void print() {
		while (true) {
			if (isEnd()) {
				System.out.println(list);
				break;
			}
		}
	}
}
/*
请按要求编写多线程应用程序，模拟多个人通过一个山洞：
	1.这个山洞每次只能通过一个人，每个人通过山洞的时间为5秒；
	2.随机生成10个人，同时准备过此山洞，显示每次通过山洞人的姓名；

1.定义一个隧道类，实现Runnable接口：
	1.1 定义一个变量，用来记录通过隧道的人数；
	1.2 重写Runnable的run方法；
	1.3 定义一个同步方法，模拟每个人通过隧道需要5秒钟：
		1.3.1 子线程睡眠5秒钟，模拟每个人通过隧道需要5秒钟；
		1.3.2 改变通过的人次；
		1.3.3 打印线程名称及其通过隧道的顺序，模拟人通过隧道及其顺序；
	1.4 调用通过隧道的方法；
2.定义一个测试类：
	2.1 在main方法中创建一个隧道类对象；
	2.2 在main方法中，循环创建10个子线程对象，通过构造方法把隧道对象
	    和线程名（作为人的姓名）传递进去，并开启子线程；
 */
public class Two2 {
	public static void main(String[] args) {
		T2RunnableImpl impl = new T2RunnableImpl(10);
		for (int i = 0; i < 10; i++) {
			new Thread(impl,"名字为"+(i+1)+"的人").start();
		}
	}
}
class T2RunnableImpl implements Runnable{
	public int num=0;
	public int total;
	public T2RunnableImpl(int n){
		this.total = n;
	}
	@Override
	public void run(){
		method();
	}
	public synchronized void method(){
		try {
			Thread.sleep(1000*5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		num++;
		System.out.println(Thread.currentThread().getName()+"正在通过隧道..."+"还有"+(total-num)+"个人在等待通过隧道");
	}
}
/*
编写线程同步模拟应用程序:
	1. 大气环境数据为:温度,湿度,风速；
	2. 一个大气环境传感器测量环境数据需要5秒时间；
	3. 一个计算机读取传感器获得数据需要0.01秒时间；
	4. 模拟100个计算机读取大气环境传感器获取的随机温度,湿度,风速；

1.定义一个传感器类,实现Runnable接口：
	1.1 重写Runnable的run方法；
	1.2 子线程睡眠5秒钟,模拟传感器获得温度,湿度,风速需要的5秒钟时间；
	1.3 使用Random模拟得到随机的温度,湿度和风速；
	1.4 子线程睡眠0.01秒,模拟计算机读取传感器的数据需要的0.01秒时间；
	1.5 打印温度,湿度和风速, 模拟计算机读取传感器的数据；
2.定义一个传感器的测试类：
	2.1 在main方法中创建传感器类的对象；
	2.2 在main方法中循环创建100个子线程对象,并把传感器对象传递给构造方法；
	2.3 每创建一个子线程，就开启，读取传感器的获取的大气环境数据；
 */
public class Two3 {
	public static void main(String[] args) {
		RunnableImpl impl = new RunnableImpl();
		for(int i = 0; i < 100; i++)
			new Thread(impl).start();
	}
}
class RunnableImpl implements Runnable{
	Random r = new Random();
	@Override
	public void run(){
		int[] datas = new int[3];
		try{
			Thread.sleep(1000*5);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		synchronized(this){
			for(int i = 0; i < 3; i++)
				datas[i]=r.nextInt(100)+1;
		}
		try{
			Thread.sleep(10);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		synchronized(this){
			System.out.print(Thread.currentThread().getName()+"[");
			for(int i = 0; i < datas.length; i++)
				System.out.print(datas[i]+" ");
			System.out.println("]");
		}
	}//run
}
/*
编写多线程同步程序，模拟3个人排除买票，张某、李某和赵某买电影票，售票员只有3张五元的钱，电影票5元一张。张某拿20元一张的RMB排在李某的前面，李某排在赵某的前面拿一张10元的RMB买票，赵某拿一张5元的RMB买票。
提示：
1.Thread类的wait()方法：是指让当前线程处于等待状态，让释放线程锁对象，让其它线程能够获得锁对象，直到其它线程调用notify()或者notifyAll()方法唤醒后才能继续执行。
2.Thread类的notifyAll()方法：是指唤醒所有正在等待锁对象的线程。

1. 定义一个购票的类：
1.1 定义三个变量，分别用来记录是售票员手中面值5,10元，20元RMB的张数；
1.2 定义一个变量用来记录购票人手中一张RMB的面值；
1.3 定义一个构造方法，把购票人手中的人民币作为参数传递进去；
1.4 重写Thread类的run方法；
1.5 定义售票找零的方法：
1.5.1 如果购票人给的RMB面值是5元的，卖票成功，打印卖票的信息；
1.5.2 如果购票人给的RMB面值是20元的,但是售票员手中5元的RMB不够
	 3张，没有零钱，就调用wait方法等待 ，让其它人买票；
1.5.3 如果售票员手中5元的RMB大于或者等于3张，就应该找回3张5元的
	 RMB,5元的RMB张数应该减3，收到一张20元的RMB,20元的RMB张
	 数应该加1，卖票成功，打印卖票的信息；
1.5.4 如果购票人给的一张10元的，但是售票员手中没有5元的RMB可以找
	 给购票人，就等待，让其它人买票；
1.5.5 如果售票员手中有5元的RMB，就找给购票人，5元的RMB张数应该减
	  1，售票员收到了一张10元的，10元的RMB张数应该加1，打印售票
	  成功的信息；
1.5.6最后唤醒所有正在等待的线程，通知所有刚才没有零钱可以找回的购票
	 可以买票了；
1.6 在run方法中，调用买票找零的规则方法，售票；
2.定义一个测试类：
2.1 在main方法中创建三个购票类的对象；
2.2 分别调用start方法，开启子线程，开始购票；
 */
public class Third1 {
	public static void main(String[] args) {
		Th1RunnableImpl impl1 = new Th1RunnableImpl(5);
		Th1RunnableImpl impl2 = new Th1RunnableImpl(10);
		Th1RunnableImpl impl3 = new Th1RunnableImpl(20);
		Thread t1 = new Thread(impl1);
		Thread t2 = new Thread(impl2);
		Thread t3 = new Thread(impl3);
		t1.start();
		t2.start();
		t3.start();
		boolean isPrint = false;
		while(true){
			isPrint = impl1.printMoney(!(t1.isAlive()||t2.isAlive()||t3.isAlive()));
			System.out.println(t1.isAlive() + "|" + t2.isAlive() + "|" + t3.isAlive());
			if(isPrint)
				break;
		}
	}
}
class Th1RunnableImpl implements Runnable{
	static int money5 = 3;
	static int money10 = 0;
	static int money20 = 0;
	int money;
	public Th1RunnableImpl(int money){
		this.money = money;
	}
	@Override
	public void run(){
		try {
			sale(money);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public synchronized void sale(int money) throws Exception{
		if(money == 5){
			System.out.println("您的钱正好,这是您的票");
			money5++;
		}else if(money == 10){
			while(money5 < 1)
				wait();
			money5--;
			money10++;
			System.out.println("找您5元,这是您的票");
		}else if(money == 20){
			while(money < 3)
				wait();
			if(money5 < 2){
				money5 --;
				money10--;
				money20++;
			}else{
				money5 -= 3;
				money20++;
			}
			System.out.println("找您15元,这是您的票");
		}
		notifyAll();
	}//sale
	public boolean printMoney(boolean isEnd){
		if(isEnd){
			System.out.println("5元有"+money5+"张, 10元有"+money10+"张, 20元有"+money20+"张");
			return true;
		}else
			return false;
	}
}
/*
请按如下要求编写多线程程序：
1. 创建两个类，一个是测试类，一个是Thread的子类；
2. 在测试类中，创建两个Thread类的子类对象，将其中的一个线程对象的优先级设置10，另一个线程对象的优先级设置为6。
3. 让优先级为10的线程打印5次“线程1正在运行”，让优先级为6的线程打印10次“线程2正在运行”；
提示：
1.设置线程优先级的方法为setPriority方法；
2.得到线程优先级的方法为getPriority方法；

1. 定义一个子线程类：
1.1 创建一个对象，用作锁对象；
1.2 重写Thread类的run方法；
1.3 使用同步代码块，保证每个线程能够按照要求连续打印语句；
1.4 调用getPriority方法获得当前线程的优先级；
1.5 如果线程的优先级为10,就打印5次“线程1正在运行”；
1.6 如果线程的优先级为6,就打印10次“线程2正在运行”；
2. 定义测试类：
2.1 在main方法中创建线程任务类的对象；
2.2 创建两个线程对象；
2.3 调用setPriority方法给线程对象设置优先级；
2.4  开启线程；
 */
public class Third2 {
	public static void main(String[] args) {
		Th2RunnableImpl impl = new Th2RunnableImpl();
		Thread t1 = new Thread(impl, "线程1");
		Thread t2 = new Thread(impl, "线程2");
		t1.setPriority(10);
		t2.setPriority(6);
		t1.start();
		t2.start();
	}
}
class Th2RunnableImpl implements Runnable {
	@Override
	public void run() {
		synchronized (this) {
			int n;
			if (Thread.currentThread().getPriority() == 10) {
				n = 5;
			} else {
				n = 10;
			}
			for (int i = 0; i < n; i++) {
				System.out.println(Thread.currentThread().getName() + "正在运行");
			}
		}
	}
}