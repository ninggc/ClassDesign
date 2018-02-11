package progress.mapper;

import progress.info.User;
import progress.info.operation.UserOperation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



public class Pro {

	public static void main(String[] args) {
		Pro.test();
		Pro.testUser();

		
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
//		System.out.println(df.format(new Date()));


	}

	static void test() {
		String result = "";
				
		System.out.println("test:" + result);
	}

	static void testUser() {
		ArrayList<User> userList = null;
		UserOperation uo = new UserOperation();
		String result = null;
		boolean flag = false;

		User testUser = new User();
		testUser.setAccount("account");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ
		testUser.setBirthday(df.format(new Date()));
		testUser.setTelephone("tel");

		try {

			// userList = uo.selectAll();
			// for (User user : userList) {
			// System.out.println(user.toString());
			// }

			 result = uo.searchByAccount("2").toString();

//			 result = uo.searchById("4").toString();

//			flag = uo.add(testUser);
//			System.out.println(testUser.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(flag);
		System.out.println(result);

	}

}
