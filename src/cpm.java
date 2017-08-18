import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.Collator;
import java.util.*;

public class cpm {
	public static void main(String[] args) {
		Comparator<Object> com = Collator.getInstance(java.util.Locale.CHINA);
		File file = new File("敏感词.txt"); // 读取文件
		List<String> list = null;
		InputStreamReader read = null;
		try {
			read = new InputStreamReader(new FileInputStream(file));
			if (file.isFile() && file.exists()) { // 文件流是否存在
				list = new ArrayList<>();
				BufferedReader bufferedReader = new BufferedReader(read);
				String txt = null;
				while ((txt = bufferedReader.readLine()) != null) { // 读取文件，将文件内容放入到set中
					list.add(txt);
				}
			} else { // 不存在抛出异常信息
				throw new Exception("敏感词库文件不存在");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (read != null) {
				try {
					read.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		Collections.sort(list, com);
		try {
			FileOutputStream fos = new FileOutputStream(new File("敏感.txt"));
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
			for (String i : list) {
				writer.write(i);
				writer.write("\r\n");
				writer.flush();
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}