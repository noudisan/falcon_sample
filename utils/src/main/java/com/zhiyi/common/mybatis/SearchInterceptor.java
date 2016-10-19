package com.zhiyi.common.mybatis;

import com.zhiyi.common.dto.PageSearchDto;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Properties;


public class SearchInterceptor implements Interceptor {


    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        Type[] interfaces = target.getClass().getGenericInterfaces();
        for (Type i : interfaces) {
            if (i.equals(StatementHandler.class)) {
                StatementHandler statementHandler = (StatementHandler) target;
                Field statementField = ReflectionUtils.findField(statementHandler.getClass(), "delegate");
                ReflectionUtils.makeAccessible(statementField);
                Object fieldInstance = ReflectionUtils.getField(statementField, statementHandler);

                Field mappedStatementField = ReflectionUtils.findField(fieldInstance.getClass(), "mappedStatement");
                ReflectionUtils.makeAccessible(mappedStatementField);

                MappedStatement mappedStatement = (MappedStatement) ReflectionUtils.getField(mappedStatementField, fieldInstance);

                if (mappedStatement.getSqlCommandType() == SqlCommandType.SELECT && fieldInstance.getClass().equals(PreparedStatementHandler.class)) {
                    PreparedStatementHandler preparedStatementHandler = (PreparedStatementHandler) fieldInstance;
                    BoundSql boundSql = preparedStatementHandler.getBoundSql();
                    if (boundSql.getParameterObject() != null &&
                            (boundSql.getParameterObject().getClass().getSuperclass().equals(PageSearchDto.class))
                            ) {
                        String additionalSql = null;
                        if (boundSql.getParameterObject().getClass().getSuperclass().equals(PageSearchDto.class)) {
                            PageSearchDto searchDto = (PageSearchDto) boundSql.getParameterObject();
                            if (searchDto.getTotalSize() == -1) {
                                return target;
                            }
                            additionalSql = searchDto.getPageableAndSortableSqlString();
                        }
                        String originalSql = boundSql.getSql();
                        Field sqlField = ReflectionUtils.findField(BoundSql.class, "sql");
                        ReflectionUtils.makeAccessible(sqlField);
                        ReflectionUtils.setField(sqlField, boundSql, originalSql + additionalSql);
                    }
                    return target;
                }
            }
        }
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }


}
