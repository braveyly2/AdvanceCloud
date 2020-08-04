import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlOptiLockTest {
    private static JdbcTemplate jdbcTemplate;

    public static void main(String[] args) throws Exception{
        String config = "application.xml";
        ApplicationContext app = new ClassPathXmlApplicationContext(config);
        // 创建template
        JdbcTemplate template = app.getBean("jdbcTemplate", JdbcTemplate.class);

        String sqlSelect = "select * from goods where id = 1";
        final List result = new ArrayList();
        final Good good = new Good();
        final Good good2 = new Good();
        List result2 = template.query(sqlSelect, new ResultSetExtractor<List>() {

            public List extractData(ResultSet rs)
                    throws SQLException, DataAccessException {
                List result = new ArrayList();
                while(rs.next()) {
                    //Map row = new HashMap();

                    good.setId(rs.getInt("id"));
                    good.setStatus(rs.getInt("status"));
                    good.setName(rs.getString("name"));
                    good.setVersion(rs.getInt("version"));

                    good2.setId(rs.getInt("id"));
                    good2.setStatus(rs.getInt("status"));
                    good2.setName(rs.getString("name"));
                    good2.setVersion(rs.getInt("version"));
                    //row.put(rs.getInt("id"), good);
                    //result.add(row);
                }
                return result;
            }});


        String sql = "UPDATE goods SET status=?, version=? WHERE version=?;";

        int updateResult = template.update(sql, 2, good.getVersion()+1, good.getVersion());
        System.out.println("good update result:" + updateResult);

        int updateResult2 = template.update(sql, 2, good2.getVersion()+1, good2.getVersion());
        System.out.println("good2 update result:" + updateResult2);
        //good update result:1   获取到了乐观锁（version=good.getVersion()），更新成功
        //good2 update result:0  没有获取乐观锁（version!=good2.getVersion()），更新失败
    }

}
