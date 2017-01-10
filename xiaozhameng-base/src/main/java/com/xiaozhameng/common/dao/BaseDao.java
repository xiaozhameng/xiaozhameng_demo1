package com.xiaozhameng.common.dao;

import java.util.List;

public interface BaseDao<T> {

	public int add(T t);

	public int update(T t);

	public int updateBySelective(T t);

	public int delete(Object id);

	public int queryByCount(T t);

	public List<T> queryByList(T t);

	public T queryById(Object id);
}
