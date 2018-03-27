
import org.springframework.util.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.math.BigInteger;

public class Base64Util {
	
    // 加密
    public static String encodeStr(byte[] data) {  
        byte[] b = data;  
        String s = null;  
        if (b != null) {
            s = new BASE64Encoder().encode(b);
        }  
        return s;  
    }  
  
    public static byte[] decodeStr(String s) {  
        byte[] b = null;  
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
            } catch (Exception e) {
                e.printStackTrace();  
            }  
        }  
        return b;  
    }

    public static void main(String[] args) {
        String sql = "select * from ( select id, amount,business_type transType, DATE_FORMAT(update_time,'%Y/%m/%d %H:%i') AS transDate from account_balance_6 where user_id=? AND business_type IN('16000', '13001', '16001', '16002', '16003', '16004', '16005', '30003', '30004') AND business_status=2 AND update_time > ? AND update_time < ? union all select id, sum(amount) amount,business_type transType, DATE_FORMAT(update_time,'%Y/%m/%d %H:%i') AS transDate from account_balance_6 where user_id=? and business_type in('17000', '17001') AND business_status=2 AND update_time > ? AND update_time < ? group by DATE_FORMAT(update_time,'%Y/%m/%d') ) t order by t.transDate desc, t.id desc limit ?";
        String encodeStr = encodeStr(sql.getBytes());
        String s = new String(decodeStr(encodeStr));

        System.out.println(encodeStr);
        System.out.println(s);
    }
}
