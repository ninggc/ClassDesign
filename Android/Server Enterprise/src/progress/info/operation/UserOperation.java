package progress.info.operation;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import progress.info.User;
import progress.mapper.SqlSessionOperation;


public class UserOperation implements IUserOperation {
	private SqlSession session = SqlSessionOperation.getSqlSession();
	private IUserOperation iuo = session.getMapper(IUserOperation.class);

	@Override
	public ArrayList<User> selectAll() {
		ArrayList<User> userList = null;
		
		try {
			userList = iuo.selectAll();
			session.commit();
		} catch (Exception e) {
			userList = new ArrayList<User>();
			e.printStackTrace();
		}
		
		return userList;
	}

	@Override
	public User searchById(String id) {
		User user = null;
		
		try {
			user = iuo.searchById(id);
			session.commit();
		} catch (Exception e) {
			user = null;
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public User searchByAccount(String account) {
		User user = null;
		
		try {
			user = iuo.searchByAccount(account);
			session.commit();
		} catch (Exception e) {
			user = null;
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public boolean add(User user) {
		try {
			iuo.add(user);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public User searchByMark(String mark) {
		User user = null;
		
		try {
			user = iuo.searchByMark(mark);
		} catch (Exception e) {
			user = null;
			e.printStackTrace();
		}
		
		return user;
	}

}
