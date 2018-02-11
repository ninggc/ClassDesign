package progress.info.operation;


import progress.info.User;

public interface IUserOperation extends IOperation<User> {
	public User searchByAccount(String account);

	public User searchByMark(String mark);
}
