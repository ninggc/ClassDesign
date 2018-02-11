package progress.server;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Action;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ning on 2017/5/11.
 */
public class ImgServer extends ActionSupport {
    private String userName;//用户名，struts框架要求变量名与客户端key一致
    public File mPhoto;//文件（注意与客户端保持一致性）
    public String mPhotoFileName;//文件名（注意命名格式为：文件变量名+FileName，不得忽略大小写）

    public void upload() {
        if (!(userName == null)) {
            System.out.println(userName);
        }
        if (!(mPhoto == null)) {
            System.out.println(mPhoto.getName());
        }
        if (!(mPhotoFileName == null)) {
            System.out.println(mPhotoFileName);
        }
        String dir = ServletActionContext.getServletContext().getRealPath(
                "files");//自行在项目路径下创建files文件夹用于存储图片

        System.out.println(dir);

        try {
            File file = new File(dir, mPhotoFileName);
            FileUtils.copyFile(mPhoto, file);
            HttpServletResponse response = ServletActionContext.getResponse();
            PrintWriter writer = response.getWriter();
            writer.write("http://公网ip:8080/okhttpTest/files/"
                    + mPhotoFileName);//向客户端写回文件完整路径（地址）
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

