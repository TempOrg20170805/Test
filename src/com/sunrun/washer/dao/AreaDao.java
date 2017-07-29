package com.sunrun.washer.dao;

import java.util.List;

import com.jeecms.common.hibernate4.Updater;
import com.jeecms.common.page.Pagination;
import com.sunrun.washer.entity.Area;

public interface AreaDao {
	/**
	 * 地区列表页
	 * @param area 用户输入地区名称
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getPage(String area,Integer parentId,Integer pageNo,Integer pageSize);
	
	/**
	 * 按照上一级地区进行查询，查询结果为上一级地区下的所有地区信息
	 * @param parentId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination getPage(Integer parentId,Integer pageNo,Integer pageSize);
	
	public List<Area>ctiyList(Integer parentId);
	
	/**
	 * 保存地区信息
	 * @param parentId
	 * @param areaName
	 * @param areaSort
	 * @param areaDeep
	 * @param isDefault
	 * @return
	 */
	public Area save(Area bean);
	
	/**
	 * 更新地区信息
	 * @param updater
	 * @return
	 */
	public Area updateByUpdater(Updater<Area> updater);

	/**
	 * 删除地区信息
	 * @param id
	 * @return
	 */
	public Area delete(Integer id);
	
	/**
	 * 按照地区ID进行查找地区信息
	 * @param id
	 * @return
	 */
	public Area findById(Integer id);
	
	/**
	 * 按照上一级地区ID，查询出一条地区结果的list
	 * @param pareneId
	 * @return
	 */
	public List<Area> findByPareantId(Integer pareneId);
	
	
	
	public List<Area> findByAreaName(String areaName);
	
	public List<Area> areaList();
	/**
	 * 查询区域列表：以parentId为父类别
	 * @param parentId 父类别id
	 * @return
	 */
	public List<Area> queryListByRoot(Integer parentId);
}
