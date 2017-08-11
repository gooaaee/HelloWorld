/*
文件复制效率比较

四种复制文件方式比较
1.字节流复制文件一次读写一个字节
2.字节流复制文件一次读写一个字节数组
3.高效流复制文件一次读写一个字节
4.高效流复制文件一次读取一个字节数组
利用上面四种方式复制同一个文件，输出每一种复制方式花费的时间。
 */
public static void main(String[] args) throws IOException{
    //1.字节流复制文件一次读写一个字节
    long start = System.currentTimeMillis();
    ByteOneByOne();
    long end = System.currentTimeMillis();
    System.out.println("字节流复制文件一次读写一个字节,花费时间为:"+(end-start)+"ms");
    //2.字节流复制文件一次读写一个字节数组
    start = System.currentTimeMillis();
    ByteOneByArray();
    end = System.currentTimeMillis();
    System.out.println("字节流复制文件一次读写一个字节数组,花费时间为:"+(end-start)+"ms");
    //3.高效流复制文件一次读写一个字节
    start = System.currentTimeMillis();
    BufferOneByOne();
    end = System.currentTimeMillis();
    System.out.println("高效字节流复制文件一次读写一个字节,花费时间为:"+(end-start)+"ms");
    //4.高效流复制文件一次读取一个字节数组
    start = System.currentTimeMillis();
    BufferOneByArray();
    end = System.currentTimeMillis();
    System.out.println("高效字节流复制文件一次读写一个字节数组,花费时间为:"+(end-start)+"ms");
}
//1.字节流复制文件一次读写一个字节
public static void ByteOneByOne() throws IOException{
    FileInputStream fis = new FileInputStream("a.avi");
    FileOutputStream fos = new FileOutputStream("b.avi");
    int b;
    while((b = fis.read()) != -1){
        fos.write(b);
    }
    fos.close();
    fis.close();
}
//2.字节流复制文件一次读写一个字节数组
public static void ByteOneByArray() throws IOException{
    FileInputStream fis = new FileInputStream("a.avi");
    FileOutputStream fos = new FileOutputStream("c.avi");
    int len;
    byte[] bs = new byte[1024];
    while((len = fis.read(bs)) != -1){
        fos.write(bs,0,len);
    }
    fos.close();
    fis.close();
}
//3.高效流复制文件一次读写一个字节
public static void BufferOneByOne() throws IOException{
    BufferedInputStream bis = new BufferedInputStream(new FileInputStream("a.avi"));
    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("d.avi"));
    int b;
    while((b = bis.read()) != -1){
        bos.write(b);
    }
    bos.close();
    bis.close();
}
public static void BufferOneByArray()throws IOException{
    BufferedInputStream bis = new BufferedInputStream(new FileInputStream("a.avi"));
    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("e.avi"));
    int len;
    byte[] bs = new byte[1024];
    while((len = bis.read(bs)) != -1){
        bos.write(bs,0,len);
    }
    bos.close();
    bis.close();
}
