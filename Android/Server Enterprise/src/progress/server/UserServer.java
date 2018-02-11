package progress.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import progress.info.User;
import progress.info.operation.UserOperation;

public class UserServer extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private InputStream is;
	private BufferedReader bufferedReader;

	UserOperation uo = new UserOperation();

	Gson gson = new Gson();

	public String ready() throws IOException {
		response = ServletActionContext.getResponse();
		request = ServletActionContext.getRequest();
		response.setContentType("textml;charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		is = request.getInputStream();
		bufferedReader = new BufferedReader(new InputStreamReader(is, "utf-8"));
		StringBuffer sb = new StringBuffer();
		String line = null;
		while ((line = bufferedReader.readLine()) != null) {
			sb.append(line);
		}

		System.out.println(sb.toString());
		return sb.toString();
	}

	public void login() {
		User user = null;

		try {
			String json = ready();

			user = uo.searchByAccount(json);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			if (user == null) {
				response.getWriter().append(null);
			} else {
				response.getWriter().append(user.toJSON());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void searchByAccount() {
		User user = null;

		try {
			String json = ready();

			user = uo.searchByAccount(json);

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			if (user == null) {
				response.getWriter().append(null);
			} else {
				response.getWriter().append(user.toJSON());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void searchByMark() {
		User user = null;
		
		try {
			String json = ready();
			
			user = uo.searchByMark(json);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			if (user == null) {
				response.getWriter().append("null");
			} else {
				response.getWriter().append(user.toJSON());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ����User����ע����Ϣ
	 */
	public void register() {
		User user = null;

		try {
			String json = ready();
			user = gson.fromJson(json, User.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (user != null && uo.add(user)) {
				response.getWriter().append("ע��ɹ�");
			} else {
				response.getWriter().append("ע��ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void add() {
		User user = null;
		
		try {
			String json = ready();
			user = gson.fromJson(json, User.class);
			uo.add(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			response.getWriter().append("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
