package com.butshow.service.user.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.butshow.dao.IBaseDao;
import com.butshow.entity.UserDefault;
import com.butshow.service.user.UserService;
import com.butshow.util.Util;
import com.butshow.util.page.Page;
import com.butshow.util.page.PageResultSet;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource
	private IBaseDao<UserDefault> userDao;
	
	@Override
	public void saveUser(UserDefault user) {
		Serializable ser = userDao.save(user);
		System.out.println(ser);
	}

	@Override
	public void updateUser(UserDefault user) {
		userDao.update(user);
	}

	@Override
	public void deleteUser(UserDefault user) {
		userDao.delete(user);
	}

	@Override
	public List<UserDefault> findAllUserList() {

		return userDao.find("from UserDefault u order by u.createTime desc");
	}

	@Override
	public List<UserDefault> findUserListByCondition(UserDefault user) {
		StringBuffer hql = new StringBuffer("from UserDefault u where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if(!Util.isNull(user.getUsername())){
			hql.append(" and u.username = ?");
			paramList.add(user.getUsername());
		}
			
		if(!Util.isNull(user.getLoginpassword())){
			hql.append(" and u.password = ?");
			paramList.add(user.getLoginpassword());
		} 
		if(!Util.isNull(user.getEmail())){
			hql.append(" and u.email = ?");
			paramList.add(user.getEmail());
		} 
		
		return userDao.find(hql.toString(), paramList);
	}

	@Override
	public UserDefault findUserByByCondition(UserDefault user) {
		StringBuffer hql = new StringBuffer("from UserDefault u where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if(!Util.isNull(user.getUsername()) && !Util.isNull(user.getLoginpassword())){
			hql.append(" and ( u.username = ? and u.loginpassword = ?) or ( u.phone = ? and u.loginpassword = ?) or ( u.email = ? and u.loginpassword = ?)");
			paramList.add(user.getUsername());
			paramList.add(user.getLoginpassword());
			paramList.add(user.getUsername());
			paramList.add(user.getLoginpassword());
			paramList.add(user.getUsername());
			paramList.add(user.getLoginpassword());
		}
			
		if (paramList.size() == 0) {
			return null;
		}
		return userDao.get(hql.toString(), paramList.toArray());
	}

	@Override
	public UserDefault getUserById(int id) {
		return userDao.get(UserDefault.class, id);
	}
	
	@Override
	public UserDefault getUserByName(String username) {
		StringBuffer hql = new StringBuffer("from UserDefault u where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if(!Util.isNull(username)){
			hql.append(" and u.username = ?");
			paramList.add(username);
			List<UserDefault> list = userDao.find(hql.toString(), paramList);
			return list.size()==1?list.get(0):null;
		}else return null;
	}
	
	@Override
	public UserDefault getUserByEmail(String email) {
		StringBuffer hql = new StringBuffer("from UserDefault u where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if(!Util.isNull(email)){
			hql.append(" and u.email = ?");
			paramList.add(email);
			List<UserDefault> list = userDao.find(hql.toString(), paramList);
			return list.size()==1?list.get(0):null;
		}else return null;
	}
	
	@Override
	public UserDefault getUserByPhone(String phone) {
		StringBuffer hql = new StringBuffer("from UserDefault u where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if(!Util.isNull(phone)){
			hql.append(" and u.phone = ?");
			paramList.add(phone);
			List<UserDefault> list = userDao.find(hql.toString(), paramList);
			return list.size()==1?list.get(0):null;
		}else return null;
	}
	
	@Override
	public UserDefault getUserByUUID(String UUID,String signupsource) {
		StringBuffer hql = new StringBuffer("from UserDefault u where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if(!Util.isNull(UUID)){
			hql.append(" and u.uuid = ?");
			paramList.add(UUID);
			hql.append(" and u.signupsource = ?");
			paramList.add(signupsource);
			List<UserDefault> list = userDao.find(hql.toString(), paramList);
			return list.size()==1?list.get(0):null;
		}else return null;
	}

	@Override
	public PageResultSet<UserDefault> findPageUserList(UserDefault user, int page,
			int pageSize) {
		
		StringBuffer hql = new StringBuffer("from UserDefault u where 1=1");
		List<Object> paramList = new ArrayList<Object>();
		if(!Util.isNull(user.getUsername())){
			hql.append(" and u.username = ?");
			paramList.add(user.getUsername());
		}
			
		if(!Util.isNull(user.getLoginpassword())){
			hql.append(" and u.password = ?");
			paramList.add(user.getLoginpassword());
		} 
		if(!Util.isNull(user.getEmail())){
			hql.append(" and u.email = ?");
			paramList.add(user.getEmail());
		} 
		
		hql.append(" order by u.createTime desc");
		
		Long totalRow = userDao.count(hql.toString(), paramList); 
		
		Page pages = new Page(totalRow.intValue(), pageSize, page);
		//这里用到了Page类中的获取首页和分页大小的方法
		List<UserDefault> list = userDao.find(hql.toString(), paramList, page, pageSize);

		//把取出来的结果放到list中
		PageResultSet<UserDefault> pageResultSet = new PageResultSet<UserDefault>();

		pageResultSet.setList(list);

		pageResultSet.setPage(pages);

		return pageResultSet;
	}

}
