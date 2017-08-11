/*
1.文件的获取
2.文件过滤器
3.递归的使用
4.字节流的使用


从控制台获取输入的文件目录然后将该目录(包含子目录)下的.java文件复制到D:/java文件夹中,并统计java文件的个数.
提示:如果有相同的名称的文件,如果两个Test01.java,则拷贝到目标文件夹时只能有一个Test01.java,
另一个Test01.java要修改为另一个名称:该名称可随机生成,只要不重复即可.
 */
	private static int count;
	public static void main(String[] args) throws IOException {
		count = 0;
		File srcFile = new File("E:\\计算机网络");
		File destFile = new File("E:\\copy文件");
		HashMap<String, Integer> map = new HashMap<>();

		countFileName(srcFile, map);
		fileCopy(srcFile, destFile, map);
		System.out.println("java文件的个数为:" + count + "个");
	}
	public static void countFileName(File srcFile, HashMap<String, Integer> map) {
		File[] files = srcFile.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				countFileName(f, map);
			} else {
				if (map.containsKey(f.getName()))
					map.put(f.getName(), map.get(f.getName()) + 1);
				else
					map.put(f.getName(), 1);
			}
		}
	}
	public static void fileCopy(File srcFile, File destFile, HashMap<String, Integer> map) throws IOException {
		if (!destFile.exists()) {
			destFile.mkdir();
		}
		File[] files = srcFile.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				if (pathname.isFile()) {
					// if(pathname.getName().endsWith(".java"))
					return true;
				}
				if (pathname.isDirectory())
					return true;
				return false;
			}
		});
		for (File f : files) {
			if (f.isDirectory())
				fileCopy(f, new File(destFile, f.getName()), map);
			else {
				if (map.get(f.getName()) == 1) {
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
					BufferedOutputStream bos = new BufferedOutputStream(
							new FileOutputStream(destFile.getAbsolutePath() + "\\" + f.getName()));
					int len;
					byte[] bs = new byte[1024];
					while ((len = bis.read(bs)) != -1) {
						bos.write(bs, 0, len);
					}
					count++;
					bos.close();
					bis.close();
				} else {
					BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
					BufferedOutputStream bos = new BufferedOutputStream(
							new FileOutputStream(destFile.getAbsolutePath() + "\\" + f.getName().split("\\.")[0] + "("
									+ new Random().nextInt(10_000) + ")." + f.getName().split("\\.")[1]));
					int len;
					byte[] bs = new byte[1024];
					while ((len = bis.read(bs)) != -1) {
						bos.write(bs, 0, len);
					}
					count++;
					bos.close();
					bis.close();
				}
			}
		}
	}
}
