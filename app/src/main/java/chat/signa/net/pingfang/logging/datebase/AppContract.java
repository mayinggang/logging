package chat.signa.net.pingfang.logging.datebase;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Administrator on 2016/1/22.
 */
public final class AppContract {
    public AppContract() {}

    public static final String AUTHORITY = "net.pingfang.signalr.chat.provider";

    public static abstract class UserEntity implements BaseColumns{
        // 访问Uri
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");
        // 内容类型
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/net.pingfang.signalr.chat.user";
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/net.pingfang.signalr.chat.user";
        // 默认排序常量
        public static final String DEFAULT_SORT_ORDER = "t_id ASC";

        public static final String TABLE_NAME="user";

        public static final String USER_ID="u_id";
        public static final String USER_NAME="u_name";
        public static final String USER_PASSWORD="u_password";
        public static final String USER_AGE="u_age";
        public static final String USER_SEX="u_sex";
        public static final String USER_ADDRESS="u_addressb ";
    }

}
