/*
Properties类的使用
有个文件 名单.txt  里面内容是 
张三=邯郸
李四=北京
王五=襄樊
赵六=长沙
钱七=襄樊
龙八=北京
读取文件中内容  ,而为了文化,襄樊改为之前的名称 襄阳
所以需要写一个新文件  新名单.txt,将旧文件中的襄樊替换成襄阳  
提示:先使用集合旧数据读到内存中
   遍历修改
   在将集合写出去
 */
public static void main(String[] args) throws IOException{
    Properties p = new Properties();
    p.load(new BufferedReader(new InputStreamReader(new FileInputStream("名单.txt"))));
    for(String key : p.stringPropertyNames()){
        System.out.println(key + "->" + p.getProperty(key));
    }
    System.out.println("---修改后---");
    for(String key : p.stringPropertyNames()){
        if(p.getProperty(key).equals("襄樊")){
            p.setProperty(key,"襄阳");
        }
        System.out.println((key + "->" + p.getProperty(key)));
    }
}

