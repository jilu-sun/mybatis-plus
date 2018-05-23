/*
 * Copyright (c) 2011-2014, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.mybatisplus.core.conditions;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * <p>
 * Entity 对象封装操作类，定义T-SQL语法
 * </p>
 *
 * @author hubin , yanghu , Dyang , Caratacus
 * @Date 2016-11-7
 */
public class UpdateWrapper<T> extends Wrapper<T> {

    /**
     * 数据库表映射实体类
     */
    protected T entity = null;
    /**
     * SQL 更新字段内容，例如：name='1',age=2
     */
    protected String sqlSet = null;

    public UpdateWrapper() {
        /* 注意，传入查询参数 */
    }

    public UpdateWrapper(T entity) {
        this.entity = entity;
    }

    public UpdateWrapper(T entity, String sqlSet) {
        this.entity = entity;
        this.sqlSet = sqlSet;
    }

    @Override
    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    /**
     * SQL SET 字段
     */
    public String getSqlSet() {
        return this.sqlSet;
    }

    /**
     * SQL 片段
     */
    @Override
    public String getSqlSegment() {
        /*
         * 无条件
         */
        String sqlWhere = sql.toString();
        if (StringUtils.isEmpty(sqlWhere)) {
            return null;
        }

        /*
         * 根据当前实体判断是否需要将WHERE替换成 AND 增加实体不为空但所有属性为空的情况
         */
        return isWhere != null ? (isWhere ? sqlWhere : sqlWhere.replaceFirst("WHERE", AND_OR)) : sqlWhere.replaceFirst("WHERE", AND_OR);
    }

}