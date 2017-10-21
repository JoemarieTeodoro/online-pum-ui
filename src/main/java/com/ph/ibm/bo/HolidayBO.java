package com.ph.ibm.bo;

import java.sql.SQLException;
import java.util.List;

import com.ph.ibm.model.Holiday;
import com.ph.ibm.repository.HolidayEngagementRepository;
import com.ph.ibm.repository.impl.HolidayRepositoryImpl;

public class HolidayBO {
	
	private HolidayEngagementRepository holidayEngagementRepository = new HolidayRepositoryImpl();

	/**
	 * return boolean
	 * @throws SQLException 
	 */
	public boolean addHolidayEngagement(Holiday holiday) throws SQLException {
		return holidayEngagementRepository.addHolidayEngagement(holiday);
	}
	
	/**
	 * return boolean
	 * @throws SQLException 
	 */
	public boolean updateHoliday(Holiday holiday) throws SQLException {
		return holidayEngagementRepository.updateHolidayEngagement(holiday);
	}
	
	/**
	 * @param holiday
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean deleteHoliday(Holiday holiday) throws SQLException {
		return holidayEngagementRepository.deleteHoliday(holiday);
	}
	
	/**
	 * @return List of Holiday
	 * @throws SQLException
	 */
	public List<Holiday> getAllHoliday() throws SQLException {
		return holidayEngagementRepository.getAllHoliday();
	}
	
	/**
	 * @param name
	 * @return Holiday Object
	 * @throws SQLException
	 */
	public Holiday checkHoliday(String name) throws SQLException {
		return holidayEngagementRepository.checkHoliday(name);
	}
}
