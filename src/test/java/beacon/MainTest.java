package beacon;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hao.logger.MyLogger;
import com.hao.storage.DataStruct1;
import com.hao.storage.SqlText;
import com.hao.storage.StoragePlatesImpl;
import com.hao.myBatis.*;




public class MainTest {

	private static void test1() {
		
		System.out.println(SqlText.MakeInsertOneString("11:22:33:44:55:01", "00:00:00:00:00:00", 10, 20, -10, "-10,-9,-11"));
		System.out.println(SqlText.MakeRemoveOneString(1));
		System.out.println(SqlText.MakeSelectAllString());
	}
	private static void test2() {

		ApplicationContext context = new ClassPathXmlApplicationContext("beacon.xml");
		StoragePlatesImpl spl = context.getBean("plateStorage", StoragePlatesImpl.class);
		DataStruct1 ds1Insert = new DataStruct1(0,"22:33:44:55:66:01", "11:11:11:11:11:11",100,201,-10,"-11,-9,-10");
		spl.put(ds1Insert);

		List<DataStruct1> list = spl.get();
		System.out.println("show datas(id,myMac,parentMac,major,minor,rssiAvr,rssiList):");
		for(DataStruct1 ds1 : list) {
			System.out.printf("%d,%s,%s,%d,%d,%d,%s\n", ds1.getId(),ds1.getMyMac(),ds1.getParentMac(),ds1.getMajor(),ds1.getMinor(),ds1.getRssiAvr(),ds1.getRssiList());
		}
	}
	private static void test3() {

		ApplicationContext context = new ClassPathXmlApplicationContext("beacon.xml");
		
		MyLogger logger = context.getBean("logger", MyLogger.class);
		logger.myLoggerTest();
	}
	
	private static void test4() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	}
	
	private static void test5() throws IOException {
		
		String resource = "myBatis/myBatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			session.selectOne("com.hao.mapping.userMapper.createTable");
			UserInfo user = new UserInfo("jack", 33);
			session.insert("com.hao.mapping.userMapper.add", user);
			session.commit();
			List<UserInfo> userinfos = session.selectList("com.hao.mapping.userMapper.list");
			for(UserInfo user1:userinfos) {
				System.out.println(user1);
			}
		}finally {
			session.close();
		}
	}

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		test3();
		test5();
	}

}
