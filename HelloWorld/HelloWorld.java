/*
 * FileOutputStream
 * 利用字节输出流一次写一个字节的方式向C盘的a.txt文件输出内容。
 * 利用字节输出流一次写一个字节数组的方式向C盘的b.txt文件输出内容。
 */
public static void main(String[] args) throws IOException{
    FileInputStream fis = new FileInputStream("a.txt");
    FileOutputStream fos = new FileOutputStream("b.txt");
    //一次写一个字节
    int b;
    while((b = fis.read()) != -1){
        fos.write(b);
    }
    fos.close();
    fis.close();
    //一次写一个字节数组
    FileInputStream fis1 = new FileInputStream("a.txt");
    FileOutputStream fos1 = new FileOutputStream("c.txt");
    int len;
    byte[] b1 = new byte[1024];
    while((len = fis1.read(b1)) != -1){
        fos1.write(b1,0,len);
    }
    fos1.close();
    fis1.close();
}
