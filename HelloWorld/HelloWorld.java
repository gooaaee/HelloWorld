/*
 * FileOutputStream
 * 利用字节输出流一次写一个字节的方式向C盘的a.txt文件输出内容。
 * 利用字节输出流一次写一个字节数组的方式向C盘的b.txt文件输出内容。
 */
public static void main(String[] args){
    FileInputStream fis = new FileInputStream("a.txt");
    FileOutputStream fos = new FileOutputStream("a.txt");
    //一次写一个字节
    int b;
    while((b = fis.read()) != -1){
        fos.write(b);
    }
    fos.close();
    fis.close();
    //一次写一个字节数组
    FileInputStream fis = new FileInputStream("a.txt");
    FileOutputStream fos = new FileOutputStream("b.txt");
    int len;
    byte[] b = new byte[1024];
    while((len = fis.read(b)) != -1){
        fos.write(b,0,len);
    }
    fos.close();
    fis.close();
}
