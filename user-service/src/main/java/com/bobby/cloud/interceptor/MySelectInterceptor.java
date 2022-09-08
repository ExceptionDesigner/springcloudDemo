package com.bobby.cloud.interceptor;

import com.bobby.cloud.utils.ReflectUtil;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;

/**
 * @author: bait
 * @create: 2022-05-09 14:24
 * @description: 自定义的mybatis plugin
 * <p>
 * 先说明Mybatis中可以被拦截的类型具体有以下四种：
 * 1.Executor：拦截执行器的方法。
 * 2.ParameterHandler：拦截参数的处理。
 * 3.ResultHandler：拦截结果集的处理。
 * 4.StatementHandler：拦截Sql语法构建的处理。
 **/
@Intercepts(value = {@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class MySelectInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation)  {
        System.out.println(123456789);
        try {
            StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
            BoundSql boundSql = statementHandler.getBoundSql();
            String sql = boundSql.getSql();
            if (sql.trim().toUpperCase().startsWith("SELECT") ) {
                String sqlNew = sql.replaceAll("user", "user order by birthday desc");
                ReflectUtil.setFieldValue(boundSql,"sql",sqlNew);
            }
            return invocation.proceed();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据拦截解析失败！");
        }
        return null;
    }


}
