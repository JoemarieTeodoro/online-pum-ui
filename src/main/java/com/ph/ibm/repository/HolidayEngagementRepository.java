package com.ph.ibm.repository;

import java.sql.SQLException;
import java.util.List;

import com.ph.ibm.model.Holiday;

public interface HolidayEngagementRepository {

	/**
	 * 
	 * @param holiday
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean addHolidayEngagement(Holiday holiday) throws SQLException;

	/**
	 * 
	 * @param holiday
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean updateHolidayEngagement(Holiday holiday) throws SQLException;

	/**
	 * 
	 * @return List
	 * @throws SQLException
	 */
	public List<Holiday> getAllHoliday() throws SQLException;

	/**
	 * 
	 * @param name
	 * @return Holiday object
	 * @throws SQLException
	 */
	public Holiday checkHoliday(String name) throws SQLException;
	
	/**
	 * 
	 * @param holiday
	 * @return
	 * @throws SQLException
	 */
	public boolean deleteHoliday(Holiday holiday) throws SQLException;

}
