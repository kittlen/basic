package basedao;

import java.util.List;

public interface iHibernatedao {
	
	/**
	 * 实现对象的持久化
	 * @param object 进行操作的对象
	 * @return 对象的主键 0为添加失败
	 */
	public Object save(Object object);
	
	/**
	 * 获取对应主键的对象
	 * @param cla 对应对象的对象类
	 * @param object 对应主键
	 * @return 对应主键的对象
	 */
	@SuppressWarnings("rawtypes")
	public Object getObj(Class cla,Object object);
	

	/**
	 * 对象的持久化修改
	 * @param object 进行操作的对象
	 * @return 1修改成功 0修改失败
	 */
	public int update(Object object);
	
	/**
	 * 删除特定持久化的对象
	 * @param object 进行操作的对象
	 * @return 1删除成功 0删除失败
	 */
	public int delete(Object object);
	
	/**
	 * 删除对应主键的持久化对象
	 * @param cla 对应对象的对象类
	 * @param object 对应对象的主键
	 * @return 1删除成功 0删除失败
	 */
	@SuppressWarnings("rawtypes")
	public int delete(Class cla,Object object);
	
	/**
	 * HQL语句查询
	 * @param HQL from Object obj
	 * @return 查询结果集 list
	 */
	@SuppressWarnings("rawtypes")
	public List select(String HQL);
	
	/**
	 * HQL语句查询(带占位符?)
	 * @param HQL from Object obj where obj.属性=?
	 * @param objects 对应占位符所对应的参数
	 * @return 查询结果集 list
	 */
	@SuppressWarnings("rawtypes")
	public List select(String HQL,Object[] parmas);
	
	/**
	 * 聚合函数查询
	 * @param HQL
	 * @return 聚合结果
	 */
	public Object seleceValue(String HQL);
	
	/**
	 * 带参数的聚合函数查询
	 * @param HQL from Object obj where obj.属性=?
	 * @param parmas 参数集[]
	 * @return 聚合结果
	 */
	public Object seleceValue(String HQL,Object[] parmas);
	
	/**
	 * 总页数
	 * @param HQL from Object obj 
	 * @param perCount 每页对象最大个数
	 * @return 总页数
	 */
	public Long getPageCount(String HQL,int perCount);
	
	/**
	 * 带参数的总页数
	 * @param HQL from Object obj where obj.属性=?
	 * @param parmas 参数集[]
	 * @param perCount 每页对象最大个数
	 * @return 总页数
	 */
	public Long getPageCount(String HQL,Object[] parmas,int perCount);
	
	/**
	 * 分页
	 * @param HQL from Object obj
	 * @param perCount 每页对象最大个数
	 * @param index 当前页 1开始
	 * @return 分页结果list
	 */
	@SuppressWarnings("rawtypes")
	public List selectPage(String HQL,int perCount,int index);
	
	/**
	 * 带参数分页
	 * @param HQL from Object obj where obj.属性=?
	 * @param parmas 参数集[]
	 * @param perCount 每页对象最大个数
	 * @param index 当前页 1开始
	 * @return 分页结果list
	 */
	@SuppressWarnings("rawtypes")
	public List selectPage(String HQL,Object[] parmas,int perCount,int index);
	
}
