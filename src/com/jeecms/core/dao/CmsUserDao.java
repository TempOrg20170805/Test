package com.jeecms.core.dao;

import java.util.List;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;

/**
 * 用户DAO接口
 */
public interface CmsUserDao{
	public Pagination getPage(Integer realnameStatus,Integer provinceId,Integer cityId,String username, String email, Integer siteId,
			Integer groupId, Boolean disabled, Boolean admin, Integer rank,
			String realName,Integer roleId,
			Boolean allChannel,
			int pageNo, int pageSize);
	
	public List<CmsUser> getList(String username, String email, Integer siteId,
			Integer groupId, Boolean disabled, Boolean admin, Integer rank);

	public List<CmsUser> getAdminList(Integer siteId, Boolean allChannel,
			Boolean disabled, Integer rank);
	
	public Pagination getAdminsByRoleId(Integer roleId, int pageNo, int pageSize);
	
	public Pagination getMembershipUser(String userName,String nickName,String mobile, Integer membership,int pageNo, int pageSize);

	public CmsUser findById(Integer id);
	
	public CmsUser findByIdNumber(String idNum);
	
	public CmsUser findByUsername(String username);

	public int countByUsername(String username);
	
	public int countMemberByUsername(String username);

	public int countByEmail(String email);

	public CmsUser save(CmsUser bean);

	public CmsUser updateByUpdater(Updater<CmsUser> updater);

	public CmsUser deleteById(Integer id);
}