package com.butshow.service.user;


import java.util.List;

import com.butshow.entity.UserDefault;
import com.butshow.util.page.PageResultSet;

/**
 * 业务层
 * 
 * @author 
 *
 */
public interface UserService {

	/**
	 * 添加
	 * @param UserDefault
	 */
	public void saveUser(UserDefault user);
	
	/**
	 * 修改
	 * @param UserDefault
	 */
	public void updateUser(UserDefault user);
	
	/**
	 * 删除
	 * @param UserDefault
	 */
	public void deleteUser(UserDefault user);
	
	/**
	 * 查询所有数据
	 * @return
	 */
	public List<UserDefault> findAllUserList();
	
	/**
	 * 根据条件查询数据
	 * @param UserDefault
	 * @return
	 */
	public List<UserDefault> findUserListByCondition(UserDefault user);
	
	/**
	 * 通过条件查找用户
	 * @param userModel
	 * @return
	 */
	public UserDefault findUserByByCondition(UserDefault user);
	
	/**
	 * 通过ID查询User
	 * @param id
	 * @return
	 */
	public UserDefault getUserById(int id);
	
	/**
	 * 通过username查询User
	 * @param username
	 * @return
	 */
	public UserDefault getUserByName(String username);
	
	/**
	 * 通过email查询User
	 * @param email
	 * @return
	 */
	public UserDefault getUserByEmail(String email);
	
	/**
	 * 通过phone查询User
	 * @param phone
	 * @return
	 */
	public UserDefault getUserByPhone(String phone);
	
	/**
	 * 通过UUID查询User
	 * @param UUID
	 * @param signupsource
	 * @return
	 */
	public UserDefault getUserByUUID(String UUID,String signupsource);
	
	/**
	 * 查询用户分页列表
	 * @param userModel
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public PageResultSet<UserDefault> findPageUserList(UserDefault user, int page, int pageSize);
}
