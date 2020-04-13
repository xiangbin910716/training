import entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

/**
 * <p>
 *
 * </p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>
 * </PRE>
 *
 * @author xiangbin
 * @version 1.0
 * @since 1.0
 */
public class UserServiceTest {

    @Test
    public void select(){
        ApplicationContext context = new ClassPathXmlApplicationContext("application-sharding.xml");
        UserService userService = (UserService) context.getBean("UserService");
        User user = userService.selectByPrimaryKey(454239198585552896L);
        System.out.println(user.getUserName());
    }
}
