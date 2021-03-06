package star.repository.generator;

import star.repository.interfaces.SqlGenerator;
import star.utils.StringUtil;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Collectors;

import static star.constant.RepositoryConstant.*;

/**
 * @author keshawn
 * @date 2017/12/29
 */
public class SaveSqlGenerator implements SqlGenerator {

    private static final SaveSqlGenerator instance = new SaveSqlGenerator();

    private SaveSqlGenerator() {
    }

    public static SaveSqlGenerator getInstance() {
        return instance;
    }

    @Override
    public String generate(Method method, String tableName, String selectAllColumns, Object[] params, Map<String, String> fieldMap) {
        String columns = fieldMap.values().stream().collect(Collectors.joining(DELIMITER));
        String bracketPlaceHolder = StringUtil.generateBracketPlaceHolder(fieldMap.size());
        return INSERT_INTO + tableName + LEFT_BRACKET + columns + RIGHT_BRACKET + VALUES + bracketPlaceHolder;
    }
}
