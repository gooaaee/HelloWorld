/*
利用字节流将C盘下的a.png图片复制到D盘下(文件名保存一致)
要求：
1.一次读写一个字节的方式

用字节流将C盘下的a.png图片复制到D盘下(文件名保存一致)
要求：一次读写一个字节数组的方式进行复制
 */
public static void main(String[] args) throws IOException{
    //一次读写一个字节
    FileInputStream fis = new FileInputStream("a.png");
    FileOutputStream fos = new FileOutputStream("b.png");
    int b;
    while((b = fis.read()) != -1){
        fos.write(b);
    }
    fos.close();
    fis.close();
    //一次读写一个字节数组
    FileInputStream fis1 = new FileInputStream("a.png");
    FileOutputStream fos1 = new FileOutputStream("c.png");
    int len;
    byte[] bs = new byte[1024];
    while((len = fis1.read(bs)) != -1){
        fos1.write(bs,0,len);
    }
    fos1.close();
    fis1.close();
}
