package beacon;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.aop.aspectj.SingletonAspectInstanceFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hao.communication.CommTcpImpl;
import com.hao.logger.MyLogger;
import com.hao.manager.Manager;
import com.hao.storage.DataStruct1;
import com.hao.storage.SqlText;
import com.hao.storage.StoragePlatesImpl;




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
		
		Manager manager = context.getBean("manager", Manager.class);
		manager.start();
		
		MyLogger logger = context.getBean("logger", MyLogger.class);
		logger.init();
		logger.myLoggerTest();
	}
	
	private static void test4() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		System.out.println(df.format(new Date()));// new Date()为获取当前系统时间
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beacon.xml");
		
		MyLogger logger = context.getBean("logger", MyLogger.class);
		logger.myLoggerTest();
		//test3();
	}

}
