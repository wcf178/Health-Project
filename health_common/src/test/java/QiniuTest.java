import com.bigdata.utils.QiniuUtils;
import org.junit.Test;

public class QiniuTest {

    @Test
    public void testUpload(){
        String localFilePath="F:\\暑期中软国际实训\\day06 预约管理-套餐管理\\资源\\图片资源\\1.jpg";
        QiniuUtils.upload2Qiniu(localFilePath, "1.jpg");

    }

    @Test
    public void testDeleteFile(){
        QiniuUtils.deleteFileFromQiniu("1.jpg");
    }
}
